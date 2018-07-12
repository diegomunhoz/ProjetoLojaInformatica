package br.com.choice.projetolojainformatica.gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.com.choice.projetolojainformatica.dao.ClienteDAO;
import br.com.choice.projetolojainformatica.dao.FuncionarioDAO;
import br.com.choice.projetolojainformatica.dao.ItemVendaDAO;
import br.com.choice.projetolojainformatica.dao.ProdutoDAO;
import br.com.choice.projetolojainformatica.dao.VendaDAO;
import br.com.choice.projetolojainformatica.model.Cliente;
import br.com.choice.projetolojainformatica.model.Funcionario;
import br.com.choice.projetolojainformatica.model.ItemVenda;
import br.com.choice.projetolojainformatica.model.Produto;
import br.com.choice.projetolojainformatica.model.Venda;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

/**
 * Classe responsável por criar JFrame para o menu de VENDA
 * 
 * @author Diego Munhoz
 * @since 28/02/2014
 */
public class TelaCadastroVenda {

	List<Cliente> listaCliente = new ArrayList<Cliente>();
	List<Funcionario> listaFucionario = new ArrayList<Funcionario>();
	List<Produto> listaProduto = new ArrayList<Produto>();
	List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();

	private JFrame janelaCadastroVenda;
	private JPanel painelDaJanelaCadastroVenda;

	private JTable tabelaCadastroVenda;
	private JScrollPane painelDeScroll;

	private JLabel jlbCodigo;
	private JLabel jlbNomeCliente;
	private JLabel jlbNomeProduto;
	private JLabel jlbNomeFuncionario;
	private JLabel jlbQuantidade;
	private JLabel jlbValorTotalVenda;
	private JLabel jlbValorPagoVenda;
	private JLabel jlbValorTrocoVenda;

	private JTextField jtfCodigo;
	private JTextField jtfQuantidade;
	private JTextField jtfValorTotalVenda;
	private JTextField jtfValorPagoVenda;
	private JTextField jtfValorTrocoVenda;

	private JButton btnNovaVenda;
	private JButton btnIncluirProduto;
	private JButton btnAlterarProduto;
	private JButton btnExcluirProduto;
	private JButton btnSalvarVenda;
	private JButton btnCancelarVenda;
	private JButton btnSairVenda;

	private JComboBox cboxCliente;
	private String[] clienteComps = { "- Selecione um CLIENTE -" };

	private JComboBox cboxFuncionario;
	private String[] funcionarioComps = { "- Selecione um FUNCIONÁRIO -" };

	private JComboBox cboxProduto;
	private String[] produtoComps = { "- Selecione um PRODUTO -" };

	private String[] colunas = new String[] { "Código", "Nome", "Quantidade",
			"Valor", "Valor Total" };
	private String[][] dados = new String[][] { {} };

	private double valorTotalVenda;
	private int quantidadeConvertida;
	private double valorTotalProduto;

	private int codigoConvertido;

	private boolean validaVenda;
	private boolean validaLinha;

	public TelaCadastroVenda() {
		inicia();
	}

