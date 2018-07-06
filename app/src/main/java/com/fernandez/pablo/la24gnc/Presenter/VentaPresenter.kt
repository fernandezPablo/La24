package com.fernandez.pablo.la24gnc.Presenter

import android.content.Context

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO
import com.fernandez.pablo.la24gnc.Model.LineaVenta
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.Service.DbHelper
import com.fernandez.pablo.la24gnc.View.Venta.VentaV2Activity

import java.util.ArrayList

/**
 * Created by pablo on 11/05/2017.
 */

class VentaPresenter(val activity: VentaV2Activity) {

    private val turno: Turno?

    init {
        val turnoDAO = TurnoDAO(activity)
        this.turno = TurnoDAO.getTurno(activity, turnoDAO.codLastTurno)
    }

    fun getProdcutos(context: Context): ArrayList<EspecificacionProducto> {
        return EspecificacionProductoDAO(DbHelper.getInstance(context)).listProductos()
    }

    fun agregarLineaVenta(producto: EspecificacionProducto, cantidad: Double?) {

        val lineaVentaDAO = LineaVentaDAO(this.activity)

        val codLVRepetida = lineaVentaDAO.findLineaVenta(producto, turno!!.venta!!)

        if (codLVRepetida == -1) {
            val lineaVenta = LineaVenta(cantidad!!, producto, turno.venta!!)
            lineaVentaDAO.createLineaVenta(lineaVenta)
        } else {
            lineaVentaDAO.incrementarLineaVenta(codLVRepetida, cantidad!!)
        }
    }

    fun getImagenProducto(codigo: Int): String? {
        return EspecificacionProductoDAO(DbHelper.getInstance(activity)).getImagenProducto(codigo)
    }

    /*public void cargarLineasVenta(){
        LineaVentaDAO lineaVentaDAO = new LineaVentaDAO(this.activity);
        activity.cargarListadoLineasVenta(lineaVentaDAO.getLineasVenta(this.turno.getVenta().getCodigo()));
    }*/

    fun inicializarCantidades() {
        val lineasVenta = LineaVentaDAO(activity).getLineasVenta(this.turno!!.venta!!.codigo)
        for (lv in lineasVenta) {
            var position = 0
            for (prod in this.activity.listProductos) {
                if (prod.codigo == lv.producto!!.codigo) {
                    this.activity.listCantidades[position] = lv.cantidad
                    break
                }
                position++
            }
        }
    }

}
