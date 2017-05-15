package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fernandez.pablo.la24gnc.Service.DbHelper;

import java.util.ArrayList;

/**
 * Created by pablo on 13/05/2017.
 */

public class DescuentoDAO {

    private DbHelper dbHelper;

    private static final String TABLE_NAME = "Descuento";

    public DescuentoDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public void createDescuento(Descuento descuento){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.execSQL("INSERT INTO "+TABLE_NAME+"(descripcion,tipo,monto,codigo_venta) VALUES(?,?,?,?)",
                new Object[] {descuento.getDescripcion(),descuento.getTipo(),descuento.getMonto(),
                        descuento.getVenta().getCodigo()});

        db.close();
    }

    public ArrayList<Descuento> getDescuentos(int codigoVenta){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Descuento> descuentos = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE codigo_venta = ?",
                new String[]{Integer.toString(codigoVenta)});

        if(c.moveToFirst()){
            do {
                descuentos.add(new Descuento(c.getInt(0),c.getString(1),c.getString(2),
                        c.getDouble(3),new Venta(c.getInt(4))));
            }while (c.moveToNext());
        }
        return descuentos;
    }

}
