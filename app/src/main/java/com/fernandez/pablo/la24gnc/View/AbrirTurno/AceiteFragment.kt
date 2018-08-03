package com.fernandez.pablo.la24gnc.View.AbrirTurno


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

import com.fernandez.pablo.la24gnc.R

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class AceiteFragment : Fragment() {

    var etAforador1: EditText? = null

    val valoresAforadores: ArrayList<Double>
        get() {

            val valoresAforadores = ArrayList<Double>()

            if (etAforador1!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(etAforador1!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }


            return valoresAforadores
        }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater!!.inflate(R.layout.fragment_aceite, container, false)

        this.etAforador1 = view.findViewById(R.id.etAforadorAceite1) as EditText
        this.etAforador1!!.textSize = 30F

        if (savedInstanceState != null) {
            etAforador1!!.setText(savedInstanceState.getString("etAforador1"))
        }

        if (arguments!= null){
            this.etAforador1!!.setText(arguments.getDouble("af_aceite").toString())
        }
        else {
            print("Arguments not Added")
        }


        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AbrirTurnoActivity).aceiteFragment = this
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState!!.putString("etAforador1", this.etAforador1!!.text.toString())
    }

}// Required empty public constructor
