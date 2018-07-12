package br.com.choice.projetolojainformatica.teste;

import java.sql.Connection;

import br.com.choice.projetolojainformatica.dao.EnderecoDAO;
import br.com.choice.projetolojainformatica.model.Endereco;
import br.com.choice.projetolojainformatica.util.ConnectionFactory;

public class TestaEndereco {

	public static void main(String[] args) {
		Connection bd = ConnectionFactory.getConnection();
		EnderecoDAO dao = new EnderecoDAO(bd);
		Endereco vo = dao.buscarEnderecoCodigo(1);
		System.out.println(vo.getCodigo());
		System.out.println(vo.getLogadouro());
		System.out.println(vo.getNumero());
	}

}
