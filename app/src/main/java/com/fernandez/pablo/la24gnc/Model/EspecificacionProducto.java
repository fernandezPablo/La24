package com.fernandez.pablo.la24gnc.Model;

/**
 * Created by pablo on 04/03/2017.
 */

public class EspecificacionProducto {

    private int codigo;
    private String descripcion;
    private double precio;
    private Stock stock;

    public EspecificacionProducto(){

    }

    public EspecificacionProducto(int codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public EspecificacionProducto(int codigo, String descripcion, double precio, Stock stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDiferenciaStock(){
        return this.stock.diferenciaStock();
    }

    @Override
    public String toString() {
        return "EspecificacionProducto{" +
                "codigo=" + codigo +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
