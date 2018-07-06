package com.fernandez.pablo.la24gnc.Model

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement
import android.util.Log

import com.fernandez.pablo.la24gnc.Service.DbHelper

import java.util.ArrayList
import java.util.Date

/**
 * Created by pablo on 07/05/2017.
 */

class TurnoDAO(context: Context) {

    private val dbHelper: DbHelper

    val codLastTurno: Int
        get() {
            val db = this.dbHelper.readableDatabase

            val columns = arrayOf("codigo")

            val c = db.rawQuery("SELECT codigo FROM $TABLE_NAME ORDER BY codigo DESC LIMIT 1", null)

            try {
                if (c.moveToFirst()) {
                    return c.getInt(0)
                }
            } finally {
                db.close()
                c.close()
            }
            return -1
        }

    init {
        this.dbHelper = DbHelper.getInstance(context)
    }

    fun createTurno(turno: Turno) {

        val db = this.dbHelper.writableDatabase

        db.execSQL(CREATE_TURNO, arrayOf<Any>(turno.nro, turno.pmz, turno.fecha!!.toString(), turno.estado!!, turno.venta!!.codigo))

        db.close()

    }

    companion object {

        private val TABLE_NAME = "Turno"
        private val COLUMNAS = arrayOf("codigo", "nro", "pmz", "estado", "fecha", "tipo", "codigo_turno")

        private val CREATE_TURNO = "INSERT INTO Turno(nro,pmz,fecha,estado,codigo_venta) VALUES(?,?,?,?,?)"

        fun hayTurnoAbierto(context: Context): String? {
            val dbHelperStatic = DbHelper.getInstance(context)
            val db = dbHelperStatic.readableDatabase

            val c = db.rawQuery("SELECT nro, fecha FROM Turno WHERE estado='ABIERTO' ORDER BY codigo DESC LIMIT 1", null)

            try {
                if (c.moveToFirst()) {
                    return "Turno " + c.getString(0) + " - " + c.getString(1)
                }
            } finally {
                db.close()
                c.close()
            }
            return null
        }

        fun getTurno(context: Context, codigo: Int): Turno? {
            val db = DbHelper.getInstance(context).readableDatabase
            val c = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE codigo = ?", arrayOf(Integer.toString(codigo)))


            try {
                if (c.moveToFirst()) {
                    val v = Venta(c.getInt(5))
                    val lvs = LineaVentaDAO(context).getLineasVenta(v.codigo)
                    v.asignarLineasVenta(lvs)

                    return Turno(c.getInt(0), c.getInt(1), c.getString(3), c.getDouble(2), c.getString(4), v)
                }
            } finally {
                db.close()
                c.close()
            }

            return null
        }

        fun cerrarTurno(context: Context, turno: Turno) {
            val db = DbHelper.getInstance(context).writableDatabase
            db.execSQL("UPDATE Turno SET estado = ?, pmz = ? WHERE codigo = ?",
                    arrayOf(Turno.TURNO_CERRADO, turno.pmz, turno.codigo)
            )
            db.close()
        }

        fun listTurnosCerrados(context: Context): ArrayList<Turno> {
            val db = DbHelper.getInstance(context).readableDatabase
            val c = db.rawQuery("SELECT * FROM Turno WHERE estado = ? ", arrayOf(Turno.TURNO_CERRADO))
            val turnos = ArrayList<Turno>()
            try {
                if (c.moveToFirst()) {
                    do {
                        turnos.add(Turno(c.getInt(0), c.getInt(1), c.getString(3), c.getDouble(2),
                                c.getString(4), Venta(c.getInt(5))))
                    } while (c.moveToNext())
                }
                return turnos
            } finally {
                c.close()
                db.close()
            }
        }
    }

}
