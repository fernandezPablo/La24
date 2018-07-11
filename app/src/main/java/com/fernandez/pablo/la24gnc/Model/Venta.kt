package com.fernandez.pablo.la24gnc.Model

import java.util.ArrayList

/**
 * Created by pablo on 04/03/2017.
 */

class Venta {

    var codigo: Int = 0
    var total: Double = 0.toDouble()
    var lineasVenta: ArrayList<LineaVenta>? = null
    var descuentos: ArrayList<Descuento>? = null

    val totalConDescuento: Double
        get() {

            var totalDescuento = this.total

            for (des in descuentos!!) {
                totalDescuento -= des.monto
            }
            return totalDescuento
        }

    constructor(codigo: Int) {
        this.codigo = codigo
        this.total = 0.0
        this.lineasVenta = ArrayList()
        this.descuentos = ArrayList()
    }

    constructor() {
        this.total = 0.0
        this.lineasVenta = ArrayList()
        this.descuentos = ArrayList()
    }

    fun calcularTotal(): Double {
        for (lv in lineasVenta!!) {
            this.total += lv.subtotal
        }

        return total
    }

    fun asignarLineasVenta(lvs: ArrayList<LineaVenta>) {
        for (lv in lvs) {
            this.lineasVenta!!.add(lv)
        }
    }

    fun crearLineaVenta(prod: EspecificacionProducto, cantidad: Double) {
        val lv = LineaVenta(cantidad, prod, this)
        this.lineasVenta!!.add(lv)
    }
}
