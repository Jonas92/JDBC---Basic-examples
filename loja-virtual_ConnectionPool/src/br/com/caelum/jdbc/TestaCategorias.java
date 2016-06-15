package br.com.caelum.jdbc;

import java.sql.Connection;
import java.util.List;

import br.com.caelum.dao.CategoriaDao;
import br.com.caelum.jdbc.conexao.Database;
import br.com.caelum.modelo.Categoria;

public class TestaCategorias {
	public static void main(String[] args) {

		Database database = new Database();

		try (Connection conexao = database.getConnection()) {

			List<Categoria> categorias = new CategoriaDao(conexao).listar();

			for (Categoria categoria : categorias) {
				System.out.println(categoria.getId());
				System.out.println(categoria.getNome());
			}

		} catch (Exception e) {
			System.err.println("Erro ao abrir a conexão! " + e.getMessage());
		}
	}
}
