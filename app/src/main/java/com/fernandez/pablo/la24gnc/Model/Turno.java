package com.fernandez.pablo.la24gnc.Model;

import java.util.Date;

/**
 * Created by pablo on 04/03/2017.
 */

public class Turno {

    private int nro;
    private Date fecha;
    private Venta venta;

    public Turno() {
    }

    public Turno(int nro, Date fecha, Venta venta) {
        this.nro = nro;
        this.fecha = fecha;
        this.venta = venta;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "nro=" + nro +
                ", fecha=" + fecha +
                ", venta=" + venta +
                '}';
    }
}
