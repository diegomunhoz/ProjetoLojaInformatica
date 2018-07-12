package br.com.choice.projetolojainformatica.model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto CLIENTE
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class Cliente {

	private int codigo;
	private String nome;
	private String email;
	private String telefone;
	private String celular;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

}// fim da classe