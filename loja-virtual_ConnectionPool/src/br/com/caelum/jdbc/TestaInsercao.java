package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.conexao.Database;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String sqlInstruction = "insert into produto (nome, descricao) values ('Notebook', 'Notebook i5')";

		try {

			connection = new Database().getConnection();
			statement = connection.createStatement();

			// 2o argumento para retornar as chaves geradas
			statement.execute(sqlInstruction, Statement.RETURN_GENERATED_KEYS);

			resultSet = statement.getGeneratedKeys();

			while (resultSet.next()) {
				System.out.print(resultSet.getInt("id"));
			}

			System.out.println(" Inserido com sucesso!");

		} catch (SQLException e) {
			System.err.println("Erro ao inserir!");
		} finally {
			resultSet.close();
			statement.close();
			connection.close();
		}

	}
}
