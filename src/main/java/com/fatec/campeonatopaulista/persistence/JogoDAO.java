package com.fatec.campeonatopaulista.persistence;

import java.sql.SQLException;
import java.util.List;

import com.fatec.campeonatopaulista.models.Jogo;

public interface JogoDAO {
	public List<Jogo> findAllByDate(String date);

	public List<Jogo> findAll();

	public void sort() throws SQLException;
}
