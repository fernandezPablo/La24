package com.fernandez.pablo.la24gnc.Presenter

import com.fernandez.pablo.la24gnc.Model.Aforador
import com.fernandez.pablo.la24gnc.Model.AforadorDAO
import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO
import com.fernandez.pablo.la24gnc.Model.LineaVenta
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.View.DetalleTurno.DetalleTurnoActivity

import java.text.DecimalFormat
import java.util.ArrayList

/**
 * Created by pablo on 25/05/2017.
 */

class DetalleTurnoPresenter(private val activity: DetalleTurnoActivity) {

    private val turno: Turno?
    private var totalDineroGnc: Double = 0.toDouble()
    private var totalDineroAceite: Double = 0.toDouble()
    private var totalDineroVarios: Double = 0.toDouble()

    val valoresInicialesGnc: DoubleArray
        get() = doubleArrayOf(this.turno!!.aforadores[0].valorInicial, this.turno.aforadores[1].valorInicial, this.turno.aforadores[2].valorInicial, this.turno.aforadores[3].valorInicial, this.turno.aforadores[4].valorInicial, this.turno.aforadores[5].valorInicial)

    val valoresFinalesGnc: DoubleArray
        get() = doubleArrayOf(this.turno!!.aforadores[0].valorFinal, this.turno.aforadores[1].valorFinal, this.turno.aforadores[2].valorFinal, this.turno.aforadores[3].valorFinal, this.turno.aforadores[4].valorFinal, this.turno.aforadores[5].valorFinal)

    val diferneciaAforadores: DoubleArray
        get() {
            val valoresIniciales = doubleArrayOf(this.turno!!.aforadores[0].valorInicial, this.turno.aforadores[1].valorInicial, this.turno.aforadores[2].valorInicial, this.turno.aforadores[3].valorInicial, this.turno.aforadores[4].valorInicial, this.turno.aforadores[5].valorInicial)

            val valoresFinales = doubleArrayOf(this.turno.aforadores[0].valorFinal, this.turno.aforadores[1].valorFinal, this.turno.aforadores[2].valorFinal, this.turno.aforadores[3].valorFinal, this.turno.aforadores[4].valorFinal, this.turno.aforadores[5].valorFinal)

            return doubleArrayOf(valoresFinales[0] - valoresIniciales[0], valoresFinales[1] - valoresIniciales[1], valoresFinales[2] - valoresIniciales[2], valoresFinales[3] - valoresIniciales[3], valoresFinales[4] - valoresIniciales[4], valoresFinales[5] - valoresIniciales[5])
        }

    val pmz: String
        get() = java.lang.Double.toString(this.turno!!.pmz)

    val valoresAceite: DoubleArray
        get() {
            val valorInicial = this.turno!!.aforadores[6].valorInicial
            val valorFinal = this.turno.aforadores[6].valorFinal

            return doubleArrayOf(valorInicial, valorFinal, valorFinal - valorInicial)
        }

    val lineasVentaVarios: ArrayList<LineaVenta>
        get() {
            val lvDao = LineaVentaDAO(activity)
            val lineasVenta = lvDao.getLineasVenta(this.turno!!.venta!!.codigo)
            val lineasVentaVarios = ArrayList<LineaVenta>()

            for (lv in lineasVenta) {
                if (lv.producto!!.codigo != 1 && lv.producto!!.codigo != 2) {
                    lineasVentaVarios.add(lv)
                }
            }
            return lineasVentaVarios
        }

    val totales: Array<Double>
        get() {

            val totalVentas = this.totalDineroGnc + this.totalDineroAceite + this.totalDineroVarios

            return arrayOf(this.totalDineroGnc, this.totalDineroAceite, this.totalDineroVarios, totalVentas)
        }

    val valoresADeclarar: ArrayList<Descuento>
        get() {
            val descuentoDAO = DescuentoDAO(activity)
            return descuentoDAO.getDescuentos(this.turno!!.venta!!.codigo)
        }

    init {
        //this.turno = TurnoDAO.getTurno(activity,new TurnoDAO(activity).getCodLastTurno());
        this.turno = TurnoDAO.getTurno(activity, activity.intent.getIntExtra("codigoTurno", 1))
        this.turno!!.aforadores = AforadorDAO(activity).listAforadores(turno.codigo, Aforador.GNC)
        this.turno.aforadores.add(AforadorDAO(activity).listAforadores(turno.codigo, Aforador.ACEITE)[0])
        this.totalDineroAceite = 0.0
        this.totalDineroGnc = 0.0
        this.totalDineroVarios = 0.0
    }

