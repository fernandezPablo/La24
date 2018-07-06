package com.fernandez.pablo.la24gnc.Model

/**
 * Created by pablo on 04/03/2017.
 */

class EspecificacionProducto {

    var codigo: Int = 0
    var rubro: Int = 0
    var descripcion: String? = null
    var precio: Double = 0.toDouble()
    var urlImagen: String? = null

    constructor() {}

    constructor(codigo: Int, descripcion: String, precio: Double, rubro: Int) {
        this.codigo = codigo
        this.descripcion = descripcion
        this.precio = precio
        this.rubro = rubro
    }

    override fun toString(): String {
        return "EspecificacionProducto{" +
                "codigo=" + codigo +
                ", rubro=" + rubro +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}'
    }
}
