package br.com.choice.projetolojainformatica.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.choice.projetolojainformatica.dao.ProdutoDAO;
import br.com.choice.projetolojainformatica.model.Produto;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

/**
 * Classe responsável por criar um JFrame para consulta de cliente
 * 
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class TelaExibeProduto {

	private JFrame janelaExibeProduto;
	private JPanel painelDajanelaExibeProduto;
	private JTable tabelaProduto;
	private JButton btnSair;

	private JScrollPane painelDeScrollProduto;

	private String[] colunas = new String[] { "Nome", "Especificação",
			"Estoque", "Custo", "Venda" };
	private String[][] dados = new String[][] { {} };

	public TelaExibeProduto() {
		inicia();
	}

	public void inicia() {

		janelaExibeProduto = new JFrame("CONSULTA DE PRODUTO");
		btnSair = new JButton("SAIR");

		painelDajanelaExibeProduto = (JPanel) janelaExibeProduto
				.getContentPane();
		painelDajanelaExibeProduto.setLayout(null);

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaProduto = new JTable(modelo);

		tabelaProduto.setEnabled(false);

		painelDeScrollProduto = new JScrollPane(tabelaProduto);
		painelDeScrollProduto
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		painelDeScrollProduto.setBounds(0, 0, 560, 310);
		tabelaProduto.setBounds(0, 0, 500, 310);
		btnSair.setBounds(190, 320, 150, 30);

		painelDajanelaExibeProduto.add(painelDeScrollProduto);
		painelDajanelaExibeProduto.add(btnSair);

		btnSair.addActionListener(new SairFrameListener());

		listarProduto();

		janelaExibeProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaExibeProduto.setSize(575, 400);
		janelaExibeProduto.setLocationRelativeTo(null);
		janelaExibeProduto.setVisible(true);

	}

	public class SairFrameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			janelaExibeProduto.setVisible(false);
		}
	}

	public void listarProduto() {
		DefaultTableModel m = (DefaultTableModel) tabelaProduto.getModel();
		m.removeRow(0);
		Connection bd = ConnectionFactory.getConnection();
		try {
			ProdutoDAO dao = new ProdutoDAO(bd);
			List<Produto> listaProduto = dao.buscarTodos();
			for (Produto produto : listaProduto) {
				DefaultTableModel modelo = (DefaultTableModel) tabelaProduto
						.getModel();
				modelo.addRow(new String[] { produto.getNome(),
						produto.getEspecificacao(), "" + produto.getEstoque(),
						"R$ " + produto.getCusto() + "0", "R$ " + produto.getVenda() + "0" });
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir PRODUTOS.");
		}
	}

}// fim da classe