package com.example.exameperiodicojf.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.exameperiodicojf.DatabaseConsulta;
import com.example.exameperiodicojf.databinding.FragmentHomeBinding;
import com.example.exameperiodicojf.model.Consulta;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Date;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private DatabaseConsulta database;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final Button myButton = binding.button3;
        final TextInputEditText textCracha = binding.crachaInput;
        database = new DatabaseConsulta();


        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Consulta consulta = new Consulta( new Date(),textCracha.getText().toString());
                textCracha.setText("");
                database.registrarConsulta(consulta);

                Toast.makeText(getContext(), "Consulta registrada!", Toast.LENGTH_SHORT).show();
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}