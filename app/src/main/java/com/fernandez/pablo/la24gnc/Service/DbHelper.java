package com.fernandez.pablo.la24gnc.Service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pablo on 20/04/2017.
 */

public class DbHelper extends SQLiteOpenHelper {


    /**
     * CONSTANTES PARA LA CREACION DE LAS TABLAS EN LA BASE DE DATOS
     * */
    private static final String TABLE_TURNO = "CREATE TABLE Turno(codigo INTEGER NOT NULL PRIMARY " +
            "KEY AUTOINCREMENT, nro INTEGER, pmz DECIMAL, fecha DATE, estado VARCHAR(10), " +
            "codigo_venta INTEGER, FOREIGN KEY(codigo_venta) REFERENCES Venta(codigo))";
    private static final String TABLE_VENTA = "CREATE TABLE Venta(codigo INTEGER NOT NULL PRIMARY " +
            "KEY AUTOINCREMENT, total DECIMAL)";
    private static final String TABLE_LVENTA = "CREATE TABLE Linea_Venta(codigo INTEGER NOT NULL " +
            "PRIMARY KEY AUTOINCREMENT, cantidad DECIMAL, codigo_producto INTEGER, FOREIGN KEY" +
            "(codigo_producto) REFERENCES especificacion_producto(codigo))";
    private static final String TABLE_ESPECIFICACIONPROD = "CREATE TABLE especificacion_producto" +
            "(codigo INTEGER NOT NULL PRIMARY KEY, rubro INTEGER NOT NULL, descripcion VARCHAR(50)," +
            "precio DECIMAL)";
    private static final String TABLE_DESCUENTO= "CREATE TABLE Descuento(codigo INTEGER NOT NULL " +
            "PRIMARY KEY AUTOINCREMENT, descripcion VARCHAR(50), monto DECIMAL, codigo_venta INTEGER," +
            " FOREIGN KEY(codigo_venta) REFERENCES Venta(codigo))";
    private static final String TABLE_AFORADOR= "CREATE TABLE Aforador(codigo INTEGER NOT NULL " +
            "PRIMARY KEY AUTOINCREMENT, numero INT NOT NULL, unidad VARCHAR(3), valor_inicial " +
            "DECIMAL, valor_final DECIMAL, tipo VARCHAR(10),codigo_turno INTEGER, " +
            "FOREIGN KEY(codigo_turno) REFERENCES Turno(codigo))";

    private static DbHelper dbHelper;

    private DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_ESPECIFICACIONPROD);
        db.execSQL(TABLE_VENTA);
        db.execSQL(TABLE_TURNO);
        db.execSQL(TABLE_LVENTA);
        db.execSQL(TABLE_DESCUENTO);
        db.execSQL(TABLE_AFORADOR);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static DbHelper getInstance(Context context){
        if (dbHelper == null){
            return new DbHelper(context,"la24gnc.db",null,1);
        }
        return dbHelper;
    }

}
