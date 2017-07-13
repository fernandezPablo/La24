package com.fernandez.pablo.la24gnc.View.ConsultarTurno

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Presenter.ConsultarTurnoPresenter

import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.RVTurnoAdapter
import java.util.*

class ConsultarTurnoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_turno)
        val consultarTurnoPresenter : ConsultarTurnoPresenter = ConsultarTurnoPresenter(this)
        val turnos : ArrayList<Turno> = consultarTurnoPresenter.getTurnosCerrados()
        val rvTurnos : RecyclerView = findViewById(R.id.rvTurnos) as RecyclerView
        val layoutManager : LinearLayoutManager? = LinearLayoutManager(this)
        val rvTurnoAdapter : RVTurnoAdapter = RVTurnoAdapter(turnos)
        rvTurnos.layoutManager = layoutManager
        rvTurnos.adapter = rvTurnoAdapter

    }
}
