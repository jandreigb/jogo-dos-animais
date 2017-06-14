package br.jgb.jogo;

/**
 * Representa um animal
 * 
 * @author Jandrei
 *
 */
public class Animal {

	private String nome;
	private OndeVive ondeVive;

	public Animal(String nome, OndeVive ondeVive) {
		super();
		this.nome = nome;
		this.ondeVive = ondeVive;
	}

	public Animal(OndeVive ondeVive) {
		super();
		this.nome = "";
		this.ondeVive = ondeVive;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public OndeVive getOndeVive() {
		return ondeVive;
	}

	public void setOndeVive(OndeVive ondeVive) {
		this.ondeVive = ondeVive;
	}

}
