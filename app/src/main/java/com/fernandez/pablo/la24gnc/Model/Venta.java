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
        this.lineasVenta = new ArrayList<>();
        this.descuentos = new ArrayList<>();
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

    public double calcularTotal(){
        for (LineaVenta lv : lineasVenta) {
            this.total += lv.getSubtotal();
        }

        return total;
    }


    public double getTotalConDescuento(){

        double totalDescuento = this.total;

        for (Descuento des: descuentos) {
            totalDescuento -= des.getMonto();
        }
        return totalDescuento;
    }

    public void asignarLineasVenta(ArrayList<LineaVenta> lvs){
        for (LineaVenta lv:
             lvs) {
            this.lineasVenta.add(lv);
        }
    }

    public ArrayList<LineaVenta> getLineasVenta() {
        return lineasVenta;
    }

    public void setLineasVenta(ArrayList<LineaVenta> lineasVenta) {
        this.lineasVenta = lineasVenta;
    }

    public ArrayList<Descuento> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(ArrayList<Descuento> descuentos) {
        for (Descuento des:
             descuentos) {
            this.descuentos.add(des);
        }
    }

    public void crearLineaVenta(EspecificacionProducto prod, double cantidad) {
        LineaVenta lv = new LineaVenta(cantidad,prod,this);
        this.lineasVenta.add(lv);
    }
}
