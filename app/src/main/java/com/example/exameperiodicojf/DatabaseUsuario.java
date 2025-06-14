package com.example.exameperiodicojf;

import android.util.Log;

import com.example.exameperiodicojf.callback.ConsultaCallback;
import com.example.exameperiodicojf.callback.UsuarioCallback;
import com.example.exameperiodicojf.model.Consulta;
import com.example.exameperiodicojf.model.Usuario;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseUsuario {

    private static final String TAG = "DatabaseHelper";
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final CollectionReference collection = db.collection("usuario");

    public void registrarUsuario(Usuario usuario) {
        Map<String, Object> consultaRegistrada = new HashMap<>();
        consultaRegistrada.put("nome", usuario.getNome());
        consultaRegistrada.put("email",usuario.getEmail());
        consultaRegistrada.put("senha",usuario.getSenha());


        collection.add(consultaRegistrada)
                .addOnSuccessListener(documentReference ->
                        Log.d(TAG, "Usuariocriado com ID: " + documentReference.getId()))
                .addOnFailureListener(e ->
                        Log.w(TAG, "Erro ao criar usuário", e));
    }
    public void fazerLogin(String email, String senha, UsuarioCallback callback) {
        collection.get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    boolean usuarioEncontrado = false;

                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        String emailDb = doc.getString("email");
                        String senhaDb = doc.getString("senha");

                        if (email.equals(emailDb) && senha.equals(senhaDb)) {
                            usuarioEncontrado = true;

                            Usuario usuario = new Usuario();
                            usuario.setNome(doc.getString("nome"));
                            usuario.setEmail(emailDb);
                            usuario.setSenha(senhaDb);

                            callback.onSuccess(usuario);
                            break;
                        }
                    }

                    if (!usuarioEncontrado) {
                        callback.onFailure("Usuário ou senha incorretos");
                    }
                })
                .addOnFailureListener(e -> {
                    callback.onFailure("Erro ao consultar usuários: " + e.getMessage());
                });
    }

}
