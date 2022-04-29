package com.fatec.campeonatopaulista.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private ConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		String hostName = "localhost";
		String dbName = "CP";
		String user = "lucas";
		String password = "123456";

		String connect = String.format("jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s", hostName,
				dbName, user, password);

		return DriverManager.getConnection(connect);
	}
}
