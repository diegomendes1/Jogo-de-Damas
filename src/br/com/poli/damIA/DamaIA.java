package br.com.poli.damIA;

import java.util.ArrayList;

public class DamaIA {

	public static void main(String[] args) {
		int[] numeros = new int[6];
		numeros[0] = 53;
		numeros[1] = 22;
		numeros[2] = 65;
		numeros[3] = 98;
		numeros[4] = 73;
		numeros[5] = 45;
		
		int[] numeros1 = new int[6];
		numeros1[0] = 58;
		numeros1[1] = 226793;
		numeros1[2] = 653;
		numeros1[3] = 984474;
		numeros1[4] = 73454;
		numeros1[5] = 454574;
		
		int[] numeros2 = new int[6];
		numeros2[0] = 545743;
		numeros2[1] = 245442;
		numeros2[2] = 657455;
		numeros2[3] = 9847;
		numeros2[4] = 4454;
		numeros2[5] = 4545745;
		
		ArrayList<int[]> listinha = new ArrayList<int[]>();
		listinha.add(numeros);
		listinha.add(numeros1);
		listinha.add(numeros2);
		System.out.println(listinha.get(2)[5]);
	}
}
