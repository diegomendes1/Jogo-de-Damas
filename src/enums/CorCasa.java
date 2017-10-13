package enums;

//Guarda a cor da casa.
public enum CorCasa {
    BRANCO(0), PRETO(1);
	
	private int valorCorCasa;
	
	private CorCasa(int valor){
		valorCorCasa = valor;
	}
	
	public int getCorCasa() {
		return this.valorCorCasa;
	}
	
	public static CorCasa getCor(int i) {
		return CorCasa.values()[i];
	}
}
