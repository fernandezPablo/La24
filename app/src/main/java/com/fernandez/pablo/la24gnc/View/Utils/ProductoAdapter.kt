package com.fernandez.pablo.la24gnc.View.Utils

import android.app.Activity
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Path
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto
import com.fernandez.pablo.la24gnc.R
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.util.jar.Manifest

/**
 * Created by pablo on 13/07/2017.
 */
class ProductoAdapter : BaseAdapter{

    var listProductos : ArrayList<EspecificacionProducto>
    var context : Context

    constructor(listProductos: ArrayList<EspecificacionProducto>, context : Context) : super() {
        this.listProductos = listProductos
        this.context = context
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        var view : View? = convertView

        if(view == null){
            var layoutInflater : LayoutInflater =
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = layoutInflater.inflate(R.layout.item_producto_list,parent,false)
        }

        var ivProducto : ImageView? = view?.findViewById(R.id.imageProducto) as ImageView?
        var tvDescripcionProducto : TextView? = view?.findViewById(R.id.tvNombreProducto) as TextView?
        var tvPrecioProducto : TextView? = view?.findViewById(R.id.tvPrecio) as TextView?

        tvDescripcionProducto?.setText(this.listProductos[position].descripcion)
        tvPrecioProducto?.setText(this.listProductos[position].precio.toString())

        var assetManager: AssetManager = this.context.assets
        var inputStream: InputStream
        var bitMap: Bitmap
        try {
            inputStream = assetManager.open(this.listProductos[position].urlImagen)
            bitMap = BitmapFactory.decodeStream(inputStream)
            ivProducto?.setImageBitmap(bitMap)
        }
        catch (ex: FileNotFoundException){
            try {
                Log.e("Exception","Archivo no encontrado")
                inputStream = File(this.listProductos[position].urlImagen).inputStream()
                ivProducto?.setImageBitmap(BitmapFactory.decodeFile(this.listProductos[position].urlImagen))
            }
            catch (ex: FileNotFoundException){
                inputStream = assetManager.open("img/no_image.jpg")
                bitMap = BitmapFactory.decodeStream(inputStream)
                ivProducto?.setImageBitmap(bitMap)
            }
        }

0
        return view
    }

    override fun getItem(position: Int): Any {
        return listProductos[position]
    }

    override fun getItemId(position: Int): Long {
        return listProductos[position].codigo.toLong()
    }

    override fun getCount(): Int {
        return listProductos.size
    }

}