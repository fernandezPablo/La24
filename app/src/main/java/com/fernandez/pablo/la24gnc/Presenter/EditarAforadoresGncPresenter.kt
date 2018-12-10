package com.fernandez.pablo.la24gnc.Presenter

import android.widget.EditText
import com.fernandez.pablo.la24gnc.Model.Aforador
import com.fernandez.pablo.la24gnc.Model.AforadorDAO
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.View.EditarTurno.EditarAforadoresGncActivity
import com.fernandez.pablo.la24gnc.View.EditarTurno.IEditarAforadoresGnc

class EditarAforadoresGncPresenter {

    val activity: IEditarAforadoresGnc?

    constructor(activity: IEditarAforadoresGnc?){
        this.activity = activity
    }

    fun obtenerAforadores(codTurno: Int) {
        val aforadorDao = AforadorDAO(activity as EditarAforadoresGncActivity)
        val aforadores = aforadorDao.listAforadores(codTurno,Aforador.GNC)
        var aforadoresInicialesString = ArrayList<String>()
        var aforadoresFinalesString = ArrayList<String>()

        aforadores.forEach {
            aforadoresInicialesString.add(it.valorInicial.toString())
            aforadoresFinalesString.add(it.valorFinal.toString())
        }

        this.activity.cargarAforadores(aforadoresInicialesString,aforadoresFinalesString)
    }

    fun obtenerPmz(codTurno: Int) {
        val turno = TurnoDAO.getTurno(activity as EditarAforadoresGncActivity,codTurno)
        activity.cargarPmz(turno!!.pmz.toString())
    }

    fun actualizarAforadores(codTurno: Int) {
        val valoresIniciales = this.activity!!.obtenerValoresInicialesDeAforadores()
        val valoresFinales = this.activity!!.obtenerValoresFinalesdeAforadores()
        val aforadorDao = AforadorDAO(this.activity as EditarAforadoresGncActivity)
        val size = valoresIniciales.size - 1

        for(index in 0..size){
            aforadorDao.updateAforador(codTurno,
                    Aforador(index+1,"m3",valoresIniciales[index],valoresFinales[index]
                            ,Aforador.GNC,codTurno))
        }
    }

    fun actualizarPmz(codTurno: Int){
        val turnoDAO = TurnoDAO(this.activity as EditarAforadoresGncActivity)
        val pmz = activity.obtenerPmzEditado()
        turnoDAO.updatePmz(this.activity as EditarAforadoresGncActivity,codTurno,pmz)
    }


}