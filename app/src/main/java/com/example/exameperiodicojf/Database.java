package com.example.exameperiodicojf;

import android.util.Log;


import com.example.exameperiodicojf.model.Consulta;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;


import java.util.HashMap;
import java.util.Map;

public class Database {

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

    public void listarUsuarios() {
        collection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (var doc : queryDocumentSnapshots) {
                        Log.d(TAG, doc.getId() + " => " + doc.getData());
                    }
                })
                .addOnFailureListener(e -> Log.w(TAG, "Erro ao listar usuários", e));
    }

    public void atualizarUsuario(String id, String novoNome, int novaIdade) {
        DocumentReference docRef = collection.document(id);
        docRef.update("nome", novoNome, "idade", novaIdade)
                .addOnSuccessListener(aVoid ->
                        Log.d(TAG, "Usuário atualizado"))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Erro ao atualizar", e));
    }

    // DELETE
    public void deletarUsuario(String id) {
        DocumentReference docRef = collection.document(id);
        docRef.delete()
                .addOnSuccessListener(aVoid ->
                        Log.d(TAG, "Usuário deletado"))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Erro ao deletar", e));
    }
}
