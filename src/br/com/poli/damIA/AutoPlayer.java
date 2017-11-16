package br.com.poli.damIA;

import br.com.poli.Interface;
import br.com.poli.Jogador;

public interface AutoPlayer {
	public int[] jogarAuto();
	public Jogador vencedor();
	public void setJogo(Interface jogo);
}
