package com.fernandez.pablo.la24gnc.View.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.R

import java.util.ArrayList

/**
 * Created by pablo on 09/03/2017.
 */

class ProductoParaVentaAdapter(private val context: Context, private val productos: List<EspecificacionProducto>, private val cantidades: List<Double>) : BaseAdapter() {

    override fun getCount(): Int {
        return productos.size
    }

    override fun getItem(position: Int): Any {
        return productos[position]
    }

    override fun getItemId(position: Int): Long {
        return productos[position].codigo.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView

        if (convertView == null) {
            // Create a new view into the list.
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.item_list_productos, parent, false)
        }

        // Set data into the view.
        val tvNombreProducto = rowView!!.findViewById(R.id.tvNombreProducto) as TextView
        val tvCantidad = rowView.findViewById(R.id.tvCantidad) as TextView

        val producto = this.productos[position]
        val cantidad = this.cantidades[position]

        tvNombreProducto.text = producto.descripcion
        tvCantidad.text = java.lang.Double.toString(cantidad)

        return rowView
    }
}


