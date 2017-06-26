package com.fernandez.pablo.la24gnc.Presenter

import android.content.Context
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Model.TurnoDAO
import com.fernandez.pablo.la24gnc.View.ConsultarTurno.ConsultarTurnoActivity

/**
 * Created by pablo on 23/06/2017.
 */
class ConsultarTurnoPresenter {

    var activity : ConsultarTurnoActivity

    constructor(activity: ConsultarTurnoActivity) {
        this.activity = activity
    }

    fun getTurnosCerrados() : ArrayList<Turno> = TurnoDAO.listTurnosCerrados(this.activity)

}