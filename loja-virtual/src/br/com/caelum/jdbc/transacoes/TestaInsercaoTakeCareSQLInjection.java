package br.com.caelum.jdbc.transacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.conexao.Database;

public class TestaInsercaoTakeCareSQLInjection {

	public static void main(String[] args) throws SQLException {

		String sqlInstruction = "insert into Produto (nome, descricao) values(?, ?)";

		try (Connection conexao = Database.getConnection()) {

			// Para controlar a transação manualmente
			conexao.setAutoCommit(false);

			try (PreparedStatement preparedStatement = conexao.prepareStatement(sqlInstruction,
					Statement.RETURN_GENERATED_KEYS)) {

				adicionar(preparedStatement, "32 polegadas", "TV LCD");
				adicionar(preparedStatement, "Full HDMI", "Blueray");
				
				//comitar as alterações, somente se obteve sucesso nas 2 chamadas 
				//do método adicionar
				conexao.commit();
				
			} catch (Exception e) {
				// System.err.println("Erro ao adicionar os produtos. " +
				// e.getMessage());
				conexao.rollback();
				e.printStackTrace();
				System.err.println("Rollback efetuado!!");
			}

		} catch (Exception e) {
			System.err.println("Erro ao abrir a conexão. " + e.getMessage());
		}

	}

	private static void adicionar(PreparedStatement preparedStatement, String descricao, String nome)
			throws SQLException {

		// if (nome.equals("Blueray")) {
		// throw new IllegalArgumentException("Problema ocorrido");
		// }

		preparedStatement.setString(1, nome);
		preparedStatement.setString(2, descricao);

		Boolean temResultSet = preparedStatement.execute();
		
		System.out.println("Comando executado com sucesso!");
		System.out.println("É um select?? " + temResultSet);

		try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
			if (resultSet.next()) {
				System.out.println("ID gerado no SGBD: " + resultSet.getString("id"));
			}
		} catch (Exception e) {
			System.err.println("Erro ao recuperar o ID!! " + e.getMessage());
		}

		System.out.println("");
	}
}
