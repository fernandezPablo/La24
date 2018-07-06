package com.fernandez.pablo.la24gnc.Presenter

import android.support.v7.app.AlertDialog
import android.widget.Toast

import com.fernandez.pablo.la24gnc.Model.Aforador
import com.fernandez.pablo.la24gnc.Model.AforadorDAO
import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO
import com.fernandez.pablo.la24gnc.Model.LineaVenta
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.Model.VentaDAO
import com.fernandez.pablo.la24gnc.View.CerrarTurno.CerrarTurnoActivity

/**
 * Created by pablo on 18/05/2017.
 */

class CerrarTurnoPresenter(val activity: CerrarTurnoActivity) {
    val turno: Turno?

    init {
        this.turno = TurnoDAO.getTurno(activity, TurnoDAO(activity).codLastTurno)
        this.turno!!.aforadores = AforadorDAO(activity).listAforadores(turno.codigo, Aforador.GNC)
        this.turno.aforadores.add(AforadorDAO(activity).listAforadores(turno.codigo, Aforador.ACEITE)[0])
    }

    fun cerrarTurno(): Int {

        val valoresFinales = activity.valoresFinales
        val aforadorDAO = AforadorDAO(this.activity)

        var cantidadAceite = 0.0

        for (lv in this.turno!!.venta!!.lineasVenta!!) {
            if (lv.producto!!.codigo == 2) {
                cantidadAceite = lv.cantidad
            }
        }

        for (i in 0 until this.turno.aforadores.size) {
            if (this.turno.aforadores[i].tipo == Aforador.GNC) {
                if (this.turno.aforadores[i].valorInicial > valoresFinales[i]) {
                    return -1
                } else {
                    this.turno.aforadores[i].valorFinal = valoresFinales[i]
                }
            } else {
                this.turno.aforadores[i].valorFinal = this.turno.aforadores[i].valorInicial + cantidadAceite

            }
        }

        val lvDAO = LineaVentaDAO(activity)

        for (af in this.turno.aforadores) {
            aforadorDAO.setValorFinal(af)
            if (af.tipo == Aforador.GNC) {
                lvDAO.createLineaVenta(LineaVenta(af.cantVendida, EspecificacionProductoDAO.getProducto(activity, 1)!!, this.turno.venta!!))
            }
        }

        this.turno.venta!!.lineasVenta = lvDAO.getLineasVenta(this.turno.venta!!.codigo)

        VentaDAO(activity).setTotalVenta(this.turno.venta!!)

        this.turno.pmz = activity.pmz

        val descuentoDAO = DescuentoDAO(activity)
        this.turno.venta!!.setDescuentos(descuentoDAO.getDescuentos(this.turno.venta!!.codigo))

        descuentoDAO.createDescuento(Descuento("Buzón", Descuento.BUZON, this.turno.venta!!.totalConDescuento, this.turno.venta!!))

        TurnoDAO.cerrarTurno(activity, this.turno)

        return 1
    }

}
