package com.fernandez.pablo.la24gnc.Model

/**
 * Created by pablo on 19/04/2017.
 */

class Aforador() {

    var numero: Int = 0
    var unidad: String? = null
    var valorInicial: Double = 0.0
    var valorFinal: Double = 0.0
    var tipo: String? = null
    var codigoTurno: Int = 0


    constructor(numero : Int,unidad: String?,valorInicial: Double, valorFinal: Double, tipo: String?,codigoTurno: Int) : this() {
        this.numero = numero
        this.unidad = unidad
        this.valorInicial = valorInicial
        this.valorFinal = valorFinal
        this.tipo = tipo
        this.codigoTurno = codigoTurno
    }

    val cantVendida: Double
        get() = this.valorFinal - this.valorInicial

    override fun toString(): String {
        return "Aforador{" +
                "numero=" + numero +
                ", unidad='" + unidad + '\'' +
                ", valorInicial=" + valorInicial +
                ", valorFinal=" + valorFinal +
                '}'
    }

    companion object {
        val ACEITE = "ACEITE"
        val GNC = "GNC"
    }

}
