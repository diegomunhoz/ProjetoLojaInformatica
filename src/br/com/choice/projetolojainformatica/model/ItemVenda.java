package br.com.choice.projetolojainformatica.model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto ITEM_VENDA
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class ItemVenda {

	private int codigo;
	private int quantidade;
	private double valorUnitario;
	private double valorTotal;
	private int produtoCodigo;
	private int vendaCodigo;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValorUnitario() {
		return valorUnitario;
	}
	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public int getProdutoCodigo() {
		return produtoCodigo;
	}
	public void setProdutoCodigo(int produtoCodigo) {
		this.produtoCodigo = produtoCodigo;
	}
	public int getVendaCodigo() {
		return vendaCodigo;
	}
	public void setVendaCodigo(int vendaCodigo) {
		this.vendaCodigo = vendaCodigo;
	}

}// fim da classe