package com.fernandez.pablo.la24gnc.Model

/**
 * Created by pablo on 19/04/2017.
 */

class Aforador(var numero: Int, var unidad: String?, var valorInicial: Double, var valorFinal: Double, var tipo: String?, var codigoTurno: Int) {

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
