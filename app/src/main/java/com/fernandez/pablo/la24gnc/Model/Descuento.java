package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class Descuento {

    public static final String BUZON = "BUZON";
    public static final String VALE = "VALE";

    private int codigo;
    private String descripcion;
    private String tipo;
    private double monto;
    private Venta venta;

    public Descuento() {
    }

    public Descuento(int codigo, String descripcion, String tipo, double monto, Venta venta) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.monto = monto;
        this.venta = venta;
    }

    public Descuento(String descripcion, String tipo, double monto, Venta venta) {
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.monto = monto;
        this.venta = venta;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "Descuento{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                '}';
    }
}
