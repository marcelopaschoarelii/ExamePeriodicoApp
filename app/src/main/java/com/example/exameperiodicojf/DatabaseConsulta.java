package com.example.exameperiodicojf;

import android.util.Log;


import androidx.annotation.Nullable;

import com.example.exameperiodicojf.adapter.ConsultaAdapter;
import com.example.exameperiodicojf.callback.ConsultaCallback;
import com.example.exameperiodicojf.model.Consulta;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;

import com.google.firebase.firestore.QueryDocumentSnapshot;


import java.util.ArrayList;
import java.util.Date;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConsulta {

    private static final String TAG = "DatabaseHelper";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference collection = db.collection("consulta");

    public void registrarConsulta(Consulta consulta) {
        Map<String, Object> consultaRegistrada = new HashMap<>();
        consultaRegistrada.put("cracha", consulta.getCracha());
        consultaRegistrada.put("dataInicio",consulta.getDataInicio());

        collection.add(consultaRegistrada)
                .addOnSuccessListener(documentReference ->
                        Log.d(TAG, "Consulta criado com ID: " + documentReference.getId()))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Erro ao criar usuário", e));
    }

    public void listarConsultas(ConsultaCallback callback) {
        collection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Consulta> lista = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String id = doc.getId();
                        String cracha = doc.getString("cracha");
                        Date dataInicio = doc.getDate("dataInicio");
                        Date dataTermino = doc.getDate("dataTermino");
                        Consulta consulta = new Consulta(dataInicio, cracha, id);
                        consulta.setDataTermino(dataTermino);
                        Log.d("FirestoreDebug", "Chamando collection.get()");

                        lista.add(consulta);
                    }
                    callback.onConsultasRecebidas(lista);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Erro ao buscar consultas", e);
                    callback.onErro(e);
                });
    }

    public void excluirConsulta(String id) {
        collection.document(id)
                .delete()
                .addOnSuccessListener(aVoid ->
                        Log.d(TAG, "Consulta removida com sucesso!"))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Erro ao remover consulta", e));
    }
    public void encerrarConsulta(String id, Date dataTermino) {
        Map<String, Object> update = new HashMap<>();
        update.put("dataTermino", dataTermino);

        collection.document(id)
                .update(update)
                .addOnSuccessListener(aVoid ->
                        Log.d(TAG, "Consulta encerrada com sucesso!"))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Erro ao encerrar consulta", e));
    }



}
