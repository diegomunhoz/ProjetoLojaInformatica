package br.com.choice.projetolojainformatica.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.choice.projetolojainformatica.model.Produto;

/**
 * Classe responsável por armazenar os métodos de ProdutoDAO
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class ProdutoDAO {
	
	private Connection bd;
	
	public ProdutoDAO(Connection bd){
		this.bd = bd;
	}
	
	public void excluir(int codigo) throws SQLException{
		String sql = "delete from produto where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, codigo);
		comando.execute();
	}
	
	public void alterar(Produto produto) throws SQLException{
		String sql = "update produto set estoque=?, custo=?, venda=? where codigo=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, produto.getEstoque());
		comando.setDouble(2, produto.getCusto());
		comando.setDouble(3, produto.getVenda());
		comando.setInt(4, produto.getCodigo());
		comando.execute();
	}
	
	public void inserir(Produto produto) throws SQLException{
		String sql = "insert into produto (codigo, nome, especificacao, estoque, custo, venda) values (?,?,?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		comando.setInt(1, produto.getCodigo());
		comando.setString(2, produto.getNome());
		comando.setString(3, produto.getEspecificacao());
		comando.setInt(4, produto.getEstoque());
		comando.setDouble(5, produto.getCusto());
		comando.setDouble(6, produto.getVenda());
		comando.execute();
	}

	public List<Produto> buscarTodos() throws SQLException{
		String sql = "select * from produto order by nome";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Produto> listaProduto = new ArrayList<Produto>();
		while (cursor.next()) {
			Produto produto = new Produto();
			produto.setCodigo(Integer.parseInt(cursor.getString("codigo")));
			produto.setNome(cursor.getString("nome"));
			produto.setEspecificacao(cursor.getString("especificacao"));
			produto.setEstoque(Integer.parseInt(cursor.getString("estoque")));
			produto.setCusto(Double.parseDouble(cursor.getString("custo")));
			produto.setVenda(Double.parseDouble(cursor.getString("venda")));
			listaProduto.add(produto);
		}
		return listaProduto;
	}

}//fim da classe