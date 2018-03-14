package br.com.poli.damIA;
import br.com.poli.componentes.Tabuleiro;
import java.util.ArrayList;
import br.com.poli.Interface;
import br.com.poli.Jogador;
import br.com.poli.enums.CorCasa;
import br.com.poli.enums.CorPeca;

public class JogadorAutonomo  extends Jogador implements AutoPlayer{
	private ArrayList<int[]> listaPossiveis;
	private ArrayList<int[]> listaPossiveisCaptura;
	private Interface jogo;
	
	public JogadorAutonomo() {
		super(true);
		listaPossiveis = new ArrayList<int[]>();
		listaPossiveisCaptura = new ArrayList<int[]>();
		this.jogo = null;
	}
	
	public int[] jogarAuto() {
		int[] escolha = new int[5];
		
		//Depth		
		int nivel = 6;
		//
		
		if(jogo.getCasaCapturaMultipla() != null) {
			int x = jogo.getCasaCapturaMultipla().getPosX();
			int y = jogo.getCasaCapturaMultipla().getPosY();
			int[] multiplo = verificarCapturaCasa(x, y, jogo.getTabuleiro());
			
			escolha[1] = x;
			escolha[2] = y;
			escolha[3] = multiplo[0];
			escolha[4] = multiplo[1];
		}else {
			criarArvore(jogo.getTabuleiro(), nivel);
			int melhorPontuacao = 0;
			melhorPontuacao = miniMax(jogo.getTabuleiro(), nivel+1, true);
			escolha = encontrarJogadaPelaPontuacao(melhorPontuacao);
		}
		return escolha;
	}
	
	public void criarArvore(Tabuleiro tabAtual, int nivel) {
		
		if(nivel <= 0) {
			jogo.setAtualJogador(jogo.getJogador2());
		}else {
			gerarPossibilidadesTabuleiro(tabAtual);
			
			for(Tabuleiro tab : tabAtual.getListaPossiveis()) {
				jogo.trocarJogador();
				criarArvore(tab, nivel-1);
			}
		}
		jogo.setAtualJogador(jogo.getJogador2());
	}
	
	public int[] encontrarJogadaPelaPontuacao(int pontuacao) {
		for(Tabuleiro possibilidade : jogo.getTabuleiro().getListaPossiveis()) {
			if(possibilidade.getScore() == pontuacao) {
				return possibilidade.getMovimento();
			}
		}
		return null;
	}
	
	public void gerarPossibilidadesTabuleiro(Tabuleiro tabuleiroAtual) {
	//tabuleiroAtual.setScore();
	tabuleiroAtual.limparPossibilidades();
	
	listaPossiveis.clear();
	listaPossiveisCaptura.clear();
	
	verificaCaptura(tabuleiroAtual);
	if(listaPossiveisCaptura.size() == 0) {
		iniciarVerificacaoMovimento(tabuleiroAtual);
		if(listaPossiveis.size() == 0) {
			System.out.println("SEM MOVIMENTOS POSSIVEIS!!!!!");
		}else {
			//System.out.println(listaPossiveis.size());
			int tamanho = listaPossiveis.size();
			for(int i = 0; i < tamanho; i++) {
				Tabuleiro novaPossibilidade = tabuleiroAtual.clone();
				novaPossibilidade.gerarTabuleiroMovimento(listaPossiveis.get(i)[1], listaPossiveis.get(i)[2], listaPossiveis.get(i)[3], listaPossiveis.get(i)[4]);
				novaPossibilidade.setMovimento(listaPossiveis.get(i));
				tabuleiroAtual.adicionarPossiblidade(novaPossibilidade);
			}
		}
	}else {
		int tamanho = listaPossiveisCaptura.size();
		for(int i = 0; i < tamanho; i++) {
			Tabuleiro novaPossibilidade = tabuleiroAtual.clone();
			novaPossibilidade.gerarTabuleiroCaptura(this, listaPossiveisCaptura.get(i)[1], listaPossiveisCaptura.get(i)[2], listaPossiveisCaptura.get(i)[3], listaPossiveisCaptura.get(i)[4]);
			novaPossibilidade.setMovimento(listaPossiveisCaptura.get(i));
			tabuleiroAtual.adicionarPossiblidade(novaPossibilidade);
		}
	}
	}
	
