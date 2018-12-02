package com.fernandez.pablo.la24gnc.Presenter

import android.util.Log
import android.widget.Toast
import com.fernandez.pablo.la24gnc.Model.*
import com.fernandez.pablo.la24gnc.View.Descuentos.DescuentoV2Activity
import com.fernandez.pablo.la24gnc.View.Descuentos.IDescuentos

/**
 * Created by pablo on 10/07/2017.
 */
class DescuentoPresenterV2 {

    var turno : Turno
    var activity : IDescuentos

    constructor(activity: DescuentoV2Activity) {
        this.activity = activity
        this.turno = TurnoDAO.getTurno(this.activity as DescuentoV2Activity,TurnoDAO((this.activity as DescuentoV2Activity)).codLastTurno)!!
    }

    fun guardarDescuento() : Unit
    {
        val descuento = this.activity.obtenerDescuento()
        if(descuento != null){
            DescuentoDAO(this.activity as DescuentoV2Activity).
                    createDescuento(descuento)
        }
        else{
            Log.w("Advertencia","No se ingreso la info del descuento")
            Toast.makeText(this.activity as DescuentoV2Activity,
                    "Debe ingresar los datos para generar el descuento",
                    Toast.LENGTH_SHORT).show()
        }
    }

    fun editarDescuento(descuento: Descuento) : Unit
    {
       DescuentoDAO(this.activity as DescuentoV2Activity).updateDescuento(descuento)
    }

    fun getDescuentos() : Unit {
        activity.cargarDescuentos(
                DescuentoDAO(this.activity as DescuentoV2Activity).getDescuentos(this.turno.venta!!.codigo))
    }

    fun eliminarDescuento(descuento: Descuento): Unit{
        DescuentoDAO(this.activity as DescuentoV2Activity).deleteDescuento(descuento)
    }

}