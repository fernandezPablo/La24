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
class DetalleAceiteFragment : Fragment() {

    private var aforadorAceite1VI: TextView? = null
    private var aforadorAceite1VF: TextView? = null
    private var diferenciaAceite: TextView? = null
    private var totalLts: TextView? = null
    private var totalDineroAceite: TextView? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_detalle_aceite, container, false)

        this.aforadorAceite1VI = view.findViewById(R.id.aforadorAceite1VI) as TextView
        this.aforadorAceite1VF = view.findViewById(R.id.aforadorAceite1VF) as TextView
        this.diferenciaAceite = view.findViewById(R.id.difAceite) as TextView

        this.totalLts = view.findViewById(R.id.totalLts) as TextView
        this.totalDineroAceite = view.findViewById(R.id.totalDineroAceite) as TextView

        (this.activity as DetalleTurnoActivity).cargarDetalleAceite()

        return view
    }

    fun cargarValorInicialAceite(valorInicial: String) {
        this.aforadorAceite1VI!!.text = valorInicial
    }

    fun cargarValorFinalAceite(valorFinal: String) {
        this.aforadorAceite1VF!!.text = valorFinal
    }

    fun cargarDiferencia(diferencia: String) {
        this.diferenciaAceite!!.text = diferencia
    }

    fun cargarTotalLts(totalLts: String) {
        this.totalLts!!.text = totalLts
    }

    fun cargarTotalDinero(totalDinero: String) {
        this.totalDineroAceite!!.text = totalDinero
    }
}// Required empty public constructor
