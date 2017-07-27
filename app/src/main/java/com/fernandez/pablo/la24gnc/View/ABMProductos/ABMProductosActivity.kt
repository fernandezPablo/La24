package com.fernandez.pablo.la24gnc.View.ABMProductos

import android.content.DialogInterface
import android.content.Intent
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
    lateinit var productoSeleccionado : EspecificacionProducto


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abmproductos)

        aBMProductosPresenter = ABMProductosPresenter(activity = this)
        val mBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
        adapter = ArrayAdapter(this,android.R.layout.select_dialog_singlechoice)

        adapter.add("Editar producto")
        adapter.add("Eliminar producto")

        mBuilder.setAdapter(adapter,DialogInterface.OnClickListener { dialog, which -> onClickOpcion(dialog,which)})

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

        this.iVAdd.setOnClickListener { view -> abrirAltaModProducto() }

    }

    fun onItemLongClick(adapter: AdapterView<*>,view : View,position: Int,id: Long) : Boolean{
        this.productoSeleccionado = listProductos[position]
        alertDialogOpciones.show()
        return true
    }

    fun onClickOpcion(dialog : DialogInterface ,which : Int) : Boolean{
        Toast.makeText(this,"PULSADO ${adapter.getItem(which)} - ${this.productoSeleccionado.descripcion}",
                Toast.LENGTH_SHORT).show()
        when(which){
            0 -> editarProducto()
            1 -> opcionEliminarProducto()
        }

        return true
    }

    fun abrirAltaModProducto(){
        val intent : Intent = Intent(this,AltaModProudctoActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun editarProducto(){
        val intent : Intent = Intent(this,AltaModProudctoActivity::class.java)
        intent.putExtra("codigo_producto",this.productoSeleccionado.codigo)
        finish()
        startActivity(intent)
    }

    fun opcionEliminarProducto(){
        val builder : AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Eliminar Producto").setMessage("Esta seguro que desea eliminar " +
                "${this.productoSeleccionado.descripcion}")
        builder.setPositiveButton("ELIMINAR", DialogInterface.OnClickListener
        {
            dialog, which -> this.eliminarProducto()
        })
        builder.setNegativeButton("CANCELAR",DialogInterface.OnClickListener
        {
            dialog, which -> dialog.dismiss() }
        )
        builder.create().show()
    }

    fun eliminarProducto(){
        this.aBMProductosPresenter.eliminarProducto(this.productoSeleccionado.codigo)
        finish()
        startActivity(intent)
    }


}
