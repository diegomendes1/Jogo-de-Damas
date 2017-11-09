package br.com.poli.enums;

//guarda a cor da peça.
public enum CorPeca {
    CLARO(0), ESCURO(1);

	private int valorCorPeca;
	
	private CorPeca(int valor){
		valorCorPeca = valor;
	}
	
	public int getCorPeca() {
		return this.valorCorPeca;
	}
}