    fun getTotalM3(diferencias: DoubleArray): Double {
        var totalM3 = 0.0
        for (dif in diferencias) {
            totalM3 += dif
        }
        return totalM3
    }

    fun getTotalDineroGnc(totalM3: Double): Double {
        val precioGnc = EspecificacionProductoDAO.getProducto(activity, 1)!!.precio
        this.totalDineroGnc = precioGnc * totalM3
        return this.totalDineroGnc
    }

    fun cargarValoresDeGnc() {

        val df = DecimalFormat("#.00")

        val valoresIniciales = doubleArrayOf(this.turno!!.aforadores[0].valorInicial, this.turno.aforadores[1].valorInicial, this.turno.aforadores[2].valorInicial, this.turno.aforadores[3].valorInicial, this.turno.aforadores[4].valorInicial, this.turno.aforadores[5].valorInicial)

        val valoresFinales = doubleArrayOf(this.turno.aforadores[0].valorFinal, this.turno.aforadores[1].valorFinal, this.turno.aforadores[2].valorFinal, this.turno.aforadores[3].valorFinal, this.turno.aforadores[4].valorFinal, this.turno.aforadores[5].valorFinal)


        val vIAforadores = arrayOf(df.format(valoresIniciales[0]), df.format(valoresIniciales[1]), df.format(valoresIniciales[2]), df.format(valoresIniciales[3]), df.format(valoresIniciales[4]), df.format(valoresIniciales[5]))
        this.activity.detalleGncFragment!!.cargarValoresIniciales(vIAforadores)

        val vFAforadores = arrayOf(df.format(valoresFinales[0]), df.format(valoresFinales[1]), df.format(valoresFinales[2]), df.format(valoresFinales[3]), df.format(valoresFinales[4]), df.format(valoresFinales[5]))
        this.activity.detalleGncFragment!!.cargarValoresFinales(vFAforadores)

        val difs = doubleArrayOf(valoresFinales[0] - valoresIniciales[0], valoresFinales[1] - valoresIniciales[1], valoresFinales[2] - valoresIniciales[2], valoresFinales[3] - valoresIniciales[3], valoresFinales[4] - valoresIniciales[4], valoresFinales[5] - valoresIniciales[5])

        val diferencias = arrayOf(df.format(difs[0]), df.format(difs[1]), df.format(difs[2]), df.format(difs[3]), df.format(difs[4]), df.format(difs[5]))
        this.activity.detalleGncFragment!!.cargarDiferencias(diferencias)

        this.activity.detalleGncFragment!!.cargarPmz(df.format(this.turno.pmz))

        val totalMts = difs[0] + difs[1] + difs[2] + difs[3] + difs[4] + difs[5]
        val totalM3 = df.format(totalMts)
        this.activity.detalleGncFragment!!.cargarTotalM3(totalM3)

        val precioGnc = EspecificacionProductoDAO.getProducto(activity, 1)!!.precio

        this.totalDineroGnc = precioGnc * totalMts
        val totalDinero = df.format(this.totalDineroGnc)

        this.activity.detalleGncFragment!!.cargarTotalDinero(totalDinero)
    }

    fun getTotalAceite(diferencia: Double): Double {
        this.totalDineroAceite = EspecificacionProductoDAO.getProducto(activity, 2)!!.precio * diferencia
        return this.totalDineroAceite
    }

    fun getTotalVentaVarios(lineasVentaVarios: ArrayList<LineaVenta>): Double {
        var total = 0.0

        for (lv in lineasVentaVarios) {
            total += lv.cantidad * lv.producto!!.precio
        }

        val df = DecimalFormat("#.00")
        this.totalDineroVarios = total
        return this.totalDineroVarios
    }

    fun eliminarDescuento(descuento: Descuento){
        DescuentoDAO(activity).deleteDescuento(descuento)
    }

    fun actualizarMontoDescuento(descuento: Descuento, monto: Double){
        descuento.monto += monto
        DescuentoDAO(activity).updateDescuento(descuento)
    }

}
