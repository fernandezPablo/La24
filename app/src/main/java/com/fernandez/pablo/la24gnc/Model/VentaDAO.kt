package com.fernandez.pablo.la24gnc.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import com.fernandez.pablo.la24gnc.Service.DbHelper

/**
 * Created by pablo on 11/05/2017.
 */

class VentaDAO(context: Context) {

    private val dbHelper: DbHelper

    init {
        this.dbHelper = DbHelper.getInstance(context)
    }

    fun createVenta(): Venta? {
        var db = dbHelper.writableDatabase
        db.execSQL("INSERT INTO venta(total) VALUES('0')")
        db.close()

        db = dbHelper.readableDatabase

        val c = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY codigo DESC LIMIT 1", null)

        try {
            if (c.moveToFirst()) {
                val v = Venta()
                v.codigo = c.getInt(0)
                v.setTotal(c.getDouble(1))
                return v
            }
        } finally {
            db.close()
            c.close()
        }
        return null
    }

    fun setTotalVenta(venta: Venta) {
        val db = dbHelper.writableDatabase

        db.execSQL("UPDATE $TABLE_NAME SET total = ? WHERE codigo = ?", arrayOf(venta.calcularTotal(), venta.codigo))

        db.close()
    }

    companion object {
        private val TABLE_NAME = "venta"
    }

}
