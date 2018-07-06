package com.fernandez.pablo.la24gnc.View.DetalleTurno


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView

import com.fernandez.pablo.la24gnc.Model.LineaVenta
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.LineaVentaAdapter

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class DetalleVariosFragment : Fragment() {

    private var listLineasVenta: ListView? = null
    private var lineaVentaAdapter: LineaVentaAdapter? = null
    private var lineasVenta: ArrayList<LineaVenta>? = null
    private var tvTotal: TextView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_detalle_varios, container, false)

        this.lineasVenta = ArrayList()
        this.listLineasVenta = view.findViewById(R.id.lvList) as ListView
        this.lineaVentaAdapter = LineaVentaAdapter(lineasVenta!!, activity.applicationContext)
        this.listLineasVenta!!.adapter = lineaVentaAdapter

        this.tvTotal = view.findViewById(R.id.totalDineroVarios) as TextView

        (activity as DetalleTurnoActivity).cargarDetalleVarios()

        return view
    }

    fun cargarListLineasVenta(lineasVenta: ArrayList<LineaVenta>) {
        for (lv in lineasVenta) {
            this.lineasVenta!!.add(lv)
        }
        this.lineaVentaAdapter!!.notifyDataSetChanged()
    }

    fun cargarTotalDineroVarios(totalDineroVarios: String) {
        this.tvTotal!!.text = totalDineroVarios
    }

}// Required empty public constructor
