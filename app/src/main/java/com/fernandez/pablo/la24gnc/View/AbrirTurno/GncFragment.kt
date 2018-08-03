package com.fernandez.pablo.la24gnc.View.AbrirTurno


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fernandez.pablo.la24gnc.R

import java.util.ArrayList


/**
 * A simple [Fragment] subclass.
 */
class GncFragment : Fragment() {

    var aforador_1: TextInputEditText? = null
    var aforador_2: TextInputEditText? = null
    var aforador_3: TextInputEditText? = null
    var aforador_4: TextInputEditText? = null
    var aforador_5: TextInputEditText? = null
    var aforador_6: TextInputEditText? = null

    val valoresAforadores: ArrayList<Double>
        get() {

            val valoresAforadores = ArrayList<Double>()
            if (this.aforador_1!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(this.aforador_1!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }
            if (this.aforador_2!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(this.aforador_2!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }
            if (this.aforador_3!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(this.aforador_3!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }
            if (this.aforador_4!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(this.aforador_4!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }
            if (this.aforador_5!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(this.aforador_5!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }
            if (this.aforador_6!!.text.length > 0) {
                valoresAforadores.add(java.lang.Double.parseDouble(this.aforador_6!!.text.toString()))
            } else {
                valoresAforadores.add(0.0)
            }

            return valoresAforadores
        }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_gnc, container, false)

        this.aforador_1 = view.findViewById(R.id.tietSurtidor1) as TextInputEditText
        this.aforador_1!!.textSize = 30F
        this.aforador_2 = view.findViewById(R.id.tietSurtidor2) as TextInputEditText
        this.aforador_2!!.textSize = 30F
        this.aforador_3 = view.findViewById(R.id.tietSurtidor3) as TextInputEditText
        this.aforador_3!!.textSize = 30F
        this.aforador_4 = view.findViewById(R.id.tietSurtidor4) as TextInputEditText
        this.aforador_4!!.textSize = 30F
        this.aforador_5 = view.findViewById(R.id.tietSurtidor5) as TextInputEditText
        this.aforador_5!!.textSize = 30F
        this.aforador_6 = view.findViewById(R.id.tietSurtidor6) as TextInputEditText
        this.aforador_6!!.textSize = 30F

        if (savedInstanceState != null) {
            this.aforador_1!!.setText(savedInstanceState.getString("aforador_1"))
            this.aforador_2!!.setText(savedInstanceState.getString("aforador_2"))
            this.aforador_3!!.setText(savedInstanceState.getString("aforador_3"))
            this.aforador_4!!.setText(savedInstanceState.getString("aforador_4"))
            this.aforador_5!!.setText(savedInstanceState.getString("aforador_5"))
            this.aforador_6!!.setText(savedInstanceState.getString("aforador_6"))
        }

        if (arguments!= null){
            this.aforador_1!!.setText(arguments.getDouble("af0").toString())
            this.aforador_2!!.setText(arguments.getDouble("af1").toString())
            this.aforador_3!!.setText(arguments.getDouble("af2").toString())
            this.aforador_4!!.setText(arguments.getDouble("af3").toString())
            this.aforador_5!!.setText(arguments.getDouble("af4").toString())
            this.aforador_6!!.setText(arguments.getDouble("af5").toString())
        }
        else {
            print("Arguments not Added")
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AbrirTurnoActivity).gncFragment = this
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        outState!!.putString("aforador_1", this.aforador_1!!.text.toString())
        outState.putString("aforador_2", this.aforador_2!!.text.toString())
        outState.putString("aforador_3", this.aforador_3!!.text.toString())
        outState.putString("aforador_4", this.aforador_4!!.text.toString())
        outState.putString("aforador_5", this.aforador_5!!.text.toString())
        outState.putString("aforador_6", this.aforador_6!!.text.toString())

    }

}// Required empty public constructor
