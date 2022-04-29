package com.fatec.campeonatopaulista.persistence;

import java.sql.SQLException;

public class GrupoDAOFactory {
	private GrupoDAOFactory() {
	}

	public static GrupoDAO create() throws ClassNotFoundException, SQLException {
		return new SQLServerGrupoDAO(ConnectionFactory.getConnection());
	}
}
