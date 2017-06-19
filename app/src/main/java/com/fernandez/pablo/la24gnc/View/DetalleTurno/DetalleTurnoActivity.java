package com.fernandez.pablo.la24gnc.View.DetalleTurno;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.fernandez.pablo.la24gnc.Model.Descuento;
import com.fernandez.pablo.la24gnc.Model.LineaVenta;
import com.fernandez.pablo.la24gnc.Presenter.DetalleTurnoPresenter;
import com.fernandez.pablo.la24gnc.R;
import com.fernandez.pablo.la24gnc.View.Main.MainV2Activity;
import com.fernandez.pablo.la24gnc.View.Utils.ViewPagerAdapter;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class DetalleTurnoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private ViewPagerAdapter viewPagerAdapter;
    private DetalleGncFragment detalleGncFragment;
    private DetalleAceiteFragment detalleAceiteFragment;
    private DetalleVariosFragment detalleVariosFragment;
    private DetalleGeneralFragment detalleGeneralFragment;
    private DetalleADeclararFragment detalleADeclararFragment;
    private DetalleTurnoPresenter detalleTurnoPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_turno);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        this.viewpager = (ViewPager) findViewById(R.id.viewPager);
        this.detalleGncFragment = new DetalleGncFragment();
        this.detalleAceiteFragment = new DetalleAceiteFragment();
        this.detalleVariosFragment = new DetalleVariosFragment();
        this.detalleGeneralFragment = new DetalleGeneralFragment();
        this.detalleADeclararFragment = new DetalleADeclararFragment();
        this.viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        this.viewPagerAdapter.addFragments(this.detalleGncFragment,"GNC");
        this.viewPagerAdapter.addFragments(this.detalleAceiteFragment,"ACEITE");
        this.viewPagerAdapter.addFragments(this.detalleVariosFragment,"VARIOS");
        this.viewPagerAdapter.addFragments(this.detalleGeneralFragment,"TOTALES");
        this.viewPagerAdapter.addFragments(this.detalleADeclararFragment,"BUZON/VALE");
        this.viewpager.setAdapter(viewPagerAdapter);
        this.tabLayout.setupWithViewPager(viewpager);
        this.detalleTurnoPresenter = new DetalleTurnoPresenter(this);
    }

    public void cargarDetalleGnc(){
        /**
         * Thread Valores Iniciles Gnc
         * */
        new AsyncTask<Void,Void,double[]>(){
            @Override
            protected double [] doInBackground(Void... params) {
                return detalleTurnoPresenter.getValoresInicialesGnc();
            }

            @Override
            protected void onPostExecute(double[] doubles) {
                super.onPostExecute(doubles);
                detalleGncFragment.cargarValoresIniciales(getArrayStringFromArrayDouble(doubles));

                /**
                 * Thread Valores Finales Gnc
                 * */
                new AsyncTask<Void,Void,double[]>(){
                    @Override
                    protected double[] doInBackground(Void... params) {
                        return detalleTurnoPresenter.getValoresFinalesGnc();
                    }

                    @Override
                    protected void onPostExecute(double[] doubles) {
                        super.onPostExecute(doubles);
                        detalleGncFragment.cargarValoresFinales(getArrayStringFromArrayDouble(doubles));

                        /**
                         * Thread Diferencias Gnc
                         * */
                        new AsyncTask<Void,Void,double[]>(){
                            @Override
                            protected double[] doInBackground(Void... params) {
                                return detalleTurnoPresenter.getDiferneciaAforadores();
                            }

                            @Override
                            protected void onPostExecute(double[] doubles) {
                                super.onPostExecute(doubles);
                                detalleGncFragment.cargarDiferencias(getArrayStringFromArrayDouble(doubles));

                                /**
                                 * Thread Total M3 GNC
                                 * */
                                new AsyncTask<double[],Void,Double>(){
                                    @Override
                                    protected Double doInBackground(double[]... params) {
                                        return detalleTurnoPresenter.getTotalM3(params[0]);
                                    }

                                    @Override
                                    protected void onPostExecute(Double aDouble) {
                                        super.onPostExecute(aDouble);
                                        detalleGncFragment.cargarTotalM3(new DecimalFormat("#.##").
                                                format(aDouble));
                                        /**
                                         * Thread Total Gnc
                                         * */
                                        new AsyncTask<Double,Void,Double>(){
                                            @Override
                                            protected Double doInBackground(Double... params) {
                                                return detalleTurnoPresenter.getTotalDineroGnc(params[0]);
                                            }

                                            @Override
                                            protected void onPostExecute(Double aDouble) {
                                                super.onPostExecute(aDouble);
                                                detalleGncFragment.cargarTotalDinero(
                                                        new DecimalFormat("#.##").format(aDouble)
                                                );
                                                /**
                                                 * Thread Pmz Gnc
                                                 * */
                                                new AsyncTask<Void,Void,String>(){
                                                    @Override
                                                    protected String doInBackground(Void... params) {
                                                        return detalleTurnoPresenter.getPmz();
                                                    }

                                                    @Override
                                                    protected void onPostExecute(String s) {
                                                        super.onPostExecute(s);
                                                        detalleGncFragment.cargarPmz(s);
                                                    }
                                                }.execute();
                                            }
                                        }.execute(aDouble);
                                    }
                                }.execute(doubles);
                            }
                        }.execute();
                    }

                }.execute();

            }
        }.execute();
    }

    public void cargarDetalleAceite()
    {
        new AsyncTask<Void,Void,double[]>(){
            @Override
            protected double [] doInBackground(Void... params) {
                return detalleTurnoPresenter.getValoresAceite();
            }

            @Override
            protected void onPostExecute(double[] doubles) {
                super.onPostExecute(doubles);
                DecimalFormat df = new DecimalFormat("#.##");
                detalleAceiteFragment.cargarValorInicialAceite(df.format(doubles[0]));
                detalleAceiteFragment.cargarValorFinalAceite(df.format(doubles[1]));
                detalleAceiteFragment.cargarDiferencia(df.format(doubles[2]));
                detalleAceiteFragment.cargarTotalLts(df.format(doubles[2]));

                new AsyncTask<Double,Void,Double>(){
                    @Override
                    protected Double doInBackground(Double... params) {
                        return detalleTurnoPresenter.getTotalAceite(params[0]);
                    }

                    @Override
                    protected void onPostExecute(Double aDouble) {
                        super.onPostExecute(aDouble);
                        DecimalFormat df = new DecimalFormat("#.##");
                        detalleAceiteFragment.cargarTotalDinero(df.format(aDouble));
                    }
                }.execute(doubles[2]);

            }
        }.execute();
    }

    public void cargarDetalleVarios()
    {
        new AsyncTask<Void,Void,ArrayList<LineaVenta>>(){
            @Override
            protected ArrayList<LineaVenta> doInBackground(Void... params) {
                ArrayList<LineaVenta> lineasVenta = detalleTurnoPresenter.getLineasVentaVarios();
                return lineasVenta;
            }

            @Override
            protected void onPostExecute(final ArrayList<LineaVenta> lineasVenta) {
                super.onPostExecute(lineasVenta);
                detalleVariosFragment.cargarListLineasVenta(lineasVenta);
                new AsyncTask<Void,Void,Double>(){
                    @Override
                    protected Double doInBackground(Void... params) {
                        return detalleTurnoPresenter.getTotalVentaVarios(lineasVenta);
                    }

                    @Override
                    protected void onPostExecute(Double aDouble) {
                        super.onPostExecute(aDouble);
                        detalleVariosFragment.cargarTotalDineroVarios(Double.toString(aDouble));
                    }
                }.execute();
            }
        }.execute();
    }

    public void cargarDetalleTotales()
    {
        new AsyncTask<Void,Void,Double[]>(){
            @Override
            protected Double[] doInBackground(Void... params) {
                return detalleTurnoPresenter.getTotales();
            }

            @Override
            protected void onPostExecute(Double[] doubles) {
                super.onPostExecute(doubles);
                DecimalFormat df = new DecimalFormat("#.##");
                detalleGeneralFragment.cargarTotales(df.format(doubles[0]),df.format(doubles[1]),
                        df.format(doubles[2]),df.format(doubles[3]));
            }
        }.execute();
    }

    public void cargarDetalleADeclarar()
    {
        new AsyncTask<Void,Void,ArrayList<Descuento>>(){

            @Override
            protected ArrayList<Descuento> doInBackground(Void... params) {
                return detalleTurnoPresenter.getValoresADeclarar();
            }

            @Override
            protected void onPostExecute(ArrayList<Descuento> descuentos) {
                super.onPostExecute(descuentos);
                detalleADeclararFragment.cargarListaDescuentos(descuentos);
            }
        }.execute();
    }

    public DetalleGncFragment getDetalleGncFragment() {
        return detalleGncFragment;
    }

    public DetalleAceiteFragment getDetalleAceiteFragment() {
        return detalleAceiteFragment;
    }

    public DetalleVariosFragment getDetalleVariosFragment() {
        return detalleVariosFragment;
    }

    public DetalleGeneralFragment getDetalleGeneralFragment() {
        return detalleGeneralFragment;
    }

    public DetalleADeclararFragment getDetalleADeclararFragment() {
        return detalleADeclararFragment;
    }

    public void goHOme(){
        finish();
        Intent intent = new Intent(this,MainV2Activity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        goHOme();
    }

    public String [] getArrayStringFromArrayDouble(double [] doubles){
        DecimalFormat df = new DecimalFormat("#.##");
        String [] strings = new String[doubles.length];
        for (int i = 0; i < doubles.length ; i++ ){
            strings[i] = df.format(doubles[i]);
        }
        return strings;
    }


}