	public int miniMax(Tabuleiro tabAtual, int depth, boolean isMax) {
		
		if(depth == 0 || tabAtual.getNumeroPossiveis() == 0) {
			tabAtual.setScore(this.jogo);
			return tabAtual.getScore();
		}else {
		
			if(isMax) {
				//Vez da IA
				int bestValue = Integer.MIN_VALUE;
				for(Tabuleiro child : tabAtual.getListaPossiveis()) {
					int v = miniMax(child, depth-1, false);
					bestValue = Math.max(bestValue, v);
				}
				tabAtual.setScore(bestValue);
				return bestValue;
			}else {
				//Vez do Jogador
				int bestValue = Integer.MAX_VALUE;
				for(Tabuleiro child : tabAtual.getListaPossiveis()) {
					int v = miniMax(child, depth-1, true);
					bestValue = Math.min(bestValue, v);
				}
				tabAtual.setScore(bestValue);
				return bestValue;
			}
		}
	}
	
	public void iniciarVerificacaoMovimento(Tabuleiro tab) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tab.getCasaGrid(i, j).getPeca() != null && tab.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
					
					int[] destinoSupEsq = verificaMovimentoSupEsq(i, j, tab);
					if(destinoSupEsq != null) {
						int[] opcao = new int[5];
						opcao[1] = i;
						opcao[2] = j;
						opcao[3] = destinoSupEsq[0];
						opcao[4] = destinoSupEsq[1];
						listaPossiveis.add(opcao);
					}
					
					int[] destinoSupDir = verificaMovimentoSupDir(i, j, tab);
					if(destinoSupDir != null) {
						int[] opcao = new int[5];
						opcao[1] = i;
						opcao[2] = j;
						opcao[3] = destinoSupDir[0];
						opcao[4] = destinoSupDir[1];
						listaPossiveis.add(opcao);
					}
					
					int[] destinoInfEsq = verificaMovimentoInfEsq(i, j, tab);
					if(destinoInfEsq != null) {
						int[] opcao = new int[5];
						opcao[1] = i;
						opcao[2] = j;
						opcao[3] = destinoInfEsq[0];
						opcao[4] = destinoInfEsq[1];
						listaPossiveis.add(opcao);
					}
					
					int[] destinoInfDir = verificaMovimentoInfDir(i, j, tab);
					if(destinoInfDir != null) {
						int[] opcao = new int[5];
						opcao[1] = i;
						opcao[2] = j;
						opcao[3] = destinoInfDir[0];
						opcao[4] = destinoInfDir[1];
						listaPossiveis.add(opcao);
					}
				}
			}
		}
	}
	
public int[] verificaMovimentoSupEsq(int posX, int posY, Tabuleiro tab) {
	int[] casaPossivel = null;
	
	if(tab.getCasaGrid(posX, posY).getPeca().getCor() == CorPeca.CLARO) {
		casaPossivel = null;
		if(posX > 0 && posY > 0) {
		//Lado Superior Esquerdo
			//este if vira true se onde o jogador quer ir esta´ vazio e o jogador queria ir para tal casa
			if(tab.getCasaGrid(posX-1, posY-1).getOcupada() == false) {
				casaPossivel = new int[] {posX-1,posY-1};
			}
		}
	}
	return casaPossivel;
}

