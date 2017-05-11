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
public class GncFragment extends Fragment {

    private View view;
    private EditText aforador_1;
    private EditText aforador_2;
    private EditText aforador_3;
    private EditText aforador_4;
    private EditText aforador_5;
    private EditText aforador_6;

    public GncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_gnc, container, false);

        this.aforador_1 = (EditText) view.findViewById(R.id.etSurtidor1);
        this.aforador_2 = (EditText) view.findViewById(R.id.etSurtidor2);
        this.aforador_3 = (EditText) view.findViewById(R.id.etSurtidor3);
        this.aforador_4 = (EditText) view.findViewById(R.id.etSurtidor4);
        this.aforador_5 = (EditText) view.findViewById(R.id.etSurtidor5);
        this.aforador_6 = (EditText) view.findViewById(R.id.etSurtidor6);

        return view;
    }

    public ArrayList<Double> getValoresAforadores(){


        //TODO Verificar que el usuario haya ingresado valores en los campos
        ArrayList<Double> valoresAforadores = new ArrayList<>();
        if(this.aforador_1.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(this.aforador_1.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }
         if(this.aforador_2.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(this.aforador_2.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }
         if(this.aforador_3.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(this.aforador_3.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }
         if(this.aforador_4.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(this.aforador_4.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }
         if(this.aforador_5.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(this.aforador_5.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }
         if(this.aforador_6.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(this.aforador_6.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }

        return valoresAforadores;
    }

}
