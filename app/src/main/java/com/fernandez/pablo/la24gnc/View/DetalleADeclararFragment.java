package com.fernandez.pablo.la24gnc.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetalleADeclararFragment extends Fragment {


    private ListView listDescuentos;
    private ArrayList<Descuento> descuentos;
    private DescuentoAdapter descuentoAdapter;

    public DetalleADeclararFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_adeclarar, container, false);

        this.listDescuentos = (ListView) view.findViewById(R.id.listDescuentos);

        this.descuentos = new ArrayList<>();
        this.descuentoAdapter = new DescuentoAdapter(getActivity(),this.descuentos);
        this.listDescuentos.setAdapter(this.descuentoAdapter);

        ((DetalleTurnoActivity) getActivity()).cargarDetalleADeclarar();

        return view;
    }

    public void cargarListaDescuentos(ArrayList<Descuento> descuentos){
        for (Descuento des:
                descuentos) {
            this.descuentos.add(des);
        }
    }

}
