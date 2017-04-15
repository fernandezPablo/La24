package com.fernandez.pablo.la24gnc.View;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.Model.Stock;
import com.fernandez.pablo.la24gnc.R;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;


/**
 * A simple {@link Fragment} subclass.
 */
public class VariosFragment extends Fragment{

    private Spinner spProductos;

    public VariosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_varios,container,false);

        spProductos = (Spinner) view.findViewById(R.id.spProductos);

        List<String> productos = new ArrayList<>();
        /*
        productos.add(new EspecificacionProducto(1,"HIELO X 5KG",45,new Stock(0,0)));
        productos.add(new EspecificacionProducto(2,"HIELO X 15KG",75,new Stock(0,0)));
        productos.add(new EspecificacionProducto(1,"REFRIGERANTE X 5LTS",50,new Stock(0,0)));
        */
        productos.add("HIELO X 5KG");
        productos.add("HIELO X 15KG");
        productos.add("REFRIGERANTE X 5LTS");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_dropdown_item,productos);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spProductos.setAdapter(arrayAdapter);


        return view;
    }

}
