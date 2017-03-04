package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class Descuento {

    private String descripcion;
    private double monto;

    public Descuento() {
    }

    public Descuento(String descripcion, double monto) {
        this.descripcion = descripcion;
        this.monto = monto;
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
                "descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                '}';
    }
}
