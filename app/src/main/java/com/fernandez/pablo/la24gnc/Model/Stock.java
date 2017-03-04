package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class Stock {

    private double cantidadInicial;
    private double cantidadFinal;

    public Stock() {
    }

    public Stock(double cantidadInicial, double cantidadFinal) {
        this.cantidadInicial = cantidadInicial;
        this.cantidadFinal = cantidadFinal;
    }

    public double getCantidadInicial() {
        return cantidadInicial;
    }

    public void setCantidadInicial(double cantidadInicial) {
        this.cantidadInicial = cantidadInicial;
    }

    public double getCantidadFinal() {
        return cantidadFinal;
    }

    public void setCantidadFinal(double cantidadFinal) {
        this.cantidadFinal = cantidadFinal;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "cantidadInicial=" + cantidadInicial +
                ", cantidadFinal=" + cantidadFinal +
                '}';
    }

    public double diferenciaStock(){
        return this.cantidadFinal - this.cantidadInicial;
    }

}
