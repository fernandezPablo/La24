package com.fernandez.pablo.la24gnc.Model;

import java.util.ArrayList;

/**
 * Created by pablo on 04/03/2017.
 */

public class Venta {

    private double total;
    private ArrayList<LineaVenta> lineasVenta;
    private Gnc gnc;
    private Aceite aceite;
    private ArrayList<Descuento> descuentos;

    public Venta() {
        lineasVenta = new ArrayList<>();
        descuentos = new ArrayList<>();
        this.gnc = new Gnc();
        this.aceite = new Aceite();
    }


    public double getTotal(){
        for (LineaVenta lv : lineasVenta) {
            this.total += lv.getSubtotal();
        }
        this.total += this.gnc.totalDineroGnc();
        this.total += this.aceite.totalDineroAceite();

        return total;
    }

    public double getTotalConDescuento(){

        double total = this.getTotal();

        for (Descuento des: descuentos) {
            this.total -= des.getMonto();
        }
        return this.total;
    }


}