public int[] verificaMovimentoSupDir(int posX, int posY, Tabuleiro tab) {
	int[] casaPossivel = null;
	
	if(tab.getCasaGrid(posX, posY).getPeca().getCor() == CorPeca.CLARO) {
		casaPossivel = null;
		if(posX > 0 && posY < 7) {
			//Lado Superior Direito
			if(tab.getCasaGrid(posX-1, posY+1).getOcupada() == false) {
				casaPossivel = new int[] {posX-1,posY+1};
			}
		}
	}
	return casaPossivel;
}

public int[] verificaMovimentoInfEsq(int posX, int posY, Tabuleiro tab) {
	int[] casaPossivel = null;
	
	if(tab.getCasaGrid(posX, posY).getPeca().getCor() == CorPeca.ESCURO) {
		casaPossivel = null;
		if(posX < 7 && posY > 0) {
			//Lado Inferior Esquerdo
			if(tab.getCasaGrid(posX+1, posY-1).getOcupada() == false) {
				casaPossivel = new int[] {posX+1,posY-1};
			}
		}
	}
	return casaPossivel;
}

public int[] verificaMovimentoInfDir(int posX, int posY, Tabuleiro tab) {
	int[] casaPossivel = null;
	
	if(tab.getCasaGrid(posX, posY).getPeca().getCor() == CorPeca.ESCURO) {
		casaPossivel = null;
		if(posX < 7 && posY < 7) {
			//Lado Inferior Direito
			if(tab.getCasaGrid(posX+1, posY+1).getOcupada() == false) {
				casaPossivel = new int[] {posX+1,posY+1};
			}
		}
	}
	return casaPossivel;
}

public void verificaCaptura(Tabuleiro tab) {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tab.getCasaGrid(i, j).getPeca() != null && tab.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
					int[] casaOponente = verificarCapturaCasa(i, j, tab);
					if(casaOponente != null) {
						int[] opcao = new int[5];
						opcao[1] = i;
						opcao[2] = j;
						opcao[3] = casaOponente[0];
						opcao[4] = casaOponente[1];
						listaPossiveisCaptura.add(opcao);
					}
				}
			}
		}
	}
	
