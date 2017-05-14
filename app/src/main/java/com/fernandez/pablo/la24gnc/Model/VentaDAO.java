package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fernandez.pablo.la24gnc.Service.DbHelper;

/**
 * Created by pablo on 11/05/2017.
 */

public class VentaDAO {

    private DbHelper dbHelper;

    private static final String TABLE_NAME = "venta";

    public VentaDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public Venta createVenta(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("INSERT INTO venta(total) VALUES('0')");
        db.close();

        db = dbHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" ORDER BY codigo DESC LIMIT 1",null);

        if(c.moveToFirst()){
            Venta v = new Venta();
            v.setCodigo(c.getInt(0));
            v.setTotal(c.getDouble(1));
            return v;
        }

        return null;
    }
}
