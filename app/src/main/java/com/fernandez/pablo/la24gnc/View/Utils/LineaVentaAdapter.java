package com.fernandez.pablo.la24gnc.View.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.R;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by pablo on 26/05/2017.
 */

public class LineaVentaAdapter extends BaseAdapter {

    private ArrayList<LineaVenta> lineasVenta;
    private Context context;

    public LineaVentaAdapter(ArrayList<LineaVenta> lineasVenta, Context context) {
        this.lineasVenta = lineasVenta;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.lineasVenta.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lineasVenta.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.lineasVenta.get(position).getCodigo();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if(rowView == null){
            LayoutInflater inflater = (LayoutInflater) context.
                    getSystemService(context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.list_lineas_venta,parent,false);
        }

        TextView tvCantidad = (TextView) rowView.findViewById(R.id.cantidad);
        TextView tvProducto = (TextView) rowView.findViewById(R.id.producto);
        TextView tvPrecio = (TextView) rowView.findViewById(R.id.precioUnitario);
        TextView tvSubTotal = (TextView) rowView.findViewById(R.id.subTotal);

        DecimalFormat df = new DecimalFormat("#.00");

        double cantidad = this.lineasVenta.get(position).getCantidad();
        double precio = this.lineasVenta.get(position).getProducto().getPrecio();

        tvCantidad.setText(df.format(cantidad));
        tvProducto.setText(this.lineasVenta.get(position).getProducto().getDescripcion());
        tvPrecio.setText(df.format(precio));
        tvSubTotal.setText(df.format(precio * cantidad));

        return rowView;
    }
}
