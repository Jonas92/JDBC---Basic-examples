package br.com.caelum.jdbc.conexao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbc.JDBCPool;

public class Database {

	private DataSource dataSource;

	public Database() {
		JDBCPool pool = new JDBCPool();
		pool.setUrl("jdbc:hsqldb:hsql://localhost/loja-virtual");
		pool.setUser("SA");
		pool.setPassword("");

		this.dataSource = pool;
	}

	public Connection getConnection() throws SQLException {
		 return this.dataSource.getConnection();
//		return DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/loja-virtual", "SA", "");
	}
}
