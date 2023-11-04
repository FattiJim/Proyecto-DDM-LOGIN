package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    ConstraintLayout btnAgregar;
    ConstraintLayout btnBack;
    ConstraintLayout btnModificar;
    ConstraintLayout btnEliminar;
    ConstraintLayout btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnAgregar = (ConstraintLayout) findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickAgregar(view);
            }
        });

        btnBack =(ConstraintLayout) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnModificar = (ConstraintLayout) findViewById(R.id.btnModificar);
        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickModificar(view);
            }
        });

        btnEliminar = (ConstraintLayout) findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickEliminar(view);
            }
        });

        btnMostrar = (ConstraintLayout) findViewById(R.id.btnMostrar);
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {onclickMostrar(view);}
        });

    }

    public void onclickAgregar(View v)
    {
        Intent  i = new Intent(this, AgregarActivity.class);
        startActivity(i);
    }

    public void onclickModificar(View v)
    {
        Intent  i = new Intent(this, ModificarActivity.class);
        startActivity(i);
    }

    public void onclickEliminar(View v)
    {
        Intent  i = new Intent(this, EliminarActivity.class);
        startActivity(i);
    }

    public void onclickMostrar(View v)
    {
        Intent  i = new Intent(this, MostrarActivity.class);
        startActivity(i);
    }


}