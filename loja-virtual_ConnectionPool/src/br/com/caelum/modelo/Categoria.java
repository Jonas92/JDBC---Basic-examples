package br.com.caelum.modelo;

import java.util.ArrayList;
import java.util.List;

public class Categoria {

	private int id;
	private String nome;
	private final List<Produto> produtos = new ArrayList<>();

	public Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public void adicionar(Produto produto) {
		produtos.add(produto);
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

}
