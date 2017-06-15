package com.fernandez.pablo.la24gnc.View.AbrirTurno;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.fernandez.pablo.la24gnc.Model.EspecificacionProducto;
import com.fernandez.pablo.la24gnc.Presenter.AbrirTurnoPresenter;
import com.fernandez.pablo.la24gnc.R;
import com.fernandez.pablo.la24gnc.View.Main.MainV2Activity;
import com.fernandez.pablo.la24gnc.View.Utils.ViewPagerAdapter;

public class AbrirTurnoActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPageAdapter;
    GncFragment gncFragment;
    AceiteFragment aceiteFragment;
    VariosFragment variosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abrir_turno);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPageAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        gncFragment = new GncFragment();
        aceiteFragment = new AceiteFragment();
        viewPageAdapter.addFragments(gncFragment,"GNC");
        viewPageAdapter.addFragments(aceiteFragment,"ACEITE");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void addProductoListView(View v){
        EspecificacionProducto productoSeleccionado = (EspecificacionProducto) variosFragment.getSpProductos().getSelectedItem();
        if (variosFragment.getProductosOnListView().size()> 0) {
            for (EspecificacionProducto prod :
                    variosFragment.getProductosOnListView()) {
                if (prod.equals(productoSeleccionado)) {
                    Toast.makeText(this, "EL PRODUCTO YA FUE AGREGADO AL LISTADO ANTERIORMENTE...", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        }
        variosFragment.getProductosOnListView().add(productoSeleccionado);
        variosFragment.getCantidades().add(Double.parseDouble(variosFragment.getTvCantidad().getText().toString()));
        variosFragment.getTvCantidad().setText("");
        variosFragment.getProductoAdapter().notifyDataSetChanged();

        Toast.makeText(this,variosFragment.getSpProductos().getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    public GncFragment getGncFragment() {
        return gncFragment;
    }

    public void setGncFragment(GncFragment gncFragment){ this.gncFragment = gncFragment; }

    public AceiteFragment getAceiteFragment() {
        return aceiteFragment;
    }

    public void setAceiteFragment(AceiteFragment aceiteFragment) {
        this.aceiteFragment = aceiteFragment;
    }

    public VariosFragment getVariosFragment() {
        return variosFragment;
    }

    public void setVariosFragment(VariosFragment variosFragment) {
        this.variosFragment = variosFragment;
    }

    public void guardarTurno(View v){

        new AsyncTask<AbrirTurnoActivity,Void,Void>(){
            @Override
            protected Void doInBackground(AbrirTurnoActivity... params) {
                AbrirTurnoPresenter.guardarTurno(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(getApplicationContext(),MainV2Activity.class);
                startActivity(intent);
                finish();
            }
        }.execute(this);

    }

}
