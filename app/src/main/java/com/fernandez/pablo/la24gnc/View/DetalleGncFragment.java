package com.fernandez.pablo.la24gnc.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.Aforador;
import com.fernandez.pablo.la24gnc.Presenter.DetalleTurnoPresenter;
import com.fernandez.pablo.la24gnc.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleGncFragment extends Fragment {


    private TextView aforador1VI;
    private TextView aforador2VI;
    private TextView aforador3VI;
    private TextView aforador4VI;
    private TextView aforador5VI;
    private TextView aforador6VI;
    private TextView aforador1VF;
    private TextView aforador2VF;
    private TextView aforador3VF;
    private TextView aforador4VF;
    private TextView aforador5VF;
    private TextView aforador6VF;
    private TextView difAforador1;
    private TextView difAforador2;
    private TextView difAforador3;
    private TextView difAforador4;
    private TextView difAforador5;
    private TextView difAforador6;
    private TextView totalM3;
    private TextView totalDinero;
    private TextView pmz;

    public DetalleGncFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_gnc, container, false);

        this.aforador1VI = (TextView) view.findViewById(R.id.aforador1VI);
        this.aforador2VI = (TextView) view.findViewById(R.id.aforador2VI);
        this.aforador3VI = (TextView) view.findViewById(R.id.aforador3VI);
        this.aforador4VI = (TextView) view.findViewById(R.id.aforador4VI);
        this.aforador5VI = (TextView) view.findViewById(R.id.aforador5VI);
        this.aforador6VI = (TextView) view.findViewById(R.id.aforador6VI);

        this.aforador1VF = (TextView) view.findViewById(R.id.aforador1VF);
        this.aforador2VF = (TextView) view.findViewById(R.id.aforador2VF);
        this.aforador3VF = (TextView) view.findViewById(R.id.aforador3VF);
        this.aforador4VF = (TextView) view.findViewById(R.id.aforador4VF);
        this.aforador5VF = (TextView) view.findViewById(R.id.aforador5VF);
        this.aforador6VF = (TextView) view.findViewById(R.id.aforador6VF);

        this.difAforador1 = (TextView) view.findViewById(R.id.difAforador1);
        this.difAforador2 = (TextView) view.findViewById(R.id.difAforador2);
        this.difAforador3 = (TextView) view.findViewById(R.id.difAforador3);
        this.difAforador4 = (TextView) view.findViewById(R.id.difAforador4);
        this.difAforador5 = (TextView) view.findViewById(R.id.difAforador5);
        this.difAforador6 = (TextView) view.findViewById(R.id.difAforador6);

        this.pmz = (TextView) view.findViewById(R.id.pmz);
        this.totalM3 = (TextView) view.findViewById(R.id.totalM3);
        this.totalDinero = (TextView) view.findViewById(R.id.totalDinero);

        ((DetalleTurnoActivity)this.getActivity()).cargarDetalleGnc();

        return view;
    }


    //TODO los metodos para cargar datos deben recibir datos nativos
    public void cargarValoresIniciales(String [] VIaforadores){
        this.aforador1VI.setText(VIaforadores[0]);
        this.aforador2VI.setText(VIaforadores[1]);
        this.aforador3VI.setText(VIaforadores[2]);
        this.aforador4VI.setText(VIaforadores[3]);
        this.aforador5VI.setText(VIaforadores[4]);
        this.aforador6VI.setText(VIaforadores[5]);
    }

    public void cargarValoresFinales(String [] VFaforadores){
        this.aforador1VF.setText(VFaforadores[0]);
        this.aforador2VF.setText(VFaforadores[1]);
        this.aforador3VF.setText(VFaforadores[2]);
        this.aforador4VF.setText(VFaforadores[3]);
        this.aforador5VF.setText(VFaforadores[4]);
        this.aforador6VF.setText(VFaforadores[5]);
    }

    public void cargarDiferencias(String [] diferencias){
        this.difAforador1.setText(diferencias[0]);
        this.difAforador2.setText(diferencias[1]);
        this.difAforador3.setText(diferencias[2]);
        this.difAforador4.setText(diferencias[3]);
        this.difAforador5.setText(diferencias[4]);
        this.difAforador6.setText(diferencias[5]);
    }

    public void cargarPmz(String pmz){
        this.pmz.setText(pmz);
    }

    public void cargarTotalM3(String totalM3){
        this.totalM3.setText(totalM3);
    }

    public void cargarTotalDinero(String totalDinero){
        this.totalDinero.setText(totalDinero);
    }

}
