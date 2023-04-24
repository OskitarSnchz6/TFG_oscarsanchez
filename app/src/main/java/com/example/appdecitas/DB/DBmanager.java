package com.example.appdecitas.DB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
public class DBmanager {

    //TABLA USUARIO
    public static final String TABLA_USUARIO = "usuario";
    public static final String ID_USUARIO = "id";
    public static final String NOMBRE_USUARIO = "nombre";
    public static final String APELLIDO_USUARIO = "apellido";
    public static final String EDAD_USUARIO = "edad";
    public static final String SEXO_USUARIO = "sexo";
    public static final String ALTURA_USUARIO = "altura";
    public static final String TELEFONO_USUARIO = "telefono";
    public static final String SEXO_BUSCADO_USUARIO = "sexo buscado";
    public static final String DESCRIPCION_USUARIO = "descripcion";

    public static final String TABLA_USUARIO_CREATE = "create table usuario(id integer not null auto_increment, nombre text not null, apellido text not null, edad text not null, sexo text not null," +
            " altura text not null, telefono text not null, sexo_buscado text not null, descripcion text not null)";

    private DBconexion conexion;
    private SQLiteDatabase basededatos;

    public DBmanager (Context context) {
        conexion = new DBconexion(context);
    }

    public DBmanager open() throws SQLException {
        basededatos = conexion.getWritableDatabase();
        return this;
    }

    public void close() {

        conexion.close();
    }
}