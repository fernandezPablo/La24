package com.fernandez.pablo.la24gnc.View.DetalleTurno;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.R;
import com.fernandez.pablo.la24gnc.View.Utils.LineaVentaAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleVariosFragment extends Fragment {

    private ListView listLineasVenta;
    private LineaVentaAdapter lineaVentaAdapter;
    private ArrayList<LineaVenta> lineasVenta;
    private TextView tvTotal;


    public DetalleVariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_varios, container, false);

        this.lineasVenta = new ArrayList<>();
        this.listLineasVenta = (ListView) view.findViewById(R.id.lvList);
        this.lineaVentaAdapter = new LineaVentaAdapter(lineasVenta,getActivity());
        this.listLineasVenta.setAdapter(lineaVentaAdapter);

        this.tvTotal = (TextView) view.findViewById(R.id.totalDineroVarios);

        ((DetalleTurnoActivity) getActivity()).cargarDetalleVarios();

        return view;
    }

    public void cargarListLineasVenta(ArrayList<LineaVenta> lineasVenta){
        for (LineaVenta lv:
             lineasVenta) {
            this.lineasVenta.add(lv);
        }
        this.lineaVentaAdapter.notifyDataSetChanged();
    }

    public void cargarTotalDineroVarios(String totalDineroVarios){
        this.tvTotal.setText(totalDineroVarios);
    }

}
