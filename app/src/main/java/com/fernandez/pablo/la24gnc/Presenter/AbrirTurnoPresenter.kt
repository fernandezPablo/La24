package com.fernandez.pablo.la24gnc.Presenter

import com.fernandez.pablo.la24gnc.Model.Aforador
import com.fernandez.pablo.la24gnc.Model.AforadorDAO
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.Model.VentaDAO
import com.fernandez.pablo.la24gnc.View.AbrirTurno.AbrirTurnoActivity
import com.fernandez.pablo.la24gnc.View.AbrirTurno.IAbrirTurno

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.GregorianCalendar

/**
 * Created by pablo on 07/05/2017.
 */

class AbrirTurnoPresenter(view: IAbrirTurno) {

    var turno = Turno()
    val view : IAbrirTurno = view

    fun guardarTurno(activity: AbrirTurnoActivity) {

        val valoresInicialesGnc = activity.gncFragment!!.valoresAforadores
        val valoresInicialesAceite = activity.aceiteFragment!!.valoresAforadores
        val aforadoresGnc = ArrayList<Aforador>()
        val aforadoresAceite = ArrayList<Aforador>()
        val turnoAbierto = TurnoDAO.hayTurnoAbierto(activity)

        if(turnoAbierto == null){
            this.nuevoTurno()
        }
        else{
            this.editarTurno()
        }

        for (i in valoresInicialesGnc.indices) {
            aforadoresGnc.add(Aforador(i + 1, "m3", valoresInicialesGnc[i].toDouble(),
                    0.0, Aforador.GNC, turno.codigo))
            this.turno.aforadores.add(aforadoresGnc.last())
        }

        for (i in valoresInicialesAceite.indices) {
            aforadoresAceite.add(Aforador(i + 1, "lts", valoresInicialesAceite[i],
                    0.0, Aforador.ACEITE, turno.codigo))
            this.turno.aforadores.add(aforadoresAceite.last())
        }

        if(turnoAbierto == null){
            this.crearAforadores(aforadoresGnc,aforadoresAceite)
        }
        else{
            this.editarAforadores(aforadoresGnc,aforadoresAceite)
        }


    }

    fun nuevoTurno(){
        val calendar = GregorianCalendar()

        val sdf = SimpleDateFormat("dd/MM/yyyy - HH:mm")

        turno.fecha = sdf.format(calendar.time)

        val hora = calendar.get(Calendar.HOUR_OF_DAY)

        if (hora >= 22 || hora < 6) {
            turno.nro = 1
        } else if (hora >= 6 && hora < 14) {
            turno.nro = 2
        } else if (hora >= 14 && hora < 22) {
            turno.nro = 3
        } else {
            turno.nro = 0
        }

        val ventaDAO = VentaDAO(this.view as AbrirTurnoActivity)
        turno.venta = ventaDAO.createVenta()

        val turnoDAO = TurnoDAO(this.view as AbrirTurnoActivity)
        turnoDAO.createTurno(turno)

        turno.codigo = turnoDAO.codLastTurno
    }

    fun editarTurno(){
        val turnoDAO = TurnoDAO(view as AbrirTurnoActivity)
        val ventaDAO = VentaDAO(view as AbrirTurnoActivity)

        this.turno = TurnoDAO.getTurno(view as AbrirTurnoActivity,turnoDAO.codLastTurno)!!

        this.turno.venta = ventaDAO.getVenta(this.turno.codigo)
    }

    fun crearAforadores(aforadoresGnc: ArrayList<Aforador>,aforadoresAceite: ArrayList<Aforador>){
        val aforadorDAO = AforadorDAO(view as AbrirTurnoActivity)

        for (af in aforadoresGnc) {
            aforadorDAO.createAforador(af)
        }

        for (af in aforadoresAceite) {
            aforadorDAO.createAforador(af)
        }

    }

    fun editarAforadores(aforadoresGnc: ArrayList<Aforador>,aforadoresAceite: ArrayList<Aforador>){
        val aforadorDAO = AforadorDAO(view as AbrirTurnoActivity)

        for (af in aforadoresGnc) {
            aforadorDAO.updateAforador(this.turno.codigo,af)
        }

        for (af in aforadoresAceite) {
            aforadorDAO.updateAforador(this.turno.codigo,af)
        }
    }

}
