package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fernandez.pablo.la24gnc.Service.DbHelper;

import java.util.ArrayList;

/**
 * Created by pablo on 12/05/2017.
 */

public class LineaVentaDAO {

    private DbHelper dbHelper;
    private static final String TABLE_NAME = "Linea_Venta";

    public LineaVentaDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public void createLineaVenta(LineaVenta lv){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("INSERT INTO "+TABLE_NAME+"(cantidad,codigo_producto,codigo_venta) " +
                "VALUES(?,?,?)",new Object[]{lv.getCantidad(),lv.getProducto().getCodigo(),lv.getVenta().getCodigo()});

        db.close();
    }

    public int findLineaVenta(EspecificacionProducto producto,Venta venta){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT codigo FROM Linea_Venta WHERE codigo_producto = ? AND codigo_venta = ? LIMIT 1",
                new String[]{Integer.toString(producto.getCodigo()),Integer.toString(venta.getCodigo())});

        if(cursor.moveToFirst()){
            return cursor.getInt(0);
        }

        return -1;
    }

    public void incrementarLineaVenta(int codigoLV,double cantidad){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("UPDATE "+TABLE_NAME+" SET cantidad = cantidad + ? WHERE codigo = ?",new Object[]{cantidad,codigoLV});
        db.close();
    }

    public ArrayList<LineaVenta> getLineasVenta(int codigoVenta){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<LineaVenta> lineasVenta = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT Linea_Venta.codigo, Linea_Venta.cantidad, " +
                        "especificacion_producto.codigo, especificacion_producto.descripcion," +
                        "especificacion_producto.precio, " +
                        "especificacion_producto.rubro, " +
                        "Linea_Venta.codigo_venta" +
                        " FROM "+TABLE_NAME+" INNER JOIN especificacion_producto ON "
                        +TABLE_NAME+".codigo_producto = especificacion_producto.codigo"+
                        " WHERE Linea_Venta.codigo_venta = ?",
                new String[]{Integer.toString(codigoVenta)});

        if(c.moveToFirst()){
            do {
                lineasVenta.add(new LineaVenta(c.getInt(0),c.getDouble(1),
                        new EspecificacionProducto(c.getInt(2),c.getString(3),c.getDouble(4),c.getInt(5)),
                        new Venta(c.getInt(6))));
            }while(c.moveToNext());
        }
        return lineasVenta;
    }

}
