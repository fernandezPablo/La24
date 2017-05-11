package com.fernandez.pablo.la24gnc.Model;

import java.util.ArrayList;

/**
 * Created by pablo on 04/03/2017.
 */

public class Turno {

    private static final int CANT_AF_GNC = 6;
    private static final int CANT_AF_ACEITE = 1;

    private static final String TURNO_ABIERTO = "ABIERTO";
    private static final String TURNO_CERRADO = "CERRADO";

    private int codigo;
    private int nro;
    private double pmz;
    private String estado;
    private String fecha;
    private Venta venta;
    private ArrayList<Aforador> aforadores;

    public Turno() {
        this.estado = TURNO_ABIERTO;
        this.venta = null;
    }

    public Turno(int nro, String fecha, double pmz, String estado, Venta venta) {
        this.nro = nro;
        this.fecha = fecha;
        this.venta = venta;
        this.pmz = pmz;
        this.estado = estado;
        this.aforadores = new ArrayList<>();
        for(int i=0;i<CANT_AF_GNC;i++){
            this.aforadores.add(new Aforador(i,"m3",0,0,Aforador.GNC,this.codigo));
        }
        for(int i=0;i<CANT_AF_ACEITE;i++){
            this.aforadores.add(new Aforador(i,"lts",0,0,Aforador.ACEITE,this.codigo));
        }
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public double getPmz() {
        return pmz;
    }

    public void setPmz(double pmz) {
        this.pmz = pmz;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<Aforador> getAforadores() {
        return aforadores;
    }

    public void setAforadores(ArrayList<Aforador> aforadores) {
        this.aforadores = aforadores;
    }

    @Override
    public String toString() {
        return "Turno{" +
                "nro=" + nro +
                ", fecha=" + fecha +
                ", venta=" + venta +
                ", aforadores=" + aforadores +
                '}';
    }

    public void crearLineaVenta(EspecificacionProducto prod, double cantidad){
        this.venta.crearLineaVenta(prod,cantidad);
    }

    public void generarLvGnc(){
        for (int i=0;i<CANT_AF_GNC;i++){
            //TODO cambiar el producto que se pasa por GNC
            this.venta.crearLineaVenta(new EspecificacionProducto(),this.aforadores.get(i).getCantVendida());
        }
    }

    public void generarLvAceite(){
        for (int i=0;i<CANT_AF_ACEITE;i++){
            //TODO cambiar el producto que se pasa por ACEITE
            this.venta.crearLineaVenta(new EspecificacionProducto(),this.aforadores.get(i).getCantVendida());
        }
    }


}

