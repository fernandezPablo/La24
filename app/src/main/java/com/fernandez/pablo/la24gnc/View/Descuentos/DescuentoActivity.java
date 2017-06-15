package com.fernandez.pablo.la24gnc.View.Descuentos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.Presenter.DescuentoPresenter;
import com.fernandez.pablo.la24gnc.R;
import com.fernandez.pablo.la24gnc.View.Utils.DescuentoAdapter;

import java.util.ArrayList;

public class DescuentoActivity extends AppCompatActivity {

    private Spinner spTipos;
    private EditText etDescripcion;
    private EditText etMonto;
    private Button btnConfirmar;
    private ListView listViewDescuentos;
    private DescuentoAdapter descuentoAdapter;
    private ArrayList<Descuento> descuentos;

    private DescuentoPresenter descuentoPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descuento);

        this.spTipos = (Spinner) findViewById(R.id.spTipos);
        this.etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        this.etMonto = (EditText) findViewById(R.id.etMonto);
        this.btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        this.listViewDescuentos = (ListView) findViewById(R.id.lvDescuentos);
        this.descuentos = new ArrayList<>();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.array_descuentos,R.layout.support_simple_spinner_dropdown_item);
        this.spTipos.setAdapter(adapter);

        this.descuentoAdapter = new DescuentoAdapter(this,descuentos);
        this.listViewDescuentos.setAdapter(descuentoAdapter);

        this.descuentoPresenter = new DescuentoPresenter(this);

        this.descuentoPresenter.getDescuentos();

    }

    public EditText getEtDescripcion() {
        return etDescripcion;
    }

    public EditText getEtMonto() {
        return etMonto;
    }

    public String getTipo(){
        return spTipos.getSelectedItem().toString();
    }

    public String getDescripcion(){
        return etDescripcion.getText().toString();
    }

    public double getMonto(){
        return Double.parseDouble(etMonto.getText().toString());
    }

    public void confirmarDescuento(View view){

        new AsyncTask<Void,Void,DescuentoActivity>(){

            @Override
            protected DescuentoActivity doInBackground(Void... params) {
                descuentoPresenter.guardarDescuento();
                return descuentoPresenter.getActivity();
            }

            @Override
            protected void onPostExecute(DescuentoActivity descuentoActivity) {
                super.onPostExecute(descuentoActivity);
                descuentoActivity.getEtDescripcion().setText("");
                descuentoActivity.getEtMonto().setText("");
                descuentoActivity.getEtDescripcion().requestFocus();
                descuentoPresenter.getDescuentos();
                Toast.makeText(descuentoActivity,"DESCUENTO AGREGADO CORRECTAMENTE...",Toast.LENGTH_SHORT).show();
            }
        }.execute();

    }

    public void cargarDescuentos(ArrayList<Descuento> descuentos){
        this.descuentos.clear();
        if(descuentos.size() > 0){
            for (Descuento des :
                 descuentos) {
                this.descuentos.add(des);
            }
            this.descuentoAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this, "TODAVIA NO EXISTEN DESCUENTOS PARA ESTA VENTA...",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
