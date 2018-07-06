package com.fernandez.pablo.la24gnc.Model

/**
 * Created by pablo on 04/03/2017.
 */

class LineaVenta {

    var codigo: Int = 0
    var cantidad: Double = 0.toDouble()
    var subtotal: Double = 0.toDouble()
        private set
    var producto: EspecificacionProducto? = null
    var venta: Venta? = null

    constructor() {}

    constructor(codigo: Int, cantidad: Double, producto: EspecificacionProducto, venta: Venta) {
        this.codigo = codigo
        this.cantidad = cantidad
        this.producto = producto
        this.venta = venta
        this.calcularSubtotal()
    }

    constructor(cantidad: Double, producto: EspecificacionProducto, venta: Venta) {
        this.cantidad = cantidad
        this.producto = producto
        this.venta = venta
        this.calcularSubtotal()
    }

    fun calcularSubtotal() {
        this.subtotal = producto!!.precio * this.cantidad
    }
}
