package com.fernandez.pablo.la24gnc.View.AbrirTurno

import android.content.Intent
import android.os.AsyncTask
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import com.fernandez.pablo.la24gnc.Model.*

import com.fernandez.pablo.la24gnc.Presenter.AbrirTurnoPresenter
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Main.MainV2Activity
import com.fernandez.pablo.la24gnc.View.Utils.ViewPagerAdapter

class AbrirTurnoActivity : AppCompatActivity(), IAbrirTurno {

    var toolbar: Toolbar? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    val viewPageAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
    var gncFragment: GncFragment? = null
    var aceiteFragment: AceiteFragment? = null
    val presenter: AbrirTurnoPresenter = AbrirTurnoPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(intent.getBooleanExtra("turno_abierto",false)){
            this.cargarAforadoresGnc()
            this.cargarAforadorAceite()
        }
        else{
            this.gncFragment = GncFragment()
            this.aceiteFragment = AceiteFragment()
        }

        setContentView(R.layout.activity_abrir_turno)
        setSupportActionBar(toolbar)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        viewPager = findViewById(R.id.viewPager) as ViewPager

        viewPageAdapter!!.addFragments(gncFragment!!, "GNC")
        viewPageAdapter!!.addFragments(aceiteFragment!!, "ACEITE")
        viewPager!!.adapter = viewPageAdapter
        tabLayout!!.setupWithViewPager(viewPager)

        val floatingButton = findViewById(R.id.saveFloatButton) as FloatingActionButton

        floatingButton.setOnClickListener {
            guardarTurno()
        }

    }

    override fun guardarTurno() {

        object : AsyncTask<AbrirTurnoActivity, Void, Void>() {
            override fun doInBackground(vararg params: AbrirTurnoActivity): Void? {
                presenter.guardarTurno(params[0])
                return null
            }

            override fun onPostExecute(aVoid: Void?) {
                super.onPostExecute(aVoid)
                val intent = Intent(applicationContext, MainV2Activity::class.java)
                startActivity(intent)
                finish()
            }
        }.execute(this)

    }

    override fun cargarAforadoresGnc(){
        val turnoDao = TurnoDAO(this)
        val turno = TurnoDAO.getTurno(this,turnoDao.codLastTurno)
        var aforadores = AforadorDAO(this).listAforadores(turno!!.codigo, Aforador.GNC)
        var bundle = Bundle()

        for((index,af) in aforadores.withIndex()){
            bundle.putDouble("af$index",af.valorInicial)
        }
        this.gncFragment = GncFragment()
        this.gncFragment!!.setArguments(bundle)
    }

    override fun leerAforadoresGnc(): ArrayList<Double> {
        var aforadores: ArrayList<Double> = ArrayList()
        aforadores.add(this.gncFragment!!.valoresAforadores[0].toDouble())
        aforadores.add(this.gncFragment!!.valoresAforadores[1])
        aforadores.add(this.gncFragment!!.valoresAforadores[0])
        aforadores.add(this.gncFragment!!.valoresAforadores[3])
        aforadores.add(this.gncFragment!!.valoresAforadores[4])
        aforadores.add(this.gncFragment!!.valoresAforadores[5])
        return aforadores
    }

    override fun cargarAforadorAceite() {
        val turnoDao = TurnoDAO(this)
        val turno = TurnoDAO.getTurno(this,turnoDao.codLastTurno)
        var aforadores = AforadorDAO(this).listAforadores(turno!!.codigo, Aforador.ACEITE)
        var bundle = Bundle()

        bundle.putDouble("af_aceite",aforadores.last().valorInicial)

        this.aceiteFragment = AceiteFragment()
        this.aceiteFragment!!.setArguments(bundle)
    }

    override fun leerAforadorAceite(): Double = this.aceiteFragment!!.valoresAforadores[0]

}
