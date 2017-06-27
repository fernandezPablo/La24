package com.fernandez.pablo.la24gnc.View.Main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.fernandez.pablo.la24gnc.Presenter.MainPresenter

import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.AbrirTurno.AbrirTurnoActivity
import com.fernandez.pablo.la24gnc.View.CerrarTurno.CerrarTurnoActivity
import com.fernandez.pablo.la24gnc.View.ConsultarTurno.ConsultarTurnoActivity
import com.fernandez.pablo.la24gnc.View.Descuentos.DescuentoActivity
import com.fernandez.pablo.la24gnc.View.Venta.VentaActivity
import com.fernandez.pablo.la24gnc.View.VentaV2Activity

class MainV2Activity : AppCompatActivity() {

    val SIN_TURNO : String? = "NINGUN TURNO ABIERTO"
    var turnoAbierto : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_v2)

        turnoAbierto = MainPresenter.hayTurnoAbierto(this)
        val tvInfoTurno : TextView = findViewById(R.id.textViewInfoTurno) as TextView
        val btnAbrirTurno : Button = findViewById(R.id.btnAbrirTurno) as Button
        val btnVenta : Button = findViewById(R.id.btnVenta) as Button
        val btnBuzonVale : Button = findViewById(R.id.btnBuzonVale) as Button
        val btnCerrarTurno : Button = findViewById(R.id.btnCerrarTurno) as Button
        val btnABMProudctos : Button = findViewById(R.id.btnABMProductos) as Button
        val btnConsultarTurno : Button = findViewById(R.id.btnConsultarTurno) as Button

        if (turnoAbierto != null)
            tvInfoTurno.setText(turnoAbierto)
        else {
            turnoAbierto = SIN_TURNO
            tvInfoTurno.setText(SIN_TURNO)
        }

        btnAbrirTurno.setOnClickListener { abrirTurno() }
        btnVenta.setOnClickListener { venta() }
        btnBuzonVale.setOnClickListener { buzonVale() }
        btnCerrarTurno.setOnClickListener { cerrarTurno() }
        btnConsultarTurno.setOnClickListener{ consultarTurno()}
    }

    fun abrirTurno(){
        if (turnoAbierto == SIN_TURNO) {
            Toast.makeText(this, "ABRIENDO TURNO...", Toast.LENGTH_SHORT).show()
            val i = Intent(this, AbrirTurnoActivity::class.java)
            startActivity(i)
            finish()
        } else {
            Toast.makeText(this, "DEBE CERRAR EL TURNO ACTUAL PARA ABRIR OTRO TURNO...", Toast.LENGTH_SHORT).show()
        }
    }

    fun venta() {
        if (turnoAbierto != SIN_TURNO) {
            Toast.makeText(this, "INICIANDO VENTA...", Toast.LENGTH_SHORT).show()
            val i = Intent(this, VentaV2Activity::class.java)
            startActivity(i)
        } else {
            Toast.makeText(this, "NO EXISTE NINGUN TURNO ABIERTO...", Toast.LENGTH_SHORT).show()
        }
    }

    fun buzonVale() {
        if (turnoAbierto != SIN_TURNO) {
            Toast.makeText(this, "INICIANDO BUZON/VALE...", Toast.LENGTH_SHORT).show()
            val i = Intent(this, DescuentoActivity::class.java)
            startActivity(i)
        } else {
            Toast.makeText(this, "NO EXISTE NINGUN TURNO ABIERTO...", Toast.LENGTH_SHORT).show()
        }
    }

    fun cerrarTurno() {
        if (turnoAbierto != SIN_TURNO) {
            finish()
            Toast.makeText(this, "INICIANDO CERRAR TURNO...", Toast.LENGTH_SHORT).show()
            val i = Intent(this, CerrarTurnoActivity::class.java)
            startActivity(i)
        } else {
            Toast.makeText(this, "NO EXISTE NINGUN TURNO ABIERTO...", Toast.LENGTH_SHORT).show()
        }
    }

    fun consultarTurno(){
        finish()
        Toast.makeText(this, "INICIANDO CONSULTAR TURNO...", Toast.LENGTH_SHORT).show()
        val i = Intent(this, ConsultarTurnoActivity::class.java)
        startActivity(i)
    }

}
