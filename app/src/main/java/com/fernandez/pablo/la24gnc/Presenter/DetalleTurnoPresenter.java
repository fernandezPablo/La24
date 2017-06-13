package com.fernandez.pablo.la24gnc.Presenter;

import com.fernandez.pablo.la24gnc.Model.Aforador;
import com.fernandez.pablo.la24gnc.Model.AforadorDAO;
import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO;
import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.View.DetalleTurnoActivity;
import com.j256.ormlite.field.types.DoubleObjectType;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by pablo on 25/05/2017.
 */

public class DetalleTurnoPresenter {

    private Turno turno;
    private DetalleTurnoActivity activity;
    private double totalDineroGnc;
    private double totalDineroAceite;
    private double totalDineroVarios;

    public DetalleTurnoPresenter(DetalleTurnoActivity activity) {
        this.activity = activity;
        this.turno = TurnoDAO.getTurno(activity,new TurnoDAO(activity).getCodLastTurno());
        this.turno.setAforadores(new AforadorDAO(activity).listAforadores(turno.getCodigo(), Aforador.GNC));
        this.turno.getAforadores().add(new AforadorDAO(activity).listAforadores(turno.getCodigo(),Aforador.ACEITE).get(0));
        this.totalDineroAceite = 0;
        this.totalDineroGnc = 0;
        this.totalDineroVarios = 0;
    }

    public void cargarValoresDeGnc(){

        DecimalFormat df = new DecimalFormat("#.00");

        double [] valoresIniciales = new double[]
                {
                        this.turno.getAforadores().get(0).getValorInicial(),
                        this.turno.getAforadores().get(1).getValorInicial(),
                        this.turno.getAforadores().get(2).getValorInicial(),
                        this.turno.getAforadores().get(3).getValorInicial(),
                        this.turno.getAforadores().get(4).getValorInicial(),
                        this.turno.getAforadores().get(5).getValorInicial(),
                };

        double [] valoresFinales = new double[]
                {
                        this.turno.getAforadores().get(0).getValorFinal(),
                        this.turno.getAforadores().get(1).getValorFinal(),
                        this.turno.getAforadores().get(2).getValorFinal(),
                        this.turno.getAforadores().get(3).getValorFinal(),
                        this.turno.getAforadores().get(4).getValorFinal(),
                        this.turno.getAforadores().get(5).getValorFinal(),
                };


        String [] vIAforadores = new String[]
                {
                        df.format(valoresIniciales[0]),
                        df.format(valoresIniciales[1]),
                        df.format(valoresIniciales[2]),
                        df.format(valoresIniciales[3]),
                        df.format(valoresIniciales[4]),
                        df.format(valoresIniciales[5]),

                };
        this.activity.getDetalleGncFragment().cargarValoresIniciales(vIAforadores);

        String [] vFAforadores = new String[]
                {
                    df.format(valoresFinales[0]),
                    df.format(valoresFinales[1]),
                    df.format(valoresFinales[2]),
                    df.format(valoresFinales[3]),
                    df.format(valoresFinales[4]),
                    df.format(valoresFinales[5]),
                };
        this.activity.getDetalleGncFragment().cargarValoresFinales(vFAforadores);

        double [] difs = new double[]
                {
                   valoresFinales[0] - valoresIniciales[0],
                   valoresFinales[1] - valoresIniciales[1],
                   valoresFinales[2] - valoresIniciales[2],
                   valoresFinales[3] - valoresIniciales[3],
                   valoresFinales[4] - valoresIniciales[4],
                   valoresFinales[5] - valoresIniciales[5],
                };

        String [] diferencias = new String[]
                {
                    df.format(difs[0]),
                    df.format(difs[1]),
                    df.format(difs[2]),
                    df.format(difs[3]),
                    df.format(difs[4]),
                    df.format(difs[5]),
                };
        this.activity.getDetalleGncFragment().cargarDiferencias(diferencias);

        this.activity.getDetalleGncFragment().cargarPmz(df.format(this.turno.getPmz()));

        double totalMts = difs[0] + difs[1] + difs[2] + difs[3] + difs[4] + difs[5];
        String totalM3 = df.format(totalMts);
        this.activity.getDetalleGncFragment().cargarTotalM3(totalM3);

        double precioGnc = EspecificacionProductoDAO.getProducto(activity,1).getPrecio();

        this.totalDineroGnc = precioGnc * totalMts;
        String totalDinero = df.format(this.totalDineroGnc);

        this.activity.getDetalleGncFragment().cargarTotalDinero(totalDinero);
    }

    public void cargarValoresDeAceite(){

        DecimalFormat df = new DecimalFormat("#.00");

        double valorInicial = this.turno.getAforadores().get(6).getValorInicial();
        double valorFinal = this.turno.getAforadores().get(6).getValorFinal();

        String valorIString = df.format(valorInicial);
        this.activity.getDetalleAceiteFragment().cargarValorInicialAceite(valorIString);

        String valorFString = df.format(valorFinal);
        this.activity.getDetalleAceiteFragment().cargarValorFinalAceite(valorFString);

        double diferencia = valorFinal - valorInicial;
        String diferenciaString = df.format(diferencia);
        this.activity.getDetalleAceiteFragment().cargarDiferencia(diferenciaString);
        this.activity.getDetalleAceiteFragment().cargarTotalLts(diferenciaString);

        double precioAceite = EspecificacionProductoDAO.getProducto(activity,2).getPrecio();
        this.totalDineroAceite = diferencia * precioAceite;
        String totalAceite = df.format(this.totalDineroAceite);
        this.activity.getDetalleAceiteFragment().cargarTotalDinero(totalAceite);
    }

    public void cargarValoresProductosVarios(){

        LineaVentaDAO lvDao = new LineaVentaDAO(activity);
        ArrayList<LineaVenta> lineasVenta = lvDao.getLineasVenta(this.turno.getVenta().getCodigo());
        ArrayList<LineaVenta> lineasVentaVarios = new ArrayList<>();

        for (LineaVenta lv:
             lineasVenta) {
            if(lv.getProducto().getCodigo() != 1 && lv.getProducto().getCodigo() != 2){
                lineasVentaVarios.add(lv);
            }
        }

        activity.getDetalleVariosFragment().cargarListLineasVenta(lineasVentaVarios);

        double total = 0;

        for (LineaVenta lv:
             lineasVentaVarios) {
            total += (lv.getCantidad() * lv.getProducto().getPrecio());
        }

        DecimalFormat df = new DecimalFormat("#.00");
        this.totalDineroVarios = total;
        activity.getDetalleVariosFragment().cargarTotalDineroVarios(df.format(this.totalDineroVarios));
    }

    public void cargarTotales(){

        DecimalFormat df = new DecimalFormat("#.00");

        double totalVentas = this.totalDineroGnc + this.totalDineroAceite + this.totalDineroVarios;

        activity.getDetalleGeneralFragment().cargarTotales(df.format(this.totalDineroGnc),
                df.format(this.totalDineroAceite),
                df.format(this.totalDineroVarios),
                df.format(totalVentas));

    }

    public void cargarValoresADeclarar(){
        DescuentoDAO descuentoDAO = new DescuentoDAO(activity);
        ArrayList<Descuento> descuentos = descuentoDAO.getDescuentos(this.turno.getVenta().getCodigo());

        activity.getDetalleADeclararFragment().cargarListaDescuentos(descuentos);
    }

}
