PGDMP     3        
        
    |            ubicaciones    15.2    15.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    91411    ubicaciones    DATABASE        CREATE DATABASE ubicaciones WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Mexico.1252';
    DROP DATABASE ubicaciones;
                postgres    false            �            1259    91413    ubicaciones    TABLE     �   CREATE TABLE public.ubicaciones (
    id integer NOT NULL,
    nombre character varying(100),
    latitud numeric(9,6),
    longitud numeric(9,6)
);
    DROP TABLE public.ubicaciones;
       public         heap    postgres    false            �            1259    91412    ubicaciones_id_seq    SEQUENCE     �   CREATE SEQUENCE public.ubicaciones_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.ubicaciones_id_seq;
       public          postgres    false    215            �           0    0    ubicaciones_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.ubicaciones_id_seq OWNED BY public.ubicaciones.id;
          public          postgres    false    214            e           2604    91416    ubicaciones id    DEFAULT     p   ALTER TABLE ONLY public.ubicaciones ALTER COLUMN id SET DEFAULT nextval('public.ubicaciones_id_seq'::regclass);
 =   ALTER TABLE public.ubicaciones ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    215    215            �          0    91413    ubicaciones 
   TABLE DATA           D   COPY public.ubicaciones (id, nombre, latitud, longitud) FROM stdin;
    public          postgres    false    215   �
       �           0    0    ubicaciones_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.ubicaciones_id_seq', 23, true);
          public          postgres    false    214            g           2606    91418    ubicaciones ubicaciones_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.ubicaciones
    ADD CONSTRAINT ubicaciones_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.ubicaciones DROP CONSTRAINT ubicaciones_pkey;
       public            postgres    false    215            �   5   x�34�L�45�3 N#c(�����.	KF�$qI�0�!���� fI#i     