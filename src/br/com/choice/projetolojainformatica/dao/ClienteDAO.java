package br.com.choice.projetolojainformatica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.projetolojainformatica.model.Cliente;

/**
 * Classe responsável por armazenar os métodos de ClienteDAO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class ClienteDAO {
	
	private Connection bd;
	private int codigo;
	
	public ClienteDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(int codigo) throws SQLException{
		String sql = "delete from cliente where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, codigo);
		comando.execute();		
	}
	
	public void alterar(Cliente cliente) throws SQLException{
		String sql = "update cliente set email=?, telefone=?, celular=? where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setString(1, cliente.getEmail());
		comando.setString(2, cliente.getTelefone());
		comando.setString(3, cliente.getCelular());
		comando.setInt(4, cliente.getCodigo());
		comando.execute();		
	}
	
	public void inserir(Cliente cliente) throws SQLException{
		String sql = "insert into cliente (nome, email, telefone, celular) values (?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setString(1, cliente.getNome());
		comando.setString(2, cliente.getEmail());
		comando.setString(3, cliente.getTelefone());
		comando.setString(4, cliente.getCelular());
		comando.execute();
	}

	public List<Cliente> buscarTodos() throws SQLException{
		String sql = "select * from cliente order by codigo";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		while (cursor.next()) {
			Cliente cliente = new Cliente();
			cliente.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			cliente.setNome(cursor.getString("nome"));
			cliente.setEmail(cursor.getString("email"));
			cliente.setTelefone(cursor.getString("telefone"));
			cliente.setCelular(cursor.getString("celular"));
			listaCliente.add(cliente);
		}
		return listaCliente;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public List<Cliente> buscarNome(String nomeRecebido) throws SQLException{
		String sql = "select * from cliente where nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setString(1, nomeRecebido);
		ResultSet cursor = comando.executeQuery();
		List<Cliente> listaCliente = new ArrayList<Cliente>();
		
		while (cursor.next()) {
			Cliente cliente = new Cliente();
			cliente.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			cliente.setNome(cursor.getString("nome"));
			cliente.setEmail(cursor.getString("email"));
			cliente.setTelefone(cursor.getString("telefone"));
			cliente.setCelular(cursor.getString("celular"));
			listaCliente.add(cliente);
		}
		return listaCliente;		
	}
	
	public int buscarUltimo() throws SQLException{
		String sql = "select max(codigo) from cliente";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		int	codigo = Integer.parseInt(cursor.getString("codigo"));
		return codigo;		
	}

}//fim da classe