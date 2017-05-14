package com.fernandez.pablo.la24gnc.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fernandez.pablo.la24gnc.Service.DbHelper;

import java.util.ArrayList;

/**
 * Created by pablo on 11/05/2017.
 */

public class EspecificacionProductoDAO {

    private static final String TABLE_NAME = "especificacion_producto";

    private DbHelper dbHelper;

    public EspecificacionProductoDAO(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void createEspecificacionProducto(EspecificacionProducto producto){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("INSERT INTO "+TABLE_NAME+"(rubro,descripcion,precio) VALUES(?,?,?)",
                new Object[]{producto.getRubro(),producto.getDescripcion(),producto.getPrecio()});

    }

    public ArrayList<EspecificacionProducto> listProductos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<EspecificacionProducto> productos = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        if(c.moveToFirst()){
            do {
                productos.add(new EspecificacionProducto(c.getInt(0),c.getString(2),c.getDouble(3),c.getInt(1)));
            }while (c.moveToNext());
        }

        return productos;
    }

}
