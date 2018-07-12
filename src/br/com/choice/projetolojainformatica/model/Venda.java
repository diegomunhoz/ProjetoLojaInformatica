package br.com.choice.projetolojainformatica.model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto CLIENTE
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class Venda {

	private int codigo;
	private int clienteCodigo;
	private int fucionarioCodigo;
	private double valorTotal;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getClienteCodigo() {
		return clienteCodigo;
	}
	public void setClienteCodigo(int clienteCodigo) {
		this.clienteCodigo = clienteCodigo;
	}
	public int getFucionarioCodigo() {
		return fucionarioCodigo;
	}
	public void setFucionarioCodigo(int fucionarioCodigo) {
		this.fucionarioCodigo = fucionarioCodigo;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}// fim da classe