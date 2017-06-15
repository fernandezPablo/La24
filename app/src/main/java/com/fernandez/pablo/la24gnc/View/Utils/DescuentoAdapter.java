package com.fernandez.pablo.la24gnc.View.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by pablo on 14/05/2017.
 */

public class DescuentoAdapter extends BaseAdapter {

    private ArrayList<Descuento> descuentos;
    private Context context;

    public DescuentoAdapter(Context context, ArrayList<Descuento> descuentos) {
        this.context = context;
        this.descuentos = descuentos;
    }

    @Override
    public int getCount() {
        return descuentos.size();
    }

    @Override
    public Object getItem(int position) {
        return descuentos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return descuentos.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item_descuento,parent,false);
        }

        TextView tvTipo = (TextView) rowView.findViewById(R.id.tvTipo);
        TextView tvDescripcion = (TextView) rowView.findViewById(R.id.tvDescripcion);
        TextView tvMonto = (TextView) rowView.findViewById(R.id.tvMonto);

        DecimalFormat df = new DecimalFormat("#.00");

        tvTipo.setText(descuentos.get(position).getTipo());
        tvDescripcion.setText(descuentos.get(position).getDescripcion());
        tvMonto.setText(df.format(descuentos.get(position).getMonto()));

        return rowView;
    }
}
