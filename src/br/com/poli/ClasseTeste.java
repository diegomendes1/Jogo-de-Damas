package br.com.poli;

import br.com.poli.componentes.Jogador;
import br.com.poli.componentes.Tabuleiro;

//Apenas para teste, esta classe testa todos os metodos principais.
	public class ClasseTeste {
		public static void main(String[] args) {
			
			Jogo jogo = new Jogo(new Jogador(), new Jogador(), new Jogador(), new Tabuleiro(), null);
			jogo.getJogador1().setNome("Primeiro jogador");
			jogo.getJogador2().setNome("Segundo jogador");
			System.out.println();
			System.out.println("O Jogador 1 se chama "+ jogo.getJogador1().getNome()+",");
			System.out.println("e o jogador 2 se chama "+ jogo.getJogador2().getNome()+".");
			
			System.out.println();
			
			//Mostra o tabuleiro pela primeira vez, dizendo a cor da peca
			jogo.getTabuleiro().mostrarTabuleiro();
			
			//Mostra quem deve jogar agora
			System.out.println();
			System.out.println("Vez do " + jogo.getAtualJogador().getNome());
			System.out.println();
			
			//Comandos para testar as excecoes.
			 int[] comandosXOrigem = {5,2,5,4,1,2,1};
			 int[] comandosYOrigem = {2,5,6,3,2,3,6};
			int[] comandosXDestino = {4,3,4,2,2,3,3};
			int[] comandosYDestino = {3,4,7,5,3,2,4};
			
			/*ORDEM DE EXECUCAO:
			 * 1- peca clara se movimenta
			 * 2- peca escura se movimenta
			 * 3- tenta mover peca mesmo tendo captura(CapturaInvalidaException)
			 * 4- peca clara captura peca escura
			 * 5- tenta mover peca escura para lugar ocupado(MovimentoInvalidoException)
			 * 6- peca escura captura peca branca*/
			
			for(int i = 0; i < 7; i++) {
				System.out.println("Executando de ("+ comandosXOrigem[i]+","+ comandosYOrigem[i]+") para ("+ comandosXDestino[i]+","+ comandosYDestino[i]+")");
				
				try {
					Thread.sleep(10000);
					jogo.jogar(jogo.getTabuleiro().getCasaGrid(comandosXOrigem[i], comandosYOrigem[i]), jogo.getTabuleiro().getCasaGrid(comandosXDestino[i], comandosYDestino[i]));
					jogo.getTabuleiro().mostrarTabuleiro();
					System.out.println();
					System.out.println("Vez do " + jogo.getAtualJogador().getNome());
					System.out.println();
				}catch(MovimentoInvalidoException e) {
					e.printStackTrace();
				}catch(CapturaInvalidaException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("FIM");
		}
	}