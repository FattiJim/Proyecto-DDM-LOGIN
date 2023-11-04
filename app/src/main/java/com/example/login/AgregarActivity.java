package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class AgregarActivity extends AppCompatActivity {
    conexionSQLHelper conn;
    SQLiteDatabase db;
    EditText editTextText4;
    EditText editTextTextEmailAddress;
    Spinner spinner;
    EditText editTextTextPassword2;
    EditText editTextTextPassword3;
    ConstraintLayout btnBack;
    Button buttonCrear;
    String selector;
    Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        editTextText4 = (EditText) findViewById(R.id.editTextText4);
        editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        spinner = (Spinner) findViewById(R.id.spinner);
        editTextTextPassword2 = (EditText) findViewById(R.id.editTextTextPassword2);
        editTextTextPassword3 = (EditText) findViewById(R.id.editTextTextPassword3);

        mostrar();
        buttonCrear = (Button) findViewById(R.id.button2);
        buttonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnOnClickAceptar(view);
            }
        });

        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                finish();
            }
        });

        ArrayList<String> obj = new ArrayList<>();
        obj.add("1");
        obj.add("2");
        obj.add("3");
        obj.add("4");
        obj.add("5");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, obj);
        spinner.setAdapter(adapter);

        btnBack =(ConstraintLayout) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selector = adapterView.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void btnOnClickAceptar(View v){
        String error = "";
        if (editTextText4.getText().toString().equals("")){
            error += "Falta el nombre\n";
        }
        if(editTextTextEmailAddress.getText().toString().equals("")){
            error += "Falta el correo\n";
        }
        if(editTextTextPassword2.getText().toString().equals("")){
            error += "Falta la contraeña\n";
        }
        if(!editTextTextPassword2.getText().toString().equals(editTextTextPassword3.getText().toString())){
            error += "El password no es correcto\n";
        }
        if (error.equals("")){
           crearUsuario(getNext(), editTextText4.getText().toString(), editTextTextEmailAddress.getText().toString(), selector, editTextTextPassword2.getText().toString());
           mostrar();
        }
        else {
            alerta("Error", error);

        }
    }

    public void mostrar(){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        try {
            String sql = "select id, nom, mail, imagen, pass from usuario";
            Cursor cursor = db.rawQuery(sql,null);

            while(cursor.moveToNext()){
                System.out.println("id: " + cursor.getString(0));
                System.out.println("Nombre: " + cursor.getString(1));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
    }

    /*public void mostrar(){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        try {
            String sql = "select id, nom, mail, imagen, pass from usuario";
            Cursor cursor = db.rawQuery(sql,null);

            while(cursor.moveToNext()){
                System.out.println("id: " + cursor.getString(0));
                System.out.println("Nombre: " + cursor.getString(1));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
    }*/


    public String getNext(){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        String id = "";
        try {
            String sql = "select max(id) from usuario";
            Cursor cursor = db.rawQuery(sql,null);

            while(cursor.moveToNext()){
                System.out.println("id: " + cursor.getString(0));
                id = cursor.getString(0);
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
        return String.valueOf(Integer.parseInt(id)+1);
    }

    /*public String getNext(){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        int total = 0;

        try {
            String sql = "select max(id) id from usuario";
            Cursor cursor = db.rawQuery(sql,null);

            while(cursor.moveToNext()){
                System.out.println("id: " + cursor.getString(0));
                //System.out.println("Nombre: " + cursor.getString(1));
                total = cursor.getInt(0);
            }
            total ++;
            return total +"";

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
        return "0";
    }*/

    public void crearUsuario(String id, String nom, String mail, String imagen, String pass){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        try {
            String sql = "insert into usuario values ("+id+", '"+nom+"', '"+mail+"', '"+imagen+"', '"+pass+"')";
            db.execSQL(sql);
            limpiarDatos();
            alerta("Exito", "Usuario creado correctamente");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
    }

    /*public void crearUsuario(String id, String nombre,String correo, String foto, String pass){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();

        String sql = "insert into usuario values (?, ?, ?, ?, ?)";
        String [] params = {id,nombre , correo, foto, pass};
        db.execSQL(sql, params);
    }*/

   public  void alerta(String titulo, String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titulo);
        builder.setMessage(mensaje);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //finish();
            }
        });
        builder.show();
   }

   public void limpiarDatos(){
        editTextText4.setText("");
        editTextTextEmailAddress.setText("");
        editTextTextPassword2.setText("");
        editTextTextPassword3.setText("");
   }

}