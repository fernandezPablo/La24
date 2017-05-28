package com.fernandez.pablo.la24gnc.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.R;

import java.util.List;

/**
 * Created by pablo on 15/04/2017.
 */

public class ItemSpinnerAdapter extends BaseAdapter{


    private List<EspecificacionProducto> productos;
    private Context context;

    public ItemSpinnerAdapter(Context context, List<EspecificacionProducto> productos) {
        this.productos = productos;
        this.context = context;
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
            rowView = inflater.inflate(R.layout.list_item_spinner, parent, false);
        }

        // Set data into the view.
        TextView tvNombreProducto = (TextView) rowView.findViewById(R.id.tvItemSpinner);

        EspecificacionProducto producto= this.productos.get(position);

        tvNombreProducto.setText(producto.getDescripcion());

        return rowView;
    }

}
