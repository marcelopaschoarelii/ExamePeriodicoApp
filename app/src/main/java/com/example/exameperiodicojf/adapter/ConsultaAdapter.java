package com.example.exameperiodicojf.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.example.exameperiodicojf.Database;
import com.example.exameperiodicojf.R;
import com.example.exameperiodicojf.model.Consulta;

import java.util.List;

public class ConsultaAdapter extends RecyclerView.Adapter<ConsultaAdapter.ViewHolder>{

    private List<Consulta> listaConsulta;
    private Database database;

    public ConsultaAdapter(List<Consulta> listaConsulta) {
        this.listaConsulta = listaConsulta;
    }

    @NonNull
    @Override
    public ConsultaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//         vincular o XML para careggar o RV
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.credencial_item, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ConsultaAdapter.ViewHolder holder, int position) {
        Consulta consulta = listaConsulta.get(position);

        String texto =  consulta.getCracha();

        holder.credencial.setText(texto);

        holder.trash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listaConsulta.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return listaConsulta.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        vincula o XML card com o JAVA

        TextView credencial;
        ConstraintLayout fundo;
        ImageView trash;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            credencial = itemView.findViewById(R.id.credencialRv);
            fundo = itemView.findViewById(R.id.fundo);
            trash = itemView.findViewById(R.id.trash);
        }
    }
}
