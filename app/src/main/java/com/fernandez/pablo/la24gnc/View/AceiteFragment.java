package com.fernandez.pablo.la24gnc.View;


import android.os.Bundle;
import android.support.annotation.Nullable;
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

        if(savedInstanceState != null){
            etAforador1.setText(savedInstanceState.getString("etAforador1"));
        }

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AbrirTurnoActivity) getActivity()).setAceiteFragment(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("etAforador1",this.etAforador1.getText().toString());
    }

    public ArrayList<Double> getValoresAforadores(){

        ArrayList<Double> valoresAforadores = new ArrayList<>();

        if(etAforador1.getText().length() > 0){
            valoresAforadores.add(Double.parseDouble(etAforador1.getText().toString()));
        }
        else{
            valoresAforadores.add(0.0);
        }


        return valoresAforadores;
    }

}
