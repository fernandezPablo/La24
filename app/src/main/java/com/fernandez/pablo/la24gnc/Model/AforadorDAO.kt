package com.fernandez.pablo.la24gnc.Model

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteStatement

import com.fernandez.pablo.la24gnc.Service.DbHelper

import java.sql.PreparedStatement
import java.util.ArrayList

/**
 * Created by pablo on 28/04/2017.
 */

class AforadorDAO(context: Context) {

    private val dbHelper: DbHelper

    init {
        this.dbHelper = DbHelper.getInstance(context)
    }


    fun createAforador(aforador: Aforador) {

        val db = dbHelper.writableDatabase

        val stmt = db.compileStatement(CREATE_AFORADOR)
        stmt.bindString(1, Integer.toString(aforador.numero))
        stmt.bindString(2, aforador.unidad)
        stmt.bindDouble(3, aforador.valorInicial)
        stmt.bindDouble(4, aforador.valorFinal)
        stmt.bindString(5, aforador.tipo)
        stmt.bindString(6, Integer.toString(aforador.codigoTurno))

        stmt.execute()
    }

    fun readAforador(codigo: Int): Aforador? {

        val db = dbHelper.readableDatabase

        val args = arrayOf(Integer.toString(codigo))

        val cursor = db.query(TABLE_NAME, COLUMNAS, "codigo=?", args, null, null, null, "LIMIT 1")

        try {
            if (cursor.moveToFirst()) {
                return Aforador(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2),
                        cursor.getDouble(3), cursor.getString(4), cursor.getInt(5))
            }
        } finally {
            db.close()
            cursor.close()
        }
        return null
    }

    fun listAforadores(codigoTurno: Int, tipo: String): ArrayList<Aforador> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Aforador WHERE codigo_turno = ? AND tipo = ?",
                arrayOf(Integer.toString(codigoTurno), tipo))
        val aforadores = ArrayList<Aforador>()
        try {
            if (cursor.moveToFirst()) {
                do {
                    aforadores.add(Aforador(cursor.getInt(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getString(5), cursor.getInt(6)))
                } while (cursor.moveToNext())
            }
        } finally {
            db.close()
            cursor.close()
        }
        return aforadores
    }

    fun updateAforador(codigoTurno: Int, aforador: Aforador) {
        val db = dbHelper.writableDatabase
        val stmt = db.compileStatement(UPDATE_AFORADOR)

        stmt.bindString(1,Integer.toString(aforador.numero))
        stmt.bindString(2,aforador.unidad)
        stmt.bindDouble(3,aforador.valorInicial)
        stmt.bindDouble(4,aforador.valorFinal)
        stmt.bindString(5,aforador.tipo)
        stmt.bindString(6,Integer.toString(aforador.numero))
        stmt.bindString(7,Integer.toString(codigoTurno))
        stmt.bindString(8,aforador.tipo)

        stmt.execute()

        stmt.close()
        db.close()
    }

    fun setValorFinal(aforador: Aforador) {
        val db = this.dbHelper.writableDatabase
        db.execSQL("UPDATE $TABLE_NAME SET valor_final = ? WHERE numero = ? AND codigo_turno = ? AND tipo = ?",
                arrayOf<Any>(aforador.valorFinal, aforador.numero, aforador.codigoTurno, aforador.tipo!!))
        db.close()
    }

    companion object {
        private val TABLE_NAME = "Aforador"
        private val CREATE_AFORADOR = "INSERT INTO Aforador(numero,unidad,valor_inicial," + "valor_final,tipo,codigo_turno) VALUES(?,?,?,?,?,?)"
        private val UPDATE_AFORADOR = "UPDATE Aforador SET numero=?," + " unidad=?, valor_inicial=?, valor_final=?, tipo=? WHERE numero = ? AND codigo_turno = ? AND tipo = ?"
        private val COLUMNAS = arrayOf("codigo", "numero", "unidad", "valor_inicial", "valor_final", "tipo", "codigo_turno")
    }

}
