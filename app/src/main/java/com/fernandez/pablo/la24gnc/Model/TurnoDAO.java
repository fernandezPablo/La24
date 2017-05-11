package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.fernandez.pablo.la24gnc.Service.DbHelper;

import java.util.Date;

/**
 * Created by pablo on 07/05/2017.
 */

public class TurnoDAO {

    private static final String TABLE_NAME = "Turno";
    private static final String[] COLUMNAS = new String[]{"codigo","nro","pmz","estado","fecha",
            "tipo","codigo_turno"};

    private DbHelper dbHelper;

    private static String CREATE_TURNO = "INSERT INTO Turno(nro,pmz,fecha,estado) VALUES(?,?,?,?)";

    public TurnoDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public void createTurno(Turno turno){

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        SQLiteStatement stmt = db.compileStatement(CREATE_TURNO);

        stmt.bindString(1, String.valueOf(turno.getNro()));
        stmt.bindDouble(2,turno.getPmz());
        stmt.bindString(3,turno.getFecha().toString());
        stmt.bindString(4,turno.getEstado());

        stmt.execute();

    }

    public int getCodLastTurno(){
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String columns[] = new String[]{"codigo"};

        Cursor c = db.rawQuery("SELECT codigo FROM "+TABLE_NAME+" ORDER BY codigo DESC LIMIT 1",null);

        if (c.moveToFirst()) {
            return c.getInt(0);
        }

        return -1;
    }

    public static String hayTurnoAbierto(Context context){
        DbHelper dbHelperStatic = DbHelper.getInstance(context);
        SQLiteDatabase db = dbHelperStatic.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT nro, fecha FROM Turno WHERE estado='ABIERTO' ORDER BY codigo DESC LIMIT 1",null);

        if(c.moveToFirst()){
            return "Turno "+c.getString(0)+" - "+c.getString(1);
        }
        return null;
    }

}
