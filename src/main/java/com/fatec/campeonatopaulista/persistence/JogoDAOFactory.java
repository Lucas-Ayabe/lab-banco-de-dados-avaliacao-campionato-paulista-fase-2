package com.fatec.campeonatopaulista.persistence;

import java.sql.SQLException;

public class JogoDAOFactory {
	private JogoDAOFactory() {
	}

	public static JogoDAO create() throws ClassNotFoundException, SQLException {
		return new SQLServerJogoDAO(ConnectionFactory.getConnection());
	}
}
