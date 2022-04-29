package com.fatec.campeonatopaulista.services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.fatec.campeonatopaulista.models.Grupo;
import com.fatec.campeonatopaulista.persistence.GrupoDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GrupoService {
	@Qualifier("sqlServerGrupoDAO")
	private GrupoDAO grupoDAO;

	@Autowired
	public GrupoService(GrupoDAO grupoDAO) {
		super();
		this.grupoDAO = grupoDAO;
	}

	public List<Grupo> listarGrupos() {
		var grupoA = this.grupoDAO.procurar("A");
		var grupoB = this.grupoDAO.procurar("B");
		var grupoC = this.grupoDAO.procurar("C");
		var grupoD = this.grupoDAO.procurar("D");
		return Arrays.asList(grupoA, grupoB, grupoC, grupoD)
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}

	public void sortearGrupos() {
		try {
			this.grupoDAO.sort();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
