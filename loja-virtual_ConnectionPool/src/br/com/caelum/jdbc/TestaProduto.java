package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.caelum.jdbc.conexao.Database;
import br.com.caelum.modelo.Produto;

public class TestaProduto {

	public static void main(String[] args) {

		Produto produto = new Produto("Mesa azul", "Mesa com 4 pés");

		try (Connection conexao = new Database().getConnection()) {

			salvar(produto, conexao);

		} catch (SQLException e) {
			System.err.println("Erro ao abrir a conexao! " + e.getMessage());
		}
	}

	private static void salvar(Produto produto, Connection conexao) {
		String sql = "insert into produto (nome, descricao) values (?, ?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getDescricao());

			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				if (resultSet.next()) {
					
					int id = resultSet.getInt("id");
					produto.setId(id);
					
					System.out.println(produto);
				}

			} catch (Exception e) {
				System.err.println("Erro ao recuperar o id! " + e.getMessage());
			}

		} catch (Exception e) {
			System.err.println("Erro ao executar o sql! " + e.getMessage());
		}
	}
}
