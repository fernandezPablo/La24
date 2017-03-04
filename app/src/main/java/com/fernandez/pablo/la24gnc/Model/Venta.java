package com.fernandez.pablo.la24gnc.Model;

import java.util.ArrayList;

/**
 * Created by pablo on 04/03/2017.
 */

public class Venta {

    private double total;
    private ArrayList<LineaVenta> lineasVenta;
    private ArrayList<Descuento> descuentos;

    public Venta() {
        lineasVenta = new ArrayList<>();
        descuentos = new ArrayList<>();
    }

    public double getTotal(){
        for (LineaVenta lv : lineasVenta) {
            this.total += lv.getSubtotal();
        }
        for (Descuento des: descuentos) {
            this.total -= des.getMonto();
        }
        return this.total;
    }
}
