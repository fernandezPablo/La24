package com.fernandez.pablo.la24gnc.View.Venta

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Presenter.VentaPresenter
import com.fernandez.pablo.la24gnc.View.Utils.ProductoParaVentaAdapter

import com.fernandez.pablo.la24gnc.R
import java.io.InputStream

class VentaV2Activity  : AppCompatActivity(), AdapterView.OnItemClickListener {

    lateinit var listViewProductos: ListView
    var listProductos: ArrayList<EspecificacionProducto> = ArrayList()
    var listCantidades: ArrayList<Double> = ArrayList()
    lateinit var productoParaVentaAdapter: ProductoParaVentaAdapter
    lateinit var ventaPresenter : VentaPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venta_v2)
        ventaPresenter = VentaPresenter(this)
        this.listViewProductos = findViewById(R.id.lvProductos) as ListView
        this.listProductos = this.ventaPresenter.getProdcutos(applicationContext)
        for(prod in listProductos) listCantidades.add(0.0)
        ventaPresenter.inicializarCantidades()
        productoParaVentaAdapter = ProductoParaVentaAdapter(applicationContext, listProductos, listCantidades)
        this.listViewProductos.adapter = productoParaVentaAdapter
        this.listViewProductos.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val mBuilder : AlertDialog.Builder = AlertDialog.Builder(this)
        val mView : View = layoutInflater.inflate(R.layout.alert_dialog_detalle_producto,null)
        val tvNombreProducto : TextView = mView.findViewById(R.id.tvNombreProducto) as TextView
        val tvPrecio : TextView = mView.findViewById(R.id.tvPrecio) as TextView
        val etCantidad : EditText = mView.findViewById(R.id.etCantidad) as EditText
        val btnCancelar : Button = mView.findViewById(R.id.btnCancelar) as Button
        val btnConfirmar : Button = mView.findViewById(R.id.btnConfirmar) as Button
        val ivProducto : ImageView = mView.findViewById(R.id.imageProducto) as ImageView

        val assetManager : AssetManager = getAssets()
        val inputStream : InputStream = assetManager.open(ventaPresenter.getImagenProducto(
                listProductos.get(position).codigo))
        val bitMap : Bitmap = BitmapFactory.decodeStream(inputStream)
        ivProducto.setImageBitmap(bitMap)

        tvNombreProducto.text = listProductos.get(position).descripcion
        tvPrecio.text = "$ ${listProductos.get(position).precio.toString()}"

        mBuilder.setView(mView)
        val alertDialog : AlertDialog = mBuilder.create()

        btnCancelar.setOnClickListener {
            alertDialog.hide()
        }

        btnConfirmar.setOnClickListener{
            listCantidades.set(position,listCantidades.get(position) + etCantidad.text.toString().toDouble())
            ventaPresenter.agregarLineaVenta(listProductos.get(position),etCantidad.text.toString().toDouble())
            productoParaVentaAdapter.notifyDataSetChanged()
            alertDialog.hide()
        }

        alertDialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)

    }
}
