package com.example.appdecitas;

public class Usuario {
    int id;
    String nombre;
    String apellido;
    int edad;
    String sexo;
    int altura;
    int telefono;
    String sexo_buscado;
    String descripcion;

    public Usuario(int id, String nombre, String apellido, int edad, String sexo, int altura, String sexo_buscado, String descripcion) {

    }

    public Usuario(String nombre, String apellido, int edad, String sexo, int altura, int telefono, String sexo_buscado, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.altura = altura;
        this.telefono = telefono;
        this.sexo_buscado = sexo_buscado;
        this.descripcion = descripcion;
    }

    public Usuario(int id, String nombre, String apellido, int edad, String sexo, int altura, int telefono, String sexo_buscado, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.altura = altura;
        this.telefono = telefono;
        this.sexo_buscado = sexo_buscado;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getSexo_buscado() {
        return sexo_buscado;
    }

    public void setSexo_buscado(String sexo_buscado) {
        this.sexo_buscado = sexo_buscado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}