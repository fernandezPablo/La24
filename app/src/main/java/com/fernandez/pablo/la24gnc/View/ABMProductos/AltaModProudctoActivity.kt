package com.fernandez.pablo.la24gnc.View.ABMProductos

import android.content.Intent
import android.content.res.AssetManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.fernandez.pablo.la24gnc.Presenter.AltaModProductoPresenter

import com.fernandez.pablo.la24gnc.R

class AltaModProudctoActivity : AppCompatActivity() {

    val REQUEST_CODE_IMAGE : Int = 100

    lateinit var presenter : AltaModProductoPresenter
    lateinit var tietRubro : TextInputEditText
    lateinit var tietDescripcion : TextInputEditText
    lateinit var tietPrecio : TextInputEditText
    lateinit var ivProducto : ImageView
    lateinit var tietPathImagen : TextInputEditText
    lateinit var btnCargarImagen : Button
    lateinit var btnGuardar : Button
    var codigoProductoAEditar : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alta_mod_proudcto)

        this.presenter = AltaModProductoPresenter(this)

        this.tietRubro = findViewById(R.id.tietRubro) as TextInputEditText
        this.tietDescripcion = findViewById(R.id.tietDescripcion) as TextInputEditText
        this.tietPrecio = findViewById(R.id.tietPrecio) as TextInputEditText
        //this.ivProducto = findViewById(R.id.ivProducto) as ImageView
        this.tietPathImagen = findViewById(R.id.tietPathImagen) as TextInputEditText
        this.btnCargarImagen = findViewById(R.id.btnCargarImagen) as Button
        this.btnGuardar = findViewById(R.id.btnGuardar) as Button

        this.codigoProductoAEditar = intent.getIntExtra("codigo_producto",0)
        if(codigoProductoAEditar != 0){
            this.presenter.setProducto(intent.getIntExtra("codigo_producto",0))
            btnGuardar.setOnClickListener {
                view -> editarProducto()
            }
        }
        else{
            btnGuardar.setOnClickListener {
                view -> guardarProducto()
            }
        }

        btnCargarImagen.setOnClickListener {
           view -> cargarImagen()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK)
        {
            val path : Uri? = data?.data
            //this.ivProducto.setImageURI(path)
            this.tietPathImagen.setText(path.toString())
        }
        else{
           Toast.makeText(this,"NO SE RETORNO LA IMAGEN...",Toast.LENGTH_SHORT).show()
        }

    }

    fun cargarImagen() {
        val intent : Intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(Intent.createChooser(intent,"ELIJA UNA APP PARA VISUALIZAR IMAGENES"),REQUEST_CODE_IMAGE)
    }

    fun guardarProducto(){
        presenter.guardarProductos()
        val intent : Intent = Intent(this,ABMProductosActivity::class.java)
        finish()
        startActivity(intent)
    }

    fun editarProducto(){
        presenter.editarProducto(codigoProductoAEditar)
        val intent : Intent = Intent(this,ABMProductosActivity::class.java)
        finish()
        startActivity(intent)
    }



}
