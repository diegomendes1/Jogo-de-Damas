package br.com.poli.damIA;

public class DamaIA {

	public static void main(String[] args) {
		int[] valores = {5,6,8,10,13};
		JogadorAutonomo ia = new JogadorAutonomo();
		System.out.println(ia.retornarMaior(valores));
		System.out.println(ia.retornarMenor(valores));
	}
}
