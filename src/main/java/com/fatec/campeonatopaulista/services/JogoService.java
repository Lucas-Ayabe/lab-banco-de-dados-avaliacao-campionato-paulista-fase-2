package com.fatec.campeonatopaulista.services;

import java.util.List;

import com.fatec.campeonatopaulista.persistence.JogoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JogoService {
    @Qualifier("sqlServerJogoDAO")
    private JogoDAO jogoDAO;

    @Autowired
    public JogoService(JogoDAO jogoDAO) {
        this.jogoDAO = jogoDAO;
    }

    public void registrarResultados(
            List<Integer> codigos,
            List<Integer> golsDosTimesA,
            List<Integer> golsDosTimesB) {
        int size = codigos.size();

        for (int itemAtual = 0; itemAtual < size; itemAtual++) {
            var codigo = codigos.get(itemAtual);
            var golsDoTimeA = golsDosTimesA.get(itemAtual);
            var golsDoTimeB = golsDosTimesB.get(itemAtual);
            jogoDAO.atualizarResultado(codigo, golsDoTimeA, golsDoTimeB);
        }
    }
}
