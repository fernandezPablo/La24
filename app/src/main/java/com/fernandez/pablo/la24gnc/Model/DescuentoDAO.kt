package com.fernandez.pablo.la24gnc.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import com.fernandez.pablo.la24gnc.Service.DbHelper

import java.util.ArrayList

/**
 * Created by pablo on 13/05/2017.
 */

class DescuentoDAO(context: Context) {

    private val dbHelper: DbHelper

    init {
        this.dbHelper = DbHelper.getInstance(context)
    }

    fun createDescuento(descuento: Descuento) {
        val db = dbHelper.writableDatabase

        db.execSQL("INSERT INTO $TABLE_NAME(descripcion,tipo,monto,codigo_venta) VALUES(?,?,?,?)",
                arrayOf<Any>(descuento.descripcion!!, descuento.tipo!!, descuento.monto, descuento.venta!!.codigo))

        db.close()
    }

    fun getDescuentos(codigoVenta: Int): ArrayList<Descuento> {
        val db = dbHelper.readableDatabase
        val descuentos = ArrayList<Descuento>()

        val c = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE codigo_venta = ?",
                arrayOf(Integer.toString(codigoVenta)))

        try {
            if (c.moveToFirst()) {
                do {
                    descuentos.add(Descuento(c.getInt(0), c.getString(1), c.getString(2),
                            c.getDouble(3), Venta(c.getInt(4))))
                } while (c.moveToNext())
            }
        } finally {
            db.close()
            c.close()
        }
        return descuentos
    }

    companion object {
        private val TABLE_NAME = "Descuento"
    }

    fun updateDescuento(descuento: Descuento){
        val db = dbHelper.writableDatabase

        db.execSQL("UPDATE $TABLE_NAME SET descripcion = ?, monto = ? where codigo = ?",
                arrayOf<Any>(descuento.descripcion!!, descuento.monto, descuento.codigo))

        db.close()
    }

}
