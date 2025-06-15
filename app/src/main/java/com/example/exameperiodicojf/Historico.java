package com.example.exameperiodicojf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.exameperiodicojf.adapter.ConsultaAdapter;
import com.example.exameperiodicojf.adapter.HistoricoAdapter;
import com.example.exameperiodicojf.callback.ConsultaCallback;
import com.example.exameperiodicojf.model.Consulta;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Historico#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Historico extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private HistoricoAdapter adapter;
    private DatabaseConsulta databaseConsulta;

    public Historico() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Historico.
     */
    // TODO: Rename and change types and number of parameters
    public static Historico newInstance(String param1, String param2) {
        Historico fragment = new Historico();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_historico, container, false);
        recyclerView = view.findViewById(R.id.recycleHistorico);

        databaseConsulta = new DatabaseConsulta();
        carregarConsultas();

        return view;
    }
    private void carregarConsultas() {
        databaseConsulta.listarConsultas(new ConsultaCallback() {
            @Override
            public void onConsultasRecebidas(List<Consulta> consultas) {
                adapter = new HistoricoAdapter(consultas, requireContext(), databaseConsulta);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onErro(Exception e) {
                Toast.makeText(getContext(), "Erro ao carregar consultas", Toast.LENGTH_SHORT).show();
            }});
    }

}