public int[] verificarCapturaCasa(int i, int j, Tabuleiro tab) {
 		//Casa em que se pode ir depois do movimento ou captura.
 	    int[] capturaPossivel = null;
 			
 		if(tab.getCasaGrid(i, j).getCor() == CorCasa.PRETO && 
 				tab.getCasaGrid(i, j).getPeca() != null &&
 						tab.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
 				
 			//procura uma possivel captura na direcao Superior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorEsq(i, j, tab);
 			if(capturaPossivel != null) {
 				return capturaPossivel;
 			}
 			//procura uma possivel captura na direcao Superior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorDir(i, j, tab);
 			if(capturaPossivel != null) {
 				return capturaPossivel;
 			}
 			//procura uma possivel captura na direcao Inferior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorEsq(i, j, tab);
 			if(capturaPossivel != null) {
 				return capturaPossivel;
 			}
 			//procura uma possivel captura na direcao Inferior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorDir(i, j, tab);
 			if(capturaPossivel != null) {
 				return capturaPossivel;
 			}
 		}
 	
 	return capturaPossivel;
 }
 
 public boolean verificarPossibilidadeCapturaCasa(int x, int y, Tabuleiro tab) {
 	if(verificarCapturaSuperiorEsq(x,y, tab) != null||
 		verificarCapturaSuperiorDir(x,y, tab)!= null||
 		verificarCapturaInferiorEsq(x,y, tab)!= null||
 		verificarCapturaInferiorDir(x,y, tab)!= null) {
 		return true;
 	}
 	return false;
 }
 
 public boolean verificarCapturaTabuleiro(Tabuleiro tab) {
 	for(int i = 0; i < 8; i++) {
 		for(int j =0; j < 8; j++) {
 			
 		//Casa em que se pode ir depois do movimento ou captura.
 	    int[] capturaPossivel = null;
 			
 		if(tab.getCasaGrid(i, j).getCor() == CorCasa.PRETO && 
 				tab.getCasaGrid(i, j).getPeca() != null &&
 						tab.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
 				
 			//procura uma possivel captura na direcao Superior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorEsq(i, j, tab);
 			if(capturaPossivel != null) {
 				return true;
 			}
 			//procura uma possivel captura na direcao Superior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorDir(i, j, tab);
 			if(capturaPossivel != null) {
 				return true;
 			}
 			//procura uma possivel captura na direcao Inferior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorEsq(i, j, tab);
 			if(capturaPossivel != null) {
 				return true;
 			}
 			//procura uma possivel captura na direcao Inferior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorDir(i, j, tab);
 			if(capturaPossivel != null) {
 				return true;
 			}
 		}
 			
 		}
 	}
 	
 	return false;
 }
 
 //Grupo de metodos para verificar se existe oportunidade de captura em certa direcao.
 public int[] verificarCapturaSuperiorEsq(int posX, int posY, Tabuleiro tab) {
 	
 	//impede de verificar captura fora do tabuleiro
 	if(posX >= 2 && posY >= 2) {
 		
 	//Se esse if for verdadeiro, a pedra nao é dama
 		if(tab.getCasaGrid(posX, posY).getPeca() != null) {
 	if(tab.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		
     	if(tab.getCasaGrid(posX-1, posY-1).getOcupada() == true && tab.getCasaGrid(posX-1, posY-1).getPeca().getJogador() != jogo.getAtualJogador()) {
 			if(tab.getCasaGrid(posX-2, posY-2).getOcupada() == false) {
 				int[] novaCasa = new int[2];
 				novaCasa[0] = posX-2;
 				novaCasa[1] = posY-2;
 				return novaCasa;
 			}
     	}
     	//Se a pedra for dama, essa parte vai rodar
 	}else {
 		//Mesmo FOR estranho visto no movimento da dama.
 		int j = posY;
 		for(int i = posX-1; i >= 1; i--) {
 			j--;
 			if(j >= 1) {
 				//System.out.println(i+","+ j);
 				if(tab.getCasaGrid(i, j) != tab.getCasaGrid(posX, posY)) {
     			if(tab.getCasaGrid(i, j).getOcupada() == true) {
     				if(tab.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
     					if(tab.getCasaGrid(i-1, j-1).getOcupada() == false) {
     						int[] novaCasa = new int[2];
     		 				novaCasa[0] = i-1;
     		 				novaCasa[1] = j-1;
     						return novaCasa;
     					}else {
     						break;
     					}
     				}else {
     					break;
     				}
     			}
 			}
     		}
     	}
 	}
 	}else {
 		return null;
 	}
 }else {
 	return null;
 }
 	return null;
 }
 
 public int[] verificarCapturaSuperiorDir(int posX, int posY, Tabuleiro tab) {
 	//impede de verificar captura fora do tabuleiro
 	if(posX >= 2 && posY <= 5) {
 	//Se esse if for verdadeiro, a pedra nao e' dama
 		
 		if(tab.getCasaGrid(posX, posY).getPeca() != null) {
 	if(tab.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		if(tab.getCasaGrid(posX-1, posY+1).getOcupada() == true && tab.getCasaGrid(posX-1, posY+1).getPeca().getJogador() != jogo.getAtualJogador()) {
 			if(tab.getCasaGrid(posX-2, posY+2).getOcupada() == false) {
 				int[] novaCasa = new int[2];
 				novaCasa[0] = posX-2;
 				novaCasa[1] = posY+2;
 				return novaCasa;
 			}
 		}
 	}else {
 		
 		int j = posY;
 		for(int i = posX-1; i >= 1; i--) {
 			j++;
     		if(j <= 6) {
     			if(tab.getCasaGrid(i, j).getOcupada() == true) {
     				if(tab.getCasaGrid(i, j) != tab.getCasaGrid(posX, posY)) {
     				if(tab.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
     				if(tab.getCasaGrid(i-1, j+1).getOcupada() == false) {
     					int[] novaCasa = new int[2];
 		 				novaCasa[0] = i-1;
 		 				novaCasa[1] = j+1;
     					return novaCasa;
     				}else {
     					break;
     				}
     				}else {
     					break;
     				}
     			}
     		}
     		}
     	}
 	}
 		}else {
     		return null;
     	}
 	}else {
 		return null;
 	}
 	return null;
 }
 
 public int[] verificarCapturaInferiorEsq(int posX, int posY, Tabuleiro tab) {
	 
 	//impede de verificar captura fora do tabuleiro
 	if(posX <= 5 && posY >= 2) {
 	//Se esse if for verdadeiro, a pedra nao e' dama
 		if(tab.getCasaGrid(posX, posY).getPeca() != null) {
 			
 		
 	if(tab.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		if(tab.getCasaGrid(posX+1, posY-1).getOcupada() == true && tab.getCasaGrid(posX+1, posY-1).getPeca().getJogador() != jogo.getAtualJogador()) {
 			if(tab.getCasaGrid(posX+2, posY-2).getOcupada() == false) {
 				int[] novaCasa = new int[2];
 				novaCasa[0] = posX+2;
 				novaCasa[1] = posY-2;
 				return novaCasa;
 			}
 		}
 	}else {
 		
 		int j = posY;
 		for(int i = posX+1; i <= 6; i++) {
 			j--;
     		if(j >= 1) {
     			if(tab.getCasaGrid(i, j).getOcupada() == true) {
     				if(tab.getCasaGrid(i, j) != tab.getCasaGrid(posX, posY)) {
     				if(tab.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
     				if(tab.getCasaGrid(i+1, j-1).getOcupada() == false) {
     					int[] novaCasa = new int[2];
 		 				novaCasa[0] = i+1;
 		 				novaCasa[1] = j-1;
     					return novaCasa;
     				}else {
     					break;
     				}
     				}else {
     					break;
     				}
     			}
     		}
     		}
     	}
 	}
 	
 	}else {
 		return null;
 	}
 	}else {
 		return null;
 	}
 	return null;
 }
 
 public int[] verificarCapturaInferiorDir(int posX, int posY, Tabuleiro tab) {
 	//impede de verificar captura fora do tabuleiro
 	if(posX <= 5 && posY <= 5) {
 	//Se esse if for verdadeiro, a pedra nao e' dama
 		if(tab.getCasaGrid(posX, posY).getPeca() != null) {
 	if(tab.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		if(tab.getCasaGrid(posX+1, posY+1).getOcupada() == true&& tab.getCasaGrid(posX+1, posY+1).getPeca().getJogador() != jogo.getAtualJogador()) {
 			if(tab.getCasaGrid(posX+2, posY+2).getOcupada() == false) {
 				int[] novaCasa = new int[2];
 				novaCasa[0] = posX+2;
 				novaCasa[1] = posY+2;
 				return novaCasa;
 			}
 		}
 	}else {
 		int j = posY;
 		for(int i = posX+1; i <= 6; i++) {
 			j++;
     		if(j <= 6) {
     			if(tab.getCasaGrid(i, j).getOcupada() == true) {
     				if(tab.getCasaGrid(i, j) != tab.getCasaGrid(posX, posY)) {
     				if(tab.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
     				if(tab.getCasaGrid(i+1, j+1).getOcupada() == false) {
     					int[] novaCasa = new int[2];
 		 				novaCasa[0] = i+1;
 		 				novaCasa[1] = j+1;
     					return novaCasa;
     				}else {
     					break;
     				}
     				}else {
     					break;
     				}
     			}
     		}
     		}
 		}
 	}
 		}else {
     		return null;
     	}
 	}else {
 		return null;
 	}
 	return null;
 }
 
 public void setJogo(Interface jogo) {
	 this.jogo = jogo;
 }
}
