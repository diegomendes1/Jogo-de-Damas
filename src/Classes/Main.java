package Classes;

import java.util.Date;
import java.util.Scanner;

import Enums.CorCasa;
import Enums.CorPeca;
import Enums.Resultado;

//Apenas para teste, esta classe testa todos os metodos principais.
public class Main {
	public static void main(String[] args) {
		Jogo jogo = new Jogo(new Jogador(), new Jogador(), new Jogador(), new Tabuleiro(), null, new Date());
		jogo.iniciarPartida();
		
		jogo.getTabuleiro().mostrarTabuleiro();
		System.out.println("Vez do " + jogo.getAtualJogador().getNome());
		while(!jogo.isFimDeJogo()) {
			int intVetores[] = new int[4];
	    	for(int i = 0; i < 4; i++) {
	    		System.out.print("Digite o numero "+ i+ ": ");
	    		Scanner scan = new Scanner(System.in);
	    		intVetores[i] = scan.nextInt();
	    		//scan.close();
	    	}
	    	if(jogo.jogar(jogo.getTabuleiro().getCasaGrid(intVetores[0], intVetores[1]), jogo.getTabuleiro().getCasaGrid(intVetores[2], intVetores[3]))) {
	    		jogo.getTabuleiro().mostrarTabuleiro();
	    		System.out.println("Vez do " + jogo.getAtualJogador().getNome());
	    	}else {
	    		jogo.getTabuleiro().mostrarTabuleiro();
	    		System.out.println("Jogue outra vez, " + jogo.getAtualJogador().getNome());
	    	}
		}
		
		System.out.println("JOGO ACABOU");
	}
	/*public static void main(String[] args) {
		//Jogadores, uma classe que cria os jogadores, adicionando seus nomes e criando e adicionando suas pecas.
		Jogadores jogadoresMetodos = new Jogadores();
		
		//Array com os dois jogadores.
		Jogador[] jogadores;
		jogadores = new Jogador[2];
		
		//Adiciona os jogadores criados na array.
		jogadores = jogadoresMetodos.criarJogadores();
		
		//Classe Casa que recebe uma cor, um boolean pra saber se esta ocupada, e uma peca.
		Casa casaBranca = new Casa(CorCasa.BRANCO, false, null);
		Casa casaPreta = new Casa(CorCasa.PRETO, false, null);
		
		//Classe Peca, que recebe uma cor e um jogador.
		Peca pecaClara = new Peca(CorPeca.CLARO, null);
		Peca pecaEscura = new Peca(CorPeca.ESCURO, null);
		
		//Classe Tabuleiro, com metodos para criar e mostrar o tabuleiro, e movimentar as pecas nele.
		Tabuleiro tabuleiro = new Tabuleiro();
		
		System.out.println();
		System.out.println("O Jogador 1 se chama "+ jogadores[0].getNome()+",");
		System.out.println("e o jogador 2 se chama "+ jogadores[1].getNome()+".");
		
		System.out.println();
		System.out.println("As casas podem ter a cor: " + casaBranca.getCor() + " ou " + casaPreta.getCor() + ".");
		System.out.println("Ja a peca, pode ser: " + pecaClara.getCor()+" ou " + pecaEscura.getCor()+".");
		
		System.out.println();
		System.out.println("Este e' o tabuleiro, com todas as pecas em suas devidas posicoes e cores.");
		System.out.println();
		tabuleiro.gerarTabuleiro(jogadores);
		tabuleiro.mostrarTabuleiro();
		
		System.out.println();
		System.out.println("A casa em (0,1) possui a cor: " + tabuleiro.GetCasaGrid(0,1).getCor());
		System.out.println("Enquanto que na casa (5,4), a peca possui a cor: " + tabuleiro.GetCasaGrid(5,4).getPeca().getCor());
		
		System.out.println();
		System.out.println("Agora, vamos movimentar a peca da casa (5,6) para a casa (4,7):");
		tabuleiro.executarMovimento(5,6,4,7);
		System.out.println();
		tabuleiro.mostrarTabuleiro();
		
		System.out.println();
		System.out.println("Por ultimo, digite 4 valores int: um X e um Y da casa onde quer movimentar a peca,");
		System.out.println("E mais outro X e Y da casa para onde deseja movimentar:");
		tabuleiro.chamarMovimento();
		
		System.out.println();
		tabuleiro.mostrarTabuleiro();
		
		
		
		
		
	}
*/
}
