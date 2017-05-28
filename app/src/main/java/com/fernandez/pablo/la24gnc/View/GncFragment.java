package com.fernandez.pablo.la24gnc.View;


import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
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
    private TextInputEditText aforador_1;
    private TextInputEditText aforador_2;
    private TextInputEditText aforador_3;
    private TextInputEditText aforador_4;
    private TextInputEditText aforador_5;
    private TextInputEditText aforador_6;

    public GncFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        this.view = inflater.inflate(R.layout.fragment_gnc, container, false);

        this.aforador_1 = (TextInputEditText) view.findViewById(R.id.tietSurtidor1);
        this.aforador_2 = (TextInputEditText) view.findViewById(R.id.tietSurtidor2);
        this.aforador_3 = (TextInputEditText) view.findViewById(R.id.tietSurtidor3);
        this.aforador_4 = (TextInputEditText) view.findViewById(R.id.tietSurtidor4);
        this.aforador_5 = (TextInputEditText) view.findViewById(R.id.tietSurtidor5);
        this.aforador_6 = (TextInputEditText) view.findViewById(R.id.tietSurtidor6);

        return view;
    }

    public ArrayList<Double> getValoresAforadores(){

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
