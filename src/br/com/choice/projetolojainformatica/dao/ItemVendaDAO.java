package br.com.choice.projetolojainformatica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.projetolojainformatica.model.ItemVenda;

/**
 * Classe responsável por armazenar os métodos de ItemVendaDAO
 * @author Diego Munhoz
 * @since 27/02/2014
 */

public class ItemVendaDAO {
	
	private Connection bd;
	
	public ItemVendaDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(ItemVenda itemVenda) throws SQLException{
		String sql = "delete from item_venda where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, itemVenda.getCodigo());
		comando.execute();		
	}
	
	public void alterar(ItemVenda itemVenda) throws SQLException{
		String sql = "update item_venda set quantidade=?, valor_unitario=?, valor_total=?, produto_codigo=? where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, itemVenda.getQuantidade());
		comando.setDouble(2, itemVenda.getValorUnitario());
		comando.setDouble(3, itemVenda.getValorTotal());
		comando.setInt(4, itemVenda.getProdutoCodigo());
		comando.setInt(5, itemVenda.getCodigo());
		comando.execute();		
	}
	
	public void inserir(ItemVenda itemVenda) throws SQLException{
		String sql = "insert into item_venda (quantidade, valor_unitario, valor_total, produto_codigo, venda_codigo) values (?,?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, itemVenda.getQuantidade());
		comando.setDouble(2, itemVenda.getValorUnitario());
		comando.setDouble(3, itemVenda.getValorTotal());
		comando.setInt(4, itemVenda.getProdutoCodigo());
		comando.setInt(5, itemVenda.getVendaCodigo());
		comando.execute();
	}

	public List<ItemVenda> buscarTodos(ItemVenda itemVendaRecebido) throws SQLException{
		String sql = "select * from item_venda where venda_codigo=? order by codigo";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, itemVendaRecebido.getVendaCodigo());
		ResultSet cursor = comando.executeQuery();
		List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();
		
		while (cursor.next()) {
			ItemVenda itemVenda = new ItemVenda();
			itemVenda.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			itemVenda.setQuantidade(Integer.parseInt(cursor.getString("quantidade")));
			itemVenda.setValorTotal(Double.parseDouble(cursor.getString("valor_total")));
			itemVenda.setProdutoCodigo(Integer.parseInt(cursor.getString("produto_codigo")));
			itemVenda.setVendaCodigo(Integer.parseInt(cursor.getString("venda_codigo")));
			listaItemVenda.add(itemVenda);
		}
		return listaItemVenda;
	}

}//fim da classe