package com.example.exameperiodicojf.callback;

import com.example.exameperiodicojf.model.Consulta;

import java.util.List;

public interface ConsultaCallback {
    void onConsultasRecebidas(List<Consulta> consultas);
    void onErro(Exception e);
}