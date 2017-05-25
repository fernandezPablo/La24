package com.fernandez.pablo.la24gnc.View;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Presenter.CerrarTurnoPresenter;
import com.fernandez.pablo.la24gnc.R;

public class CerrarTurnoActivity extends AppCompatActivity {

    private TextInputEditText tietPmz;
    private TextInputEditText tietSurtidor1;
    private TextInputEditText tietSurtidor2;
    private TextInputEditText tietSurtidor3;
    private TextInputEditText tietSurtidor4;
    private TextInputEditText tietSurtidor5;
    private TextInputEditText tietSurtidor6;

    private CerrarTurnoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_turno);

        this.tietPmz = (TextInputEditText) findViewById(R.id.tietPmz);
        this.tietSurtidor1 = (TextInputEditText) findViewById(R.id.tietSurtidor1);
        this.tietSurtidor2 = (TextInputEditText) findViewById(R.id.tietSurtidor2);
        this.tietSurtidor3 = (TextInputEditText) findViewById(R.id.tietSurtidor3);
        this.tietSurtidor4 = (TextInputEditText) findViewById(R.id.tietSurtidor4);
        this.tietSurtidor5 = (TextInputEditText) findViewById(R.id.tietSurtidor5);
        this.tietSurtidor6 = (TextInputEditText) findViewById(R.id.tietSurtidor6);

        this.presenter = new CerrarTurnoPresenter(this);
    }

    public double getPmz(){
        return Double.parseDouble(this.tietPmz.getText().toString());
    }

    public double [] getValoresFinales(){

        Editable s1 = this.tietSurtidor1.getText();
        Editable s2 = this.tietSurtidor2.getText();
        Editable s3 = this.tietSurtidor3.getText();
        Editable s4 = this.tietSurtidor4.getText();
        Editable s5 = this.tietSurtidor5.getText();
        Editable s6 = this.tietSurtidor6.getText();

        return new double[]
                {
                        (s1.length()>0)? Double.parseDouble(s1.toString()) : 0.0,
                        (s2.length()>0)? Double.parseDouble(s2.toString()) : 0.0,
                        (s3.length()>0)? Double.parseDouble(s3.toString()) : 0.0,
                        (s4.length()>0)? Double.parseDouble(s4.toString()) : 0.0,
                        (s5.length()>0)? Double.parseDouble(s5.toString()) : 0.0,
                        (s6.length()>0)? Double.parseDouble(s6.toString()) : 0.0,
                };
    }

    public void cerrarTurno(View view){
        this.presenter.cerrarTurno();
        finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        Toast.makeText(this,"TURNO CERRADO CORRECTAMENTE...",Toast.LENGTH_SHORT);
    }

}
