package com.example.demo;

public class prodotto {
	String nome;
	String marca;
	int prezzo;
	@Override
	public String toString() {
		return "prodotto [nome=" + nome + ", marca=" + marca + ", prezzo=" + prezzo + "]";
	}
	public prodotto(String nome, String marca, int prezzo) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.prezzo = prezzo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public int getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

}
