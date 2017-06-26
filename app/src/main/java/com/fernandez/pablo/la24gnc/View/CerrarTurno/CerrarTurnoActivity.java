package com.fernandez.pablo.la24gnc.View.CerrarTurno;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Presenter.CerrarTurnoPresenter;
import com.fernandez.pablo.la24gnc.R;
import com.fernandez.pablo.la24gnc.View.DetalleTurno.DetalleTurnoActivity;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(presenter.getActivity());
        builder.setMessage("ESTA SEGURO QUE DESEA CERRAR EL TURNO?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... params) {
                        presenter.cerrarTurno();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        super.onPostExecute(aVoid);
                        presenter.getActivity().finish();
                        Toast.makeText(presenter.getActivity(),"TURNO CERRADO CORRECTAMENTE...",Toast.LENGTH_SHORT);
                        Intent i = new Intent(presenter.getActivity(),DetalleTurnoActivity.class);
                        i.putExtra("codigoTurno",presenter.getTurno().getCodigo());
                        startActivity(i);
                    }
                }.execute();
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(presenter.getActivity(),"CONTINUANDO EL INGRESO DE AFORADORES...",Toast.LENGTH_SHORT);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
