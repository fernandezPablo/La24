package com.fernandez.pablo.la24gnc.View.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.R

import java.text.DecimalFormat
import java.util.ArrayList

/**
 * Created by pablo on 14/05/2017.
 */

class DescuentoAdapter(private val context: Context, private var descuentos: ArrayList<Descuento>) : BaseAdapter() {

    override fun getCount(): Int {
        return descuentos.size
    }

    override fun getItem(position: Int): Any {
        return descuentos[position]
    }

    override fun getItemId(position: Int): Long {
        return descuentos[position].codigo.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var rowView = convertView

        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.list_item_descuento, parent, false)
        }

        val tvTipo = rowView!!.findViewById(R.id.tvTipo) as TextView
        val tvDescripcion = rowView.findViewById(R.id.tvDescripcion) as TextView
        val tvMonto = rowView.findViewById(R.id.tvMonto) as TextView

        val df = DecimalFormat("#.00")

        tvTipo.text = descuentos[position].tipo
        tvDescripcion.text = descuentos[position].descripcion
        tvMonto.text = df.format(descuentos[position].monto)

        return rowView
    }

    fun setData(descuentos: ArrayList<Descuento>){
        this.descuentos = descuentos
        this.notifyDataSetChanged()
    }
}
