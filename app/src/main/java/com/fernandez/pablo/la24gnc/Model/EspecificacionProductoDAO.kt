package com.fernandez.pablo.la24gnc.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

import com.fernandez.pablo.la24gnc.Service.DbHelper

import java.util.ArrayList

/**
 * Created by pablo on 11/05/2017.
 */

class EspecificacionProductoDAO(private val dbHelper: DbHelper) {

    fun createEspecificacionProducto(producto: EspecificacionProducto) {

        val db = dbHelper.writableDatabase

        db.execSQL("INSERT INTO $TABLE_NAME(rubro,descripcion,precio,url_imagen) VALUES(?,?,?,?)",
                arrayOf<Any>(producto.rubro, producto.descripcion!!, producto.precio, producto.urlImagen!!))

        db.close()

    }

    fun getImagenProducto(codigo: Int): String? {
        val db = dbHelper.readableDatabase
        val c = db.rawQuery("SELECT url_imagen FROM especificacion_producto WHERE codigo = ?",
                arrayOf(Integer.toString(codigo)))
        try {
            if (c.moveToFirst()) {
                return c.getString(0)
            }
        } finally {
            db.close()
            c.close()
        }
        return null
    }

    fun listProductos(): ArrayList<EspecificacionProducto> {
        val db = dbHelper.readableDatabase
        val productos = ArrayList<EspecificacionProducto>()

        val c = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE NOT codigo = 1", null)

        try {
            if (c.moveToFirst()) {
                do {
                    productos.add(EspecificacionProducto(c.getInt(0), c.getString(2), c.getDouble(3), c.getInt(1),c.getString(4)))
                } while (c.moveToNext())
            }
        } finally {
            db.close()
            c.close()
        }

        return productos
    }

    fun listAllProductos(): ArrayList<EspecificacionProducto> {
        val db = dbHelper.readableDatabase
        val productos = ArrayList<EspecificacionProducto>()

        val c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

        try {
            if (c.moveToFirst()) {
                do {
                    productos.add(EspecificacionProducto(c.getInt(0), c.getString(2), c.getDouble(3),
                            c.getInt(1),c.getString(4)))
                } while (c.moveToNext())
            }
        } finally {
            db.close()
            c.close()
        }

        return productos
    }

    fun updateEspecificacionProducto(producto: EspecificacionProducto) {
        val db = dbHelper.writableDatabase

        db.execSQL("UPDATE " + TABLE_NAME + " SET rubro=    ?, descripcion=?, precio=?," +
                " url_imagen=? WHERE codigo = ? ", arrayOf<Any>(producto.rubro, producto.descripcion!!, producto.precio, producto.urlImagen!!, producto.codigo))

        db.close()
    }

    fun deleteEspecificacionProducto(codigoProducto: Int) {
        val db = dbHelper.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME WHERE codigo = ?", arrayOf<Any>(codigoProducto))
        db.close()
    }

    companion object {

        private val TABLE_NAME = "especificacion_producto"

        fun getProducto(context: Context, codigo: Int): EspecificacionProducto? {
            val db = DbHelper.getInstance(context).readableDatabase
            val c = db.rawQuery("SELECT * FROM especificacion_producto WHERE codigo = ? LIMIT 1",
                    arrayOf(Integer.toString(codigo)))

            try {
                if (c.moveToFirst()) {
                    return EspecificacionProducto(c.getInt(0), c.getString(2), c.getDouble(3),
                            c.getInt(1),c.getString(4))
                }
            } finally {
                db.close()
                c.close()
            }
            return null
        }
    }

}
