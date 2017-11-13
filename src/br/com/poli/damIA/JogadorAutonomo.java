package br.com.poli.damIA;

import java.util.ArrayList;

import br.com.poli.Interface;
import br.com.poli.componentes.Jogador;
import br.com.poli.componentes.Tabuleiro;

public class JogadorAutonomo extends Jogador implements AutoPlayer{
	public ArrayList<int[]> listaJogadas;
	
	public JogadorAutonomo() {
		super();
		this.listaJogadas = new ArrayList<int[]>();
	}
	
	
	@Override
	public boolean jogarAuto() {
	//	Tabuleiro tab = jogo.getTabuleiro();
		
		return false;
	}

	@Override
	public Jogador vencedor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void povoarListaJogadas() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public int retornarMaior(int[] numeros) {
		int maior = numeros[0];
		for(int i = 0; i < numeros.length; i++) {
			int atual = numeros[i];
			if(atual > maior) {
				maior = atual;
			}
		}
		return maior;
	}
	
	public int retornarMenor(int[] numeros) {
		int maior = numeros[0];
		for(int i = 0; i < numeros.length; i++) {
			int atual = numeros[i];
			if(atual < maior) {
				maior = atual;
			}
		}
		return maior;
	}

}
