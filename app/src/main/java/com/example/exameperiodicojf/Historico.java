package com.example.exameperiodicojf;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exameperiodicojf.adapter.HistoricoAdapter;
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

        List<Consulta> lista = new ArrayList<>();
        lista.add(new Consulta("1021634025",new Date(0,0,0,12,34), new Date()));
        lista.add(new Consulta(new Date(0,0,0,12,34),"56789024432"));

        HistoricoAdapter historicoAdapter = new HistoricoAdapter(lista);

        RecyclerView recyclerView = view.findViewById(R.id.recycleHistorico);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(historicoAdapter);

        return view;
    }

}