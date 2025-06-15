package com.example.exameperiodicojf.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exameperiodicojf.DatabaseConsulta;
import com.example.exameperiodicojf.Historico;
import com.example.exameperiodicojf.R;
import com.example.exameperiodicojf.model.Consulta;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class HistoricoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Consulta> listaConsulta;
    private DatabaseConsulta database;
    private int tipo;
    private Context context;


    public HistoricoAdapter(List<Consulta> lista, Context context, DatabaseConsulta database) {
        this.listaConsulta = lista;
        this.context = context;
        this.database = database;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType==1 ){
             view = inflater.inflate(R.layout.exame_item, parent, false);
             return new ViewHolder(view);
        } else{
            view = inflater.inflate(R.layout.exame_item_encerrado, parent, false);
            return new ViewHolderTermino(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Consulta consulta = listaConsulta.get(position);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        String cracha = consulta.getCracha();
        String horaInicio = sdf.format(consulta.getDataInicio());


        Dialog caixaAlert = new Dialog(context);
        caixaAlert.setContentView(R.layout.custom);

//      Definfindo que o tamanho do layout é wrap content
        caixaAlert.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        caixaAlert.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        Button btSim = caixaAlert.findViewById(R.id.buttonSimCustom);
        Button btNao = caixaAlert.findViewById(R.id.buttonNaoCustom);


        if (holder instanceof ViewHolderTermino) {
            ViewHolderTermino viewH = (ViewHolderTermino) holder;
            viewH.cracha.setText(cracha);
            viewH.horaInicio.setText(horaInicio);
            viewH.horaTermino.setText(sdf.format(consulta.getDataTermino()));
        } else if (holder instanceof ViewHolder) {
            ViewHolder viewH = (ViewHolder) holder;
            viewH.cracha.setText(cracha);
            viewH.horaInicio.setText(horaInicio);
            viewH.encerrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    caixaAlert.show();

                    btSim.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Date dataTermino = new Date();
                            consulta.setDataTermino(dataTermino);
                            database.encerrarConsulta(consulta.getId(), dataTermino);
                            notifyDataSetChanged();
                            caixaAlert.dismiss();
                        }
                    });

                    btNao.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            caixaAlert.dismiss();
                        }
                    });
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return listaConsulta.size();
    }

    @Override
    public int getItemViewType(int position) {
        Consulta consulta = listaConsulta.get(position);
        if (consulta.getDataTermino()!=null){
            return 2;
        } else {
            return 1;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView cracha;
        TextView horaInicio;
        RadioButton encerrar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cracha = itemView.findViewById(R.id.cracha);
            horaInicio = itemView.findViewById(R.id.horaInicio);

            encerrar = itemView.findViewById(R.id.encerrar);
        }

    }

    public class ViewHolderTermino extends RecyclerView.ViewHolder{
        TextView horaInicio;
        TextView cracha;

        TextView horaTermino;
        public ViewHolderTermino(@NonNull View itemView) {
            super(itemView);
            horaInicio = itemView.findViewById(R.id.horaInicio);
            cracha = itemView.findViewById(R.id.cracha);
            horaTermino = itemView.findViewById(R.id.horaTermino);
        }
    }

    public HistoricoAdapter(List<Consulta> lista, DatabaseConsulta database) {
        this.listaConsulta = lista;
        this.database = database;
    }
}
