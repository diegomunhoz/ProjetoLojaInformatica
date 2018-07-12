package br.com.choice.projetolojainformatica.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;

/**
 * Classe responsável por criar JFrame para o MENU PRINCIPAL
 * 
 * @author Diego Munhoz
 * @since 27/02/2014
 */
public class MenuPrincipal {

	private JFrame janelaPrincipal;

	private JMenuBar barraMenu;

	private JMenu menuCadastro;
	private JMenu menuConsulta;
	private JMenu menuVenda;
	private JMenu menuSair;

	private JMenuItem itemMenuCadastroFuncionario;
	private JMenuItem itemMenuCadastroCliente;
	private JMenuItem itemMenuCadastroProduto;

	private JMenuItem itemMenuConsultaFuncionario;
	private JMenuItem itemMenuConsultaCliente;
	private JMenuItem itemMenuConsultaProduto;

	private JMenuItem itemMenuVendaNova;
	private JMenuItem itemMenuVendaAlterar;
	private JMenuItem itemMenuVendaExcluir;
	private JMenuItem itemMenuVendaConsultar;

	private JMenuItem itemMenuSair;

	public void iniciaTela() {

		janelaPrincipal = new JFrame("MENU PRINCIPAL");

		barraMenu = new JMenuBar();

		menuCadastro = new JMenu("Cadastrar");
		menuConsulta = new JMenu("Consultar");
		menuVenda = new JMenu("Venda");
		menuSair = new JMenu("Sair");

		itemMenuCadastroFuncionario = new JMenuItem("FUNCIONARIO");
		itemMenuCadastroCliente = new JMenuItem("CLIENTE");
		itemMenuCadastroProduto = new JMenuItem("PRODUTO");

		itemMenuConsultaFuncionario = new JMenuItem("FUNCIONARIO");
		itemMenuConsultaCliente = new JMenuItem("CLIENTE");
		itemMenuConsultaProduto = new JMenuItem("PRODUTO");

		itemMenuVendaNova = new JMenuItem("NOVA");
		itemMenuVendaAlterar = new JMenuItem("ALTERAR");
		itemMenuVendaExcluir = new JMenuItem("EXCLUIR");
		itemMenuVendaConsultar = new JMenuItem("CONSULTAR");

		itemMenuSair = new JMenuItem("Sair");

		itemMenuCadastroFuncionario
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cadastroFuncionarioActionListener(evt);
					}
				});

		itemMenuCadastroCliente
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cadastroClienteActionListener(evt);
					}
				});

		itemMenuCadastroProduto
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cadastroProdutoActionListener(evt);
					}
				});

		itemMenuConsultaFuncionario
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						consultaFuncionarioActionListener(evt);
					}
				});

		itemMenuConsultaCliente
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						consultaClienteActionListener(evt);
					}
				});

		itemMenuConsultaProduto
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						consultaProdutoActionListener(evt);
					}
				});

		itemMenuVendaNova
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						cadastraVendaActionListener(evt);
					}
				});

		itemMenuVendaAlterar
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						alteraVendaActionListener(evt);
					}
				});

		itemMenuVendaExcluir
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						excluiVendaActionListener(evt);
					}
				});

		itemMenuVendaConsultar
				.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						consultaVendaActionListener(evt);
					}
				});

		itemMenuSair.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});

		menuCadastro.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuConsulta.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuVenda.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		menuSair.setFont(new Font("Arial Narrow", Font.BOLD, 15));

		barraMenu.add(menuCadastro);
		barraMenu.add(menuConsulta);
		barraMenu.add(menuVenda);
		barraMenu.add(menuSair);

		menuCadastro.add(itemMenuCadastroFuncionario);
		menuCadastro.add(itemMenuCadastroCliente);
		menuCadastro.add(itemMenuCadastroProduto);

		menuConsulta.add(itemMenuConsultaFuncionario);
		menuConsulta.add(itemMenuConsultaCliente);
		menuConsulta.add(itemMenuConsultaProduto);

		menuVenda.add(itemMenuVendaNova);
		menuVenda.add(itemMenuVendaAlterar);
		menuVenda.add(itemMenuVendaExcluir);
		menuVenda.add(itemMenuVendaConsultar);

		menuSair.add(itemMenuSair);

		janelaPrincipal.setJMenuBar(barraMenu);
		janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		janelaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
		janelaPrincipal.setVisible(true);

	}

	private static void cadastroFuncionarioActionListener(ActionEvent evt) {
		new TelaCadastroFuncionario();
	}

	private static void cadastroClienteActionListener(ActionEvent evt) {
		new TelaCadastroCliente();
	}

	private static void cadastroProdutoActionListener(ActionEvent evt) {
		new TelaCadastroProduto();
	}

	private static void consultaFuncionarioActionListener(ActionEvent evt) {

	}

	private static void consultaClienteActionListener(ActionEvent evt) {
		new TelaExibeCliente();
	}

	private static void consultaProdutoActionListener(ActionEvent evt) {
		new TelaExibeProduto();
	}

	private static void cadastraVendaActionListener(ActionEvent evt) {
		new TelaCadastroVenda();
	}

	private static void alteraVendaActionListener(ActionEvent evt) {

	}

	private static void excluiVendaActionListener(ActionEvent evt) {

	}

	private static void consultaVendaActionListener(ActionEvent evt) {

	}

	public static void main(String[] args) {
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		new MenuPrincipal().iniciaTela();
	}

}// fim da classe