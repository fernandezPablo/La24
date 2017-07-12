package com.fernandez.pablo.la24gnc.View.Descuentos

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import com.fernandez.pablo.la24gnc.Model.Descuento
import com.fernandez.pablo.la24gnc.Presenter.DescuentoPresenterV2

import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.Utils.DescuentoAdapter

class DescuentoV2Activity:AppCompatActivity() {

    //PRESENTER
    lateinit var descuentoPresenter : DescuentoPresenterV2

    //WIDGET VISTA
    lateinit var listViewDescuentos: ListView
    lateinit var btnBuzon: Button
    lateinit var btnVale: Button

    //RECURSOS PARA EL LISTVIEW
    lateinit var listDescuentos: ArrayList<Descuento>
    lateinit var descuentoAdapter: DescuentoAdapter

    //ALERTDIALOG
    lateinit var mBuilder: AlertDialog.Builder
    lateinit var mView: View
    lateinit var alertDialog : AlertDialog
    lateinit var tvDescuento : TextView
    lateinit var etDescripcion : EditText
    lateinit var etMonto : EditText
    lateinit var btnCancelar : Button
    lateinit var btnConfirmar : Button

    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descuento_v2)

        //INIT PRESENTER
        this.descuentoPresenter = DescuentoPresenterV2(activity = this)

        //INIT WIDGETS
        this.listViewDescuentos = findViewById(R.id.lvDescuentos) as ListView
        this.btnBuzon = findViewById(R.id.btnBuzon) as Button
        this.btnVale = findViewById(R.id.btnVale) as Button

        //INIT LISTVIEW
        this.listDescuentos = ArrayList()
        this.descuentoAdapter = DescuentoAdapter(this, listDescuentos)
        this.descuentoPresenter.getDescuentos()
        this.listViewDescuentos.adapter = descuentoAdapter

        //INIT ALERTDIALOG
        this.mBuilder = AlertDialog.Builder(this)
        this.mView = layoutInflater.inflate(R.layout.alert_dialog_descuento, null)
        this.tvDescuento = mView.findViewById(R.id.tvDescuento) as TextView
        this.etDescripcion = mView.findViewById(R.id.etDescripcion) as EditText
        this.etMonto = mView.findViewById(R.id.etMonto) as EditText
        this.btnCancelar = mView.findViewById(R.id.btnCancelar) as Button
        this.btnConfirmar = mView.findViewById(R.id.btnConfirmar) as Button
        this.mBuilder.setView(mView)
        this.alertDialog = mBuilder.create()

        //EVENTOS BOTONES
        this.btnBuzon.setOnClickListener {
            this.tvDescuento.text = Descuento.BUZON
            this.etDescripcion.setText("")
            this.etMonto.setText("")
            this.alertDialog.show()
        }
        this.btnVale.setOnClickListener {
            this.tvDescuento.text = Descuento.VALE
            this.etDescripcion.setText("")
            this.etMonto.setText("")
            this.alertDialog.show()
        }
        this.btnCancelar.setOnClickListener{
            this.alertDialog.hide()
        }
        this.btnConfirmar.setOnClickListener {
            this.descuentoPresenter.guardarDescuento()
            this.descuentoPresenter.getDescuentos()
            this.alertDialog.hide()
        }


    }

    fun cargarDescuentos(descuentos : ArrayList<Descuento>) : Unit {
        this.listDescuentos.clear()
        if(descuentos.size > 0){
            for (des in descuentos){
             this.listDescuentos.add(des)
            }
            this.descuentoAdapter.notifyDataSetChanged()
        }
    }

}