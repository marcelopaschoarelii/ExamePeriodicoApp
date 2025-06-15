package com.example.exameperiodicojf.ui.cadastro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exameperiodicojf.DatabaseUsuario;
import com.example.exameperiodicojf.MainActivity;
import com.example.exameperiodicojf.R;
import com.example.exameperiodicojf.model.Usuario;
import com.example.exameperiodicojf.ui.login.Login;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

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
        TextView erroSenha = findViewById(R.id.erroSenha);


        // Não sei se no banco está tudo como string
        String stringNome  = nomeEditText.getText().toString().trim();
        String stringEmail = emailEditText.getText().toString().trim();
        String stringSenha = senhaEditText.getText().toString().trim();

        if (stringNome.isEmpty() || stringEmail.isEmpty() || stringSenha.isEmpty()) {
            Log.d("Erro", "Campos obrigatórios estão vazios");
            return;
        }
        if(stringSenha.length() != 6){
            erroSenha.setText("A Senha deve conter no mínimo 6 carcateres");
        }
        else{
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DatabaseUsuario databaseUsuario = new DatabaseUsuario();
            databaseUsuario.registrarUsuario(new Usuario(stringNome, stringEmail, stringSenha));
            startActivity(new Intent(Cadastro.this, MainActivity.class));
        }

    }

    public void cliqueCadastrar(View view) {
        SalvarUsuario();
    }
}