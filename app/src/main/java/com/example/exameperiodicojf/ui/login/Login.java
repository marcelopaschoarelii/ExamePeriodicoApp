package com.example.exameperiodicojf.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exameperiodicojf.DatabaseUsuario;
import com.example.exameperiodicojf.MainActivity;
import com.example.exameperiodicojf.R;
import com.example.exameperiodicojf.callback.UsuarioCallback;
import com.example.exameperiodicojf.model.Usuario;
import com.example.exameperiodicojf.ui.cadastro.Cadastro;
import com.example.exameperiodicojf.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

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
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerLogin();
            }
        });
    }

    private void fazerLogin() {

        TextInputEditText emailEditText = findViewById(R.id.emailLogin);
        TextInputEditText senhaEditText = findViewById(R.id.senhaLogin);

        String stringEmail = emailEditText.getText().toString().trim();
        String stringSenha = senhaEditText.getText().toString().trim();

        if (stringEmail.isEmpty() || stringSenha.isEmpty()) {
            Log.d("Erro", "Campos obrigatórios estão vazios");
            return;
        }
        DatabaseUsuario databaseUsuario = new DatabaseUsuario();
        databaseUsuario.fazerLogin(stringEmail, stringSenha, new UsuarioCallback() {
            @Override
            public void onSuccess(Usuario usuario) {
                Log.d("Login", "Login OK: " + usuario.getNome());
                startActivity(new Intent(Login.this, MainActivity.class));
            }

            @Override
            public void onFailure(String erro) {
                Log.e("Login", "Erro: " + erro);
            }
        });

    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(view.getContext(), Cadastro.class);
        view.getContext().startActivity(intent);
    }
}
