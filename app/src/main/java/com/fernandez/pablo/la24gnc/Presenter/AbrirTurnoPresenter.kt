package com.fernandez.pablo.la24gnc.Presenter

import com.fernandez.pablo.la24gnc.Model.Aforador
import com.fernandez.pablo.la24gnc.Model.AforadorDAO
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.Model.VentaDAO
import com.fernandez.pablo.la24gnc.View.AbrirTurno.AbrirTurnoActivity

import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.GregorianCalendar

/**
 * Created by pablo on 07/05/2017.
 */

object AbrirTurnoPresenter {

    fun guardarTurno(activity: AbrirTurnoActivity) {

        val turno = Turno()
        val valoresInicialesGnc = activity.gncFragment.valoresAforadores
        val valoresInicialesAceite = activity.aceiteFragment.valoresAforadores
        val aforadoresGnc = ArrayList<Aforador>()
        val aforadoresAceite = ArrayList<Aforador>()

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

        val ventaDAO = VentaDAO(activity)
        turno.venta = ventaDAO.createVenta()

        val turnoDAO = TurnoDAO(activity)
        turnoDAO.createTurno(turno)

        turno.codigo = turnoDAO.codLastTurno

        for (i in valoresInicialesGnc.indices) {
            aforadoresGnc.add(Aforador(i + 1, "m3", valoresInicialesGnc[i].toDouble(), 0.0, Aforador.GNC, turno.codigo))
        }

        val aforadorDAO = AforadorDAO(activity)

        for (af in aforadoresGnc) {
            aforadorDAO.createAforador(af)
        }

        for (i in valoresInicialesAceite.indices) {
            aforadoresAceite.add(Aforador(i + 1, "lts", valoresInicialesAceite[i], 0.0, Aforador.ACEITE, turno.codigo))
        }

        for (af in aforadoresAceite) {
            aforadorDAO.createAforador(af)
        }

    }

}
