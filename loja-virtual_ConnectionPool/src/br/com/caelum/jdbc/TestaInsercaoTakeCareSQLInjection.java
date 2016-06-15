package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.conexao.Database;

public class TestaInsercaoTakeCareSQLInjection {

	public static void main(String[] args) throws SQLException {
		Connection conexao = null;
		PreparedStatement preparedStatement = null;

		String sqlInstruction = "insert into Produto (nome, descricao) values(?, ?)";
		String descricao = "Armazenar muita água para o verão";
		String nome = "Caixa d'água";

		try {
			conexao = new Database().getConnection();
			preparedStatement = conexao.prepareStatement(sqlInstruction, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, descricao);

			Boolean temResultSet = preparedStatement.execute();
						
			System.out.println("Comando executado com sucesso!");
			System.out.println("É um select?? " + temResultSet);
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
						
			if (resultSet.next()) {
				System.out.println("ID gerado no SGBD: " + resultSet.getString("id"));
			}

		} catch (SQLException e) {
			System.err.println("Erro!! " + e.getMessage());
		} finally {
			preparedStatement.close();
			conexao.close();
		}
	}
}
