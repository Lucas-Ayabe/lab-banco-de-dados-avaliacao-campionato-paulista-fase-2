package com.fatec.campeonatopaulista.services;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.fatec.campeonatopaulista.models.ResultadosDoGrupo;
import com.fatec.campeonatopaulista.persistence.ResultadoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ResultadoService {
    @Qualifier("sqlServerResultadoDAO")
    private ResultadoDAO resultadoDAO;

    @Autowired
    public ResultadoService(ResultadoDAO resultadoDAO) {
        this.resultadoDAO = resultadoDAO;
    }

    public List<ResultadosDoGrupo> listarResultadosDosGrupos() {
        Function<String, ResultadosDoGrupo> paraResultados = grupo -> {
            return new ResultadosDoGrupo(grupo, resultadoDAO.procurarResultadosPorGrupo(grupo));
        };

        return List
                .of("A", "B", "C", "D")
                .stream()
                .map(paraResultados)
                .collect(Collectors.toList());
    }
}
