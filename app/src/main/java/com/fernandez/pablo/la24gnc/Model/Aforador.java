package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 19/04/2017.
 */

public class Aforador {

    private int numero;
    private String unidad;
    private double valorInicial;
    private double valorFinal;

    public Aforador(int numero, String unidad, double valorInicial, double valorFinal) {
        this.numero = numero;
        this.unidad = unidad;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    @Override
    public String toString() {
        return "Aforador{" +
                "numero=" + numero +
                ", unidad='" + unidad + '\'' +
                ", valorInicial=" + valorInicial +
                ", valorFinal=" + valorFinal +
                '}';
    }

    public double getCantVendida(){
        return this.valorFinal - this.valorInicial;
    }

}
