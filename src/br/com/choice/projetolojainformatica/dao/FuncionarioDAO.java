package br.com.choice.projetolojainformatica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.projetolojainformatica.model.Funcionario;

/**
 * Classe responsável por armazenar os métodos de FuncionarioDAO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class FuncionarioDAO {
	
	private Connection bd;
	
	public FuncionarioDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(int codigo) throws SQLException{
		String sql = "delete from funcionario where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, codigo);
		comando.execute();
	}

	public void alterar(Funcionario funcionario) throws SQLException{
		String sql = "update funcionario set cargo=?, salario=? where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setString(1, funcionario.getCargo());
		comando.setDouble(2, funcionario.getSalario());
		comando.setInt(3, funcionario.getCodigo());
		comando.execute();		
	}
	
	public void inserir(Funcionario funcionario) throws SQLException{
		String sql = "insert into funcionario (codigo, nome, cargo, salario, endereco_codigo) values (?,?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, funcionario.getCodigo());
		comando.setString(2, funcionario.getNome());
		comando.setString(3, funcionario.getCargo());
		comando.setDouble(4, funcionario.getSalario());
		comando.setInt(5, funcionario.getCodigoEndereco());
		comando.execute();
	}

	public List<Funcionario> buscarTodos() throws SQLException{
		String sql = "select * from funcionario order by nome";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		while (cursor.next()) {
			Funcionario funcionario = new Funcionario();
			funcionario.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			funcionario.setNome(cursor.getString("nome"));
			funcionario.setCargo(cursor.getString("cargo"));
			funcionario.setSalario(Double.parseDouble(cursor.getString("salario")));
			funcionario.setCodigoEndereco(Integer.parseInt(cursor.getString("endereco_codigo")));
			listaFuncionario.add(funcionario);
		}
		return listaFuncionario;
	}
	
}//fim da classe