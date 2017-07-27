package com.fernandez.pablo.la24gnc.Presenter;

import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Model.Aforador;
import com.fernandez.pablo.la24gnc.Model.AforadorDAO;
import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO;
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.Model.VentaDAO;
import com.fernandez.pablo.la24gnc.View.CerrarTurno.CerrarTurnoActivity;

/**
 * Created by pablo on 18/05/2017.
 */

public class CerrarTurnoPresenter {

    private CerrarTurnoActivity activity;
    private Turno turno;

    public CerrarTurnoPresenter(CerrarTurnoActivity activity) {
        this.activity = activity;
        this.turno = TurnoDAO.getTurno(activity,new TurnoDAO(activity).getCodLastTurno());
        this.turno.setAforadores(new AforadorDAO(activity).listAforadores(turno.getCodigo(),Aforador.GNC));
        this.turno.getAforadores().add(new AforadorDAO(activity).listAforadores(turno.getCodigo(),Aforador.ACEITE).get(0));
    }

    public CerrarTurnoActivity getActivity() {
        return activity;
    }

    public Turno getTurno() {
        return turno;
    }

    public int cerrarTurno(){

        double [] valoresFinales = activity.getValoresFinales();
        AforadorDAO aforadorDAO = new AforadorDAO(this.activity);

        double cantidadAceite = 0;

        for (LineaVenta lv:
             this.turno.getVenta().getLineasVenta()) {
            if(lv.getProducto().getCodigo() == 2){
                cantidadAceite = lv.getCantidad();
            }
        }

        for (int i = 0 ; i < this.turno.getAforadores().size() ; i++){
            if (this.turno.getAforadores().get(i).getTipo().equals(Aforador.GNC)) {
                if (this.turno.getAforadores().get(i).getValorInicial() > valoresFinales[i]) {
                    return -1;
                } else {
                    this.turno.getAforadores().get(i).setValorFinal(valoresFinales[i]);
                }
            }
            else{
                this.turno.getAforadores().get(i).setValorFinal(this.turno.getAforadores().get(i).
                        getValorInicial() + cantidadAceite);

            }
        }

        LineaVentaDAO lvDAO = new LineaVentaDAO(activity);

        for (Aforador af :
                this.turno.getAforadores()) {
            aforadorDAO.setValorFinal(af);
            if(af.getTipo().equals(Aforador.GNC)){
                lvDAO.createLineaVenta(new LineaVenta(af.getCantVendida(),EspecificacionProductoDAO.
                        getProducto(activity,1),this.turno.getVenta()));
            }
        }

        this.turno.getVenta().setLineasVenta(lvDAO.getLineasVenta(this.turno.getVenta().getCodigo()));

        new VentaDAO(activity).setTotalVenta(this.turno.getVenta());

        this.turno.setPmz(activity.getPmz());

        DescuentoDAO descuentoDAO = new DescuentoDAO(activity);
        this.turno.getVenta().setDescuentos(descuentoDAO.getDescuentos(this.turno.getVenta().getCodigo()));

        descuentoDAO.createDescuento(new Descuento("Buzón",Descuento.BUZON,this.turno.getVenta().
                getTotalConDescuento(),this.turno.getVenta()));

        TurnoDAO.cerrarTurno(activity,this.turno);

        return 1;
    }

}
