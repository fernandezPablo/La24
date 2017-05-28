package com.fernandez.pablo.la24gnc.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Presenter.VentaPresenter;
import com.fernandez.pablo.la24gnc.R;

import java.util.ArrayList;

public class VentaActivity extends AppCompatActivity {

    private ItemSpinnerAdapter spinnerAdapter;
    private ArrayList<EspecificacionProducto> productosLista;
    
    private LineaVentaAdapter lineaVentaAdapter;
    private ArrayList<LineaVenta> lineasVenta;

    private ListView listProductos;
    private Spinner spinerProductos;
    private ImageButton btnAdd;
    private EditText etCantidad;

    private VentaPresenter ventaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venta);

        this.productosLista = VentaPresenter.getProdcutos(this);
        this.lineasVenta = new ArrayList<>();

        this.btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        this.etCantidad = (EditText) findViewById(R.id.etCantidad);
        this.listProductos = (ListView) findViewById(R.id.lvProductos);
        this.spinerProductos = (Spinner) findViewById(R.id.spProductos);

        this.spinnerAdapter = new ItemSpinnerAdapter(this,productosLista);
        this.lineaVentaAdapter = new LineaVentaAdapter(this.lineasVenta,this);

        this.spinerProductos.setAdapter(spinnerAdapter);
        this.listProductos.setAdapter(lineaVentaAdapter);

        this.ventaPresenter = new VentaPresenter(this);

        this.actualizarLineasVentaListView();
    }

    public void addProductoListView(View v){

        this.ventaPresenter.agregarLineaVenta();
        this.etCantidad.setText("");
        this.actualizarLineasVentaListView();
        Toast.makeText(this,"VENTA CONCRETADA CORRECTAMENTE ...",Toast.LENGTH_SHORT).show();
    }

    public EspecificacionProducto getProductoSeleccionado(){
        return (EspecificacionProducto) this.spinerProductos.getSelectedItem();
    }

    public double getCantidadLineaVenta(){
        return Double.parseDouble(etCantidad.getText().toString());
    }

    public void actualizarLineasVentaListView(){
        this.ventaPresenter.cargarLineasVenta();
    }

    public void cargarListadoLineasVenta(ArrayList<LineaVenta> lineasVenta){
        this.lineasVenta.clear();
        if(lineasVenta.size() > 0) {
            for (LineaVenta lv :
                    lineasVenta) {
                this.lineasVenta.add(lv);
            }
            this.lineaVentaAdapter.notifyDataSetChanged();
        }
        else{
            Toast.makeText(this,"NO EXISTEN LINEAS DE VENTA PARA LA VENTA ACTUAL...",Toast.LENGTH_SHORT).show();
        }
    }
}
