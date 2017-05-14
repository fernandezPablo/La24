package com.fernandez.pablo.la24gnc.Presenter;

import android.content.Context;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.Model.Venta;
import com.fernandez.pablo.la24gnc.Model.VentaDAO;
import com.fernandez.pablo.la24gnc.Service.DbHelper;
import com.fernandez.pablo.la24gnc.View.VentaActivity;

import java.util.ArrayList;

/**
 * Created by pablo on 11/05/2017.
 */

public class VentaPresenter {

    private Turno turno;
    private VentaActivity activity;

    public VentaPresenter(VentaActivity activity) {
        this.activity = activity;
        TurnoDAO turnoDAO = new TurnoDAO(activity);
        this.turno = TurnoDAO.getTurno(activity,turnoDAO.getCodLastTurno());
    }

    public static ArrayList<EspecificacionProducto> getProdcutos(Context context){
        return new EspecificacionProductoDAO(DbHelper.getInstance(context)).listProductos();
    }

    public void agregarLineaVenta(){
        EspecificacionProducto producto = activity.getProductoSeleccionado();

        LineaVentaDAO lineaVentaDAO = new LineaVentaDAO(this.activity);

        int codLVRepetida = lineaVentaDAO.findLineaVenta(producto,turno.getVenta());

        if(codLVRepetida == -1){
            LineaVenta lineaVenta = new LineaVenta(activity.getCantidadLineaVenta(),producto,turno.getVenta());
            lineaVentaDAO.createLineaVenta(lineaVenta);
        }
        else{
            lineaVentaDAO.incrementarLineaVenta(codLVRepetida,activity.getCantidadLineaVenta());
        }
    }

    public void cargarLineasVenta(){
        LineaVentaDAO lineaVentaDAO = new LineaVentaDAO(this.activity);
        activity.cargarListadoLineasVenta(lineaVentaDAO.getLineasVenta(this.turno.getVenta().getCodigo()));
    }

}
