package com.fernandez.pablo.la24gnc.Presenter;

import com.fernandez.pablo.la24gnc.Model.Aforador;
import com.fernandez.pablo.la24gnc.Model.AforadorDAO;
import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.Model.DescuentoDAO;
import com.fernandez.pablo.la24gnc.Model.EspecificacionProductoDAO;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Model.LineaVentaDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.View.DetalleTurno.DetalleTurnoActivity;

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
        //this.turno = TurnoDAO.getTurno(activity,new TurnoDAO(activity).getCodLastTurno());
        this.turno = TurnoDAO.getTurno(activity,activity.getIntent().getIntExtra("codigoTurno",1));
        this.turno.setAforadores(new AforadorDAO(activity).listAforadores(turno.getCodigo(), Aforador.GNC));
        this.turno.getAforadores().add(new AforadorDAO(activity).listAforadores(turno.getCodigo(),Aforador.ACEITE).get(0));
        this.totalDineroAceite = 0;
        this.totalDineroGnc = 0;
        this.totalDineroVarios = 0;
    }

    public double [] getValoresInicialesGnc(){
        return new double[]
                {
                        this.turno.getAforadores().get(0).getValorInicial(),
                        this.turno.getAforadores().get(1).getValorInicial(),
                        this.turno.getAforadores().get(2).getValorInicial(),
                        this.turno.getAforadores().get(3).getValorInicial(),
                        this.turno.getAforadores().get(4).getValorInicial(),
                        this.turno.getAforadores().get(5).getValorInicial(),
                };
    }

    public double [] getValoresFinalesGnc(){

        return new double[]
                {
                        this.turno.getAforadores().get(0).getValorFinal(),
                        this.turno.getAforadores().get(1).getValorFinal(),
                        this.turno.getAforadores().get(2).getValorFinal(),
                        this.turno.getAforadores().get(3).getValorFinal(),
                        this.turno.getAforadores().get(4).getValorFinal(),
                        this.turno.getAforadores().get(5).getValorFinal(),
                };
    }

    public double [] getDiferneciaAforadores(){
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

        return new double[]
                {
                        valoresFinales[0] - valoresIniciales[0],
                        valoresFinales[1] - valoresIniciales[1],
                        valoresFinales[2] - valoresIniciales[2],
                        valoresFinales[3] - valoresIniciales[3],
                        valoresFinales[4] - valoresIniciales[4],
                        valoresFinales[5] - valoresIniciales[5],
                };
    }

    public String getPmz(){
     return Double.toString(this.turno.getPmz());
    }

    public double getTotalM3(double [] diferencias){
        double totalM3 = 0;
        for (double dif:
             diferencias) {
            totalM3 += dif;
        }
        return totalM3;
    }

    public double getTotalDineroGnc(double totalM3){
        double precioGnc = EspecificacionProductoDAO.getProducto(activity,1).getPrecio();
        return this.totalDineroGnc = precioGnc * totalM3;
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

    public double [] getValoresAceite(){
        double valorInicial = this.turno.getAforadores().get(6).getValorInicial();
        double valorFinal = this.turno.getAforadores().get(6).getValorFinal();

        return new double[]{valorInicial, valorFinal, valorFinal - valorInicial};
    }

    public double getTotalAceite(double diferencia){
        return this.totalDineroAceite = EspecificacionProductoDAO.getProducto(activity,2).
                getPrecio() * diferencia;
    }

    public ArrayList<LineaVenta> getLineasVentaVarios(){
        LineaVentaDAO lvDao = new LineaVentaDAO(activity);
        ArrayList<LineaVenta> lineasVenta = lvDao.getLineasVenta(this.turno.getVenta().getCodigo());
        ArrayList<LineaVenta> lineasVentaVarios = new ArrayList<>();

        for (LineaVenta lv:
                lineasVenta) {
            if(lv.getProducto().getCodigo() != 1 && lv.getProducto().getCodigo() != 2){
                lineasVentaVarios.add(lv);
            }
        }
        return lineasVentaVarios;
    }

    public double getTotalVentaVarios(ArrayList<LineaVenta> lineasVentaVarios){
        double total = 0;

        for (LineaVenta lv:
                lineasVentaVarios) {
            total += (lv.getCantidad() * lv.getProducto().getPrecio());
        }

        DecimalFormat df = new DecimalFormat("#.00");
        this.totalDineroVarios = total;
        return this.totalDineroVarios;
    }

    public Double[] getTotales(){

        double totalVentas = this.totalDineroGnc + this.totalDineroAceite + this.totalDineroVarios;

        return  new Double[]{this.totalDineroGnc,this.totalDineroAceite,this.totalDineroVarios,totalVentas};
    }

    public ArrayList<Descuento> getValoresADeclarar(){
        DescuentoDAO descuentoDAO = new DescuentoDAO(activity);
        return descuentoDAO.getDescuentos(this.turno.getVenta().getCodigo());
    }

}
