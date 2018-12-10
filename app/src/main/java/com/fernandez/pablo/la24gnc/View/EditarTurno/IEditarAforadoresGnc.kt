package com.fernandez.pablo.la24gnc.View.EditarTurno

import com.fernandez.pablo.la24gnc.Model.Aforador

interface IEditarAforadoresGnc {

    fun cargarAforadores(aforadoresIniciales: ArrayList<String>, aforadoresFinales: ArrayList<String>): Unit
    fun cargarPmz(pmz: String): Unit
    fun obtenerValoresInicialesDeAforadores(): ArrayList<Double>
    fun obtenerValoresFinalesdeAforadores(): ArrayList<Double>
    fun obtenerPmzEditado(): Double
}