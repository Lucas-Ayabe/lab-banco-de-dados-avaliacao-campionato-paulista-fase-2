package com.fatec.campeonatopaulista.persistence;

import java.sql.SQLException;
import java.util.List;

import com.fatec.campeonatopaulista.models.Jogo;

public interface JogoDAO {
	public List<Jogo> encontrarTodosPelaData(String date);

	public List<Jogo> encontrarTodos();

	public List<Jogo> listarQuartasDeFinal();

	public void atualizarResultado(int codigo, int golsDoTimeA, int golsDosTimesB);

	public void embaralhar() throws SQLException;
}
