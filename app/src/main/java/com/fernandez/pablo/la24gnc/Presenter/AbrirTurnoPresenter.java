package com.fernandez.pablo.la24gnc.Presenter;

import com.fernandez.pablo.la24gnc.Model.Aforador;
import com.fernandez.pablo.la24gnc.Model.AforadorDAO;
import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.Model.TurnoDAO;
import com.fernandez.pablo.la24gnc.View.AbrirTurnoActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by pablo on 07/05/2017.
 */

public class AbrirTurnoPresenter {

    public static void guardarTurno(AbrirTurnoActivity activity) {

        Turno turno = new Turno();
        ArrayList<Double> valoresInicialesGnc = activity.getGncFragment().getValoresAforadores();
        ArrayList<Double> valoresInicialesAceite = activity.getAceiteFragment().getValoresAforadores();
        ArrayList<Aforador> aforadoresGnc = new ArrayList<>();
        ArrayList<Aforador> aforadoresAceite = new ArrayList<>();

        Calendar calendar = new GregorianCalendar();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm");

        turno.setFecha(sdf.format(calendar.getTime()));

        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        if(hora >=22 || hora < 6){
            turno.setNro(1);
        }
        else if(hora >= 6 && hora < 14){
            turno.setNro(2);
        }
        else if (hora >= 14 && hora < 22){
            turno.setNro(3);
        }
        else{
            turno.setNro(0);
        }

        TurnoDAO turnoDAO = new TurnoDAO(activity);
        turnoDAO.createTurno(turno);

        turno.setCodigo(turnoDAO.getCodLastTurno());

        for (int i = 0; i < valoresInicialesGnc.size(); i++) {
            aforadoresGnc.add(new Aforador(i + 1, "mt3", valoresInicialesGnc.get(i).doubleValue(), 0, Aforador.GNC, turno.getCodigo()));
        }

        AforadorDAO aforadorDAO = new AforadorDAO(activity);

        for (Aforador af : aforadoresGnc) {
            aforadorDAO.createAforador(af);
        }

        for (int i = 0; i < valoresInicialesAceite.size(); i++) {
            aforadoresAceite.add(new Aforador(i + 1, "lts", valoresInicialesAceite.get(i), 0, Aforador.ACEITE, turno.getCodigo()));
        }

        for (Aforador af : aforadoresAceite){
            aforadorDAO.createAforador(af);
        }

    }

}
