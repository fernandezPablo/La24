package com.fernandez.pablo.la24gnc.Presenter

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO
import com.fernandez.pablo.la24gnc.Service.DbHelper
import com.fernandez.pablo.la24gnc.View.ABMProductos.AltaModProudctoActivity

/**
 * Created by pablo on 23/07/2017.
 */
class AltaModProductoPresenter {

    val mActivity : AltaModProudctoActivity

    constructor(mActivity: AltaModProudctoActivity) {
        this.mActivity = mActivity
    }

    fun guardarProductos() : Unit{
        val producto : EspecificacionProducto = EspecificacionProducto()

        producto.rubro = mActivity.tietRubro.text.toString().toInt()
        producto.descripcion = mActivity.tietDescripcion.text.toString()
        producto.precio = mActivity.tietPrecio.text.toString().toDouble()
        producto.urlImagen = "img/no_image.jpg"

        EspecificacionProductoDAO(DbHelper.getInstance(mActivity)).createEspecificacionProducto(producto)
    }

    fun editarProducto(codigoProducto : Int) : Unit {
        val producto : EspecificacionProducto = EspecificacionProducto()

        producto.codigo = codigoProducto
        producto.rubro = mActivity.tietRubro.text.toString().toInt()
        producto.descripcion = mActivity.tietDescripcion.text.toString()
        producto.precio = mActivity.tietPrecio.text.toString().toDouble()
        producto.urlImagen = "img/no_image.jpg"

        EspecificacionProductoDAO(DbHelper.getInstance(mActivity)).updateEspecificacionProducto(producto)
    }

    fun setProducto(codigo : Int) : Unit{
        val producto : EspecificacionProducto = EspecificacionProductoDAO.getProducto(mActivity,codigo)

        mActivity.tietRubro.setText(producto.rubro.toString())
        mActivity.tietDescripcion.setText(producto.descripcion.toString())
        mActivity.tietPrecio.setText(producto.precio.toString())
    }
}