package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 19/04/2017.
 */

public class Aforador {

    public static final String ACEITE = "ACEITE";
    public static final String GNC = "GNC";

    private int numero;
    private String unidad;
    private double valorInicial;
    private double valorFinal;
    private String tipo;
    private int codigoTurno;

    public Aforador(int numero, String unidad, double valorInicial, double valorFinal, String tipo,int codigoTurno) {
        this.numero = numero;
        this.unidad = unidad;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.tipo = tipo;
        this.codigoTurno = codigoTurno;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(int codigoTurno) {
        this.codigoTurno = codigoTurno;
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
