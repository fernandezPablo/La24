package com.fernandez.pablo.la24gnc.View.ABMProductos

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto

/**
 * Created by PABLO on 16/6/2018.
 */
interface IABMProductos {

    fun getProducts() : ArrayList<EspecificacionProducto>
    fun setProductsList(products : ArrayList<EspecificacionProducto>) : Unit
    fun showAlert(message: String, title: String) : Unit

}