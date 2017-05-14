package com.fernandez.pablo.la24gnc.Model;

import android.content.Context;
import android.content.Intent;
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

    private static String CREATE_TURNO = "INSERT INTO Turno(nro,pmz,fecha,estado,codigo_venta) VALUES(?,?,?,?,?)";

    public TurnoDAO(Context context) {
        this.dbHelper = DbHelper.getInstance(context);
    }

    public void createTurno(Turno turno){

        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.execSQL(CREATE_TURNO,new Object[]{turno.getNro(),turno.getPmz(),
                turno.getFecha().toString(),turno.getEstado(),turno.getVenta().getCodigo()});

        db.close();

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

    public static Turno getTurno(Context context,int codigo){
        SQLiteDatabase db = DbHelper.getInstance(context).getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE codigo = ?",new String[]{Integer.toString(codigo)});

        if(c.moveToFirst()){
            Venta v = new Venta(c.getInt(5));
            return new Turno(c.getInt(1),c.getString(3),c.getDouble(2),c.getString(4),v);
        }

        return null;
    }

}
