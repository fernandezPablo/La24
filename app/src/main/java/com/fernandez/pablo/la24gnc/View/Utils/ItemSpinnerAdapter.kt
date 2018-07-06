package com.fernandez.pablo.la24gnc.View.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.R

/**
 * Created by pablo on 15/04/2017.
 */

class ItemSpinnerAdapter(private val context: Context, private val productos: ArrayList<EspecificacionProducto>?) : BaseAdapter() {

    override fun getCount(): Int {
        return productos!!.size
    }

    override fun getItem(position: Int): Any {
        return productos!![position]
    }

    override fun getItemId(position: Int): Long {
        return productos!![position].codigo.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView

        if (convertView == null) {
            // Create a new view into the list.
            val inflater = context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.list_item_spinner, parent, false)
        }

        // Set data into the view.
        val tvNombreProducto = rowView!!.findViewById(R.id.tvItemSpinner) as TextView

        val producto = this.productos!![position]

        tvNombreProducto.text = producto.descripcion

        return rowView
    }

}
