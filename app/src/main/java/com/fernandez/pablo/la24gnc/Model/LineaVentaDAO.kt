package com.fernandez.pablo.la24gnc.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import com.fernandez.pablo.la24gnc.Service.DbHelper

import java.util.ArrayList

/**
 * Created by pablo on 12/05/2017.
 */

class LineaVentaDAO(context: Context) {

    private val dbHelper: DbHelper

    init {
        this.dbHelper = DbHelper.getInstance(context)
    }

    fun createLineaVenta(lv: LineaVenta) {
        val db = dbHelper.writableDatabase

        db.execSQL("INSERT INTO " + TABLE_NAME + "(cantidad,codigo_producto,codigo_venta) " +
                "VALUES(?,?,?)", arrayOf(lv.cantidad, lv.producto!!.codigo, lv.venta!!.codigo))

        db.close()
    }

    fun findLineaVenta(producto: EspecificacionProducto, venta: Venta): Int {
        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery("SELECT codigo FROM Linea_Venta WHERE codigo_producto = ? AND codigo_venta = ? LIMIT 1",
                arrayOf(Integer.toString(producto.codigo), Integer.toString(venta.codigo)))


        try {
            if (cursor.moveToFirst()) {
                return cursor.getInt(0)
            }
        } finally {
            db.close()
            cursor.close()
        }

        return -1
    }

    fun incrementarLineaVenta(codigoLV: Int, cantidad: Double) {
        val db = dbHelper.writableDatabase
        db.execSQL("UPDATE $TABLE_NAME SET cantidad = cantidad + ? WHERE codigo = ?", arrayOf(cantidad, codigoLV))
        db.close()
    }

    fun getLineasVenta(codigoVenta: Int): ArrayList<LineaVenta> {
        val db = dbHelper.readableDatabase
        val lineasVenta = ArrayList<LineaVenta>()
        val c = db.rawQuery("SELECT Linea_Venta.codigo, Linea_Venta.cantidad, " +
                "especificacion_producto.codigo, especificacion_producto.descripcion," +
                "especificacion_producto.precio, " +
                "especificacion_producto.rubro, " +
                "Linea_Venta.codigo_venta" +
                " FROM " + TABLE_NAME + " INNER JOIN especificacion_producto ON "
                + TABLE_NAME + ".codigo_producto = especificacion_producto.codigo" +
                " WHERE Linea_Venta.codigo_venta = ?",
                arrayOf(Integer.toString(codigoVenta)))

        try {
            if (c.moveToFirst()) {
                do {
                    lineasVenta.add(LineaVenta(c.getInt(0), c.getDouble(1),
                            EspecificacionProducto(c.getInt(2), c.getString(3), c.getDouble(4), c.getInt(5)),
                            Venta(c.getInt(6))))
                } while (c.moveToNext())
            }
        } finally {
            db.close()
            c.close()
        }
        return lineasVenta
    }

    companion object {
        private val TABLE_NAME = "Linea_Venta"
    }

}
