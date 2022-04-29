package com.fatec.campeonatopaulista.services;

import java.sql.SQLException;

import com.fatec.campeonatopaulista.persistence.GrupoDAOFactory;

public class GrupoServiceFactory {
	private GrupoServiceFactory() {
	}

	public static GrupoService create() throws ClassNotFoundException, SQLException {
		return new GrupoService(GrupoDAOFactory.create());
	}
}
