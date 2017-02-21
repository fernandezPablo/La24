package com.fernandez.pablo.la24gnc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickAbrirTurno(View v){
        Toast.makeText(this, "ABRIENDO TURNO...", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,AbrirTurnoActivity.class);
        startActivity(i);
    }

    public void clickVenta(View v){
        Toast.makeText(this, "INICIANDO VENTA...", Toast.LENGTH_SHORT).show();
    }

    public void clickCerrarTurno(View v){
        Toast.makeText(this, "CERRANDO TURNO...", Toast.LENGTH_SHORT).show();
    }
}
