package com.fernandez.pablo.la24gnc.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleGeneralFragment extends Fragment {


    private TextView tvTotalGnc;
    private TextView tvTotalAceite;
    private TextView tvTotalVarios;
    private TextView tvTotalVentas;
    private ListView listDescuentos;
    private ArrayList<Descuento> descuentos;
    private DescuentoAdapter descuentoAdapter;

    public DetalleGeneralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_general, container, false);

        this.tvTotalGnc = (TextView) view.findViewById(R.id.totalGnc);
        this.tvTotalAceite = (TextView) view.findViewById(R.id.tvTotaAceite);
        this.tvTotalVarios = (TextView) view.findViewById(R.id.tvTotalVarios);
        this.tvTotalVentas = (TextView) view.findViewById(R.id.tvTotalVentas);
        this.listDescuentos = (ListView) view.findViewById(R.id.listDescuentos);

        this.descuentos = new ArrayList<>();
        this.descuentoAdapter = new DescuentoAdapter(getActivity(),this.descuentos);
        this.listDescuentos.setAdapter(this.descuentoAdapter);

        ((DetalleTurnoActivity) getActivity()).cargarDetalleGeneral();

        return view;
    }

    public void cargarTotales(String totalGnc, String totalAceite, String totalVarios, String totalVentas){
        this.tvTotalGnc.setText(totalGnc);
        this.tvTotalAceite.setText(totalAceite);
        this.tvTotalVarios.setText(totalVarios);
        this.tvTotalVentas.setText(totalVentas);
    }

    public void cargarListaDescuentos(ArrayList<Descuento> descuentos){
        for (Descuento des:
             descuentos) {
            this.descuentos.add(des);
        }
    }

}
