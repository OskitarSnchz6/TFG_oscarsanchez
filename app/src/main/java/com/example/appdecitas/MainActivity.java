package com.example.appdecitas;

import android.app.Dialog;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appdecitas.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    UsuarioDAO usuarioDAO;
    Adaptador adaptador;
    ArrayList<Usuario> lista;
    Usuario user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pantalla principal de registro/inicio sesion
        usuarioDAO = new UsuarioDAO(MainActivity.this);
        lista = usuarioDAO.verTodos();
        adaptador = new Adaptador(this, lista, usuarioDAO);
        ListView list = (ListView) findViewById(R.id.lista);
        Button agregar = (Button) findViewById(R.id.btn_registro);

        if (lista != null && lista.size() > 0) {
            list.setAdapter(adaptador);
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Dialog para ver la vista previa de registro vista.xml
            }
        });

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Dialog de agregar dialog.xml
                final Dialog dialogo = new Dialog(MainActivity.this);
                dialogo.setTitle("Nuevo Usuario");
                dialogo.setCancelable(true);
                dialogo.setContentView(R.layout.dialogo);
                dialogo.show();

                EditText nombre = (EditText) dialogo.findViewById(R.id.txt_nombre);
                EditText apellido = (EditText) dialogo.findViewById(R.id.txt_apellido);
                EditText edad = (EditText) dialogo.findViewById(R.id.int_edad);
                EditText sexo = (EditText) dialogo.findViewById(R.id.txt_sexo);
                EditText altura = (EditText) dialogo.findViewById(R.id.int_altura);
                EditText telefono = (EditText) dialogo.findViewById(R.id.int_telefono);
                EditText sexo_buscado = (EditText) dialogo.findViewById(R.id.txt_sexo_buscado);
                EditText descripcion = (EditText) dialogo.findViewById(R.id.txt_descripcion);

                Button guardar = (Button) dialogo.findViewById(R.id.btn_agregar_user);
                guardar.setText("Usuario agregado");
                Button cancelar = (Button) dialogo.findViewById(R.id.btn_cancelar_user);

                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            user = new Usuario(nombre.getText().toString(), apellido.getText().toString(), Integer.parseInt(edad.getText().toString()), sexo.getText().toString(),
                                    Integer.parseInt(altura.getText().toString()), Integer.parseInt(telefono.getText().toString()), sexo_buscado.getText().toString(),
                                            descripcion.getText().toString());
                            usuarioDAO.insertar(user);
                            lista = usuarioDAO.verTodos();
                            adaptador.notifyDataSetChanged();
                            dialogo.dismiss();
                        } catch (Exception e) {
                            Toast.makeText(getApplication(), "ERROR", Toast.LENGTH_LONG).show();
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
    }
}