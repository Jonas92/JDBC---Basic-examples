package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.conexao.Database;

public class TestaRemocao {
	public static void main(String[] args) throws SQLException {

		Connection conexao = null;
		Statement statement = null;

		String sqlInstructuction = "delete from Produto where id>3";

		try {
			conexao = Database.getConnection();
			statement = conexao.createStatement();
			statement.execute(sqlInstructuction);
			
			// Pega o número de linhas atualizadas no banco
			int rowsUpdated = statement.getUpdateCount();

			System.out.println("Instrução SQL executada com sucesso!");
			System.out.println("Número de linhas atualizadas: " + rowsUpdated);

		} catch (SQLException e) {
			System.err.println("Erro. " + e.getMessage());
		} finally {
			statement.close();
			conexao.close();
		}

	}
}
