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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class EliminarActivity extends AppCompatActivity {

    Button button4;
    conexionSQLHelper conn;
    SQLiteDatabase db;
    EditText txtNombreModificar;
    EditText txtIDNombreModificar;
    EditText txtCorreoModificar;
    Spinner spinnerModificar;
    EditText txtPassModificar2;
    EditText txtPassModificar;
    Button btnModificarAceptar;
    ConstraintLayout exit;
    Button btnModificarRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        button4 = (Button) findViewById(R.id.button4 );
        txtNombreModificar = (EditText) findViewById(R.id.txtNombreModificar);
        txtIDNombreModificar = (EditText) findViewById(R.id.txtIDNombreModificar);
        txtCorreoModificar = (EditText) findViewById(R.id.txtCorreoModificar);
        spinnerModificar = (Spinner) findViewById(R.id.spinnerModificar);
        txtPassModificar2 = (EditText) findViewById(R.id.txtPassModificar2);
        txtPassModificar = (EditText) findViewById(R.id.txtPassModificar);
        //botones
        btnModificarAceptar = (Button) findViewById(R.id.btnModificarAceptar);
        btnModificarRegresar = (Button) findViewById(R.id.btnModificarRegresar);
        exit = (ConstraintLayout) findViewById(R.id.exit);

        ArrayList<String> obj = new ArrayList<>();
        obj.add("1");
        obj.add("2");
        obj.add("3");
        obj.add("4");
        obj.add("5");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, obj);
        spinnerModificar.setAdapter(adapter);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickFund(view);
            }
        });

        btnModificarRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EliminarActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnModificarAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickbtnEliminar(view);
            }
        });
    }

    public void clickFund(View v){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        try {
            String ID = txtNombreModificar.getText().toString();
            String [] parametros = {ID};
            String sql = "select id, nom, mail, imagen, pass from usuario where id = ?";
            Cursor cursor = db.rawQuery(sql, parametros);

            while(cursor.moveToNext()){

                System.out.println("id: " + cursor.getString(0));
                System.out.println("Nombre: " + cursor.getString(1));

                txtIDNombreModificar.setText(cursor.getString(1));
                txtCorreoModificar.setText(cursor.getString(2));

                int selectedPosition = Integer.parseInt(cursor.getString(3)) - 1; // Resta 1 para ajustar el índice (si los valores en la base de datos comienzan en 1)
                spinnerModificar.setSelection(selectedPosition);
                txtPassModificar2.setText(cursor.getString(4));
                txtPassModificar.setText(cursor.getString(4));

            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
    }
    public void clickbtnEliminar(View v){
        String id = txtNombreModificar.getText().toString();
        String nom = txtIDNombreModificar.getText().toString();
        String mail = txtCorreoModificar.getText().toString();
        String pass = txtPassModificar2.getText().toString();
        String pass2 = txtPassModificar.getText().toString();

        String error = "";
        if (id.equals("")){
            error += "El id no puede estar vacio\n";
        }
        if (nom.equals("")){
            error += "El nombre no puede estar vacio\n";
        }
        if (mail.equals("")){
            error += "El correo no puede estar vacio\n";
        }
        if (pass.equals("")){
            error += "La contraseña no puede estar vacia\n";
        }
        if (pass2.equals("")){
            error += "La contraseña no puede estar vacia\n";
        }
        if (!pass.equals(pass2)){
            error += "Las contraseñas no coinciden\n";
        }

        if (error.equals("")) {
            eliminarUsuario(id);
            limpiarDatos();

        }
        else {
            alerta("Error", error);
        }

    }

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

    public  void eliminarUsuario(String id){
        conn = new conexionSQLHelper(this, "buap", null, 1);
        db = conn.getReadableDatabase();
        try {
            String sql = "delete from usuario where id = " + id;
            db.execSQL(sql);
            alerta("Exito", "Se elimino correctamente");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        conn.close();
        db.close();
    }

    public void  limpiarDatos(){
        txtNombreModificar.setText("");
        txtIDNombreModificar.setText("");
        txtCorreoModificar.setText("");
        txtPassModificar2.setText("");
        txtPassModificar.setText("");
    }
}