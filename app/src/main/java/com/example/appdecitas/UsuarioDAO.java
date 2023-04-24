package com.example.appdecitas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsuarioDAO {
    SQLiteDatabase sqlite;
    ArrayList<Usuario> lista = new ArrayList<Usuario>();
    Usuario user;
    Context ct;
    String nombreBD = "APPdecitas";
    String table = "create table if not exists usuario(id integer primary key autoincrement, nombre text, apellido text, edad integer, sexo text, altura integer, telefono integer, sexo_buscado text, descripcion text)";

    public UsuarioDAO(Context user){
        this.ct = user;
        sqlite = user.openOrCreateDatabase(nombreBD, user.MODE_PRIVATE, null);
        sqlite.execSQL(table);
    }

    public boolean insertar(Usuario user){
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", user.getNombre());
        contenedor.put("apellido", user.getApellido());
        contenedor.put("edad", user.getEdad());
        contenedor.put("sexo", user.getSexo());
        contenedor.put("altura", user.getAltura());
        contenedor.put("telefono", user.getTelefono());
        contenedor.put("sexo buscado", user.getSexo_buscado());
        contenedor.put("descripcion", user.getDescripcion());

        return (sqlite.insert("usuario", null, contenedor))>0;
    }

    public boolean eliminar(int id){
        return (sqlite.delete("usuario", "id="+id, null))>0;
    }

    public boolean editar (Usuario user)    {
        ContentValues contenedor = new ContentValues();
        contenedor.put("nombre", user.getNombre());
        contenedor.put("apellido", user.getApellido());
        contenedor.put("edad", user.getEdad());
        contenedor.put("sexo", user.getSexo());
        contenedor.put("altura", user.getAltura());
        contenedor.put("telefono", user.getTelefono());
        contenedor.put("sexo buscado", user.getSexo_buscado());
        contenedor.put("descripcion", user.getDescripcion());

        return (sqlite.update("usuario", contenedor, "id="+user.getId(), null))>0;
    }

    public ArrayList<Usuario> verTodos()    {
        lista.clear();
        Cursor cursor = sqlite.rawQuery("select * from usuario", null);
        if(cursor !=null && cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                lista.add(new Usuario(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        cursor.getString(6),
                        cursor.getString(7)
                ));
            }while (cursor.moveToNext());
        }

        return lista;
    }

    public Usuario verUno(int posicion) {
        Cursor cursor = sqlite.rawQuery("select * from usuario", null);
        cursor.moveToPosition(posicion);
        user = new Usuario(cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getString(6),
                cursor.getString(7)
        );
        return user;
    }
}