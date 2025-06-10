package com.example.exameperiodicojf.ui.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exameperiodicojf.R;
import com.example.exameperiodicojf.ui.login.Login;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void abrirLogin(View view) {
        Intent intent = new Intent(view.getContext(), Login.class);
        view.getContext().startActivity(intent);
    }

    private void SalvarUsuario(){

        TextInputEditText nomeEditText = findViewById(R.id.nome);
        TextInputEditText emailEditText = findViewById(R.id.email);
        TextInputEditText senhaEditText = findViewById(R.id.senha);

        // Não sei se no banco está tudo como string
        String stringNome  = nomeEditText.getText().toString().trim();
        String stringEmail = emailEditText.getText().toString().trim();
        String stringSenha = senhaEditText.getText().toString().trim();

        if (stringNome.isEmpty() || stringEmail.isEmpty() || stringSenha.isEmpty()) {
            Log.d("Erro", "Campos obrigatórios estão vazios");
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> usuarios = new HashMap<>();
        usuarios.put("nome", stringNome);
        usuarios.put("email", stringEmail);
        usuarios.put("senha", stringSenha);

        // Trocar nome da colection aqui, tbm não sei se é esse, coloquei o email como id, tbm não sei se está assim

        db.collection("Usuarios").document(stringEmail)
                .set(usuarios)
                .addOnSuccessListener(unused ->
                        Log.d("db", "Sucesso ao salvar usuário"))
                .addOnFailureListener(e ->
                        Log.e("db", "Erro ao salvar usuário", e));
    }

    public void cliqueCadastrar(View view) {
        SalvarUsuario();
    }
}