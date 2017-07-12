package com.fernandez.pablo.la24gnc.Presenter

import com.fernandez.pablo.la24gnc.Model.*
import com.fernandez.pablo.la24gnc.View.Descuentos.DescuentoV2Activity

/**
 * Created by pablo on 10/07/2017.
 */
class DescuentoPresenterV2 {

    lateinit var turno : Turno
    lateinit var activity : DescuentoV2Activity

    constructor(activity: DescuentoV2Activity) {
        this.activity = activity
        this.turno = TurnoDAO.getTurno(this.activity,TurnoDAO(this.activity).codLastTurno)
    }

    fun guardarDescuento() : Unit
    {
        DescuentoDAO(this.activity).
                createDescuento(
                        Descuento(
                                activity.etDescripcion.text.toString(),
                                activity.tvDescuento.text.toString(),
                                activity.etMonto.text.toString().toDouble(),
                                this.turno.venta)
                                )
    }

    fun getDescuentos() : Unit {
        activity.cargarDescuentos(
                DescuentoDAO(this.activity).getDescuentos(this.turno.venta.codigo))
    }

}