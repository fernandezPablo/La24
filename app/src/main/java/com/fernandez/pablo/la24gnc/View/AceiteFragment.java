package com.fernandez.pablo.la24gnc.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.fernandez.pablo.la24gnc.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AceiteFragment extends Fragment {

    EditText etAforador1;


    public AceiteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_aceite, container, false);

        etAforador1 = (EditText) view.findViewById(R.id.etAforadorAceite1);

        // Inflate the layout for this fragment
        return view;
    }

    public ArrayList<Double> getValoresAforadores(){

        ArrayList<Double> valoresAforadores = new ArrayList<>();

        //TODO Verificar que el usuario haya ingresado valores en los campos
        if(etAforador1.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(etAforador1.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }


        return valoresAforadores;
    }

}
