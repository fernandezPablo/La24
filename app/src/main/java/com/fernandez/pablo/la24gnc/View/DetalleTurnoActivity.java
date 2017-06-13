package com.fernandez.pablo.la24gnc.View;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.fernandez.pablo.la24gnc.Presenter.DetalleTurnoPresenter;
import com.fernandez.pablo.la24gnc.R;

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
        this.detalleTurnoPresenter.cargarValoresDeGnc();
    }

    public void cargarDetalleAceite(){
        this.detalleTurnoPresenter.cargarValoresDeAceite();
    }

    public void cargarDetalleVarios(){
        this.detalleTurnoPresenter.cargarValoresProductosVarios();
    }

    public void cargarDetalleTotales(){ this.detalleTurnoPresenter.cargarTotales();}

    public void cargarDetalleADeclarar(){ this.detalleTurnoPresenter.cargarValoresADeclarar();}

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

}
