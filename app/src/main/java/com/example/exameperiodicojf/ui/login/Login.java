package com.example.exameperiodicojf.ui.login;

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
import com.example.exameperiodicojf.ui.cadastro.Cadastro;
import com.example.exameperiodicojf.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void FazerLogin() {

        TextInputEditText emailEditText = findViewById(R.id.emailLogin);
        TextInputEditText senhaEditText = findViewById(R.id.senhaLogin);

        String stringEmail = emailEditText.getText().toString().trim();
        String stringSenha = senhaEditText.getText().toString().trim();

        if (stringEmail.isEmpty() || stringSenha.isEmpty()) {
            Log.d("Erro", "Campos obrigatórios estão vazios");
            return;
        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("pipopipo")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot documentSnapshots = task.getResult();
                        for (DocumentSnapshot document : documentSnapshots) {

                            String senha = document.getString("senha");

                            if (stringSenha.equals(senha)) {
                                Intent intent = new Intent(Login.this, HomeFragment.class);
                                startActivity(intent);
                            } else {
                                Log.d("Erro", "Senha incorreta");
                            }
                        }
                    } else {
                        Log.w("FirestoreData", "Erro ao obter documentos.");
                    }
                });
    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(view.getContext(), Cadastro.class);
        view.getContext().startActivity(intent);
    }
}
