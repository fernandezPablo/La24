package com.fernandez.pablo.la24gnc.Presenter

import android.content.Context
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO
import com.fernandez.pablo.la24gnc.Service.DbHelper
import com.fernandez.pablo.la24gnc.View.ABMProductos.IABMProductos

/**
 * Created by pablo on 13/07/2017.
 */
class ABMProductosPresenter(val activity: IABMProductos? = null, val context: Context? = null) {

    fun cargarProductos() : Unit =
            activity!!.setProductsList(EspecificacionProductoDAO(DbHelper.getInstance(context)).listAllProductos())

    fun eliminarProducto(codigoProducto : Int){
        EspecificacionProductoDAO(DbHelper.getInstance(context)).deleteEspecificacionProducto(codigoProducto)
    }

}