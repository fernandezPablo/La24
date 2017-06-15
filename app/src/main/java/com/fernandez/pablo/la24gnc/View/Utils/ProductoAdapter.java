package com.fernandez.pablo.la24gnc.View.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo on 09/03/2017.
 */

public class ProductoAdapter  extends BaseAdapter{

    private List<EspecificacionProducto> productos;
    private List<Double> cantidades;
    private Context context;

    public ProductoAdapter(Context context, List<EspecificacionProducto> productos, List<Double> cantidades) {
        this.productos = productos;
        this.context = context;
        this.cantidades = cantidades;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return productos.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // Set data into the view.
        TextView tvNombreProducto = (TextView) rowView.findViewById(R.id.tvNombreProducto);
        TextView tvCantidad = (TextView) rowView.findViewById(R.id.tvCantidad);

        EspecificacionProducto producto= this.productos.get(position);
        Double cantidad = this.cantidades.get(position);

        tvNombreProducto.setText(producto.getDescripcion());
        tvCantidad.setText(Double.toString(cantidad));

        return rowView;
    }
}


