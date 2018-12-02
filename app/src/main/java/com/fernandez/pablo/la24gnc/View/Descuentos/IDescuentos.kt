package com.fernandez.pablo.la24gnc.View.Descuentos

import com.fernandez.pablo.la24gnc.Model.Descuento

/**
 * Created by PABLO on 30/7/2018.
 */
interface IDescuentos {

    fun cargarDescuentos(descuentos : ArrayList<Descuento>) : Unit
    fun obtenerDescuento(): Descuento?
}