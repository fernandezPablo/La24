package com.fernandez.pablo.la24gnc.View.DetalleTurno


import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO
import com.fernandez.pablo.la24gnc.Model.Venta
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.Service.DbHelper
import com.fernandez.pablo.la24gnc.View.Utils.DescuentoAdapter

import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class DetalleADeclararFragment : Fragment() {


    private var listDescuentos: ListView? = null
    private var descuentos: ArrayList<Descuento>? = null
    private var descuentoAdapter: DescuentoAdapter? = null
    private var descuentoSeleccionado: Descuento? = null
    private var posicionDescuentoSeleccionado: Int? = null
    private var alertDialog: AlertDialog? = null
    private var alertDialogView: View? = null
    private var montoEditText: EditText? = null
    private var cancelarButton: Button? = null
    private var dividirButton: Button? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_detalle_adeclarar, container, false)

        this.listDescuentos = view.findViewById(R.id.listDescuentos) as ListView

        this.descuentos = ArrayList()
        this.descuentoAdapter = DescuentoAdapter(activity, this.descuentos!!)
        this.listDescuentos!!.adapter = this.descuentoAdapter

        this.listDescuentos!!.setOnItemLongClickListener {
            parent, view, position, id -> this.onItemLongClick(position)
        }

        val mBuilder = AlertDialog.Builder(activity)

        this.alertDialogView = inflater.inflate(R.layout.alert_dialog_dividir_descuento,container,false)
        this.montoEditText = this!!.alertDialogView!!.findViewById(R.id.etMonto) as EditText
        this.cancelarButton = this!!.alertDialogView!!.findViewById(R.id.btnCancelar) as Button
        this.cancelarButton!!.setOnClickListener {
            this.alertDialog!!.dismiss()
        }
        this.dividirButton = this!!.alertDialogView!!.findViewById(R.id.btnDividir) as Button
        this.dividirButton!!.setOnClickListener{
            this.dividirBuzon(it)
            this.alertDialog!!.dismiss()
            this.montoEditText!!.text.clear()
        }

        mBuilder.setView(this.alertDialogView)

        this.alertDialog = mBuilder.create()

        (activity as DetalleTurnoActivity).cargarDetalleADeclarar()

        return view
    }

    private fun dividirBuzon(it: View?) {
        val montoADividir: Double = this.montoEditText!!.text.toString().toDouble()
        if(montoADividir < this.descuentoSeleccionado!!.monto){
            val descuentoNuevo = Descuento("Buzon",
                    Descuento.BUZON,montoADividir, this.descuentoSeleccionado!!.venta!!)
            this.descuentos!!.add(descuentoNuevo)
            DescuentoDAO(activity).createDescuento(descuentoNuevo)
            this.descuentoSeleccionado!!.monto -= montoADividir
            this.descuentos!!.set(this.posicionDescuentoSeleccionado!!, this.descuentoSeleccionado!!)
            DescuentoDAO(activity).updateDescuento(this.descuentoSeleccionado!!)
            this.descuentoAdapter!!.notifyDataSetChanged()
            /*
            * TODO
            * - crear un nuevo descuento en la db
            * - actualizar el descuento seleccionado
            * - actualizar el listView
            * */
        }
        else{
            Toast.makeText(activity,"El monto ingresado es mayor o igual al monto actual del buzon",Toast.LENGTH_SHORT).show()
        }
    }

    fun cargarListaDescuentos(descuentos: ArrayList<Descuento>) {
        for (des in descuentos) {
            this.descuentos!!.add(des)
        }
        this.descuentoAdapter!!.notifyDataSetChanged()
    }

    fun onItemLongClick(position: Int): Boolean{
        if(this.descuentos!![position].tipo!!.equals(Descuento.BUZON)){
            this.descuentoSeleccionado = this.descuentos!![position]
            this.posicionDescuentoSeleccionado = position
            Toast.makeText(activity,"Buzon: ${this.descuentoSeleccionado}",Toast.LENGTH_LONG).show()
            alertDialog!!.show()
        }
        else{
            Toast.makeText(activity,"Los vales no se pueden dividir",Toast.LENGTH_SHORT).show()
        }
        return true
    }


}// Required empty public constructor
