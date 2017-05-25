package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.fernandez.pablo.la24gnc.Service.DbHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * Created by pablo on 28/04/2017.
 */

public class AforadorDAO {


    private static final String TABLE_NAME = "Aforador";
    private static final String CREATE_AFORADOR = "INSERT INTO Aforador(numero,unidad,valor_inicial," +
            "valor_final,tipo,codigo_turno) VALUES(?,?,?,?,?,?)";
    private static final String UPDATE_AFORADOR = "UPDATE Aforador WHERE codigo = ? SET numero=?," +
            " unidad=?, valor_inicial=?, valor_final=?, tipo=?, codigo_turno=?";

    private static final String[] COLUMNAS = new String[]{"codigo","numero","unidad","valor_inicial","valor_final",
            "tipo","codigo_turno"};

    private DbHelper dbHelper;

    public AforadorDAO(Context context){
        this.dbHelper = DbHelper.getInstance(context);
    }


    public void createAforador(Aforador aforador){

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement(CREATE_AFORADOR);
        stmt.bindString(1,Integer.toString(aforador.getNumero()));
        stmt.bindString(2,aforador.getUnidad());
        stmt.bindDouble(3,aforador.getValorInicial());
        stmt.bindDouble(4,aforador.getValorFinal());
        stmt.bindString(5,aforador.getTipo());
        stmt.bindString(6,Integer.toString(aforador.getCodigoTurno()));

        stmt.execute();
    }

    public Aforador readAforador(int codigo){

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String args[] = new String []{Integer.toString(codigo)};

        Cursor cursor = db.query(TABLE_NAME,COLUMNAS,"codigo=?",args,null,null,null,"LIMIT 1");

        try {
            if (cursor.moveToFirst()) {
                return new Aforador(cursor.getInt(0), cursor.getString(1), cursor.getDouble(2),
                        cursor.getDouble(3), cursor.getString(4), cursor.getInt(5));
            }
        }
        finally {
            db.close();
            cursor.close();
        }
        return null;
    }

    public ArrayList<Aforador> listAforadores(int codigoTurno, String tipo){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Aforador WHERE codigo_turno = ? AND tipo = ?",
                new String[]{Integer.toString(codigoTurno),tipo});

        ArrayList<Aforador> aforadores = new ArrayList<>();

        try {
            if (cursor.moveToFirst()) {
                do {
                    aforadores.add(new Aforador(cursor.getInt(1), cursor.getString(2), cursor.getDouble(3)
                            , cursor.getDouble(4), cursor.getString(5), cursor.getInt(6)));
                } while (cursor.moveToNext());
            }
        }
        finally {
            db.close();
            cursor.close();
        }
        return aforadores;
    }

    public void updateAforador(int codigo, Aforador aforador){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement(UPDATE_AFORADOR);

        stmt.execute();

    }

    public void setValorFinal(Aforador aforador){
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.execSQL("UPDATE "+TABLE_NAME+" SET valor_final = ? WHERE numero = ? AND codigo_turno = ? AND tipo = ?",
                new Object[]{aforador.getValorFinal(),aforador.getNumero(),aforador.getCodigoTurno(),aforador.getTipo()});

        db.close();
    }

}
