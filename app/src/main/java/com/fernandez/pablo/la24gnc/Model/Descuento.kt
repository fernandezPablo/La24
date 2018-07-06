package com.fernandez.pablo.la24gnc.Model

/**
 * Created by pablo on 04/03/2017.
 */

class Descuento {

    var codigo: Int = 0
    var descripcion: String? = null
    var tipo: String? = null
    var monto: Double = 0.toDouble()
    var venta: Venta? = null

    constructor(codigo: Int, descripcion: String, tipo: String, monto: Double, venta: Venta) {
        this.codigo = codigo
        this.descripcion = descripcion
        this.tipo = tipo
        this.monto = monto
        this.venta = venta
    }

    constructor(descripcion: String, tipo: String, monto: Double, venta: Venta) {
        this.descripcion = descripcion
        this.tipo = tipo
        this.monto = monto
        this.venta = venta
    }

    override fun toString(): String {
        return "Descuento{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                '}'
    }

    companion object {
        val BUZON = "BUZON"
        val VALE = "VALE"
    }
}
