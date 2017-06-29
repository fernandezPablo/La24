package com.fernandez.pablo.la24gnc.Presenter;

import android.content.Context;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.Service.DbHelper;
import com.fernandez.pablo.la24gnc.View.Venta.VentaV2Activity;

import java.util.ArrayList;

/**
 * Created by pablo on 11/05/2017.
 */

public class VentaPresenter {

    private Turno turno;

    public VentaV2Activity getActivity() {
        return activity;
    }

    private VentaV2Activity activity;

    public VentaPresenter(VentaV2Activity activity) {
        this.activity = activity;
        TurnoDAO turnoDAO = new TurnoDAO(activity);
        this.turno = TurnoDAO.getTurno(activity,turnoDAO.getCodLastTurno());
    }

    public  ArrayList<EspecificacionProducto> getProdcutos(Context context){
        return new EspecificacionProductoDAO(DbHelper.getInstance(context)).listProductos();
    }

    public void agregarLineaVenta(EspecificacionProducto producto, Double cantidad){

        LineaVentaDAO lineaVentaDAO = new LineaVentaDAO(this.activity);

        int codLVRepetida = lineaVentaDAO.findLineaVenta(producto,turno.getVenta());

        if(codLVRepetida == -1){
            LineaVenta lineaVenta = new LineaVenta(cantidad,producto,turno.getVenta());
            lineaVentaDAO.createLineaVenta(lineaVenta);
        }
        else{
            lineaVentaDAO.incrementarLineaVenta(codLVRepetida,cantidad);
        }
    }

    public String getImagenProducto(int codigo){
        return new EspecificacionProductoDAO(DbHelper.getInstance(activity)).getImagenProducto(codigo);
    }

    /*public void cargarLineasVenta(){
        LineaVentaDAO lineaVentaDAO = new LineaVentaDAO(this.activity);
        activity.cargarListadoLineasVenta(lineaVentaDAO.getLineasVenta(this.turno.getVenta().getCodigo()));
    }*/

    public void inicializarCantidades(){
        ArrayList<LineaVenta> lineasVenta = new LineaVentaDAO(activity).
                getLineasVenta(this.turno.getVenta().getCodigo());
        for (LineaVenta lv:
             lineasVenta)
        {
            int position = 0;
            for (EspecificacionProducto prod:
                 this.activity.getListProductos())
            {
                if(prod.getCodigo() == lv.getProducto().getCodigo())
                {
                    this.activity.getListCantidades().set(position,lv.getCantidad());
                    break;
                }
                position++;
            }
        }
    }

}
