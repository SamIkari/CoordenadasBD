package com.example.ubicaciongeografica

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputLatitude = findViewById<EditText>(R.id.inputLatitude)
        val inputLongitude = findViewById<EditText>(R.id.inputLongitude)
        val inputLocationName = findViewById<EditText>(R.id.inputLocationName)
        val buttonBuscar = findViewById<Button>(R.id.buttonBuscar)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)
        val mapImageView = findViewById<ImageView>(R.id.mapImageView)

        buttonBuscar.setOnClickListener {
            val latitude = inputLatitude.text.toString()
            val longitude = inputLongitude.text.toString()

            if (latitude.isNotEmpty() && longitude.isNotEmpty()) {
                val url = buildMapboxUrl(latitude, longitude)
                loadMapImage(url, mapImageView)
            } else {
                Toast.makeText(this, "Por favor ingresa la latitud y la longitud", Toast.LENGTH_SHORT).show()
            }
        }

        buttonGuardar.setOnClickListener {
            val latitude = inputLatitude.text.toString()
            val longitude = inputLongitude.text.toString()
            val locationName = inputLocationName.text.toString()

            if (latitude.isNotEmpty() && longitude.isNotEmpty() && locationName.isNotEmpty()) {
                saveLocationToDatabase(latitude, longitude, locationName)
            } else {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buildMapboxUrl(latitude: String, longitude: String): String {
        return "https://api.mapbox.com/styles/v1/mapbox/satellite-streets-v12/static/geojson(%7B%22type%22%3A%22FeatureCollection%22%2C%22features%22%3A%5B%7B%22type%22%3A%22Feature%22%2C%22geometry%22%3A%7B%22type%22%3A%22Point%22%2C%22coordinates%22%3A%5B$longitude%2C$latitude%5D%7D%2C%22properties%22%3A%7B%22marker-color%22%3A%22%23FF0000%22%7D%7D%5D%7D)/$longitude,$latitude,14/600x400?access_token=pk.eyJ1IjoiamFuZG8tODUiLCJhIjoiY2trZGt2NWNjMDQ4NDJvczBpamQ4cXc5eSJ9.U2xM76b9foKMmzzzA6VZEg"
    }

    private fun loadMapImage(url: String, imageView: ImageView) {
        Glide.with(this)
            .load(url)
            .into(imageView)
    }

    private fun saveLocationToDatabase(latitude: String, longitude: String, locationName: String) {
        try {
            // Convertir latitud y longitud a Double (números decimales)
            val latitudeDouble = latitude.toDouble()
            val longitudeDouble = longitude.toDouble()

            // URL con los parámetros en la query
            val url = "http://192.168.1.73/guardar_ubicacion.php?latitude=$latitudeDouble&longitude=$longitudeDouble&locationName=$locationName"

            // Crear la solicitud GET
            val request = Request.Builder()
                .url(url)
                .build()

            // Enviar la solicitud
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        Toast.makeText(this@MainActivity, "Error al guardar la ubicación: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    runOnUiThread {
                        val responseBody = response.body?.string()
                        if (response.isSuccessful) {
                            Toast.makeText(this@MainActivity, "Ubicación guardada: $responseBody", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@MainActivity, "Error en la respuesta del servidor: $responseBody", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } catch (e: NumberFormatException) {
            runOnUiThread {
                Toast.makeText(this@MainActivity, "Latitud o Longitud no válidos", Toast.LENGTH_LONG).show()
            }
        }
    }




}
