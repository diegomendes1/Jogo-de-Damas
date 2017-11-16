package br.com.poli;

import br.com.poli.componentes.Casa;
import br.com.poli.componentes.Tabuleiro;
import br.com.poli.enums.Resultado;

public interface Interface {
	public boolean jogar(Casa casaOrigem, Casa casaDestino) throws MovimentoInvalidoException, CapturaInvalidaException, CapturaMultiplaException;
	public void iniciarPartida();
	public boolean isFimDeJogo(boolean desistiu);
	public Tabuleiro getTabuleiro();
	public Jogador getJogador1();
	public Jogador getJogador2();
	public Jogador getAtualJogador();
	public Jogador getVencedor();
	public Resultado getResultado();
	public int getPecasCapturadas1();
	public int getPecasCapturadas2();
	public int getJogadas();
	public boolean verificarCapturaTabuleiro();
	public Casa verificarCapturaCasa(Casa casa, Casa casaDestino);
	public boolean verificarPossibilidadeCapturaCasa(Casa casa);
	public Casa getCasaCapturaMultipla();
	public void setAtualJogador(Jogador jogador);
	public void capturar(int origemX, int origemY, int destinoX, int destinoY);
}
