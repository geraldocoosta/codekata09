package com.codekata;

public class Product {
	private final String nome;
	private final int preco;
	private int precoPromocional;
	private int qtdItensPromocao;

	public Product(String nome, int preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public Product(String nome, int preco, int qtdItensPromocao, int precoPromocional) {
		this(nome, preco);
		this.precoPromocional = precoPromocional;
		this.qtdItensPromocao = qtdItensPromocao;
	}

	public String getNome() {
		return nome;
	}

	public boolean isPromocional() {
		return precoPromocional > 1 && qtdItensPromocao > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + preco;
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
		Product other = (Product) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (preco != other.preco)
			return false;
		return true;
	}

	public int getPrecoItens(int qtdItens) {
		if (isPromocional() && qtdItens / qtdItensPromocao >= 1) {
			int aux = 0;
			aux += (qtdItens / qtdItensPromocao) * precoPromocional;
			aux += (qtdItens % qtdItensPromocao) * preco;
			return aux;
		}
		return qtdItens * preco;
	}

}
