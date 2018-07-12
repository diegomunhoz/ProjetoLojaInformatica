package br.com.choice.projetolojainformatica.teste;

import javax.swing.JOptionPane;

import br.com.choice.projetolojainformatica.util.FormataHorario;

public class TestaHora {
	
	public static void main(String[] args) {
		
		FormataHorario hora = new FormataHorario();
		String h = hora.getHora();
		JOptionPane.showMessageDialog(null, "Horário Atutal: " + h);
		
	}
	
}