package br.com.choice.projetolojainformatica.util;

import java.util.GregorianCalendar;

public class FormataHorario {

	public String getHora() {

	    // cria um StringBuilder
	    StringBuilder sb = new StringBuilder();  

	    // cria um GregorianCalendar que vai conter a hora atual  
	    GregorianCalendar d = new GregorianCalendar();  

	    // anexa do StringBuilder os dados da hora
	    sb.append( d.get( GregorianCalendar.HOUR_OF_DAY ) );  
	    sb.append( ":" );
	    sb.append( d.get( GregorianCalendar.MINUTE ) );  
	    //sb.append( ":" );  
	    //sb.append( d.get( GregorianCalendar.SECOND ) );  

	    // retorna a String do StringBuilder  
	    return sb.toString();

	}

}