package com.example.exameperiodicojf.callback;

import com.example.exameperiodicojf.model.Usuario;

public interface UsuarioCallback {
    void onSuccess(Usuario usuario);
    void onFailure(String erro);
}
