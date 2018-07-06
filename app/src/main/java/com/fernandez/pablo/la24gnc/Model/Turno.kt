package com.fernandez.pablo.la24gnc.Model

import java.util.ArrayList

/**
 * Created by pablo on 04/03/2017.
 */

class Turno {

    var codigo: Int = 0
    var nro: Int = 0
    var pmz: Double = 0.toDouble()
    var estado: String? = null
    var fecha: String? = null
    var venta: Venta? = null
    var aforadores: ArrayList<Aforador> = ArrayList()

    constructor() {
        this.estado = TURNO_ABIERTO
        this.venta = null
    }

    constructor(codigo: Int, nro: Int, fecha: String, pmz: Double, estado: String, venta: Venta) {
        this.codigo = codigo
        this.nro = nro
        this.fecha = fecha
        this.venta = venta
        this.pmz = pmz
        this.estado = estado
        this.aforadores = ArrayList()
    }


    fun settingAforadores(aforadores: ArrayList<Aforador>) {
        for (af in aforadores) {
            this.aforadores.add(af)
        }
    }

    override fun toString(): String {
        return "Turno{" +
                "nro=" + nro +
                ", fecha=" + fecha +
                ", venta=" + venta +
                ", aforadores=" + aforadores +
                '}'
    }

    fun crearLineaVenta(prod: EspecificacionProducto, cantidad: Double) {
        this.venta!!.crearLineaVenta(prod, cantidad)
    }

    fun generarLvGnc(gnc: EspecificacionProducto) {
        for (i in 0 until CANT_AF_GNC) {
            this.venta!!.crearLineaVenta(EspecificacionProducto(), this.aforadores[i].cantVendida)
        }
    }

    fun generarLvAceite() {
        for (i in 0 until CANT_AF_ACEITE) {
            this.venta!!.crearLineaVenta(EspecificacionProducto(), this.aforadores[i].cantVendida)
        }
    }

    companion object {

        private val CANT_AF_GNC = 6
        private val CANT_AF_ACEITE = 1

        val TURNO_ABIERTO = "ABIERTO"
        val TURNO_CERRADO = "CERRADO"
    }


}

