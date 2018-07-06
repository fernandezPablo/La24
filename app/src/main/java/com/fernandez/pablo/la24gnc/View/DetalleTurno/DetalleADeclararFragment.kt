package com.fernandez.pablo.la24gnc.View.DetalleTurno


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView

import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.DescuentoAdapter

import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DetalleADeclararFragment : Fragment() {


    private var listDescuentos: ListView? = null
    private var descuentos: ArrayList<Descuento>? = null
    private var descuentoAdapter: DescuentoAdapter? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_detalle_adeclarar, container, false)

        this.listDescuentos = view.findViewById(R.id.listDescuentos) as ListView

        this.descuentos = ArrayList()
        this.descuentoAdapter = DescuentoAdapter(activity, this.descuentos!!)
        this.listDescuentos!!.adapter = this.descuentoAdapter

        (activity as DetalleTurnoActivity).cargarDetalleADeclarar()

        return view
    }

    fun cargarListaDescuentos(descuentos: ArrayList<Descuento>) {
        for (des in descuentos) {
            this.descuentos!!.add(des)
        }
        this.descuentoAdapter!!.notifyDataSetChanged()
    }

}// Required empty public constructor
