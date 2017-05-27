package com.fernandez.pablo.la24gnc.View;


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
public class DetalleAceiteFragment extends Fragment {

    private TextView aforadorAceite1VI;
    private TextView aforadorAceite1VF;
    private TextView diferenciaAceite;
    private TextView totalLts;
    private TextView totalDineroAceite;


    public DetalleAceiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_aceite, container, false);

        this.aforadorAceite1VI = (TextView) view.findViewById(R.id.aforadorAceite1VI);
        this.aforadorAceite1VF = (TextView) view.findViewById(R.id.aforadorAceite1VF);
        this.diferenciaAceite = (TextView) view.findViewById(R.id.difAceite);

        this.totalLts = (TextView) view.findViewById(R.id.totalLts);
        this.totalDineroAceite = (TextView) view.findViewById(R.id.totalDineroAceite);

        ((DetalleTurnoActivity)this.getActivity()).cargarDetalleAceite();

        return view;
    }

    public void cargarValorInicialAceite(String valorInicial){
        this.aforadorAceite1VI.setText(valorInicial);
    }

    public void cargarValorFinalAceite(String valorFinal){
        this.aforadorAceite1VF.setText(valorFinal);
    }

    public void cargarDiferencia(String diferencia){
        this.diferenciaAceite.setText(diferencia);
    }

    public void cargarTotalLts(String totalLts){
        this.totalLts.setText(totalLts);
    }

    public void cargarTotalDinero(String totalDinero){
        this.totalDineroAceite.setText(totalDinero);
    }
}
