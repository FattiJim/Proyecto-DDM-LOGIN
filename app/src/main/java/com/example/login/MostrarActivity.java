package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MostrarActivity extends AppCompatActivity {

    ArrayList<usuarioItem> listDatos;
    conexionSQLHelper conn;
    SQLiteDatabase db;
    RecyclerView recyclerView;
    Button btnModificarAceptar;
    ConstraintLayout exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);
        recyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        btnModificarAceptar = (Button) findViewById(R.id.btnModificarAceptar);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        listDatos = new ArrayList<usuarioItem>();
        mostrar();
        AdaptadorDatos adapter = new AdaptadorDatos(listDatos);
        recyclerView.setAdapter(adapter);

        exit = (ConstraintLayout) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnModificarAceptar.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });


    }

    public void mostrar(){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        try {
            String sql = "select id, nom, mail, pass, imagen from usuario";
            Cursor cursor = db.rawQuery(sql,null);

            while(cursor.moveToNext()){
                usuarioItem u = new usuarioItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                listDatos.add(u);

                System.out.println("id: " + cursor.getString(0));
                System.out.println("Nombre: " + cursor.getString(1));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
    }
}