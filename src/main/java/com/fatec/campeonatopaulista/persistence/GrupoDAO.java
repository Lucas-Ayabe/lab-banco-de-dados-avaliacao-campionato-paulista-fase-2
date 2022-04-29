package com.fatec.campeonatopaulista.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.fatec.campeonatopaulista.models.Grupo;
import com.fatec.campeonatopaulista.models.Resultado;

public interface GrupoDAO {
	public Optional<Grupo> procurar(String grupo);

	public List<Resultado> procurarResultados(String grupo);

	public void sort() throws SQLException;
}
