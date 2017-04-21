package com.fernandez.pablo.la24gnc.Model;

import java.util.ArrayList;

/**
 * Created by pablo on 19/04/2017.
 */

public class Aceite extends EspecificacionProducto {

    private ArrayList<Aforador> aforadores;
    public static final String UNIDAD = "lts";
    public static final int CANT_AFORADORES = 1;

    public Aceite() {
        this.aforadores = new ArrayList<>();
        for (int i = 0 ; i < Aceite.CANT_AFORADORES ; i++) {
            this.aforadores.add(new Aforador(i,Aceite.UNIDAD,0,0));
        }
    }

    public Aceite(int codigo, String descripcion, double precio, int rubro, ArrayList<Aforador> aforadores) {
        super(codigo, descripcion, precio, rubro);
        this.aforadores = aforadores;
    }

    public ArrayList<Aforador> getAforadores() {
        return aforadores;
    }

    public void setAforadores(ArrayList<Aforador> aforadores) {
        this.aforadores = aforadores;
    }

    @Override
    public String toString() {
        return "Aceite{" +
                "aforadores=" + aforadores +
                '}';
    }

    public double ltsVendidos(){
        double totalLts = 0;
        for (Aforador aforador : aforadores) {
            totalLts += aforador.getCantVendida();
        }
        return totalLts;
    }

    public double totalDineroAceite(){
        return super.getPrecio() * this.ltsVendidos();
    }

    public double ltsVendidosAforadorN(int n){
        return this.aforadores.get(n).getCantVendida();
    }

    public double dineroAforadorN(int n){
        return this.ltsVendidosAforadorN(n) * super.getPrecio();
    }

}
