package com.fatec.campeonatopaulista.models;

import java.util.List;

public class ResultadosDoGrupo {
    private final String grupo;
    private final List<Resultado> resultados;

    public ResultadosDoGrupo(final String grupo, final List<Resultado> resultados) {
        this.grupo = grupo;
        this.resultados = resultados;
    }

    public String getGrupo() {
        return grupo;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

}
