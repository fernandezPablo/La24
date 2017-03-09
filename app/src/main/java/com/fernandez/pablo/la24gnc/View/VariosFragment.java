package com.fernandez.pablo.la24gnc.View;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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

    private ListView listView;

    public VariosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_varios,container,false);

        List<EspecificacionProducto> productos = new ArrayList<>();
        productos.add(new EspecificacionProducto(101,"HIELO X 5KG",45.0,new Stock(0.0,0.0)));
        productos.add(new EspecificacionProducto(102,"HIELO X 15KG",75.0,new Stock(0.0,0.0)));
        productos.add(new EspecificacionProducto(103,"AGUA DESTILADA X 5LTS",35.0,new Stock(0.0,0.0)));
        productos.add(new EspecificacionProducto(104,"LIQUIDO DE FRENOS",40.0,new Stock(0.0,0.0)));
        productos.add(new EspecificacionProducto(105,"LIQUIDO REFRIGERANTE X 5LTS",50.0,new Stock(0.0,0.0)));
        productos.add(new EspecificacionProducto(106,"LIQUIDO REFRIGERANTE X 1LTS",20.0,new Stock(0.0,0.0)));
        productos.add(new EspecificacionProducto(107,"ACEITE NORMAL 40 YPF",40.0,new Stock(0.0,0.0)));

        listView = (ListView) view.findViewById(R.id.listProductos);
        listView.setAdapter(new ProductoAdapter(getContext(),productos));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.BLUE);
            }
        });

        return view;
    }

}
