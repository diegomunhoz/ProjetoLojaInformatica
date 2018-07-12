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

import br.com.choice.projetolojainformatica.dao.ClienteDAO;
import br.com.choice.projetolojainformatica.model.Cliente;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

/**
 * Classe responsável por criar um JFrame para consulta de cliente
 * 
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class TelaExibeCliente {

	private JFrame janelaExibeCliente;
	private JPanel painelDaJanelaExibeCliente;
	private JTable tabelaCliente;
	private JButton btnSair;

	private JScrollPane painelDeScrollCliente;

	private String[] colunas = new String[] { "Nome", "Telefone", "E-mail" };
	private String[][] dados = new String[][] { {} };

	public TelaExibeCliente() {
		inicia();
	}

	public void inicia() {

		janelaExibeCliente = new JFrame("CONSULTA DE ALUNO");
		btnSair = new JButton("SAIR");

		painelDaJanelaExibeCliente = (JPanel) janelaExibeCliente
				.getContentPane();
		painelDaJanelaExibeCliente.setLayout(null);

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaCliente = new JTable(modelo);

		tabelaCliente.setEnabled(false);

		painelDeScrollCliente = new JScrollPane(tabelaCliente);
		painelDeScrollCliente
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		painelDeScrollCliente.setBounds(0, 0, 560, 310);
		tabelaCliente.setBounds(0, 0, 500, 310);
		btnSair.setBounds(190, 320, 150, 30);

		painelDaJanelaExibeCliente.add(painelDeScrollCliente);
		painelDaJanelaExibeCliente.add(btnSair);

		btnSair.addActionListener(new SairFrameListener());

		listarCliente();

		janelaExibeCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaExibeCliente.setSize(575, 400);
		janelaExibeCliente.setLocationRelativeTo(null);
		janelaExibeCliente.setVisible(true);

	}

	public class SairFrameListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			janelaExibeCliente.setVisible(false);
		}
	}

	public void listarCliente() {
		DefaultTableModel m = (DefaultTableModel) tabelaCliente.getModel();
		m.removeRow(0);
		Connection bd = ConnectionFactory.getConnection();
		try {
			ClienteDAO dao = new ClienteDAO(bd);
			List<Cliente> listaCliente = dao.buscarTodos();
			for (Cliente cliente : listaCliente) {
				DefaultTableModel modelo = (DefaultTableModel) tabelaCliente
						.getModel();
				modelo.addRow(new String[] { cliente.getNome(),
						cliente.getTelefone(), cliente.getCelular() });
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTES.");
		}
	}

}// fim da classe