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

import br.com.choice.projetolojainformatica.dao.ClienteDAO;
import br.com.choice.projetolojainformatica.model.Cliente;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

/**
 * Classe responsável por criar JFrame para o cadastro de CLIENTE
 * 
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class TelaCadastroCliente {

	List<Cliente> listaCliente = new ArrayList<Cliente>();

	private JFrame janelaCadastroCliente;
	private JPanel painelDaJanelaCadastroCliente;

	private JTable tabelaCadastroCliente;
	private JScrollPane painelDeScroll;

	private JLabel jlbCodigo;
	private JLabel jlbNome;
	private JLabel jlbEmail;
	private JLabel jlbTelefone;
	private JLabel jlbCelular;

	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JTextField jtfEmail;
	private JTextField jtfTelefone;
	private JTextField jtfCelular;

	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnSair;

	private String[] colunas = new String[] { "Código", "Nome", "e-mail",
			"Telefone", "Celular" };
	private String[][] dados = new String[][] { {} };

	private boolean validaCliente = false;
	private boolean validaLinha = false;
	private boolean validaSalvar = false;
	
	private int indSelecionado;

	public TelaCadastroCliente() {
		inicia();
	}

	public void inicia() {

		janelaCadastroCliente = new JFrame("CADASTRO DE CLIENTE");
		painelDaJanelaCadastroCliente = (JPanel) janelaCadastroCliente
				.getContentPane();

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaCadastroCliente = new JTable(modelo);
		modelo.removeRow(0);

		painelDeScroll = new JScrollPane(tabelaCadastroCliente);
		painelDeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jlbCodigo = new JLabel("CODIGO:");
		jlbNome = new JLabel("NOME:");
		jlbEmail = new JLabel("EMAIL:");
		jlbTelefone = new JLabel("TELEFONE:");
		jlbCelular = new JLabel("CELULAR:");

		jtfCodigo = new JTextField();
		jtfNome = new JTextField();
		jtfEmail = new JTextField();
		jtfTelefone = new JTextField();
		jtfCelular = new JTextField();

		btnNovo = new JButton("NOVO CLIENTE");
		btnAlterar = new JButton("ALTERAR");
		btnExcluir = new JButton("EXCLUIR");
		btnSalvar = new JButton("SALVAR");
		btnCancelar = new JButton("CANCELAR");
		btnSair = new JButton("SAIR");

		jlbCodigo.setBounds(20, 70, 70, 20);
		jlbNome.setBounds(20, 100, 50, 20);
		jlbEmail.setBounds(20, 130, 60, 20);
		jlbTelefone.setBounds(20, 160, 70, 20);
		jlbCelular.setBounds(20, 190, 70, 20);

		jtfCodigo.setBounds(100, 67, 100, 25);
		jtfNome.setBounds(100, 97, 460, 25);
		jtfEmail.setBounds(100, 127, 460, 25);
		jtfTelefone.setBounds(100, 157, 200, 25);
		jtfCelular.setBounds(100, 187, 200, 25);

		btnExcluir.setBounds(25, 10, 150, 40);
		btnAlterar.setBounds(220, 10, 150, 40);
		btnNovo.setBounds(25, 360, 535, 40);
		btnSalvar.setBounds(220, 10, 150, 40);
		btnCancelar.setBounds(410, 10, 150, 40);
		btnSair.setBounds(410, 10, 150, 40);

		painelDeScroll.setBounds(0, 230, 585, 120);
		tabelaCadastroCliente.setBounds(0, 230, 585, 120);

		painelDaJanelaCadastroCliente.add(painelDeScroll);
		painelDaJanelaCadastroCliente.add(jlbCodigo);
		painelDaJanelaCadastroCliente.add(jlbNome);
		painelDaJanelaCadastroCliente.add(jlbEmail);
		painelDaJanelaCadastroCliente.add(jlbTelefone);
		painelDaJanelaCadastroCliente.add(jlbCelular);
		painelDaJanelaCadastroCliente.add(jtfCodigo);
		painelDaJanelaCadastroCliente.add(jtfNome);
		painelDaJanelaCadastroCliente.add(jtfEmail);
		painelDaJanelaCadastroCliente.add(jtfTelefone);
		painelDaJanelaCadastroCliente.add(jtfCelular);
		painelDaJanelaCadastroCliente.add(btnNovo);
		painelDaJanelaCadastroCliente.add(btnExcluir);
		painelDaJanelaCadastroCliente.add(btnAlterar);
		painelDaJanelaCadastroCliente.add(btnCancelar);
		painelDaJanelaCadastroCliente.add(btnSalvar);
		painelDaJanelaCadastroCliente.add(btnSair);

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

		btnNovo.addActionListener(new NovoClienteListener());
		btnAlterar.addActionListener(new AlterarClienteListener());
		btnExcluir.addActionListener(new ExcluirClienteListener());
		btnCancelar.addActionListener(new CancelarListener());
		btnSair.addActionListener(new SairSistemaListener());
		btnSalvar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (validaSalvar == false) {
					salvarCliente();
				}else {
					alterarCliente();
					validaSalvar = false;
				}
			}
		});

		bloquearTelaCliente();
		listarCliente();

		painelDaJanelaCadastroCliente.setLayout(null);

		janelaCadastroCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		janelaCadastroCliente.setSize(600, 450);
		janelaCadastroCliente.setLocationRelativeTo(null);
		janelaCadastroCliente.setVisible(true);
	}

	public class NovoClienteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			liberarTelaCliente();
		}
	}

	public class AlterarClienteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			validaSalvar = true;
			validaCliente = false;
			validaLinha = false;
			validarLinha();
			liberarTelaCliente();
			for (Cliente cliente : listaCliente){
				if (cliente.getCodigo() == indSelecionado) {
					jtfCodigo.setEditable(false);
					jtfNome.setEditable(false);
					jtfCodigo.setText("" + cliente.getCodigo());
					jtfNome.setText(cliente.getNome());
					jtfEmail.setText(cliente.getEmail());
					jtfCelular.setText(cliente.getCelular());
					jtfTelefone.setText(cliente.getTelefone());
				}
			}
		}
	}

	public class ExcluirClienteListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			validaLinha = false;
			validarLinha();
			excluirCliente();
			limparTabela();
			listarCliente();
		}
	}

	public class CancelarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			limparTelaCliente();
			bloquearTelaCliente();
		}
	}

	public class SairSistemaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			janelaCadastroCliente.setVisible(false);
		}
	}

	public void listarCliente() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			ClienteDAO dao = new ClienteDAO(bd);
			listaCliente = dao.buscarTodos();
			DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroCliente
					.getModel();
			for (Cliente cliente : listaCliente) {
				modelo.addRow(new String[] { "" + cliente.getCodigo(),
						cliente.getNome().toUpperCase(), cliente.getEmail(),
						cliente.getTelefone(), cliente.getCelular() });
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTE.");
		}
	}

	public void liberarTelaCliente() {
		jtfNome.setEditable(true);
		jtfEmail.setEditable(true);
		jtfTelefone.setEditable(true);
		jtfCelular.setEditable(true);
		btnSalvar.setVisible(true);
		btnAlterar.setVisible(false);
		btnNovo.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setVisible(true);
		btnSair.setVisible(false);
		jtfNome.setFocusable(true);
	}

	public void bloquearTelaCliente() {
		btnSalvar.setVisible(false);
		btnExcluir.setEnabled(true);
		btnNovo.setEnabled(true);
		btnAlterar.setVisible(true);
		btnSair.setVisible(true);
		btnCancelar.setVisible(false);
		jtfCodigo.setEditable(false);
		jtfNome.setEditable(false);
		jtfEmail.setEditable(false);
		jtfTelefone.setEditable(false);
		jtfCelular.setEditable(false);
	}

	public void limparTelaCliente() {
		jtfNome.setText("");
		jtfEmail.setText("");
		jtfTelefone.setText("");
		jtfCelular.setText("");
	}

	public void salvarCliente() {
		validarCliente();
		if (validaCliente == false) {
			Connection bd = ConnectionFactory.getConnection();
			Cliente cliente = new Cliente();
			cliente.setNome(jtfNome.getText());
			cliente.setEmail(jtfEmail.getText());
			cliente.setTelefone(jtfTelefone.getText());
			cliente.setCelular(jtfCelular.getText());
			try {
				ClienteDAO dao = new ClienteDAO(bd);
				dao.inserir(cliente);
				JOptionPane.showMessageDialog(null,
						"Cliente inserido com sucesso");
				limparTabela();
				limparTelaCliente();
				listarCliente();
				bloquearTelaCliente();
				bd.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao inserir cliente.");
			}
		}
	}

	public void alterarCliente() {
		if (validaCliente == false) {
			Connection bd = ConnectionFactory.getConnection();
			Cliente cliente = new Cliente();
			cliente.setCodigo(Integer.parseInt(jtfCodigo.getText()));
			cliente.setNome(jtfNome.getText());
			cliente.setEmail(jtfEmail.getText());
			cliente.setTelefone(jtfTelefone.getText());
			cliente.setCelular(jtfCelular.getText());
			try {
				ClienteDAO dao = new ClienteDAO(bd);
				dao.alterar(cliente);
				JOptionPane.showMessageDialog(null,
						"Cliente alterado com sucesso.");
				limparTelaCliente();
				limparTabela();
				listarCliente();
				bloquearTelaCliente();
				bd.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar cliente");
			}
		}
	}

	public void excluirCliente() {
		if (validaLinha == false) {
			for (Cliente cliente : listaCliente) {
				if (cliente.getCodigo() == indSelecionado){
					Connection bd = ConnectionFactory.getConnection();
					try {
						ClienteDAO dao = new ClienteDAO(bd);
						dao.excluir(indSelecionado);
						JOptionPane.showMessageDialog(null,
								"Cliente excluido com sucesso.");
						limparTelaCliente();
						limparTabela();
						listarCliente();
						bloquearTelaCliente();
						bd.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir cliente");
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void validarCliente() {
		if (jtfNome.getText().equals(null) || jtfNome.getText().equals("")) {
			validaCliente = true;
			JOptionPane.showMessageDialog(null,
					"Informe o nome, campo obrigatório!");
			jtfNome.grabFocus();
		}
		if (jtfEmail.getText().equals(null) || jtfEmail.getText().equals("")) {
			validaCliente = true;
			JOptionPane.showMessageDialog(null,
					"Informe o email, campo obrigatório!");
			jtfEmail.grabFocus();
		}
		String email = jtfEmail.getText();
		int index = email.indexOf("@");
		if (index >= 0) {
			validaCliente = false;
		} else {
			JOptionPane.showMessageDialog(null,
					"E-mail inválido, digite novamente!");
			jtfEmail.grabFocus();
			validaCliente = true;
		}
		if (jtfTelefone.getText().equals(null)
				|| jtfTelefone.getText().equals("")) {
			validaCliente = true;
			JOptionPane.showMessageDialog(null,
					"Informe o nome, campo obrigatório!");
			jtfTelefone.grabFocus();
		}
	}

	public void validarLinha() {
		if (tabelaCadastroCliente.getSelectedRow() == -1) {
			validaLinha = true;
			JOptionPane.showMessageDialog(null,
					"É necessário selecionar uma linha.");
		} else {
			indSelecionado = tabelaCadastroCliente.getSelectedRow();
			indSelecionado++;
		}
	}
	
	public void limparTabela(){
		DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroCliente.getModel();
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