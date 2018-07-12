package br.com.choice.projetolojainformatica.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.choice.projetolojainformatica.dao.ProdutoDAO;
import br.com.choice.projetolojainformatica.model.Produto;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

/**
 * Classe responsável por criar JFrame para o cadastro de PRODUTO
 * 
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class TelaCadastroProduto {

	List<Produto> listaProduto = new ArrayList<Produto>();
	
	private JFrame janelaCadastroProduto;
	private JPanel painelDaJanelaCadastroProduto;

	private JTable tabelaCadastroProduto;
	private JScrollPane painelDeScroll;

	private JLabel jlbCodigo;
	private JLabel jlbNome;
	private JLabel jlbEspecificacao;
	private JLabel jlbEstoque;
	private JLabel jlbCusto;
	private JLabel jlbVenda;

	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JTextField jtfEspecificacao;
	private JTextField jtfEstoque;
	private JTextField jtfCusto;
	private JTextField jtfVenda;

	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnSair;

	private String[] colunas = new String[] { "Código", "Nome", "Especificação",
			"Estoque", "Custo", "Venda" };
	private String[][] dados = new String[][] { {} };

	private int estoqueConvertido;
	private double custoConvertido;
	private double vendaConvertido;

	private boolean validaProduto = false;
	private boolean validaSalvar = false;
	private boolean validaLinha = false;

	private int indSelecionado;
	
	public TelaCadastroProduto() {
		inicia();
	}
	
	public void inicia(){

		janelaCadastroProduto = new JFrame("CADASTRO DE PRODUTOS");
		painelDaJanelaCadastroProduto = (JPanel) janelaCadastroProduto
				.getContentPane();

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaCadastroProduto = new JTable(modelo);
		modelo.removeRow(0);

		painelDeScroll = new JScrollPane(tabelaCadastroProduto);
		painelDeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jlbCodigo = new JLabel("CODIGO:");
		jlbNome = new JLabel("NOME:");
		jlbEspecificacao = new JLabel("ESPECIFICAÇÃO:");
		jlbEstoque = new JLabel("ESTOQUE:");
		jlbCusto = new JLabel("CUSTO:");
		jlbVenda = new JLabel("VENDA:");

		jtfCodigo = new JTextField();
		jtfNome = new JTextField();
		jtfEspecificacao = new JTextField();
		jtfEstoque = new JTextField();
		jtfCusto = new JTextField();
		jtfVenda = new JTextField();

		btnNovo = new JButton("NOVO");
		btnAlterar = new JButton("ALTERAR");
		btnExcluir = new JButton("EXCLUIR");
		btnSalvar = new JButton("SALVAR");
		btnCancelar = new JButton("CANCELAR");
		btnSair = new JButton("SAIR");

		jlbCodigo.setBounds(20, 70, 70, 20);
		jlbNome.setBounds(20, 100, 50, 20);
		jlbEspecificacao.setBounds(20, 130, 110, 20);
		jlbEstoque.setBounds(20, 160, 70, 20);
		jlbCusto.setBounds(20, 190, 70, 20);
		jlbVenda.setBounds(20, 220, 70, 20);

		jtfCodigo.setBounds(130, 67, 100, 25);
		jtfNome.setBounds(130, 97, 420, 25);
		jtfEspecificacao.setBounds(130, 127, 420, 25);
		jtfEstoque.setBounds(130, 157, 200, 25);
		jtfCusto.setBounds(130, 187, 200, 25);
		jtfVenda.setBounds(130, 217, 200, 25);

		btnExcluir.setBounds(25, 10, 150, 40);
		btnAlterar.setBounds(220, 10, 150, 40);
		btnNovo.setBounds(25, 380, 535, 40);
		btnSalvar.setBounds(220, 10, 150, 40);
		btnCancelar.setBounds(410, 10, 150, 40);
		btnSair.setBounds(410, 10, 150, 40);
		
		painelDeScroll.setBounds(0, 250, 585, 120);
		tabelaCadastroProduto.setBounds(0, 250, 585, 120);

		ImageIcon icone = new ImageIcon(
				"src/br/com/choice/projetolojainformatica/icones/salvar.png");
		btnSalvar.setIcon(icone);

		icone = new ImageIcon(
				"src/br/com/choice/projetolojainformatica/icones/novo_cliente.png");
		btnNovo.setIcon(icone);

		icone = new ImageIcon(
				"src/br/com/choice/projetolojainformatica/icones/alterar.png");
		btnAlterar.setIcon(icone);

		icone = new ImageIcon(
				"src/br/com/choice/projetolojainformatica/icones/excluir.png");
		btnExcluir.setIcon(icone);

		icone = new ImageIcon(
				"src/br/com/choice/projetolojainformatica/icones/cancelar.png");
		btnCancelar.setIcon(icone);

		icone = new ImageIcon(
				"src/br/com/choice/projetolojainformatica/icones/sair.png");
		btnSair.setIcon(icone);

		painelDaJanelaCadastroProduto.add(painelDeScroll);
		painelDaJanelaCadastroProduto.add(jlbCodigo);
		painelDaJanelaCadastroProduto.add(jlbNome);
		painelDaJanelaCadastroProduto.add(jlbEspecificacao);
		painelDaJanelaCadastroProduto.add(jlbEstoque);
		painelDaJanelaCadastroProduto.add(jlbCusto);
		painelDaJanelaCadastroProduto.add(jlbVenda);
		painelDaJanelaCadastroProduto.add(jtfCodigo);
		painelDaJanelaCadastroProduto.add(jtfNome);
		painelDaJanelaCadastroProduto.add(jtfEspecificacao);
		painelDaJanelaCadastroProduto.add(jtfEstoque);
		painelDaJanelaCadastroProduto.add(jtfVenda);
		painelDaJanelaCadastroProduto.add(jtfCusto);
		painelDaJanelaCadastroProduto.add(btnNovo);
		painelDaJanelaCadastroProduto.add(btnAlterar);
		painelDaJanelaCadastroProduto.add(btnExcluir);
		painelDaJanelaCadastroProduto.add(btnCancelar);
		painelDaJanelaCadastroProduto.add(btnSalvar);
		painelDaJanelaCadastroProduto.add(btnSair);
		
		btnNovo.addActionListener(new NovoProdutoListener());
		btnAlterar.addActionListener(new AlterarProdutoListener());
		btnExcluir.addActionListener(new ExcluirProdutoListener());
		btnCancelar.addActionListener(new CancelarListener());
		btnSair.addActionListener(new SairSistemaListener());
		btnSalvar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (validaSalvar == false) {
					salvarProduto();
				}else {
					alterarProduto();
					validaSalvar = false;
				}
			}
		});

		listarProduto();
		bloquearTelaProduto();

		painelDaJanelaCadastroProduto.setLayout(null);

		janelaCadastroProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		janelaCadastroProduto.setSize(600, 470);
		janelaCadastroProduto.setLocationRelativeTo(null);
		janelaCadastroProduto.setVisible(true);

	}
	
	public class NovoProdutoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			liberarTelaProduto();
		}
	}

	public class AlterarProdutoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			validaSalvar = true;
			validaProduto = false;
			validaLinha = false;
			validarLinha();
			liberarTelaProduto();
			for (Produto produto : listaProduto){
				if (produto.getCodigo() == indSelecionado) {
					jtfCodigo.setEditable(false);
					jtfNome.setEditable(false);
					jtfCodigo.setText("" + produto.getCodigo());
					jtfNome.setText(produto.getNome());
					jtfEspecificacao.setText(produto.getEspecificacao());
					jtfCusto.setText("" + produto.getCusto());
					jtfVenda.setText("" + produto.getVenda());
				}
			}
		}
	}

	public class ExcluirProdutoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			validaLinha = false;
			validarLinha();
			excluirProduto();
			limparTabela();
			listarProduto();
		}
	}

	public class CancelarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			limparTelaProduto();
			bloquearTelaProduto();
		}
	}

	public class SairSistemaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			janelaCadastroProduto.setVisible(false);
		}
	}

	public void liberarTelaProduto() {
		jtfNome.setEditable(true);
		jtfEspecificacao.setEditable(true);
		jtfEstoque.setEditable(true);
		jtfCusto.setEditable(true);
		jtfVenda.setEditable(true);
		btnSalvar.setVisible(true);
		btnAlterar.setVisible(false);
		btnNovo.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setVisible(true);
		btnSair.setVisible(false);
	}

	public void bloquearTelaProduto() {
		btnSalvar.setVisible(false);
		btnExcluir.setEnabled(true);
		btnNovo.setEnabled(true);
		btnAlterar.setVisible(true);
		btnSair.setVisible(true);
		btnCancelar.setVisible(false);
		jtfCodigo.setEditable(false);
		jtfNome.setEditable(false);
		jtfEspecificacao.setEditable(false);
		jtfEstoque.setEditable(false);
		jtfCusto.setEditable(false);
		jtfVenda.setEditable(false);
	}

	public void limparTelaProduto() {
		jtfNome.setText("");
		jtfEspecificacao.setText("");
		jtfEstoque.setText("");
		jtfCusto.setText("");
		jtfVenda.setText("");
	}

	public void validarProduto() {
		if (jtfNome.getText().equals(null) || jtfNome.getText().equals("")) {
			validaProduto = true;
			JOptionPane.showMessageDialog(null,
					"Informe o nome, campo obrigatório!");
			jtfNome.grabFocus();
		}
		if (jtfEspecificacao.getText().equals(null) || jtfEspecificacao.getText().equals("")) {
			validaProduto = true;
			JOptionPane.showMessageDialog(null,
					"Informe a especificação, campo obrigatório!");
			jtfEspecificacao.grabFocus();
		}
		if (jtfEstoque.getText().equals(null) || jtfEstoque.getText().equals("")) {
			validaProduto = true;
			JOptionPane.showMessageDialog(null,
					"Informe a quantidade em estoque, campo obrigatório!");
			jtfEstoque.grabFocus();
		}else {
			try {
				estoqueConvertido = Integer.parseInt(jtfEstoque.getText());
			} catch (Exception e) {
				validaProduto = true;
				JOptionPane.showMessageDialog(null,
						"Estoque inválido, digite novamente");
				jtfEstoque.grabFocus();
			}
		}
		if (jtfCusto.getText().equals(null) || jtfCusto.getText().equals("")) {
			validaProduto = true;
			JOptionPane.showMessageDialog(null,
					"Informe o valor de custo, campo obrigatório!");
			jtfCusto.grabFocus();
		}else {
			try {
				custoConvertido = Double.parseDouble(jtfCusto.getText());
			} catch (Exception e) {
				validaProduto = true;
				JOptionPane.showMessageDialog(null,
						"Valor de custo inválido, digite novamente");
				jtfCusto.grabFocus();
			}
		}
		if (jtfVenda.getText().equals(null) || jtfVenda.getText().equals("")) {
			validaProduto = true;
			JOptionPane.showMessageDialog(null,
					"Informe o valor de venda, campo obrigatório!");
			jtfEstoque.grabFocus();
		}else {
			try {
				vendaConvertido = Double.parseDouble(jtfVenda.getText());
			} catch (Exception e) {
				validaProduto = true;
				JOptionPane.showMessageDialog(null,
						"Valor de venda inválido, digite novamente");
				jtfVenda.grabFocus();
			}
		}
	}
	
	public void salvarProduto() {
		validarProduto();
		if (validaProduto == false) {
			Connection bd = ConnectionFactory.getConnection();
			Produto produto = new Produto();
			produto.setNome(jtfNome.getText());
			produto.setEspecificacao(jtfEspecificacao.getText());
			produto.setEstoque(estoqueConvertido);
			produto.setCusto(custoConvertido);
			produto.setVenda(vendaConvertido);
			try {
				ProdutoDAO dao = new ProdutoDAO(bd);
				dao.inserir(produto);
				JOptionPane.showMessageDialog(null,
						"Produto inserido com sucesso");
				limparTelaProduto();
				bd.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir produto.");
			}
		}
	}
	
	public void alterarProduto() {
		if (validaProduto == false) {
			Connection bd = ConnectionFactory.getConnection();
			Produto produto = new Produto();
			produto.setCodigo(Integer.parseInt(jtfCodigo.getText()));
			produto.setNome(jtfNome.getText());
			produto.setEspecificacao(jtfEspecificacao.getText());
			produto.setCusto(Double.parseDouble(jtfCusto.getText()));
			produto.setVenda(Double.parseDouble(jtfVenda.getText()));
			try {
				ProdutoDAO dao = new ProdutoDAO(bd);
				dao.alterar(produto);
				JOptionPane.showMessageDialog(null,
						"Produto alterado com sucesso.");
				limparTelaProduto();
				limparTabela();
				listarProduto();
				bloquearTelaProduto();
				bd.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar cliente");
			}
		}
	}

	public void excluirProduto() {
		if (validaLinha == false) {
			for (Produto produto : listaProduto) {
				if (produto.getCodigo() == indSelecionado){
					Connection bd = ConnectionFactory.getConnection();
					try {
						ProdutoDAO dao = new ProdutoDAO(bd);
						dao.excluir(indSelecionado);
						JOptionPane.showMessageDialog(null,
								"Produto excluido com sucesso.");
						limparTelaProduto();
						limparTabela();
						listarProduto();
						bloquearTelaProduto();
						bd.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir produto");
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void listarProduto() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			ProdutoDAO dao = new ProdutoDAO(bd);
			listaProduto = dao.buscarTodos();
			DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroProduto
					.getModel();
			for (Produto produto: listaProduto) {
				modelo.addRow(new String[] { "" + produto.getCodigo(),
						produto.getNome().toUpperCase(), produto.getEspecificacao(),
						"" + produto.getCusto(), "" + produto.getVenda() });
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTE.");
		}
	}

	public void validarLinha() {
		if (tabelaCadastroProduto.getSelectedRow() == -1) {
			validaLinha = true;
			JOptionPane.showMessageDialog(null,
					"É necessário selecionar uma linha.");
		} else {
			indSelecionado = tabelaCadastroProduto.getSelectedRow();
			indSelecionado++;
		}
	}
	
	public void limparTabela(){
		DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroProduto.getModel();
		int i = modelo.getRowCount();
		if (i != 0) {
			for (int j = 0; j > i; j++) {
				modelo.removeRow(j);
			}
		}else {
			modelo.removeRow(0);
		}
	}

}// fim da classe