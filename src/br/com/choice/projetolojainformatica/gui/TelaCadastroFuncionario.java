package br.com.choice.projetolojainformatica.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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

import br.com.choice.projetolojainformatica.dao.EnderecoDAO;
import br.com.choice.projetolojainformatica.dao.FuncionarioDAO;
import br.com.choice.projetolojainformatica.model.Endereco;
import br.com.choice.projetolojainformatica.model.Funcionario;

import br.com.choice.projetolojainformatica.util.ConnectionFactory;

/**
 * Classe responsável por criar JFrame para o cadastro de FUNCIONARIO
 * 
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class TelaCadastroFuncionario {

	List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
	Endereco enderecoAltera = new Endereco();

	private JFrame janelaCadastroFuncionario;
	private JPanel painelDaJanelaCadastroFuncionario;
	
	private JTable tabelaCadastroFuncionario;
	private JScrollPane painelDeScroll;

	private JLabel jlbCodigo;
	private JLabel jlbNome;
	private JLabel jlbRua;
	private JLabel jlbNumero;
	private JLabel jlbBairro;
	private JLabel jlbCidade;
	private JLabel jlbEstado;
	private JLabel jlbCep;
	private JLabel jlbCargo;
	private JLabel jlbSalario;
	
	private JTextField jtfCodigo;
	private JTextField jtfNome;
	private JTextField jtfRua;
	private JTextField jtfNumero;
	private JTextField jtfBairro;
	private JTextField jtfCidade;
	private JComboBox cbEstado;
	private JTextField jtfCep;
	private JTextField jtfCargo;
	private JTextField jtfSalario;

	private JButton btnNovo;
	private JButton btnAlterar;
	private JButton btnExcluir;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnSair;

	private boolean validaFuncionario;
	private boolean validaSalvar = false;
	private boolean validaLinha = false;

	private double salarioConvertido;
	private int codigoConvertido;
	private int indSelecionado;

	private String[] colunas = new String[] { "Código", "Nome", "Cargo", "Salário" };
	private String[][] dados = new String[][] { {} };

	private String[] estadoComps = {"- - - -", "SP", "PR", "RJ", "MG", "RN", "RO", "AM"};
	
	public TelaCadastroFuncionario() {
		inicia();
	}
	
	public void inicia(){
		
		janelaCadastroFuncionario = new JFrame("CADASTRO DE FUNCIONÁRIO");
		painelDaJanelaCadastroFuncionario = (JPanel) janelaCadastroFuncionario
				.getContentPane();

		DefaultTableModel modelo = new DefaultTableModel(dados, colunas);
		tabelaCadastroFuncionario = new JTable(modelo);
		modelo.removeRow(0);

		painelDeScroll = new JScrollPane(tabelaCadastroFuncionario);
		painelDeScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		jlbCodigo = new JLabel("CODIGO:");
		jlbNome = new JLabel("NOME:");
		jlbRua = new JLabel("RUA:");
		jlbNumero = new JLabel("NUMERO:");
		jlbBairro = new JLabel("BAIRRO:");
		jlbCidade = new JLabel("CIDADE:");
		jlbEstado = new JLabel("ESTADO:");
		jlbCep = new JLabel("CEP:");
		jlbCargo = new JLabel("CARGO:");
		jlbSalario = new JLabel("SALÁRIO:");
		
		jtfCodigo = new JTextField();
		jtfNome = new JTextField();
		jtfRua = new JTextField();
		jtfNumero = new JTextField();
		jtfBairro = new JTextField();
		jtfCidade = new JTextField();
		cbEstado = new JComboBox(estadoComps);
		jtfCep = new JTextField();
		jtfCargo= new JTextField();
		jtfSalario = new JTextField();
		
		btnNovo = new JButton("NOVO");
		btnAlterar = new JButton("ALTERAR");
		btnExcluir = new JButton("EXCLUIR");
		btnSalvar = new JButton("SALVAR");
		btnCancelar = new JButton("CANCELAR");
		btnSair = new JButton("SAIR");

		jlbCodigo.setBounds(20, 70, 70, 20);
		jlbNome.setBounds(20, 100, 50, 20);
		jlbRua.setBounds(20, 130, 40, 20);
		jlbNumero.setBounds(420, 130, 70, 20);
		jlbBairro.setBounds(20, 160, 70, 20);
		jlbCidade.setBounds(20, 190, 70, 20);
		jlbEstado.setBounds(420, 190, 70, 20);
		jlbCep.setBounds(20, 220, 60, 20);
		jlbCargo.setBounds(20, 250, 70, 20);
		jlbSalario.setBounds(20,280, 70, 20);

		jtfCodigo.setBounds(100, 67, 50, 25);
		jtfNome.setBounds(100, 97, 460, 25);
		jtfRua.setBounds(100, 127, 300, 25);
		jtfNumero.setBounds(490, 127, 70, 25);
		jtfBairro.setBounds(100, 157, 460, 25);
		jtfCidade.setBounds(100, 187, 300, 25);
		cbEstado.setBounds(490, 187, 70, 25);
		jtfCep.setBounds(100, 217, 200, 25);
		jtfCargo.setBounds(100, 247, 200, 25);
		jtfSalario.setBounds(100, 277, 200, 25);

		btnExcluir.setBounds(25, 10, 150, 40);
		btnAlterar.setBounds(220, 10, 150, 40);
		btnNovo.setBounds(25, 450, 535, 40);
		btnSalvar.setBounds(220, 10, 150, 40);
		btnCancelar.setBounds(410, 10, 150, 40);
		btnSair.setBounds(410, 10, 150, 40);

		painelDeScroll.setBounds(0, 320, 585, 120);
		tabelaCadastroFuncionario.setBounds(0, 320, 585, 120);

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

		painelDaJanelaCadastroFuncionario.add(painelDeScroll);
		painelDaJanelaCadastroFuncionario.add(jlbCodigo);
		painelDaJanelaCadastroFuncionario.add(jlbNome);
		painelDaJanelaCadastroFuncionario.add(jlbRua);
		painelDaJanelaCadastroFuncionario.add(jlbNumero);
		painelDaJanelaCadastroFuncionario.add(jlbBairro);
		painelDaJanelaCadastroFuncionario.add(jlbCidade);
		painelDaJanelaCadastroFuncionario.add(jlbEstado);
		painelDaJanelaCadastroFuncionario.add(jlbCep);
		painelDaJanelaCadastroFuncionario.add(jlbCargo);
		painelDaJanelaCadastroFuncionario.add(jlbSalario);
		
		painelDaJanelaCadastroFuncionario.add(jtfCodigo);
		painelDaJanelaCadastroFuncionario.add(jtfNome);
		painelDaJanelaCadastroFuncionario.add(jtfRua);
		painelDaJanelaCadastroFuncionario.add(jtfBairro);
		painelDaJanelaCadastroFuncionario.add(jtfCidade);		
		painelDaJanelaCadastroFuncionario.add(jtfNumero);
		painelDaJanelaCadastroFuncionario.add(cbEstado);
		painelDaJanelaCadastroFuncionario.add(jtfCep);
		painelDaJanelaCadastroFuncionario.add(jtfCargo);
		painelDaJanelaCadastroFuncionario.add(jtfSalario);
		
		painelDaJanelaCadastroFuncionario.add(btnNovo);
		painelDaJanelaCadastroFuncionario.add(btnAlterar);
		painelDaJanelaCadastroFuncionario.add(btnExcluir);
		painelDaJanelaCadastroFuncionario.add(btnCancelar);
		painelDaJanelaCadastroFuncionario.add(btnSalvar);
		painelDaJanelaCadastroFuncionario.add(btnSair);

		btnNovo.addActionListener(new NovoFuncionarioListener());
		btnAlterar.addActionListener(new AlterarProdutoListener());
		btnExcluir.addActionListener(new ExcluirFuncionarioListener());
		btnCancelar.addActionListener(new CancelarListener());
		btnSair.addActionListener(new SairSistemaListener());
		btnSalvar.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (validaSalvar == false) {
					salvarFuncionario();
				}else {
					alterarFuncionario();
					validaSalvar = false;
				}
			}
		});

		bloquearTelaFuncionario();
		listarFuncionario();

		painelDaJanelaCadastroFuncionario.setLayout(null);

		janelaCadastroFuncionario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		janelaCadastroFuncionario.setSize(600, 540);
		janelaCadastroFuncionario.setLocationRelativeTo(null);
		janelaCadastroFuncionario.setVisible(true);

	}

	public class NovoFuncionarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			liberarTelaFuncionario();
		}
	}
	
	public class AlterarProdutoListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			validaSalvar = true;
			validaFuncionario = false;
			validaLinha = false;
			validarLinha();
			liberarTelaFuncionario();
			for (Funcionario funcionario : listaFuncionario){
				if (funcionario.getCodigo() == indSelecionado) {
					jtfCodigo.setEditable(false);
					jtfNome.setEditable(false);
					jtfCodigo.setText("" + funcionario.getCodigo());
					jtfNome.setText(funcionario.getNome());
					buscarEndereco();
					jtfRua.setText(enderecoAltera.getLogadouro());
					jtfNumero.setText(enderecoAltera.getNumero());
					jtfBairro.setText(enderecoAltera.getBairro());
					jtfCidade.setText(enderecoAltera.getCidade());
					cbEstado.setSelectedIndex(0);
					jtfCep.setText(enderecoAltera.getCep());
					jtfCargo.setText(funcionario.getCargo());
					jtfSalario.setText("" + funcionario.getSalario());
				}
			}
		}
	}

	public class ExcluirFuncionarioListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			validaLinha = false;
			validarLinha();
			excluirFuncionario();
			limparTabela();
			listarFuncionario();
		}
	}

	public class CancelarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			limparTelaFuncionario();
			bloquearTelaFuncionario();
		}
	}

	public class SairSistemaListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			janelaCadastroFuncionario.setVisible(false);
		}
	}

	public void liberarTelaFuncionario() {
		jtfCodigo.setEditable(true);
		jtfNome.setEditable(true);
		jtfRua.setEditable(true);
		jtfNumero.setEditable(true);
		jtfBairro.setEditable(true);
		jtfCidade.setEditable(true);
		cbEstado.setEnabled(true);
		jtfCep.setEditable(true);
		jtfCargo.setEditable(true);
		jtfSalario.setEditable(true);
		btnSalvar.setVisible(true);
		btnAlterar.setVisible(false);
		btnNovo.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setVisible(true);
		btnSair.setVisible(false);
		jtfCodigo.setFocusable(true);
	}

	public void bloquearTelaFuncionario() {
		btnSalvar.setVisible(false);
		btnExcluir.setEnabled(true);
		btnNovo.setEnabled(true);
		btnAlterar.setVisible(true);
		btnSair.setVisible(true);
		btnCancelar.setVisible(false);
		jtfCodigo.setEditable(false);
		jtfNome.setEditable(false);
		jtfRua.setEditable(false);
		jtfNumero.setEditable(false);
		jtfBairro.setEditable(false);
		jtfCidade.setEditable(false);
		cbEstado.setEnabled(false);
		jtfCep.setEditable(false);
		jtfCargo.setEditable(false);
		jtfSalario.setEditable(false);
	}

	public void limparTelaFuncionario() {
		jtfCodigo.setText("");
		jtfNome.setText("");
		jtfRua.setText("");
		jtfNumero.setText("");
		jtfBairro.setText("");
		jtfCidade.setText("");
		cbEstado.setSelectedIndex(0);
		jtfCep.setText("");
		jtfCargo.setText("");
		jtfSalario.setText("");
	}

	public void salvarFuncionario() {
		validarFuncionario();
		if (validaFuncionario== false) {
			Connection bdEndereco = ConnectionFactory.getConnection();
			Connection bdFuncionario = ConnectionFactory.getConnection();

			Endereco endereco = new Endereco();
			endereco.setCodigo(codigoConvertido);
			endereco.setLogadouro(jtfRua.getText());
			endereco.setNumero(jtfNumero.getText());
			endereco.setBairro(jtfBairro.getText());
			endereco.setCidade(jtfCidade.getText());
			endereco.setEstado(cbEstado.getSelectedItem() + "");
			endereco.setCep(jtfCep.getText());

			Funcionario funcionario = new Funcionario();
			funcionario.setCodigo(codigoConvertido);
			funcionario.setNome(jtfNome.getText());
			funcionario.setCargo(jtfCargo.getText());
			funcionario.setSalario(salarioConvertido);
			funcionario.setCodigoEndereco(codigoConvertido);

			try {
				EnderecoDAO dao = new EnderecoDAO(bdEndereco);
				dao.inserir(endereco);
			} catch (SQLException e) {
				limparTelaFuncionario();
				bloquearTelaFuncionario();				
				JOptionPane.showMessageDialog(null, "Erro ao inserir o endereço.");
			}

			try {
				FuncionarioDAO dao = new FuncionarioDAO(bdFuncionario);
				dao.inserir(funcionario);
				JOptionPane.showMessageDialog(null,
						"Funcionario inserido com sucesso");
				limparTelaFuncionario();
				bloquearTelaFuncionario();
				bdFuncionario.close();
			} catch (SQLException e) {
				limparTelaFuncionario();
				bloquearTelaFuncionario();
				JOptionPane.showMessageDialog(null, "Erro ao inserir o Funcionário.");
			}
		}
	}

	public void validarFuncionario() {
		if (jtfCodigo.getText().equals(null) || jtfCodigo.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o código, campo obrigatório!");
			jtfCodigo.grabFocus();
		} else {
			try {
				codigoConvertido = Integer.parseInt(jtfCodigo.getText());
			} catch (Exception e) {
				validaFuncionario = true;
				JOptionPane.showMessageDialog(null,
						"Código inválido, digite novamente");
				jtfCodigo.grabFocus();
			}
		}

		if (jtfNome.getText().equals(null) || jtfNome.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o nome, campo obrigatório!");
			jtfNome.grabFocus();
		}
		
		if (jtfRua.getText().equals(null) || jtfRua.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o rua, campo obrigatório!");
			jtfRua.grabFocus();
		}
		
		if (jtfNumero.getText().equals(null) || jtfNumero.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o número, campo obrigatório!");
			jtfNumero.grabFocus();
		}

		if (jtfBairro.getText().equals(null) || jtfBairro.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o  bairro, campo obrigatório!");
			jtfBairro.grabFocus();
		}

		if (jtfCidade.getText().equals(null) || jtfCidade.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe a cidade, campo obrigatório!");
			jtfCidade.grabFocus();
		}

		if (cbEstado.getSelectedIndex() == 0 ) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Selecione um estado, campo obrigatório!");
			cbEstado.grabFocus();
		}

		if (jtfCargo.getText().equals(null) || jtfCargo.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o cargo, campo obrigatório!");
			jtfCargo.grabFocus();
		}
		
		if (jtfSalario.getText().equals(null) || jtfSalario.getText().equals("")) {
			validaFuncionario = true;
			JOptionPane.showMessageDialog(null,
					"Informe o salário, campo obrigatório!");
			jtfSalario.grabFocus();
		} else {
			try {
				salarioConvertido = Double.parseDouble(jtfSalario.getText());
			} catch (Exception e) {
				validaFuncionario = true;
				JOptionPane.showMessageDialog(null,
						"Salário inválido, digite novamente");
				jtfSalario.grabFocus();
			}
		}
	}

	public void alterarFuncionario() {
		validarFuncionario();
		if (validaFuncionario == false) {
			Connection bdEndereco = ConnectionFactory.getConnection();
			Connection bdFuncionario = ConnectionFactory.getConnection();
			
			Endereco endereco = new Endereco();
			endereco.setCodigo(codigoConvertido);
			endereco.setLogadouro(jtfRua.getText());
			endereco.setNumero(jtfNumero.getText());
			endereco.setBairro(jtfBairro.getText());
			endereco.setCidade(jtfCidade.getText());
			endereco.setEstado(cbEstado.getSelectedItem() + "");
			endereco.setCep(jtfCep.getText());

			Funcionario funcionario = new Funcionario();
			funcionario.setCodigo(codigoConvertido);
			funcionario.setNome(jtfNome.getText());
			funcionario.setCargo(jtfCargo.getText());
			funcionario.setSalario(salarioConvertido);
			funcionario.setCodigoEndereco(codigoConvertido);

			try {
				EnderecoDAO dao = new EnderecoDAO(bdEndereco);
				dao.alterar(endereco);
			} catch (SQLException e) {
				limparTelaFuncionario();
				bloquearTelaFuncionario();				
				JOptionPane.showMessageDialog(null, "Erro ao alterar endereço.");
				e.printStackTrace();
			}

			try {
				FuncionarioDAO dao = new FuncionarioDAO(bdFuncionario);
				dao.alterar(funcionario);
				JOptionPane.showMessageDialog(null,
						"Funcionário alterado com sucesso.");
				limparTelaFuncionario();
				limparTabela();
				listarFuncionario();
				bloquearTelaFuncionario();
				bdFuncionario.close();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Erro ao alterar cliente");
			}
		}
	}

	public void excluirFuncionario() {
		if (validaLinha == false) {
			for (Funcionario funcionario : listaFuncionario) {
				if (funcionario.getCodigo() == indSelecionado){
					Connection bd = ConnectionFactory.getConnection();
					try {
						FuncionarioDAO dao = new FuncionarioDAO(bd);
						dao.excluir(indSelecionado);
						JOptionPane.showMessageDialog(null,
								"Funcionário excluido com sucesso.");
						limparTelaFuncionario();
						limparTabela();
						listarFuncionario();
						bloquearTelaFuncionario();
						bd.close();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Erro ao excluir funcionario");
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void listarFuncionario() {
		Connection bd = ConnectionFactory.getConnection();
		try {
			FuncionarioDAO dao = new FuncionarioDAO(bd);
			listaFuncionario = dao.buscarTodos();
			DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroFuncionario
					.getModel();
			for (Funcionario funcionario: listaFuncionario) {
				modelo.addRow(new String[] { "" + funcionario.getCodigo(),
						funcionario.getNome().toUpperCase(), funcionario.getCargo(), "R$ " + funcionario.getSalario()});
			}
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTE.");
		}
	}

	public void buscarEndereco(){
		Connection bd = ConnectionFactory.getConnection();
		EnderecoDAO dao = new EnderecoDAO(bd);
		enderecoAltera = dao.buscarEnderecoCodigo(Integer.parseInt(jtfCodigo.getText()));
	}
	
	public void validarLinha() {
		if (tabelaCadastroFuncionario.getSelectedRow() == -1) {
			validaLinha = true;
			JOptionPane.showMessageDialog(null,
					"É necessário selecionar uma linha.");
		} else {
			indSelecionado = tabelaCadastroFuncionario.getSelectedRow();
			indSelecionado++;
		}
	}
	
	public void limparTabela(){
		DefaultTableModel modelo = (DefaultTableModel) tabelaCadastroFuncionario.getModel();
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