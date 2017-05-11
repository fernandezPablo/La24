package com.fernandez.pablo.la24gnc.Presenter;

import android.content.Context;

import com.fernandez.pablo.la24gnc.Model.TurnoDAO;

/**
 * Created by pablo on 10/05/2017.
 */

public class MainPresenter {

    public static String hayTurnoAbierto(Context context){

        return TurnoDAO.hayTurnoAbierto(context);

    }

}
