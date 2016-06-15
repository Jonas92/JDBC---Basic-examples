package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.caelum.dao.ProdutoDao;
import br.com.caelum.jdbc.conexao.Database;
import br.com.caelum.modelo.Produto;

public class TestaProdutoDao {

	public static void main(String[] args) {

		Produto produto = new Produto("Mesa azul", "Mesa com 4 pés");

		try (Connection conexao = new Database().getConnection()) {

			ProdutoDao produtoDao = new ProdutoDao();

			produtoDao.salvar(produto, conexao);

			System.out.println("\n");

			List<Produto> produtos = new ArrayList<>();

			produtos = produtoDao.listar(conexao);

			// Collections.sort(produtos);

			for (Produto produto2 : produtos) {
				System.out.println(produto2);
			}

		} catch (SQLException e) {
			System.err.println("Erro ao abrir a conexao! " + e.getMessage());
		}
	}

}
