package com.fatec.campeonatopaulista.persistence;

import java.util.List;

import com.fatec.campeonatopaulista.models.Resultado;

public interface ResultadoDAO {
    public List<Resultado> procurarResultadosGerais();

    public List<Resultado> procurarResultadosPorGrupo(String grupo);
}
