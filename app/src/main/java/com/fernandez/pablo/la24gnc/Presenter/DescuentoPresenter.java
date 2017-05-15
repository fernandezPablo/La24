package com.fernandez.pablo.la24gnc.Presenter;

import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.View.DescuentoActivity;

import java.util.ArrayList;

/**
 * Created by pablo on 13/05/2017.
 */

public class DescuentoPresenter {

    private Turno turno;
    private DescuentoActivity activity;

    public DescuentoPresenter(DescuentoActivity activity) {
        this.activity = activity;
        this.turno = TurnoDAO.getTurno(this.activity,new TurnoDAO(activity).getCodLastTurno());

    }

    public void guardarDescuento(){
        DescuentoDAO descuentoDAO = new DescuentoDAO(this.activity);
        descuentoDAO.createDescuento(new Descuento(activity.getDescripcion(),activity.getTipo(),activity.getMonto(),this.turno.getVenta()));
    }

    public void getDescuentos(){
        DescuentoDAO descuentoDAO = new DescuentoDAO(activity);

        activity.cargarDescuentos(descuentoDAO.getDescuentos(this.turno.getVenta().getCodigo()));
    }

}
