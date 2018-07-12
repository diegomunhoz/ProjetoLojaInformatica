package br.com.choice.projetolojainformatica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.projetolojainformatica.model.Venda;

/**
 * Classe responsável por armazenar os métodos de VendaDAO
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class VendaDAO {
	
	private Connection bd;
	
	public VendaDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(Venda venda) throws SQLException{
		String sql = "delete from cliente where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, venda.getCodigo());
		comando.execute();		
	}
	
	public void alterar(Venda venda) throws SQLException{
		String sql = "update venda set valor_total=? where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setDouble(1, venda.getValorTotal());
		comando.setInt(2, venda.getCodigo());
		comando.execute();		
	}
	
	public void inserir(Venda venda) throws SQLException{
		String sql = "insert into venda (codigo, cliente_codigo, funcionario_codigo, valor_total) values (?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, venda.getCodigo());
		comando.setInt(2, venda.getClienteCodigo());
		comando.setInt(3, venda.getFucionarioCodigo());
		comando.setDouble(4, venda.getValorTotal());
		comando.execute();
	}

	public List<Venda> buscarTodos() throws SQLException{
		String sql = "select * from venda order by nome";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Venda> listaVenda = new ArrayList<Venda>();
		
		while (cursor.next()) {
			Venda venda = new Venda();
			venda.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			venda.setClienteCodigo(Integer.parseInt(cursor.getString("cliente_codigo")));
			venda.setFucionarioCodigo(Integer.parseInt(cursor.getString("funcionario_codigo")));
			venda.setValorTotal(Double.parseDouble(cursor.getString("valor_total")));
			listaVenda.add(venda);
		}
		return listaVenda;
	}

}//fim da classe