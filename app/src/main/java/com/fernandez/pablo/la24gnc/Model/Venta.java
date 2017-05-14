package com.fernandez.pablo.la24gnc.Model;

import java.util.ArrayList;

/**
 * Created by pablo on 04/03/2017.
 */

public class Venta {

    private int codigo;
    private double total;
    private ArrayList<LineaVenta> lineasVenta;
    private ArrayList<Descuento> descuentos;

    public Venta(int codigo) {
        this.codigo = codigo;
        this.total = 0.0;
    }

    public Venta() {
        this.total = 0.0;
        this.lineasVenta = new ArrayList<>();
        this.descuentos = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setTotal(double total){
        this.total = total;
    }

    public double getTotal(){
        for (LineaVenta lv : lineasVenta) {
            this.total += lv.getSubtotal();
        }

        return total;
    }


    public double getTotalConDescuento(){

        double total = this.getTotal();

        for (Descuento des: descuentos) {
            this.total -= des.getMonto();
        }
        return this.total;
    }


    public void crearLineaVenta(EspecificacionProducto prod, double cantidad) {
        LineaVenta lv = new LineaVenta(cantidad,prod,this);
        this.lineasVenta.add(lv);
    }
}
