package com.fernandez.pablo.la24gnc.View

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Presenter.VentaPresenter
import com.fernandez.pablo.la24gnc.View.Utils.ProductoAdapter

import com.fernandez.pablo.la24gnc.R

class VentaV2Activity  : AppCompatActivity() {

    lateinit var listViewProductos: ListView
    var listProductos: ArrayList<EspecificacionProducto> = ArrayList()
    var listCantidades: ArrayList<Double> = ArrayList()
    lateinit var productoAdapter: ProductoAdapter
    lateinit var ventaPresenter : VentaPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_venta_v2)
        ventaPresenter = VentaPresenter(this)
        this.listViewProductos = findViewById(R.id.lvProductos) as ListView
        this.listProductos = this.ventaPresenter.getProdcutos(applicationContext)
        for(prod in listProductos) listCantidades.add(0.0)
        productoAdapter = ProductoAdapter(applicationContext,listProductos,listCantidades)
        this.listViewProductos.adapter = productoAdapter
    }
}
