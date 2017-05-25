package com.fernandez.pablo.la24gnc.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Presenter.AbrirTurnoPresenter;
import com.fernandez.pablo.la24gnc.Presenter.MainPresenter;
import com.fernandez.pablo.la24gnc.R;

public class MainActivity extends AppCompatActivity {

    private String turnoAbierto;
    private CardView cardAbrirTurno;
    private CardView cardVenta;
    private CardView cardBuzonVale;
    private CardView cardCerrarTurno;
    private TextView tvInfoTurno;

    private static final String SIN_TURNO = "NINNGUN TURNO ABIERTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        turnoAbierto = MainPresenter.hayTurnoAbierto(this);
        cardAbrirTurno = (CardView) findViewById(R.id.card_abrir_turno);
        cardVenta = (CardView) findViewById(R.id.card_venta);
        cardBuzonVale = (CardView) findViewById(R.id.card_buzon_vale);
        cardCerrarTurno = (CardView) findViewById(R.id.card_cerrar_turno);
        tvInfoTurno = (TextView) findViewById(R.id.tvInfoTurno);

        if(turnoAbierto != null){
            tvInfoTurno.setText(turnoAbierto);
        }
        else{
            tvInfoTurno.setText(SIN_TURNO);
            turnoAbierto = SIN_TURNO;
        }
    }

    public void clickAbrirTurno(View v){
        if(turnoAbierto.equals(SIN_TURNO)) {
            Toast.makeText(this, "ABRIENDO TURNO...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, AbrirTurnoActivity.class);
            startActivity(i);
            finish();
        }
        else{
            Toast.makeText(this, "DEBE CERRAR EL TURNO ACTUAL PARA ABRIR OTRO TURNO...", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickVenta(View v){
            if(!turnoAbierto.equals(SIN_TURNO)) {
                Toast.makeText(this, "INICIANDO VENTA...", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this,VentaActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(this, "NO EXISTE NINGUN TURNO ABIERTO...", Toast.LENGTH_SHORT).show();
            }
        }

    public void clickBuzonVale(View v){
        if(!turnoAbierto.equals(SIN_TURNO)) {
            Toast.makeText(this, "INICIANDO BUZON/VALE...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,DescuentoActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "NO EXISTE NINGUN TURNO ABIERTO...", Toast.LENGTH_SHORT).show();
        }
    }

    public void clickCerrarTurno(View v){
        if(!turnoAbierto.equals(SIN_TURNO)) {
            Toast.makeText(this, "INICIANDO CERRAR TURNO...", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,CerrarTurnoActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(this, "NO EXISTE NINGUN TURNO ABIERTO...", Toast.LENGTH_SHORT).show();
        }
    }

}
