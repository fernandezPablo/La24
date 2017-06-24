package com.fernandez.pablo.la24gnc.View.ConsultarTurno

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.fernandez.pablo.la24gnc.Model.Turno

import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.RVTurnoAdapter

class ConsultarTurnoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_turno)
        val turnos : ArrayList<Turno> = ArrayList()
        turnos.add(Turno(1,1,"20/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(2,2,"20/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(3,3,"20/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(4,3,"21/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(5,2,"22/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(6,2,"23/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(7,2,"24/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(8,1,"25/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        turnos.add(Turno(9,1,"26/06/2017",234512.0,Turno.TURNO_CERRADO,null))
        val rvTurnos : RecyclerView = findViewById(R.id.rvTurnos) as RecyclerView
        val layoutManager : LinearLayoutManager? = LinearLayoutManager(this)
        val rvTurnoAdapter : RVTurnoAdapter = RVTurnoAdapter(turnos)
        rvTurnos.layoutManager = layoutManager
        rvTurnos.adapter = rvTurnoAdapter
    }
}
