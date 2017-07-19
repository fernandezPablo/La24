package com.fernandez.pablo.la24gnc.View.ABMProductos

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Presenter.ABMProductosPresenter

import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.ProductoAdapter

class ABMProductosActivity : AppCompatActivity(){

    lateinit var iVAdd : ImageView
    lateinit var lvProductos : ListView
    lateinit var listProductos : ArrayList<EspecificacionProducto>
    lateinit var aBMProductosPresenter : ABMProductosPresenter
    lateinit var productoAdapter : ProductoAdapter

    lateinit var alertDialogOpciones : AlertDialog
    lateinit var adapter : ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abmproductos)

        aBMProductosPresenter = ABMProductosPresenter(activity = this)
        val mBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
        adapter = ArrayAdapter(this,android.R.layout.select_dialog_singlechoice)

        adapter.add("Editar producto")
        adapter.add("Eliminar producto")

        mBuilder.setAdapter(adapter,DialogInterface.OnClickListener { dialog, which -> onClickItem(dialog,which)})

        alertDialogOpciones = mBuilder.create()

        //INIT WIDGETS
        this.iVAdd = findViewById(R.id.ivAddProducto) as ImageView
        this.lvProductos = findViewById(R.id.lvProductos) as ListView

        this.listProductos = aBMProductosPresenter.getProductos()
        this.productoAdapter = ProductoAdapter(listProductos = this.listProductos, context = this)
        this.lvProductos.adapter = this.productoAdapter

        this.lvProductos.setOnItemLongClickListener{
            parent, view, position, id ->  onItemLongClick(parent,view,position,id)
        }



    }

    fun onItemLongClick(adapter: AdapterView<*>,view : View,position: Int,id: Long) : Boolean{
        alertDialogOpciones.show()
        return true
    }

    fun onClickItem(dialog : DialogInterface ,which : Int) : Boolean{
        Toast.makeText(this,"PULSADO ${adapter.getItem(which)}",Toast.LENGTH_SHORT).show()
        return true
    }


}
