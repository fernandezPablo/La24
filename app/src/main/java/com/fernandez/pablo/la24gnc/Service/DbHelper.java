package com.fernandez.pablo.la24gnc.Service;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pablo on 20/04/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TABLE_TURNO = "";
    private static final String TABLE_VENTA = "";
    private static final String TABLE_LVENTA = "";
    private static final String TABLE_ESPECIFICACIONPROD = "CREATE TABLE especificacion_producto" +
            "(codigo INT NOT NULL, rubro INT NOT NULL, descripcion VARCHAR(50),precio DECIMAL, PRIMARY KEY(codigo))";
    private static final String TABLE_GNC = "";
    private static final String TABLE_ACEITE= "";
    private static final String TABLE_DESCUENTO= "";
    private static final String TABLE_AFORADOR= "CREATE TABLE afor" +
            "            \"(codigo INT NOT NULL, rubro INT NOT NULL, descripcion VARCHAR(50),precio DECIMAL, PRIMARY KEY(codigo))";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
