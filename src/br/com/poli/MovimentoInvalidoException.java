package br.com.poli;

@SuppressWarnings("serial")
public class MovimentoInvalidoException extends Exception{
	
	public MovimentoInvalidoException(String s) {
		super(s);
	}

}