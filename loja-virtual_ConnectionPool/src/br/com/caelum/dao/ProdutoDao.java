package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.modelo.Categoria;
import br.com.caelum.modelo.Produto;

public class ProdutoDao {

	public void salvar(Produto produto, Connection conexao) {
		String sql = "insert into produto (nome, descricao) values (?, ?)";

		try (PreparedStatement preparedStatement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getDescricao());

			preparedStatement.execute();

			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {

				if (resultSet.next()) {

					int id = resultSet.getInt("id");
					produto.setId(id);

					System.out.printf("Produto %d salvo com sucesso! ", produto.getId());
				}

			} catch (Exception e) {
				System.err.println("Erro ao recuperar o id! " + e.getMessage());
			}

		} catch (Exception e) {
			System.err.println("Erro ao executar o sql! " + e.getMessage());
		}
	}

	public List<Produto> listar(Connection conexao) {

		String query = "select * from produto order by nome, id";
		List<Produto> produtos = new ArrayList<>();

		try (PreparedStatement preparedStatement = conexao.prepareStatement(query)) {

			transformarEm(produtos, preparedStatement);

		} catch (Exception e) {
			System.err.println("Erro ao executar a consulta. " + e.getMessage());
		}

		return produtos;
	}

	private void transformarEm(List<Produto> produtos, PreparedStatement preparedStatement) {
		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			while (resultSet.next()) {
				String nome = resultSet.getString("nome");
				String descricao = resultSet.getString("descricao");
				int id = resultSet.getInt("id");

				Produto produto = new Produto(nome, descricao, id);

				produtos.add(produto);
			}

		} catch (Exception e) {
			System.err.println("Erro ao recuperar os registros! " + e.getMessage());
		}
	}

	public List<Produto> buscarPor(Categoria categoria, Connection conexao) {

		String sql = "select * from produto where categoria_id = ?";
		List<Produto> produtos = new ArrayList<>();

		try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

			preparedStatement.setInt(1, categoria.getId());

			transformarEm(produtos, preparedStatement);

		} catch (Exception e) {
			System.err.println("Erro ao executar a consulta. " + e.getMessage());
		}

		return produtos;
	}
}
