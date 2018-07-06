package com.fernandez.pablo.la24gnc.View.DetalleTurno


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.fernandez.pablo.la24gnc.R

/**
 * A simple [Fragment] subclass.
 */
class DetalleGeneralFragment : Fragment() {


    private var tvTotalGnc: TextView? = null
    private var tvTotalAceite: TextView? = null
    private var tvTotalVarios: TextView? = null
    private var tvTotalVentas: TextView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_detalle_general, container, false)

        this.tvTotalGnc = view.findViewById(R.id.totalGnc) as TextView
        this.tvTotalAceite = view.findViewById(R.id.tvTotaAceite) as TextView
        this.tvTotalVarios = view.findViewById(R.id.tvTotalVarios) as TextView
        this.tvTotalVentas = view.findViewById(R.id.tvTotalVentas) as TextView


        (activity as DetalleTurnoActivity).cargarDetalleTotales()

        return view
    }

    fun cargarTotales(totalGnc: String, totalAceite: String, totalVarios: String, totalVentas: String) {
        this.tvTotalGnc!!.text = totalGnc
        this.tvTotalAceite!!.text = totalAceite
        this.tvTotalVarios!!.text = totalVarios
        this.tvTotalVentas!!.text = totalVentas
    }


}// Required empty public constructor
