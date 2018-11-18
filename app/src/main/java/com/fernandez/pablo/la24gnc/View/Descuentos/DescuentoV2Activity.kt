package com.fernandez.pablo.la24gnc.View.Descuentos

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.*
import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.Presenter.DescuentoPresenterV2

import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.DescuentoAdapter

class DescuentoV2Activity:AppCompatActivity(), IDescuentos{

    //PRESENTER
    lateinit var descuentoPresenter : DescuentoPresenterV2

    //WIDGET VISTA
    lateinit var listViewDescuentos: ListView
    lateinit var btnBuzon: Button
    lateinit var btnVale: Button

    //RECURSOS PARA EL LISTVIEW
    lateinit var listDescuentos: ArrayList<Descuento>
    lateinit var descuentoAdapter: DescuentoAdapter
    lateinit var descuentoSeleccionado: Descuento

    //ALERTDIALOG
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mView: View
    lateinit var alertDialog : AlertDialog
    lateinit var tvDescuento : TextView
    lateinit var etDescripcion : EditText
    lateinit var etMonto : EditText
    lateinit var btnCancelar : Button
    lateinit var btnConfirmar : Button

    //ALERTDIALOG OPTIONS
    lateinit var mBuilderOptions: AlertDialog.Builder
    lateinit var adapterOptions: ArrayAdapter<String>
    lateinit var alertDialogOptions: AlertDialog

    var isEdit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descuento_v2)

        //INIT PRESENTER
        this.descuentoPresenter = DescuentoPresenterV2(activity = this)

        //INIT WIDGETS
        this.listViewDescuentos = findViewById(R.id.lvDescuentos) as ListView
        this.btnBuzon = findViewById(R.id.btnBuzon) as Button
        this.btnVale = findViewById(R.id.btnVale) as Button

        //INIT LISTVIEW
        this.listDescuentos = ArrayList()
        this.descuentoAdapter = DescuentoAdapter(this, listDescuentos)
        this.descuentoPresenter.getDescuentos()
        this.listViewDescuentos.adapter = descuentoAdapter
        this.listViewDescuentos.setOnItemLongClickListener{
            parent, view, position, id -> onItemLongClick(parent,view,position,id)
        }

        //INIT ALERTDIALOG
        this.mBuilder = AlertDialog.Builder(this)
        this.mView = layoutInflater.inflate(R.layout.alert_dialog_descuento, null)
        this.tvDescuento = mView.findViewById(R.id.tvDescuento) as TextView
        this.etDescripcion = mView.findViewById(R.id.etDescripcion) as EditText
        this.etMonto = mView.findViewById(R.id.etMonto) as EditText
        this.btnCancelar = mView.findViewById(R.id.btnCancelar) as Button
        this.btnConfirmar = mView.findViewById(R.id.btnConfirmar) as Button
        this.mBuilder.setView(mView)
        this.alertDialog = mBuilder.create()

        //EVENTOS BOTONES
        this.btnBuzon.setOnClickListener {
            this.tvDescuento.text = Descuento.BUZON
            this.etDescripcion.setText("")
            this.etMonto.setText("")
            this.alertDialog.show()
        }
        this.btnVale.setOnClickListener {
            this.tvDescuento.text = Descuento.VALE
            this.etDescripcion.setText("")
            this.etMonto.setText("")
            this.alertDialog.show()
        }
        this.btnCancelar.setOnClickListener{
            this.alertDialog.hide()
        }
        this.btnConfirmar.setOnClickListener {
            if(isEdit){
                this.listDescuentos.remove(this.descuentoSeleccionado)
                this.descuentoSeleccionado.descripcion = this.etDescripcion.text.toString()
                try{
                    this.descuentoSeleccionado.monto = this.etMonto.text.toString().toDouble()
                }
                catch (ex: java.lang.NumberFormatException){
                    Log.w("Warning",ex.message)
                }

                this.listDescuentos.add(this.descuentoSeleccionado)
                this.descuentoAdapter.setData(this.listDescuentos)
                this.descuentoPresenter.editarDescuento(this.descuentoSeleccionado)
                this.isEdit = false
            }
            else{
                this.descuentoPresenter.guardarDescuento()
            }
            this.descuentoPresenter.getDescuentos()
            this.alertDialog.hide()
        }

        //INIT ALERTDIALOG OPTIONS
        this.mBuilderOptions = AlertDialog.Builder(this)
        this.adapterOptions = ArrayAdapter(this, android.R.layout.select_dialog_singlechoice)

        this.adapterOptions.add("Editar")
        this.adapterOptions.add("Elminiar")

        mBuilderOptions.setAdapter(this.adapterOptions, DialogInterface.OnClickListener { dialog, which -> onClickOpcion(dialog,which)})

        this.alertDialogOptions = mBuilderOptions.create()

    }

    private fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        this.descuentoSeleccionado = this.listDescuentos[position]
        this.alertDialogOptions.show()
        Toast.makeText(this,"Descuento seleccionado ${this.descuentoSeleccionado.toString()}",Toast.LENGTH_SHORT).show()
        return true
    }

    private fun onClickOpcion(dialog: DialogInterface?, which: Int): Boolean{
        when(which){
            0 -> editarDescuento()
            1 -> eliminarDescuento()
        }

        return true
    }

    private fun eliminarDescuento() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Descuento").setMessage("Esta seguro que desea eliminar el descuento ${this.descuentoSeleccionado.descripcion}")
        builder.setPositiveButton("Eliminar",DialogInterface.OnClickListener{
          dialog, wich ->  this.confirmacionEliminarDescuento()
        })
        builder.setNegativeButton("Cancelar",DialogInterface.OnClickListener {
            dialogInterface, i -> dialogInterface.dismiss()
        })

        builder.create().show()
    }

    private fun confirmacionEliminarDescuento(){
        descuentoPresenter.eliminarDescuento(this.descuentoSeleccionado)
        this.listDescuentos.remove(this.descuentoSeleccionado)
        this.descuentoAdapter.setData(this.listDescuentos)
    }

    private fun editarDescuento() {
        Toast.makeText(this,"Editando Descuento",Toast.LENGTH_LONG).show()
        this.isEdit = true
        this.tvDescuento.setText(this.descuentoSeleccionado.tipo)
        this.etDescripcion.setText(this.descuentoSeleccionado.descripcion)
        this.etMonto.setText(this.descuentoSeleccionado.monto.toString())
        this.alertDialog.show()
    }

    override fun onPause() {
        super.onPause()
        alertDialog.dismiss()
    }

    override fun cargarDescuentos(descuentos : ArrayList<Descuento>) : Unit {
        this.listDescuentos.clear()
        if(descuentos.size > 0){
            for (des in descuentos){
             this.listDescuentos.add(des)
            }
            this.descuentoAdapter.notifyDataSetChanged()
        }
    }

    override fun obtenerDescuento(): Descuento =
            Descuento(
                    etDescripcion.text.toString(),
                    tvDescuento.text.toString(),
                    etMonto.text.toString().toDouble(),
                    descuentoPresenter.turno.venta!!
            )
}