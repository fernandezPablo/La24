package com.fernandez.pablo.la24gnc.View.EditarTurno

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.fernandez.pablo.la24gnc.Model.Aforador
import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.Presenter.EditarAforadoresGncPresenter
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.DetalleTurno.DetalleTurnoActivity
import kotlinx.android.synthetic.main.alert_dialog_detalle_producto.*

class EditarAforadoresGncActivity : AppCompatActivity(), IEditarAforadoresGnc {

    var gncPresenter: EditarAforadoresGncPresenter? = null
    var etAforadoresIniciales: ArrayList<EditText>? = null
    var etAforadoresFinales: ArrayList<EditText>? = null
    var etPmz: EditText? = null
    var cancelarButton: Button? = null
    var confirmarButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_aforadores)

        val codTurno = this.intent.getIntExtra("codigoTurno",-1)

        this.etAforadoresIniciales = ArrayList()
        this.etAforadoresFinales = ArrayList()

        //Bind edit text de aforadores iniciales
        this.etAforadoresIniciales!!.add(findViewById(R.id.etAforadorInicial1) as EditText)
        this.etAforadoresIniciales!!.add(findViewById(R.id.etAforadorInicial2) as EditText)
        this.etAforadoresIniciales!!.add(findViewById(R.id.etAforadorInicial3) as EditText)
        this.etAforadoresIniciales!!.add(findViewById(R.id.etAforadorInicial4) as EditText)
        this.etAforadoresIniciales!!.add(findViewById(R.id.etAforadorInicial5) as EditText)
        this.etAforadoresIniciales!!.add(findViewById(R.id.etAforadorInicial6) as EditText)

        //Bind edit text de aforadores finales
        this.etAforadoresFinales!!.add(findViewById(R.id.etAforadorFinal1) as EditText)
        this.etAforadoresFinales!!.add(findViewById(R.id.etAforadorFinal2) as EditText)
        this.etAforadoresFinales!!.add(findViewById(R.id.etAforadorFinal3) as EditText)
        this.etAforadoresFinales!!.add(findViewById(R.id.etAforadorFinal4) as EditText)
        this.etAforadoresFinales!!.add(findViewById(R.id.etAforadorFinal5) as EditText)
        this.etAforadoresFinales!!.add(findViewById(R.id.etAforadorFinal6) as EditText)

        this.etPmz = findViewById(R.id.etPmz) as EditText

        this.cancelarButton = findViewById(R.id.btnCancelar) as Button
        this.cancelarButton!!.setOnClickListener {
            Log.i("La24Info","Click boton CANCELAR")
            this.finish()
            val intent = Intent(this,DetalleTurnoActivity::class.java)
            intent.putExtra("codigoTurno",codTurno)
            startActivity(intent)
        }

        this.confirmarButton = findViewById(R.id.btnConfirmar) as Button
        this.confirmarButton!!.setOnClickListener {
            Log.i("La24Info","Click boton CONFIRMAR")
            this.gncPresenter!!.actualizarAforadores(codTurno)
            this.gncPresenter!!.actualizarPmz(codTurno)
            this.finish()
            val intent = Intent(this,DetalleTurnoActivity::class.java)
            intent.putExtra("codigoTurno",codTurno)
            startActivity(intent)
        }

        this.gncPresenter = EditarAforadoresGncPresenter(this)
        this.gncPresenter!!.obtenerAforadores(codTurno)
        this.gncPresenter!!.obtenerPmz(codTurno)
    }

    override fun cargarAforadores(aforadoresIniciales: ArrayList<String>, aforadoresFinales: ArrayList<String>) {
        for(index in 0..5){
            this.etAforadoresIniciales!![index].setText(aforadoresIniciales[index])
            this.etAforadoresFinales!![index].setText(aforadoresFinales[index])
        }
    }

    override fun cargarPmz(pmz: String) {
        this.etPmz!!.setText(pmz)
    }

    override fun obtenerValoresInicialesDeAforadores(): ArrayList<Double> {
        var valoresIniciales = ArrayList<Double>()

        this.etAforadoresIniciales!!.forEach {
            valoresIniciales.add(it.text.toString().toDouble())
        }

        return valoresIniciales
    }

    override fun obtenerValoresFinalesdeAforadores(): ArrayList<Double> {
        var valoresFinales = ArrayList<Double>()

        this.etAforadoresFinales!!.forEach {
            valoresFinales.add(it.text.toString().toDouble())
        }

        return valoresFinales
    }

    override fun obtenerPmzEditado(): Double {
        return etPmz!!.text.toString().toDouble()
    }
}
