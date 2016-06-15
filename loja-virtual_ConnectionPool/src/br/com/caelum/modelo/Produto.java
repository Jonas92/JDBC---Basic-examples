package br.com.caelum.modelo;

public class Produto implements Comparable<Produto> {

	private int id;
	private String nome;
	private String descricao;

	public Produto(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public Produto(String nome, String descricao, int id) {
		this.id = id;
		this.descricao = descricao;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return String.format("Produto: %s \nDescrição: %s \nID: %d \n", this.nome, this.descricao, this.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Produto produto) {

		int comparacaoPorNome = compararPorNome(produto);

		if (comparacaoPorNome == 0) {
			return compararPorId(produto);
		}

		return comparacaoPorNome;
	}

	private int compararPorId(Produto produto) {
		return this.getId() - produto.getId();
	}

	private int compararPorNome(Produto produto) {
		return this.nome.compareTo(produto.getNome());
	}

}
