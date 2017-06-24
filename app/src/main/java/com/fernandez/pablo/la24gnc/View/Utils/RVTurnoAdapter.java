package com.fernandez.pablo.la24gnc.View.Utils;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandez.pablo.la24gnc.Model.Turno;
import com.fernandez.pablo.la24gnc.R;

import java.util.ArrayList;


/**
 * Created by pablo on 22/06/2017.
 */

public class RVTurnoAdapter extends RecyclerView.Adapter<RVTurnoAdapter.TurnoViewHolder>{

    ArrayList<Turno> turnos;

    public RVTurnoAdapter(ArrayList<Turno> turnos) {
        this.turnos = turnos;
    }

    public static class TurnoViewHolder extends RecyclerView.ViewHolder{

        CardView cardTurno;
        TextView tvIdTurno;

        public TurnoViewHolder(View itemView) {
            super(itemView);
            cardTurno = (CardView) itemView.findViewById(R.id.card_consultar_turno);
            tvIdTurno = (TextView) itemView.findViewById(R.id.id_turno);
        }
    }

    @Override
    public TurnoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_turno,parent,false);
        TurnoViewHolder turnoViewHolder = new TurnoViewHolder(v);
        return turnoViewHolder;
    }

    @Override
    public void onBindViewHolder(TurnoViewHolder holder, int position) {
        holder.tvIdTurno.setText(turnos.get(position).getNro() +" - " +
                turnos.get(position).getFecha());
    }

    @Override
    public int getItemCount() {
        return turnos.size();
    }

}
