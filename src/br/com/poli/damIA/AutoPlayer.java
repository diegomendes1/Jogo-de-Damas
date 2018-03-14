package br.com.poli.damIA;

import br.com.poli.Interface;
import br.com.poli.Jogador;
import br.com.poli.componentes.Tabuleiro;

public interface AutoPlayer {
	public int[] jogarAuto();
	public void setJogo(Interface jogo);
	public int[] verificarCapturaCasa(int i, int j, Tabuleiro tab);
}
