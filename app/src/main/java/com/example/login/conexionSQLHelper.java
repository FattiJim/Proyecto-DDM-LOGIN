package com.example.login;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class conexionSQLHelper extends SQLiteOpenHelper {
    String tabla = "create table usuario(id int primary key not null, nom text, mail text, imagen text, pass text)";

    public conexionSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla);
        sqLiteDatabase.execSQL("insert into usuario values (1, 'root', 'root@gmail.com', 1, 'toor')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists usuario");
        onCreate(sqLiteDatabase);
    }
}
