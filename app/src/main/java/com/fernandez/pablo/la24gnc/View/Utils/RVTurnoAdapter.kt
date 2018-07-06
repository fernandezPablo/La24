package com.fernandez.pablo.la24gnc.View.Utils

import android.content.Context
import android.content.Intent
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.fernandez.pablo.la24gnc.Model.Turno
import com.fernandez.pablo.la24gnc.R
import com.fernandez.pablo.la24gnc.View.DetalleTurno.DetalleTurnoActivity
import com.fernandez.pablo.la24gnc.View.Utils.RVTurnoAdapter.TurnoViewHolder

import java.util.ArrayList


/**
 * Created by pablo on 22/06/2017.
 */

class RVTurnoAdapter(internal var turnos: ArrayList<Turno>) : RecyclerView.Adapter<RVTurnoAdapter.TurnoViewHolder>() {

    class TurnoViewHolder(itemView: View, internal var context: Context, turnos: ArrayList<Turno>) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        internal var cardTurno: CardView
        internal var tvIdTurno: TextView
        internal var turnos = ArrayList<Turno>()

        init {
            itemView.setOnClickListener(this)
            cardTurno = itemView.findViewById(R.id.card_consultar_turno) as CardView
            tvIdTurno = itemView.findViewById(R.id.id_turno) as TextView
            this.turnos = turnos
        }

        override fun onClick(v: View) {
            val i = Intent(this.context, DetalleTurnoActivity::class.java)
            i.putExtra("codigoTurno", this.turnos[adapterPosition].codigo)
            this.context.startActivity(i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TurnoViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_turno, parent, false)
        return TurnoViewHolder(v, parent.context, turnos)
    }

    override fun onBindViewHolder(holder: TurnoViewHolder, position: Int) {
        holder.tvIdTurno.text = turnos[position].nro.toString() + " - " +
                turnos[position].fecha
    }

    override fun getItemCount(): Int {
        return turnos.size
    }

}
