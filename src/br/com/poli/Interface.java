package br.com.poli;

import enums.Resultado;

public interface Interface {
	public boolean jogar(Casa casaOrigem, Casa casaDestino) throws MovimentoInvalidoException, CapturaInvalidaException;
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
	public boolean verificarCapturaCasa(Casa casa);
}