package com.example.appdecitas;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {
    ArrayList<Usuario> lista;
    UsuarioDAO usuarioDAO;
    Usuario user;
    Activity a;
    int id = 0;

    public Adaptador(Activity a, ArrayList<Usuario> lista, UsuarioDAO usuarioDAO) {
        this.lista = lista;
        this.a = a;
        this.usuarioDAO = usuarioDAO;
    }

    public int getId(){
        return id;
    }

    public void setId(int id)   {
        this.id = id;
    }

    @Override
    public int getCount()   {
        return lista.size();
    }

    @Override
    public Usuario getItem(int i)    {
        user = lista.get(i);
        return user;
    }

    @Override
    public long getItemId(int i)    {
        user = lista.get(i);
        return user.getId();
    }

    @Override
    public View getView (int posicion, View view, ViewGroup viewGroup)  {
        View v = view;
        if(v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.activity_main, null);
        }
        user = lista.get(posicion);
        TextView nombre = (TextView) v.findViewById(R.id.txt_nombre);
        TextView apellido = (TextView) v.findViewById(R.id.txt_apellido);
        TextView edad = (TextView) v.findViewById(R.id.int_edad);
        TextView sexo = (TextView) v.findViewById(R.id.txt_sexo);
        TextView altura = (TextView) v.findViewById(R.id.int_altura);
        TextView telefono = (TextView) v.findViewById(R.id.int_telefono);
        TextView sexo_buscado = (TextView) v.findViewById(R.id.txt_sexo_buscado);
        TextView descripcion = (TextView) v.findViewById(R.id.txt_descripcion);

        final Button editar = (Button) v.findViewById(R.id.btn_agregar_user);
        Button eliminar = (Button) v.findViewById(R.id.btn_cancelar_user);
        nombre.setText(user.getNombre());
        apellido.setText(user.getApellido());
        edad.setText(user.getEdad());
        sexo.setText(user.getSexo());
        altura.setText(user.getAltura());
        telefono.setText(user.getTelefono());
        sexo_buscado.setText(user.getSexo_buscado());
        descripcion.setText(""+user.getDescripcion());
        editar.setTag(posicion);
        eliminar.setTag(posicion);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog de editar dialogo.xml

                int pos = Integer.parseInt(view.getTag().toString());
                final Dialog dialogo = new Dialog(a);
                dialogo.setTitle("Editar registro");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                final EditText nombre = (EditText) dialogo.findViewById(R.id.txt_nombre);
                final EditText apellido = (EditText) dialogo.findViewById(R.id.txt_apellido);
                final EditText edad = (EditText) dialogo.findViewById(R.id.int_edad);
                final EditText sexo = (EditText) dialogo.findViewById(R.id.txt_sexo);
                final EditText altura = (EditText) dialogo.findViewById(R.id.int_altura);
                final EditText telefono = (EditText) dialogo.findViewById(R.id.int_telefono);
                final EditText sexo_buscado = (EditText) dialogo.findViewById(R.id.txt_sexo_buscado);
                final EditText descripcion = (EditText) dialogo.findViewById(R.id.txt_descripcion);

                Button guardar = (Button) dialogo.findViewById(R.id.btn_agregar_user);
                guardar.setText("Guardar");
                Button cancelar = (Button) dialogo.findViewById(R.id.btn_cancelar_user);

                user = lista.get(pos);
                setId(user.getId());
                nombre.setText(user.getNombre());
                apellido.setText(user.getApellido());
                edad.setText(user.getEdad());
                sexo.setText(user.getSexo());
                altura.setText(user.getAltura());
                telefono.setText(user.getTelefono());
                sexo_buscado.setText(user.getSexo_buscado());
                descripcion.setText(""+user.getDescripcion());

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    user = new Usuario(getId(), nombre.getText().toString(),
                            apellido.getText().toString(),
                            Integer.parseInt(edad.getText().toString()),
                            sexo.getText().toString(),
                            Integer.parseInt(altura.getText().toString()),
                            Integer.parseInt(telefono.getText().toString()),
                            sexo_buscado.getText().toString(),
                            descripcion.getText().toString());

                    usuarioDAO.editar(user);
                    lista = usuarioDAO.verTodos();
                    notifyDataSetChanged();
                    dialogo.dismiss();

                } catch (Exception e)   {
                    Toast.makeText(a, "ERROR", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.dismiss();
            }
        });
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog para confirmar si o no
                int pos = Integer.parseInt(view.getTag().toString());
                user = lista.get(pos);
                setId(getId());
                AlertDialog.Builder del = new AlertDialog.Builder(a);
                del.setMessage("¿Seguro que quieres eliminar este usuario?");
                del.setCancelable(false);
                del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        usuarioDAO.eliminar(user.getId());
                        lista = usuarioDAO.verTodos();
                        notifyDataSetChanged();
                    }
                });

                del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                del.show();
            }
        });
        return view;
    }
}