package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
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

        db.close();

    }

    public String getImagenProducto(int codigo) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT url_imagen FROM especificacion_producto WHERE codigo = ?",
                new String[]{Integer.toString(codigo)});
        try {
            if (c.moveToFirst()) {
                return c.getString(0);
            }
        }
        finally {
            db.close();
            c.close();
        }
        return null;
    }

    public ArrayList<EspecificacionProducto> listProductos(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<EspecificacionProducto> productos = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE NOT codigo = 1",null);

        try {
            if (c.moveToFirst()) {
                do {
                    productos.add(new EspecificacionProducto(c.getInt(0), c.getString(2), c.getDouble(3), c.getInt(1)));
                } while (c.moveToNext());
            }
        }
        finally {
            db.close();
            c.close();
        }

        return productos;
    }

    public static EspecificacionProducto getProducto(Context context, int codigo){
        SQLiteDatabase db = DbHelper.getInstance(context).getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM especificacion_producto WHERE codigo = ? LIMIT 1",
                new String[]{Integer.toString(codigo)});

        try {
            if (c.moveToFirst()) {
                return new EspecificacionProducto(c.getInt(0), c.getString(2), c.getDouble(3), c.getInt(1));
            }
        }
        finally {
            db.close();
            c.close();
        }
        return null;
    }

}
