package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.conexao.Database;

public class TestaListagem {
	public static void main(String[] args) throws SQLException {

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		Database database = new Database();

		for (int i = 0; i < 100; i++) {

			try {
				
				connection = database.getConnection();
				statement = connection.createStatement();
				String query = "select * from Produto";
				statement.execute(query);// DEVOLVE UM BOOLEAN
				resultSet = statement.getResultSet();

				while (resultSet.next()) {
					int id = resultSet.getInt("id");
					String nome = resultSet.getString("nome");
					String descricao = resultSet.getString("descricao");

					System.out.println("ID: " + id);
					System.out.println("Nome: " + nome);
					System.out.println("Descrição: " + descricao);
					System.out.println("");
				}

			} catch (SQLException e) {
				System.err.println("Erro ao conectar" + e.getMessage());
			} finally {
				resultSet.close();
				statement.close();
				connection.close();
			}
		}
	}

}
