package com.example.exameperiodicojf.model;

import java.util.Date;

public class Consulta {
    String cracha;
    Date dataInicio;
    Date dataTermino;

    public String getCracha() {
        return cracha;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public Consulta(String cracha, Date dataInicio, Date dataTermino) {
        this.cracha = cracha;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    public Consulta(Date dataInicio, String cracha) {
        this.dataInicio = dataInicio;
        this.cracha = cracha;
    }

    public Consulta(){

    }
}
