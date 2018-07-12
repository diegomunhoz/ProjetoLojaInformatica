package br.com.choice.projetolojainformatica.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.choice.projetolojainformatica.util.ConnectionFactory;

public class TestaCliente {

	public static void main(String[] args) {
		Connection bd = ConnectionFactory.getConnection();
		try {
			String sql = "select max(codigo) from cliente";
			PreparedStatement comando = bd.prepareStatement(sql);
			ResultSet cursor = comando.executeQuery();
			System.out.println(cursor.getString("nome"));
			bd.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Não foi possível exibir CLIENTE.");
			e.printStackTrace();
		}


	}


}