	public void inicia() {

		janelaCadastroVenda = new JFrame("VENDA DE PRODUTO");

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaCadastroVenda = new JTable(modelo);
		modelo.removeRow(0);

		painelDeScroll = new JScrollPane(tabelaCadastroVenda);
		painelDeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jlbCodigo = new JLabel("Código:");
		jlbNomeCliente = new JLabel("Nome do Cliente:");
		jlbNomeProduto = new JLabel("Descrição Produto:");
		jlbNomeFuncionario = new JLabel("Nome do Funcionario:");
		jlbQuantidade = new JLabel("QTDE:");
		jlbValorTotalVenda = new JLabel("Valor Total:");
		jlbValorPagoVenda = new JLabel("Valor Pago:");
		jlbValorTrocoVenda = new JLabel("Troco:");

		jtfCodigo = new JTextField("");
		jtfQuantidade = new JTextField("");
		jtfValorTotalVenda = new JTextField("R$ 0,00");
		jtfValorPagoVenda = new JTextField("");
		jtfValorTrocoVenda = new JTextField("R$ 0,00");

		cboxCliente = new JComboBox(clienteComps);
		cboxFuncionario = new JComboBox(funcionarioComps);
		cboxProduto = new JComboBox(produtoComps);

		btnIncluirProduto = new JButton("INCLUIR PRODUTO");
		btnAlterarProduto = new JButton("ALTERAR PRODUTO");
		btnExcluirProduto = new JButton("EXCLUIR PRODUTO");
		btnNovaVenda = new JButton("NOVO");
		btnSalvarVenda = new JButton("SALVAR");
		btnCancelarVenda = new JButton("CANCELAR");
		btnSairVenda = new JButton("SAIR");

		jlbCodigo.setBounds(10, 10, 100, 30);
		jlbNomeCliente.setBounds(10, 50, 150, 30);
		jlbNomeFuncionario.setBounds(10, 90, 150, 30);
		jlbNomeProduto.setBounds(10, 130, 150, 30);
		jlbQuantidade.setBounds(530, 130, 90, 30);

		jlbValorTotalVenda.setBounds(10, 380, 120, 30);
		jlbValorPagoVenda.setBounds(230, 380, 150, 30);
		jlbValorTrocoVenda.setBounds(460, 380, 150, 30);

		jtfCodigo.setBounds(160, 10, 120, 30);
		jtfQuantidade.setBounds(580, 130, 80, 30);
		jtfValorTotalVenda.setBounds(80, 380, 120, 30);
		jtfValorPagoVenda.setBounds(310, 380, 120, 30);
		jtfValorTrocoVenda.setBounds(520, 380, 120, 30);

		cboxCliente.setBounds(160, 50, 500, 30);
		cboxFuncionario.setBounds(160, 90, 500, 30);
		cboxProduto.setBounds(160, 130, 350, 30);

		painelDeScroll.setBounds(0, 220, 685, 145);
		tabelaCadastroVenda.setBounds(0, 220, 685, 145);

		btnIncluirProduto.setBounds(30, 175, 180, 30);
		btnAlterarProduto.setBounds(250, 175, 180, 30);
		btnExcluirProduto.setBounds(470, 175, 180, 30);
		btnNovaVenda.setBounds(210, 420, 120, 30);
		btnSalvarVenda.setBounds(210, 420, 120, 30);
		btnCancelarVenda.setBounds(340, 420, 120, 30);
		btnSairVenda.setBounds(340, 420, 120, 30);

		tabelaCadastroVenda.setEnabled(false);

		painelDaJanelaCadastroVenda = (JPanel) janelaCadastroVenda
				.getContentPane();
		painelDaJanelaCadastroVenda.setLayout(null);

		cboxCliente.setSelectedIndex(0);
		cboxFuncionario.setSelectedIndex(0);
		cboxProduto.setSelectedIndex(0);

		painelDaJanelaCadastroVenda.add(painelDeScroll);
		painelDaJanelaCadastroVenda.add(jlbCodigo);
		painelDaJanelaCadastroVenda.add(jlbNomeCliente);
		painelDaJanelaCadastroVenda.add(jlbNomeFuncionario);
		painelDaJanelaCadastroVenda.add(jlbNomeProduto);
		painelDaJanelaCadastroVenda.add(jlbQuantidade);
		painelDaJanelaCadastroVenda.add(jlbValorTotalVenda);
		painelDaJanelaCadastroVenda.add(jlbValorPagoVenda);
		painelDaJanelaCadastroVenda.add(jlbValorTrocoVenda);
		painelDaJanelaCadastroVenda.add(jtfCodigo);
		painelDaJanelaCadastroVenda.add(jtfQuantidade);
		painelDaJanelaCadastroVenda.add(jtfValorTotalVenda);
		painelDaJanelaCadastroVenda.add(jtfValorPagoVenda);
		painelDaJanelaCadastroVenda.add(jtfValorTrocoVenda);
		painelDaJanelaCadastroVenda.add(cboxCliente);
		painelDaJanelaCadastroVenda.add(cboxFuncionario);
		painelDaJanelaCadastroVenda.add(cboxProduto);
		painelDaJanelaCadastroVenda.add(btnSalvarVenda);
		painelDaJanelaCadastroVenda.add(btnNovaVenda);
		painelDaJanelaCadastroVenda.add(btnCancelarVenda);
		painelDaJanelaCadastroVenda.add(btnSairVenda);
		painelDaJanelaCadastroVenda.add(btnIncluirProduto);
		painelDaJanelaCadastroVenda.add(btnAlterarProduto);
		painelDaJanelaCadastroVenda.add(btnExcluirProduto);

		btnIncluirProduto
		.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				adicionaProduto();
			}
		});

		btnAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
			}
		});

		btnExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				excluirProduto();
			}
		});

		btnNovaVenda.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				liberarTela();
			}
		});

		btnSalvarVenda.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				salvarVenda();
			}
		});

		btnSairVenda.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				janelaCadastroVenda.setVisible(false);
			}
		});

		btnCancelarVenda.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				limparTela();
				bloquearTela();
			}
		});

		listarCliente();
		listarFuncionario();
		listarProduto();
		bloquearTela();

		janelaCadastroVenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		janelaCadastroVenda.setSize(700, 500);
		janelaCadastroVenda.setLocationRelativeTo(null);
		janelaCadastroVenda.setVisible(true);
	}

	public void listarCliente() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			ClienteDAO dao = new ClienteDAO(bd);
			listaCliente = dao.buscarTodos();
			for (Cliente cliente : listaCliente) {
				cboxCliente.addItem(cliente.getNome());
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTE.");
		}
	}

	public void listarFuncionario() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			FuncionarioDAO dao = new FuncionarioDAO(bd);
			listaFucionario = dao.buscarTodos();
			for (Funcionario funcionario : listaFucionario) {
				cboxFuncionario.addItem(funcionario.getNome());
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir FUNCIONÁRIO.");
		}
	}

	public void listarProduto() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			ProdutoDAO dao = new ProdutoDAO(bd);
			listaProduto = dao.buscarTodos();
			for (Produto produto : listaProduto) {
				cboxProduto.addItem(produto.getNome());
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir PRODUTO.");
		}
	}

	public void adicionaProduto() {
		validaLinha = false;
		validarLinha();
		if (validaLinha == false) {
			DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroVenda
					.getModel();
			for (Produto produto : listaProduto) {
				if (cboxProduto.getSelectedItem().equals(produto.getNome())) {
					valorTotalProduto = produto.getVenda()
							* quantidadeConvertida;
					valorTotalVenda = valorTotalVenda + valorTotalProduto;
					modelo.addRow(new String[] { "" + produto.getCodigo(),
							produto.getNome().toUpperCase(),
							"" + quantidadeConvertida,
							"R$ " + produto.getVenda(),
							"R$ " + valorTotalProduto });
					jtfValorTotalVenda.setText("R$ " + valorTotalVenda);
					ItemVenda itemVenda = new ItemVenda();
					itemVenda.setQuantidade(quantidadeConvertida);
					itemVenda.setValorUnitario(produto.getVenda());
					itemVenda.setValorTotal(valorTotalProduto);
					itemVenda.setProdutoCodigo(produto.getCodigo());
					itemVenda.setVendaCodigo(codigoConvertido);
					listaItemVenda.add(itemVenda);
					valorTotalProduto = 0;
					jtfQuantidade.setText(null);
					cboxProduto.setSelectedIndex(0);
				}
			}
		}
	}

	public void validarLinha() {
		if (jtfCodigo.getText().equals(null) || jtfCodigo.getText().equals("")) {
			validaLinha = true;
			JOptionPane.showMessageDialog(null,
					"Informe o código, campo obrigatório.");
		} else {
			try {
				codigoConvertido = Integer.parseInt(jtfCodigo.getText());
			} catch (Exception e) {
				validaVenda = true;
				JOptionPane.showMessageDialog(null,
						"Código inválido, digite novamente.");
			}
		}
		if (jtfQuantidade.equals("") || jtfQuantidade.equals(null)) {
			validaLinha = true;
			JOptionPane.showMessageDialog(null,
					"Informe a quantidade, campo obrigatório.");
		} else {
			try {
				quantidadeConvertida = Integer
						.parseInt(jtfQuantidade.getText());
			} catch (Exception e) {
				validaLinha = true;
				JOptionPane.showMessageDialog(null,
						"Valor quantidade inválido, digite novamente.");
			}
		}
		if (cboxProduto.getSelectedIndex() == 0) {
			validaLinha = true;
			JOptionPane.showMessageDialog(null,
					"Selecione um produto, campo obrigatório.");

		}
	}

	public void salvarVenda() {
		validaVenda = false;
		validarVenda();
		if (validaVenda == false) {
			Connection bdVenda = ConnectionFactory.getConnection();
			Venda venda = new Venda();
			int indCliente = cboxCliente.getSelectedIndex();
			indCliente--;
			int indFuncionario = cboxFuncionario.getSelectedIndex();
			indFuncionario--;
			venda.setCodigo(codigoConvertido);
			venda.setClienteCodigo(listaCliente.get(indCliente).getCodigo());
			venda.setFucionarioCodigo(listaFucionario.get(indFuncionario)
					.getCodigo());
			venda.setValorTotal(valorTotalVenda);
			try {
				VendaDAO dao = new VendaDAO(bdVenda);
				dao.inserir(venda);
				salvarItemVenda();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro ao inserir no méotodo VENDA.");
			}
		}
	}

	public void salvarItemVenda() {
		Connection bdItemVenda = ConnectionFactory.getConnection();
		ItemVenda itemVenda = new ItemVenda();
		for (ItemVenda p : listaItemVenda) {
			itemVenda.setQuantidade(p.getQuantidade());
			itemVenda.setValorUnitario(p.getValorUnitario());
			itemVenda.setValorTotal(p.getValorTotal());
			itemVenda.setProdutoCodigo(p.getProdutoCodigo());
			itemVenda.setVendaCodigo(p.getVendaCodigo());
			try {
				ItemVendaDAO dao = new ItemVendaDAO(bdItemVenda);
				dao.inserir(itemVenda);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,
						"Erro ao inserir no méotodo da ITEM_VENDA.");
			}
		}
		JOptionPane.showMessageDialog(null, "Venda efetuada com sucesso");
		limparTela();
		bloquearTela();
	}

	public void validarVenda() {
		if (jtfCodigo.getText().equals(null) || jtfCodigo.getText().equals("")) {
			validaVenda = true;
			JOptionPane.showMessageDialog(null,
					"Informe o código, campo obrigatório.");
		} else {
			try {
				codigoConvertido = Integer.parseInt(jtfCodigo.getText());
			} catch (Exception e) {
				validaVenda = true;
				JOptionPane.showMessageDialog(null,
						"Código inválido, digite novamente.");
			}
		}
		if (cboxCliente.getSelectedIndex() == 0) {
			validaVenda = true;
			JOptionPane.showMessageDialog(null,
					"Selecione um cliente, campo obrigatório.");
		}

		if (cboxFuncionario.getSelectedIndex() == 0) {
			validaVenda = true;
			JOptionPane.showMessageDialog(null,
					"Selecione um funcionario, campo obrigatório.");
		}

		if (listaItemVenda.size() < 1) {
			validaVenda = true;
			JOptionPane.showMessageDialog(null,
					"É necessário adicionar produtos à venda.");
		}
	}

	public void bloquearTela() {
		btnCancelarVenda.setVisible(false);
		btnNovaVenda.setVisible(true);
		btnSairVenda.setVisible(true);
		btnSalvarVenda.setVisible(false);
		jtfCodigo.setEditable(false);
		jtfQuantidade.setEditable(false);
		jtfValorPagoVenda.setEditable(false);
		jtfValorTotalVenda.setEditable(false);
		jtfValorTrocoVenda.setEditable(false);
		cboxCliente.setEnabled(false);
		cboxFuncionario.setEnabled(false);
		cboxProduto.setEnabled(false);
		btnIncluirProduto.setEnabled(false);
		btnAlterarProduto.setEnabled(false);
		btnExcluirProduto.setEnabled(false);
	}

	public void liberarTela() {
		btnCancelarVenda.setVisible(true);
		btnNovaVenda.setVisible(false);
		btnSairVenda.setVisible(false);
		btnSalvarVenda.setVisible(true);
		jtfCodigo.setEditable(true);
		jtfQuantidade.setEditable(true);
		jtfValorPagoVenda.setEditable(true);
		cboxCliente.setEnabled(true);
		cboxFuncionario.setEnabled(true);
		cboxProduto.setEnabled(true);
		btnIncluirProduto.setEnabled(true);
		btnAlterarProduto.setEnabled(true);
		btnExcluirProduto.setEnabled(true);
		jtfCodigo.setFocusable(true);
		tabelaCadastroVenda.setEnabled(true);
	}

	public void alterarProduto(){
		DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroVenda
				.getModel();
		int indSelecionado = tabelaCadastroVenda.getSelectedRow();
		indSelecionado++;
		for (ItemVenda itemVenda: listaItemVenda) {
			if (itemVenda.getCodigo() == tabelaCadastroVenda.getSelectedRow()) {
				int ind = itemVenda.getCodigo();
				
			}
		}
	}

	public void excluirProduto(){
		DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroVenda
				.getModel();
		modelo.removeRow(tabelaCadastroVenda.getSelectedRow());
	}

	public void limparTela() {
		DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroVenda
				.getModel();
		if (modelo.getRowCount() != 0) {
			for (int i = 0; i < modelo.getRowCount(); i++) {
				modelo.removeRow(i);
			}
		}
		jtfCodigo.setText(null);
		jtfQuantidade.setText(null);
		jtfValorTotalVenda.setText("R$ 0,00");
		jtfValorPagoVenda.setText(null);
		jtfValorTrocoVenda.setText("R$ 0,00");
		cboxCliente.setSelectedIndex(0);
		cboxFuncionario.setSelectedIndex(0);
		cboxProduto.setSelectedIndex(0);
	}
	
}// fim da classe