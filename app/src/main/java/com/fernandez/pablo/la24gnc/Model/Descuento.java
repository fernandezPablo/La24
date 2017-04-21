package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class Descuento {

    private int codigo;
    private String descripcion;
    private double monto;

    public Descuento() {
    }

    public Descuento(int codigo, String descripcion, double monto) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.monto = monto;
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

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
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
