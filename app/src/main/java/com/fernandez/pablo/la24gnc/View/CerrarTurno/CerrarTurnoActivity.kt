package com.fernandez.pablo.la24gnc.View.CerrarTurno

import android.content.Intent
import android.os.AsyncTask
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

import com.fernandez.pablo.la24gnc.Presenter.CerrarTurnoPresenter
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.DetalleTurno.DetalleTurnoActivity

class CerrarTurnoActivity : AppCompatActivity() {

    private var tietPmz: TextInputEditText? = null
    private var tietSurtidor1: TextInputEditText? = null
    private var tietSurtidor2: TextInputEditText? = null
    private var tietSurtidor3: TextInputEditText? = null
    private var tietSurtidor4: TextInputEditText? = null
    private var tietSurtidor5: TextInputEditText? = null
    private var tietSurtidor6: TextInputEditText? = null

    private var presenter: CerrarTurnoPresenter? = null

    val pmz: Double
        get() = java.lang.Double.parseDouble(this.tietPmz!!.text.toString())

    val valoresFinales: DoubleArray
        get() {

            val s1 = this.tietSurtidor1!!.text
            val s2 = this.tietSurtidor2!!.text
            val s3 = this.tietSurtidor3!!.text
            val s4 = this.tietSurtidor4!!.text
            val s5 = this.tietSurtidor5!!.text
            val s6 = this.tietSurtidor6!!.text

            return doubleArrayOf(if (s1.length > 0) java.lang.Double.parseDouble(s1.toString()) else 0.0, if (s2.length > 0) java.lang.Double.parseDouble(s2.toString()) else 0.0, if (s3.length > 0) java.lang.Double.parseDouble(s3.toString()) else 0.0, if (s4.length > 0) java.lang.Double.parseDouble(s4.toString()) else 0.0, if (s5.length > 0) java.lang.Double.parseDouble(s5.toString()) else 0.0, if (s6.length > 0) java.lang.Double.parseDouble(s6.toString()) else 0.0)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cerrar_turno)

        this.tietPmz = findViewById(R.id.tietPmz) as TextInputEditText
        this.tietSurtidor1 = findViewById(R.id.tietSurtidor1) as TextInputEditText
        this.tietSurtidor2 = findViewById(R.id.tietSurtidor2) as TextInputEditText
        this.tietSurtidor3 = findViewById(R.id.tietSurtidor3) as TextInputEditText
        this.tietSurtidor4 = findViewById(R.id.tietSurtidor4) as TextInputEditText
        this.tietSurtidor5 = findViewById(R.id.tietSurtidor5) as TextInputEditText
        this.tietSurtidor6 = findViewById(R.id.tietSurtidor6) as TextInputEditText

        this.presenter = CerrarTurnoPresenter(this)
    }

    fun cerrarTurno(view: View) {
        val builder = AlertDialog.Builder(presenter!!.activity)
        builder.setMessage("ESTA SEGURO QUE DESEA CERRAR EL TURNO?")
        builder.setPositiveButton("OK") { dialog, which ->
            object : AsyncTask<Void, Void, Int>() {
                override fun doInBackground(vararg params: Void): Int? {
                    return presenter!!.cerrarTurno()
                }

                override fun onPostExecute(result: Int?) {
                    super.onPostExecute(result)
                    if (result!! > 0) {
                        presenter!!.activity.finish()
                        Toast.makeText(presenter!!.activity, "TURNO CERRADO CORRECTAMENTE...", Toast.LENGTH_SHORT)
                        val i = Intent(presenter!!.activity, DetalleTurnoActivity::class.java)
                        i.putExtra("codigoTurno", presenter!!.turno!!.codigo)
                        startActivity(i)
                    } else {
                        Toast.makeText(applicationContext,
                                "EL VALOR FINAL NO PUEDE SER MENOR QUE EL VALOR INICIAL", Toast.LENGTH_SHORT).show()
                    }
                }
            }.execute()
        }
        builder.setNegativeButton("CANCELAR") { dialog, which -> Toast.makeText(presenter!!.activity, "CONTINUANDO EL INGRESO DE AFORADORES...", Toast.LENGTH_SHORT) }
        val alertDialog = builder.create()
        alertDialog.show()
    }

}
