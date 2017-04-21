package com.fernandez.pablo.la24gnc.Model;

import java.util.ArrayList;

/**
 * Created by pablo on 19/04/2017.
 */

public class Gnc extends EspecificacionProducto {

    private double pmz;
    private ArrayList<Aforador> aforadores;
    public static final String UNIDAD = "mts3";
    public static final int CANT_AFORADORES = 6;

    public Gnc() {
        this.pmz = 0;
        this.aforadores = new ArrayList<>();
        for(int i = 0; i < Gnc.CANT_AFORADORES ; i++){
            this.aforadores.add(new Aforador(i,Gnc.UNIDAD,0,0));
        }
    }

    public Gnc(int codigo, String descripcion, double precio, int rubro, double pmz, ArrayList<Aforador> aforadores) {
        super(codigo, descripcion, precio, rubro);
        this.pmz = pmz;
        this.aforadores = aforadores;
    }

    public double getPmz() {
        return pmz;
    }

    public void setPmz(double pmz) {
        this.pmz = pmz;
    }

    public ArrayList<Aforador> getAforadores() {
        return aforadores;
    }

    public void setAforadores(ArrayList<Aforador> aforadores) {
        this.aforadores = aforadores;
    }

    @Override
    public String toString() {
        return "Gnc{" +
                "pmz=" + pmz +
                ", aforadores=" + aforadores +
                '}';
    }

    public double mts3Vendidos(){
        double totalm3 = 0;
        for (Aforador aforador : aforadores) {
            totalm3 += aforador.getCantVendida();
        }
        return totalm3;
    }

    public double totalDineroGnc(){
        return super.getPrecio() * this.mts3Vendidos();
    }

    public double mts3VendidosAforadorN(int n){
        return this.aforadores.get(n).getCantVendida();
    }

    public double dineroSurtidorN(int n){
        return this.mts3VendidosAforadorN(n) * super.getPrecio();
    }

}
