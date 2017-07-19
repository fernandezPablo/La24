package com.fernandez.pablo.la24gnc.Presenter

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO
import com.fernandez.pablo.la24gnc.Service.DbHelper
import com.fernandez.pablo.la24gnc.View.ABMProductos.ABMProductosActivity

/**
 * Created by pablo on 13/07/2017.
 */
class ABMProductosPresenter {

    var activity : ABMProductosActivity

    constructor(activity: ABMProductosActivity) {
        this.activity = activity
    }

    fun getProductos() : ArrayList<EspecificacionProducto> =
            EspecificacionProductoDAO(DbHelper.getInstance(activity)).listAllProductos()


}