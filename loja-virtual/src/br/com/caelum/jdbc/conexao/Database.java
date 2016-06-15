package br.com.caelum.jdbc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	public static Connection getConnection() throws SQLException {
		
		Connection connection = null;
		
			connection = DriverManager.getConnection
					("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
		
		return connection;
	}
}
