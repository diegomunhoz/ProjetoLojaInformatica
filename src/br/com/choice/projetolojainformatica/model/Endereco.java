package br.com.choice.projetolojainformatica.model;

/**
 * Classe respons�vel por armazenar os atributos e m�todos do objeto ENDERECO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class Endereco {
	
	private int codigo;
	private String logadouro;
	private String bairro;
	private String numero;
	private String cep;
	private String cidade;
	private String estado;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getLogadouro() {
		return logadouro;
	}
	public void setLogadouro(String logadouro) {
		this.logadouro = logadouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

}// fim da classe