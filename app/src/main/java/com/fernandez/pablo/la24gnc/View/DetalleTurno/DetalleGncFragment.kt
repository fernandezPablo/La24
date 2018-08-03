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
class DetalleGncFragment : Fragment() {


    private var aforador1VI: TextView? = null
    private var aforador2VI: TextView? = null
    private var aforador3VI: TextView? = null
    private var aforador4VI: TextView? = null
    private var aforador5VI: TextView? = null
    private var aforador6VI: TextView? = null
    private var aforador1VF: TextView? = null
    private var aforador2VF: TextView? = null
    private var aforador3VF: TextView? = null
    private var aforador4VF: TextView? = null
    private var aforador5VF: TextView? = null
    private var aforador6VF: TextView? = null
    private var difAforador1: TextView? = null
    private var difAforador2: TextView? = null
    private var difAforador3: TextView? = null
    private var difAforador4: TextView? = null
    private var difAforador5: TextView? = null
    private var difAforador6: TextView? = null
    private var totalM3: TextView? = null
    private var totalDinero: TextView? = null
    private var pmz: TextView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_detalle_gnc, container, false)

        this.aforador1VI = view.findViewById(R.id.aforador1VI) as TextView
        this.aforador2VI = view.findViewById(R.id.aforador2VI) as TextView
        this.aforador3VI = view.findViewById(R.id.aforador3VI) as TextView
        this.aforador4VI = view.findViewById(R.id.aforador4VI) as TextView
        this.aforador5VI = view.findViewById(R.id.aforador5VI) as TextView
        this.aforador6VI = view.findViewById(R.id.aforador6VI) as TextView

        this.aforador1VF = view.findViewById(R.id.aforador1VF) as TextView
        this.aforador2VF = view.findViewById(R.id.aforador2VF) as TextView
        this.aforador3VF = view.findViewById(R.id.aforador3VF) as TextView
        this.aforador4VF = view.findViewById(R.id.aforador4VF) as TextView
        this.aforador5VF = view.findViewById(R.id.aforador5VF) as TextView
        this.aforador6VF = view.findViewById(R.id.aforador6VF) as TextView

        this.difAforador1 = view.findViewById(R.id.difAforador1) as TextView
        this.difAforador2 = view.findViewById(R.id.difAforador2) as TextView
        this.difAforador3 = view.findViewById(R.id.difAforador3) as TextView
        this.difAforador4 = view.findViewById(R.id.difAforador4) as TextView
        this.difAforador5 = view.findViewById(R.id.difAforador5) as TextView
        this.difAforador6 = view.findViewById(R.id.difAforador6) as TextView

        this.pmz = view.findViewById(R.id.pmz) as TextView
        this.totalM3 = view.findViewById(R.id.totalM3) as TextView
        this.totalDinero = view.findViewById(R.id.totalDinero) as TextView

        (this.activity as DetalleTurnoActivity).cargarDetalleGnc()

        return view
    }


    //TODO los metodos para cargar datos deben recibir datos nativos
    fun cargarValoresIniciales(VIaforadores: Array<String>) {
        this.aforador1VI!!.text = VIaforadores[0]
        this.aforador2VI!!.text = VIaforadores[1]
        this.aforador3VI!!.text = VIaforadores[2]
        this.aforador4VI!!.text = VIaforadores[3]
        this.aforador5VI!!.text = VIaforadores[4]
        this.aforador6VI!!.text = VIaforadores[5]
    }

    fun cargarValoresFinales(VFaforadores: Array<String>) {
        this.aforador1VF!!.text = VFaforadores[0]
        this.aforador2VF!!.text = VFaforadores[1]
        this.aforador3VF!!.text = VFaforadores[2]
        this.aforador4VF!!.text = VFaforadores[3]
        this.aforador5VF!!.text = VFaforadores[4]
        this.aforador6VF!!.text = VFaforadores[5]
    }

    fun cargarDiferencias(diferencias: Array<String>) {
        this.difAforador1!!.text = diferencias[0]
        this.difAforador2!!.text = diferencias[1]
        this.difAforador3!!.text = diferencias[2]
        this.difAforador4!!.text = diferencias[3]
        this.difAforador5!!.text = diferencias[4]
        this.difAforador6!!.text = diferencias[5]
    }

    fun cargarPmz(pmz: String) {
        this.pmz!!.text = pmz
    }

    fun cargarTotalM3(totalM3: String) {
        this.totalM3!!.text = totalM3
    }

    fun cargarTotalDinero(totalDinero: String) {
        this.totalDinero!!.text = totalDinero
    }

}