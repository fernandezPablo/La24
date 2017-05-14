package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class LineaVenta {

    private int codigo;
    private double cantidad;
    private double subtotal;
    private EspecificacionProducto producto;
    private Venta venta;

    public LineaVenta() {
    }

    public LineaVenta(int codigo, double cantidad, EspecificacionProducto producto,Venta venta) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.producto = producto;
        this.venta = venta;
        this.calcularSubtotal();
    }

    public LineaVenta(double cantidad, EspecificacionProducto producto,Venta venta) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.venta = venta;
        this.calcularSubtotal();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
