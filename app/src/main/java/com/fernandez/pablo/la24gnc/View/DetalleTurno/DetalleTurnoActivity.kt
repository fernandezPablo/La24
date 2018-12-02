package com.fernandez.pablo.la24gnc.View.DetalleTurno

import android.content.Intent
import android.os.AsyncTask
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.Model.LineaVenta
import com.fernandez.pablo.la24gnc.Presenter.DetalleTurnoPresenter
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Main.MainV2Activity
import com.fernandez.pablo.la24gnc.View.Utils.ViewPagerAdapter

import java.text.DecimalFormat
import java.util.ArrayList

class DetalleTurnoActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewpager: ViewPager? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null
    var detalleGncFragment: DetalleGncFragment? = null
        private set
    var detalleAceiteFragment: DetalleAceiteFragment? = null
        private set
    var detalleVariosFragment: DetalleVariosFragment? = null
        private set
    var detalleGeneralFragment: DetalleGeneralFragment? = null
        private set
    var detalleADeclararFragment: DetalleADeclararFragment? = null
        private set
    var detalleTurnoPresenter: DetalleTurnoPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_turno)
        this.toolbar = findViewById(R.id.toolbar) as Toolbar
        this.tabLayout = findViewById(R.id.tabLayout) as TabLayout
        this.viewpager = findViewById(R.id.viewPager) as ViewPager
        this.detalleGncFragment = DetalleGncFragment()
        this.detalleAceiteFragment = DetalleAceiteFragment()
        this.detalleVariosFragment = DetalleVariosFragment()
        this.detalleGeneralFragment = DetalleGeneralFragment()
        this.detalleADeclararFragment = DetalleADeclararFragment()
        this.viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        this.viewPagerAdapter!!.addFragments(this.detalleGncFragment!!, "GNC")
        this.viewPagerAdapter!!.addFragments(this.detalleAceiteFragment!!, "ACEITE")
        this.viewPagerAdapter!!.addFragments(this.detalleVariosFragment!!, "VARIOS")
        this.viewPagerAdapter!!.addFragments(this.detalleGeneralFragment!!, "TOTALES")
        this.viewPagerAdapter!!.addFragments(this.detalleADeclararFragment!!, "BUZON/VALE")
        this.viewpager!!.adapter = viewPagerAdapter
        this.tabLayout!!.setupWithViewPager(viewpager)
        this.detalleTurnoPresenter = DetalleTurnoPresenter(this)
    }

    fun cargarDetalleGnc() {
        /**
         * Thread Valores Iniciles Gnc
         */
        object : AsyncTask<Void, Void, DoubleArray>() {
            override fun doInBackground(vararg params: Void): DoubleArray {
                return detalleTurnoPresenter!!.valoresInicialesGnc
            }

            override fun onPostExecute(doubles: DoubleArray) {
                super.onPostExecute(doubles)
                detalleGncFragment!!.cargarValoresIniciales(getArrayStringFromArrayDouble(doubles))

                /**
                 * Thread Valores Finales Gnc
                 */
                object : AsyncTask<Void, Void, DoubleArray>() {
                    override fun doInBackground(vararg params: Void): DoubleArray {
                        return detalleTurnoPresenter!!.valoresFinalesGnc
                    }

                    override fun onPostExecute(doubles: DoubleArray) {
                        super.onPostExecute(doubles)
                        detalleGncFragment!!.cargarValoresFinales(getArrayStringFromArrayDouble(doubles))

                        /**
                         * Thread Diferencias Gnc
                         */
                        object : AsyncTask<Void, Void, DoubleArray>() {
                            override fun doInBackground(vararg params: Void): DoubleArray {
                                return detalleTurnoPresenter!!.diferneciaAforadores
                            }

                            override fun onPostExecute(doubles: DoubleArray) {
                                super.onPostExecute(doubles)
                                detalleGncFragment!!.cargarDiferencias(getArrayStringFromArrayDouble(doubles))

                                /**
                                 * Thread Total M3 GNC
                                 */
                                object : AsyncTask<DoubleArray, Void, Double>() {
                                    override fun doInBackground(vararg params: DoubleArray): Double? {
                                        return detalleTurnoPresenter!!.getTotalM3(params[0])
                                    }

                                    override fun onPostExecute(aDouble: Double?) {
                                        super.onPostExecute(aDouble)
                                        detalleGncFragment!!.cargarTotalM3(DecimalFormat("#.##").format(aDouble))
                                        /**
                                         * Thread Total Gnc
                                         */
                                        object : AsyncTask<Double, Void, Double>() {

                                            override fun doInBackground(vararg p0: Double?): Double? {
                                                return p0[0]?.let { detalleTurnoPresenter!!.getTotalDineroGnc(it) }
                                            }

                                            override fun onPostExecute(aDouble: Double?) {
                                                super.onPostExecute(aDouble)
                                                detalleGncFragment!!.cargarTotalDinero(
                                                        DecimalFormat("#.##").format(aDouble)
                                                )
                                                /**
                                                 * Thread Pmz Gnc
                                                 */
                                                object : AsyncTask<Void, Void, String>() {
                                                    override fun doInBackground(vararg params: Void): String {
                                                        return detalleTurnoPresenter!!.pmz
                                                    }

                                                    override fun onPostExecute(s: String) {
                                                        super.onPostExecute(s)
                                                        detalleGncFragment!!.cargarPmz(s)
                                                    }
                                                }.execute()
                                            }
                                        }.execute(aDouble)
                                    }
                                }.execute(doubles)
                            }
                        }.execute()
                    }

                }.execute()

            }
        }.execute()
    }

    fun cargarDetalleAceite() {
        object : AsyncTask<Void, Void, DoubleArray>() {
            override fun doInBackground(vararg params: Void): DoubleArray {
                return detalleTurnoPresenter!!.valoresAceite
            }

            override fun onPostExecute(doubles: DoubleArray) {
                super.onPostExecute(doubles)
                val df = DecimalFormat("#.##")
                detalleAceiteFragment!!.cargarValorInicialAceite(df.format(doubles[0]))
                detalleAceiteFragment!!.cargarValorFinalAceite(df.format(doubles[1]))
                detalleAceiteFragment!!.cargarDiferencia(df.format(doubles[2]))
                detalleAceiteFragment!!.cargarTotalLts(df.format(doubles[2]))

                object : AsyncTask<Double, Void, Double>() {

                    override fun doInBackground(vararg p0: Double?): Double? {
                        return p0[0]?.let { detalleTurnoPresenter!!.getTotalAceite(it) }
                    }

                    override fun onPostExecute(aDouble: Double?) {
                        super.onPostExecute(aDouble)
                        val df = DecimalFormat("#.##")
                        detalleAceiteFragment!!.cargarTotalDinero(df.format(aDouble))
                    }
                }.execute(doubles[2])

            }
        }.execute()
    }

    fun cargarDetalleVarios() {
        object : AsyncTask<Void, Void, ArrayList<LineaVenta>>() {
            override fun doInBackground(vararg params: Void): ArrayList<LineaVenta> {
                return detalleTurnoPresenter!!.lineasVentaVarios
            }

            override fun onPostExecute(lineasVenta: ArrayList<LineaVenta>) {
                super.onPostExecute(lineasVenta)
                detalleVariosFragment!!.cargarListLineasVenta(lineasVenta)
                object : AsyncTask<Void, Void, Double>() {
                    override fun doInBackground(vararg params: Void): Double? {
                        return detalleTurnoPresenter!!.getTotalVentaVarios(lineasVenta)
                    }

                    override fun onPostExecute(aDouble: Double?) {
                        super.onPostExecute(aDouble)
                        detalleVariosFragment!!.cargarTotalDineroVarios(java.lang.Double.toString(aDouble!!))
                    }
                }.execute()
            }
        }.execute()
    }

    fun cargarDetalleTotales() {
        object : AsyncTask<Void, Void, Array<Double>>() {
            override fun doInBackground(vararg params: Void): Array<Double> {
                return detalleTurnoPresenter!!.totales
            }

            override fun onPostExecute(doubles: Array<Double>) {
                super.onPostExecute(doubles)
                val df = DecimalFormat("#.##")
                detalleGeneralFragment!!.cargarTotales(df.format(doubles[0]), df.format(doubles[1]),
                        df.format(doubles[2]), df.format(doubles[3]))
            }
        }.execute()
    }

    fun cargarDetalleADeclarar() {
        object : AsyncTask<Void, Void, ArrayList<Descuento>>() {

            override fun doInBackground(vararg params: Void): ArrayList<Descuento> {
                return detalleTurnoPresenter!!.valoresADeclarar
            }

            override fun onPostExecute(descuentos: ArrayList<Descuento>) {
                super.onPostExecute(descuentos)
                detalleADeclararFragment!!.cargarListaDescuentos(descuentos)
            }
        }.execute()
    }

    fun goHOme() {
        finish()
        val intent = Intent(this, MainV2Activity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        goHOme()
    }

    fun getArrayStringFromArrayDouble(doubles: DoubleArray): Array<String> {
        val df = DecimalFormat("#.##")
        val strings = Array<String>(doubles.size,{i -> df.format(doubles[i])})
        return strings
    }


}
