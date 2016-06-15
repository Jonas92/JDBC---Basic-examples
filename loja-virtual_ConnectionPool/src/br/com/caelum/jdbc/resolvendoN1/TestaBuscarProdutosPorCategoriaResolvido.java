package br.com.caelum.jdbc.resolvendoN1;

import java.sql.Connection;
import java.util.List;

import br.com.caelum.dao.CategoriaDao;
import br.com.caelum.jdbc.conexao.Database;
import br.com.caelum.modelo.Categoria;
import br.com.caelum.modelo.Produto;

public class TestaBuscarProdutosPorCategoriaResolvido {
	public static void main(String[] args) {

		Database database = new Database();

		try (Connection conexao = database.getConnection()) {

			List<Categoria> categorias = new CategoriaDao(conexao).listarComProdutos();

			for (Categoria categoria : categorias) {

				for (Produto produto : categoria.getProdutos()) {
					System.out.println(categoria.getNome() + " - " + produto.getNome());
					System.out.println("");
				}

			}

		} catch (Exception e) {
			System.err.println("Erro ao abrir a conexão. " + e.getMessage());
		}

	}
}
