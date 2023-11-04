package com.example.login;

import androidx.activity.result.ActivityResult;

public class usuarioItem {
    public String id;
    public String nombre;
    public String correo;
    public String pass;
    public String imagen;


    public usuarioItem(String id, String nombre, String correo, String pass, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.pass = pass;
        this.imagen = imagen;
    }



}
