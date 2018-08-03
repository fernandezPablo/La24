package com.fernandez.pablo.la24gnc.View.AbrirTurno

import com.fernandez.pablo.la24gnc.Model.Aforador

/**
 * Created by PABLO on 26/7/2018.
 */

interface IAbrirTurno {

    fun guardarTurno(): Unit
    fun cargarAforadoresGnc(): Unit
    fun leerAforadoresGnc(): ArrayList<Double>
    fun cargarAforadorAceite(): Unit
    fun leerAforadorAceite(): Double

}