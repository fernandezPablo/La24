package com.fernandez.pablo.la24gnc.View.AbrirTurno


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.ItemSpinnerAdapter
import com.fernandez.pablo.la24gnc.View.Utils.ProductoParaVentaAdapter

import kotlin.collections.ArrayList

import android.widget.Toast.makeText


/**
 * A simple [Fragment] subclass.
 */
class VariosFragment : Fragment() {

    val spProductos: Spinner = activity.findViewById(R.id.spProductos) as Spinner
    val tvCantidad: EditText = activity.findViewById(R.id.etCantidad) as EditText
    var productosOnSpinner: ArrayList<EspecificacionProducto>? = ArrayList()
    var productosOnListView: ArrayList<EspecificacionProducto> = ArrayList()
    var cantidades: ArrayList<Double> = ArrayList()
    val spProductoAdapter: ItemSpinnerAdapter = ItemSpinnerAdapter(activity, productosOnSpinner)
    val listViewProductos: ListView = parentFragment.activity.findViewById(R.id.lvProductos) as ListView
    val productoParaVentaAdapter: ProductoParaVentaAdapter = ProductoParaVentaAdapter(activity, productosOnListView, cantidades)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_varios, container, false)

        productosOnSpinner!!.add(EspecificacionProducto(1, "HIELO X 5KG", 45.0, 2))
        productosOnSpinner!!.add(EspecificacionProducto(2, "HIELO X 15KG", 75.0, 2))
        productosOnSpinner!!.add(EspecificacionProducto(1, "REFRIGERANTE X 5LTS", 50.0, 3))

        spProductos!!.adapter = spProductoAdapter
        listViewProductos!!.adapter = productoParaVentaAdapter

        return view
    }

}// Required empty public constructor
