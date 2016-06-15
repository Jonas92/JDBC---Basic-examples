package br.com.caelum.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.modelo.Categoria;
import br.com.caelum.modelo.Produto;

public class CategoriaDao {

	private Connection conexao;

	public CategoriaDao(Connection conexao) {
		this.conexao = conexao;
	}

	public List<Categoria> listar() {

		String sql = "select * from categoria";
		List<Categoria> categorias = new ArrayList<>();

		try (PreparedStatement preparedStatement = conexao.prepareStatement(sql)) {

			try (ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					String nome = resultSet.getString("nome");
					int id = resultSet.getInt("id");

					categorias.add(new Categoria(id, nome));
				}

			} catch (Exception e) {
				System.err.println("Erro ao recuperar os dados. " + e.getMessage());
			}
		} catch (Exception e) {
			System.err.println("Erro ao executar a consulta! " + e.getMessage());
		}

		return categorias;
	}

	public List<Categoria> listarComProdutos() throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Categoria ultima = null;

		String sql = "select c.id as c_id, c.nome as c_nome, p.id as p_id, p.nome as p_nome, p.descricao as p_descricao "
				+ " from Categoria as c join Produto as p on p.categoria_id = c.id";
		try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
			stmt.execute();
			try (ResultSet rs = stmt.getResultSet()) {
				while (rs.next()) {
					String nome = rs.getString("c_nome");
					Integer id = rs.getInt("c_id");

					if (ultima == null || !ultima.getNome().equals(nome)) {
						Categoria categoria = new Categoria(id, nome);
						categorias.add(categoria);
						ultima = categoria;
					}

					Integer idProduto = rs.getInt("p_id");
					String nomeProduto = rs.getString("p_nome");
					String descricao = rs.getString("p_descricao");

					ultima.adicionar(new Produto(nomeProduto, descricao, idProduto));

				}
			}
		}
		return categorias;
	}

}
