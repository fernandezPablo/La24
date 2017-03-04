package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class LineaVenta {

    private double cantidad;
    private double subtotal;
    EspecificacionProducto producto;

    public LineaVenta() {
    }

    public LineaVenta(double cantidad, EspecificacionProducto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.calcularSubtotal();
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void calcularSubtotal() {
        this.subtotal = producto.getPrecio() * this.cantidad;
    }

    public EspecificacionProducto getProducto() {
        return producto;
    }

    public void setProducto(EspecificacionProducto producto) {
        this.producto = producto;
    }
}
