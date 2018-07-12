package br.com.choice.projetolojainformatica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.projetolojainformatica.model.Endereco;

/**
 * Classe responsável por armazenar os métodos de EnderecoDAO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class EnderecoDAO {
	
	private Connection bd;
	
	public EnderecoDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(Endereco endereco) throws SQLException{
		String sql = "delete from endereco where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, endereco.getCodigo());
		comando.execute();		
	}
	
	public void alterar(Endereco endereco) throws SQLException{
		String sql = "update endereco set logadouro=?, bairro=?, numero=?, cep=?, cidade=?, estado=? where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setString(1, endereco.getLogadouro());
		comando.setString(2, endereco.getBairro());
		comando.setString(3, endereco.getNumero());
		comando.setString(4, endereco.getCep());
		comando.setString(5, endereco.getCidade());
		comando.setString(6, endereco.getEstado());
		comando.setInt(7, endereco.getCodigo());
		comando.execute();		
	}
	
	public void inserir(Endereco endereco) throws SQLException{
		String sql = "insert into endereco (codigo, logadouro, bairro, numero, cep, cidade, estado) values (?,?,?,?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, endereco.getCodigo());
		comando.setString(2, endereco.getLogadouro());
		comando.setString(3, endereco.getBairro());
		comando.setString(4, endereco.getNumero());
		comando.setString(5, endereco.getCep());
		comando.setString(6, endereco.getCidade());
		comando.setString(7, endereco.getEstado());
		comando.execute();
	}

	public List<Endereco> buscarTodos() throws SQLException{
		String sql = "select * from endereco";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		while (cursor.next()) {
			Endereco endereco = new Endereco();
			endereco.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			endereco.setLogadouro(cursor.getString("logadouro"));
			endereco.setBairro(cursor.getString("bairro"));
			endereco.setNumero(cursor.getString("numero"));
			endereco.setCep(cursor.getString("cep"));
			endereco.setCidade(cursor.getString("cidade"));
			endereco.setEstado(cursor.getString("estado"));
			listaEndereco.add(endereco);
		}
		return listaEndereco;
	}
	
	public Endereco buscarEnderecoCodigo(int codigo){
		Endereco endereco = new Endereco();
		try {
			String sql = "select * from endereco where codigo=?";
			PreparedStatement comando = bd.prepareStatement(sql);
			comando.setInt(1, codigo);
			ResultSet cursor = comando.executeQuery();
			while (cursor.next()) {
				endereco.setCodigo(Integer.parseInt(cursor.getString("codigo")));
				endereco.setLogadouro(cursor.getString("logadouro"));
				endereco.setNumero(cursor.getString("numero"));
				endereco.setBairro(cursor.getString("bairro"));
				endereco.setCep(cursor.getString("cep"));
				endereco.setCidade(cursor.getString("cidade"));
				endereco.setEstado(cursor.getString("estado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return endereco;
	}
	
}//fim da classe