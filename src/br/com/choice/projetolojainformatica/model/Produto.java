package br.com.choice.projetolojainformatica.model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto FUNCIONARIO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class Produto {
	
	private int codigo;
	private String nome;
	private String especificacao;
	private int estoque;
	private double custo;
	private double venda;
	
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
	public String getEspecificacao() {
		return especificacao;
	}
	public void setEspecificacao(String especificacao) {
		this.especificacao = especificacao;
	}
	public int getEstoque() {
		return estoque;
	}
	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}
	public double getCusto() {
		return custo;
	}
	public void setCusto(double custo) {
		this.custo = custo;
	}
	public double getVenda() {
		return venda;
	}
	public void setVenda(double venda) {
		this.venda = venda;
	}

}// fim da classe