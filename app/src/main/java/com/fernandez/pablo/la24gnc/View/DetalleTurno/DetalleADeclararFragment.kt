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
    private var descuentoSeleccionado: Descuento? = null
    private var posicionDescuentoSeleccionado: Int? = null
    private var alertDialogDivBuzon: AlertDialog? = null
    private var alertDialogViewDivBuzon: View? = null
    private var montoBuzonEditText: EditText? = null
    private var cancelarDivBuzonButton: Button? = null
    private var dividirButton: Button? = null
    private var alertDialogGenVale: AlertDialog? = null
    private var alertDialogViewGenVale: View? = null
    private var descripcionValeEditText: EditText? = null
    private var montoValeEditText: EditText? = null
    private var cancelarGenValeButton: Button? = null
    private var genValeButton: Button? = null


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

        /*
        * GENERATE DIALOG VIEW FOR DIVIDIR BUZON
        * */
        val mBuilderDivBuzon = AlertDialog.Builder(activity)

        this.alertDialogViewDivBuzon = inflater.inflate(R.layout.alert_dialog_dividir_descuento,container,false)
        this.montoBuzonEditText = this!!.alertDialogViewDivBuzon!!.findViewById(R.id.etMonto) as EditText
        this.cancelarDivBuzonButton = this!!.alertDialogViewDivBuzon!!.findViewById(R.id.btnCancelar) as Button
        this.cancelarDivBuzonButton!!.setOnClickListener {
            this.alertDialogDivBuzon!!.dismiss()
        }
        this.dividirButton = this!!.alertDialogViewDivBuzon!!.findViewById(R.id.btnDividir) as Button
        this.dividirButton!!.setOnClickListener{
            this.dividirBuzon(it)
            this.alertDialogDivBuzon!!.dismiss()
            this.montoBuzonEditText!!.text.clear()
        }

        mBuilderDivBuzon.setView(this.alertDialogViewDivBuzon)

        this.alertDialogDivBuzon = mBuilderDivBuzon.create()

        /*
        * GENERATE DIALOG VIEW FOR GENERAR VALE
        * */

        val mBuilderGenVale = AlertDialog.Builder(activity)

        this.alertDialogViewGenVale = inflater.inflate(R.layout.alert_dialog_generar_vale,container,false)
        this.descripcionValeEditText = this!!.alertDialogViewGenVale!!.findViewById(R.id.etDescripcionVale) as EditText
        this.montoValeEditText = this!!.alertDialogViewGenVale!!.findViewById(R.id.etMonto) as EditText
        this.cancelarGenValeButton = this!!.alertDialogViewGenVale!!.findViewById(R.id.btnCancelar) as Button
        this.cancelarGenValeButton!!.setOnClickListener {
            this.alertDialogGenVale!!.dismiss()
        }
        this.genValeButton = this!!.alertDialogViewGenVale!!.findViewById(R.id.btnGenerarVale) as Button
        this.genValeButton!!.setOnClickListener{
            this.generarVale(it)
            this.alertDialogGenVale!!.dismiss()
            this.descripcionValeEditText!!.text.clear()
            this.montoValeEditText!!.text.clear()
        }

        mBuilderGenVale.setView(this.alertDialogViewGenVale)

        this.alertDialogGenVale = mBuilderGenVale.create()

        (activity as DetalleTurnoActivity).cargarDetalleADeclarar()

        return view
    }

    private fun dividirBuzon(it: View?) {
        val montoADividir: Double = this.montoBuzonEditText!!.text.toString().toDouble()
        if(montoADividir < this.descuentoSeleccionado!!.monto){
            val descuentoNuevo = Descuento("Buzon",
                    Descuento.BUZON,montoADividir, this.descuentoSeleccionado!!.venta!!)
            this.descuentos!!.add(descuentoNuevo)
            DescuentoDAO(activity).createDescuento(descuentoNuevo)
            this.descuentoSeleccionado!!.monto -= montoADividir
            this.descuentos!!.set(this.posicionDescuentoSeleccionado!!, this.descuentoSeleccionado!!)
            DescuentoDAO(activity).updateDescuento(this.descuentoSeleccionado!!)
            this.descuentoAdapter!!.notifyDataSetChanged()
        }
        else{
            Toast.makeText(activity,"El monto ingresado es mayor o igual al monto actual del buzon",Toast.LENGTH_SHORT).show()
        }
    }

    private fun generarVale(it: View){
        val montoVale = this.montoValeEditText!!.text.toString().toDouble()
        if(montoVale <= this.descuentoSeleccionado!!.monto){
            val descuentoNuevo = Descuento(this.descripcionValeEditText!!.text.toString(),
                    Descuento.VALE,montoVale,this.descuentoSeleccionado!!.venta!!)
            this.descuentos!!.add(descuentoNuevo)
            DescuentoDAO(activity).createDescuento(descuentoNuevo)
            this.descuentoSeleccionado!!.monto -= montoVale
            this.descuentos!!.set(this.posicionDescuentoSeleccionado!!,this.descuentoSeleccionado!!)
            DescuentoDAO(activity).updateDescuento(this.descuentoSeleccionado!!)
            this.descuentoAdapter!!.notifyDataSetChanged()
        }
        else{
            Toast.makeText(activity,"El monto ingresado es mayor al monto actual del buzon",Toast.LENGTH_SHORT).show()
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
            val optionsDialog : AlertDialog.Builder = AlertDialog.Builder(activity)
            optionsDialog.setTitle("Seleccione una opcion")
            optionsDialog.setItems(arrayOf("Dividir Buzon","Generar Vale"), DialogInterface.OnClickListener { dialogInterface, i ->
                when(i){
                    0 -> alertDialogDivBuzon!!.show()
                    1 -> alertDialogGenVale!!.show()
                    else -> Toast.makeText(activity,"La opcion seleccionada no fue definida",Toast.LENGTH_SHORT).show()
                }
            })
            optionsDialog.show()
            //alertDialogDivBuzon!!.show()
        }
        else{
            Toast.makeText(activity,"Los vales no se pueden dividir",Toast.LENGTH_SHORT).show()
        }
        return true
    }


}// Required empty public constructor
