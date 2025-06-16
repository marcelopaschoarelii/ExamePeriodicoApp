package com.example.exameperiodicojf;

import static android.view.View.INVISIBLE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exameperiodicojf.adapter.ConsultaAdapter;
import com.example.exameperiodicojf.callback.ConsultaCallback;
import com.example.exameperiodicojf.model.Consulta;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AreaRestrita#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AreaRestrita extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String senhaCredencial;
    private TextInputEditText senha;
    private TextView credencial;
    private TextView mensagemRestrita;
    private RecyclerView recyclerView;
    private ConsultaAdapter adapter;
    private DatabaseConsulta databaseConsulta;
    private Button validar;
    private TextInputLayout textInputLayout;


    public AreaRestrita() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AreaRestrita.
     */
    // TODO: Rename and change types and number of parameters
    public static AreaRestrita newInstance(String param1, String param2) {
        AreaRestrita fragment = new AreaRestrita();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_area_restrita, container, false);

        senhaCredencial = "admin123";
        validar = view.findViewById(R.id.validar);

        credencial = view.findViewById(R.id.credenciais);
        senha = view.findViewById(R.id.senha);

        mensagemRestrita = view.findViewById(R.id.mensagemRestrita);
        mensagemRestrita.setVisibility(INVISIBLE);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setVisibility(INVISIBLE);

        textInputLayout = view.findViewById(R.id.textInputLayout);

        databaseConsulta = new DatabaseConsulta();



        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (senha.getText().toString().equals(senhaCredencial)) {
                    credencial.setText("Seja bem Vindo!");

                    senha.setText("");
                    credencial.setTextSize(24);
                    senha.setVisibility(View.INVISIBLE);
                    textInputLayout.setVisibility(View.INVISIBLE);
                    validar.setVisibility(View.INVISIBLE);
                    mensagemRestrita.setVisibility(View.INVISIBLE);

                    carregarConsultas();

                    Calendar inicio = Calendar.getInstance();
                    Calendar termino = Calendar.getInstance();
                    termino.add(Calendar.DAY_OF_MONTH, 7);


                } else {
                    mensagemRestrita.setVisibility(View.VISIBLE);
                }
            }

        });


        return view;
    }

    private void carregarConsultas() {
        databaseConsulta.listarConsultas(new ConsultaCallback() {
            @Override
            public void onConsultasRecebidas(List<Consulta> consultas) {
                adapter = new ConsultaAdapter(consultas);
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