package br.com.poli;

@SuppressWarnings("serial")
public class CapturaInvalidaException extends Exception{
	public CapturaInvalidaException(String s) {
		super(s);
	}
}