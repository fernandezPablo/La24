package com.fernandez.pablo.la24gnc.View.AbrirTurno

import android.content.Intent
import android.os.AsyncTask
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.Presenter.AbrirTurnoPresenter
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Main.MainV2Activity
import com.fernandez.pablo.la24gnc.View.Utils.ViewPagerAdapter

class AbrirTurnoActivity : AppCompatActivity() {

    var toolbar: Toolbar? = null
    var tabLayout: TabLayout? = null
    var viewPager: ViewPager? = null
    val viewPageAdapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
    var gncFragment: GncFragment = GncFragment()
    var aceiteFragment: AceiteFragment = AceiteFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_abrir_turno)
        setSupportActionBar(toolbar)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        tabLayout = findViewById(R.id.tabLayout) as TabLayout
        viewPager = findViewById(R.id.viewPager) as ViewPager

        viewPageAdapter!!.addFragments(gncFragment, "GNC")
        viewPageAdapter!!.addFragments(aceiteFragment, "ACEITE")
        viewPager!!.adapter = viewPageAdapter
        tabLayout!!.setupWithViewPager(viewPager)

        val floatingButton = findViewById(R.id.saveFloatButton) as FloatingActionButton

        floatingButton.setOnClickListener {
            guardarTurno(it)
        }
    }

    fun guardarTurno(v: View) {

        object : AsyncTask<AbrirTurnoActivity, Void, Void>() {
            override fun doInBackground(vararg params: AbrirTurnoActivity): Void? {
                AbrirTurnoPresenter.guardarTurno(params[0])
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

}
