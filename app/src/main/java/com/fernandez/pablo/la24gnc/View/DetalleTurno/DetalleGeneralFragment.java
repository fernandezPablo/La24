package com.fernandez.pablo.la24gnc.View.DetalleTurno;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleGeneralFragment extends Fragment {


    private TextView tvTotalGnc;
    private TextView tvTotalAceite;
    private TextView tvTotalVarios;
    private TextView tvTotalVentas;

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


        ((DetalleTurnoActivity) getActivity()).cargarDetalleTotales();

        return view;
    }

    public void cargarTotales(String totalGnc, String totalAceite, String totalVarios, String totalVentas){
        this.tvTotalGnc.setText(totalGnc);
        this.tvTotalAceite.setText(totalAceite);
        this.tvTotalVarios.setText(totalVarios);
        this.tvTotalVentas.setText(totalVentas);
    }



}
