package com.fernandez.pablo.la24gnc.View.AbrirTurno;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.R;
import com.fernandez.pablo.la24gnc.View.Utils.ItemSpinnerAdapter;
import com.fernandez.pablo.la24gnc.View.Utils.ProductoParaVentaAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;


/**
 * A simple {@link Fragment} subclass.
 */
public class VariosFragment extends Fragment{

    private Spinner spProductos;
    private EditText tvCantidad;
    private ItemSpinnerAdapter spProductoAdapter;
    private ListView listViewProductos;
    private ProductoParaVentaAdapter productoParaVentaAdapter;
    private List<EspecificacionProducto> productosOnSpinner;
    private List<EspecificacionProducto> productosOnListView;
    private List<Double> cantidades;


    public VariosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_varios,container,false);

        spProductos = (Spinner) view.findViewById(R.id.spProductos);
        tvCantidad = (EditText) view.findViewById(R.id.etCantidad);
        listViewProductos = (ListView) view.findViewById(R.id.lvProductos);

        productosOnSpinner = new ArrayList<>();
        productosOnListView = new ArrayList<>();
        cantidades = new ArrayList<>();

        productosOnSpinner.add(new EspecificacionProducto(1,"HIELO X 5KG",45,2));
        productosOnSpinner.add(new EspecificacionProducto(2,"HIELO X 15KG",75,2));
        productosOnSpinner.add(new EspecificacionProducto(1,"REFRIGERANTE X 5LTS",50,3));


        spProductoAdapter = new ItemSpinnerAdapter(getActivity(),productosOnSpinner);
        spProductos.setAdapter(spProductoAdapter);

        productoParaVentaAdapter = new ProductoParaVentaAdapter(getActivity(),productosOnListView,cantidades);
        listViewProductos.setAdapter(productoParaVentaAdapter);

        return view;
    }

    public Spinner getSpProductos() {
        return spProductos;
    }

    public void setSpProductos(Spinner spProductos) {
        this.spProductos = spProductos;
    }

    public EditText getTvCantidad() {
        return tvCantidad;
    }

    public void setTvCantidad(EditText tvCantidad) {
        this.tvCantidad = tvCantidad;
    }

    public ItemSpinnerAdapter getSpProductoAdapter() {
        return spProductoAdapter;
    }

    public void setSpProductoAdapter(ItemSpinnerAdapter spProductoAdapter) {
        this.spProductoAdapter = spProductoAdapter;
    }

    public ListView getListViewProductos() {
        return listViewProductos;
    }

    public void setListViewProductos(ListView listViewProductos) {
        this.listViewProductos = listViewProductos;
    }

    public ProductoParaVentaAdapter getProductoParaVentaAdapter() {
        return productoParaVentaAdapter;
    }

    public void setProductoParaVentaAdapter(ProductoParaVentaAdapter productoParaVentaAdapter) {
        this.productoParaVentaAdapter = productoParaVentaAdapter;
    }

    public List<EspecificacionProducto> getProductosOnSpinner() {
        return productosOnSpinner;
    }

    public void setProductosOnSpinner(List<EspecificacionProducto> productosOnSpinner) {
        this.productosOnSpinner = productosOnSpinner;
    }

    public List<EspecificacionProducto> getProductosOnListView() {
        return productosOnListView;
    }

    public void setProductosOnListView(List<EspecificacionProducto> productosOnListView) {
        this.productosOnListView = productosOnListView;
    }

    public List<Double> getCantidades() {
        return cantidades;
    }

    public void setCantidades(List<Double> cantidades) {
        this.cantidades = cantidades;
    }
}
