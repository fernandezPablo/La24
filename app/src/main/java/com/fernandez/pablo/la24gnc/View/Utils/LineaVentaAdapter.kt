package com.fernandez.pablo.la24gnc.View.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.fernandez.pablo.la24gnc.Model.LineaVenta
import com.fernandez.pablo.la24gnc.R

import java.text.DecimalFormat
import java.util.ArrayList

/**
 * Created by pablo on 26/05/2017.
 */

class LineaVentaAdapter(private val lineasVenta: ArrayList<LineaVenta>, private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return this.lineasVenta.size
    }

    override fun getItem(position: Int): Any {
        return this.lineasVenta[position]
    }

    override fun getItemId(position: Int): Long {
        return this.lineasVenta[position].codigo.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var rowView: View? = convertView

        if (rowView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            rowView = inflater.inflate(R.layout.list_lineas_venta, parent, false)
        }

        val tvCantidad = rowView!!.findViewById(R.id.cantidad) as TextView
        val tvProducto = rowView.findViewById(R.id.producto) as TextView
        val tvPrecio = rowView.findViewById(R.id.precioUnitario) as TextView
        val tvSubTotal = rowView.findViewById(R.id.subTotal) as TextView

        val df = DecimalFormat("#.00")

        val cantidad = this.lineasVenta[position].cantidad
        val precio = this.lineasVenta[position].producto!!.precio

        tvCantidad.text = df.format(cantidad)
        tvProducto.text = this.lineasVenta[position].producto!!.descripcion
        tvPrecio.text = df.format(precio)
        tvSubTotal.text = df.format(precio * cantidad)

        return rowView
    }
}
