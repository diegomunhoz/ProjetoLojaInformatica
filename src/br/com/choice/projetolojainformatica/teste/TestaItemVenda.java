package br.com.choice.projetolojainformatica.teste;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.choice.projetolojainformatica.dao.ItemVendaDAO;
import br.com.choice.projetolojainformatica.model.ItemVenda;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

public class TestaItemVenda {

	public static void main(String[] args) {
		Connection bd = ConnectionFactory.getConnection();
		try {
			ItemVenda vo = new ItemVenda();
			vo.setVendaCodigo(2);
			ItemVendaDAO dao = new ItemVendaDAO(bd);
			List<ItemVenda>listaCliente = dao.buscarTodos(vo);
			for (ItemVenda item: listaCliente) {
				System.out.println(item.getProdutoCodigo());
			}

			
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTE.");
			e.printStackTrace();
		}


	}


}
