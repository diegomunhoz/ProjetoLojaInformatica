package br.com.choice.projetolojainformatica.model;

/**
 * Classe responsável por armazenar os atributos e métodos do objeto FUNCIONARIO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class Funcionario {
	
	private int codigo;
	private String nome;
	private String cargo;
	private int codigoEndereco;
	private double salario;
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getCodigoEndereco() {
		return codigoEndereco;
	}
	public void setCodigoEndereco(int codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}
	
}// fim da classe