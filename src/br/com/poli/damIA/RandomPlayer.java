package br.com.poli.damIA;

import java.util.ArrayList;
import java.util.Random;
import br.com.poli.Interface;
import br.com.poli.componentes.Casa;
import br.com.poli.componentes.Jogador;
import br.com.poli.componentes.Tabuleiro;
import br.com.poli.enums.CorCasa;
import br.com.poli.enums.CorPeca;

public class RandomPlayer  extends Jogador implements AutoPlayer{
	private Tabuleiro tabuleiro;
	private ArrayList<Casa[]> listaPossiveis;
	private ArrayList<Casa[]> listaPossiveisCaptura;
	private Interface jogo;
	public RandomPlayer() {
		this.tabuleiro = null;
		listaPossiveis = new ArrayList<Casa[]>();
		listaPossiveisCaptura = new ArrayList<Casa[]>();
		jogo = null;
	}
	
	public boolean jogarAuto() {
		listaPossiveis.clear();
		listaPossiveisCaptura.clear();
		this.tabuleiro = jogo.getTabuleiro();
		int posX = 0, posY = 0;
		
		verificaCaptura();
		
		if(listaPossiveisCaptura.size() == 0) {
			iniciarVerificacaoMovimento();
			if(listaPossiveis.size() == 0) {
				return false;
			}else {
				 Random randomGenerator = new Random();
				 Casa[] possivel = listaPossiveis.get(randomGenerator.nextInt(listaPossiveis.size()));
				if(possivel != null) {
					tabuleiro.executarMovimento(possivel[0].getPosX(), possivel[0].getPosY(), possivel[1].getPosX(), possivel[1].getPosY());
					return true;
				}
			}
		}else {
			Random randomGenerator = new Random();
			 Casa[] possivel = listaPossiveisCaptura.get(randomGenerator.nextInt(listaPossiveisCaptura.size()));
			if(possivel != null) {
				jogo.capturar(possivel[0].getPosX(), possivel[0].getPosY(), possivel[1].getPosX(), possivel[1].getPosY());
				return true;
			}
			
		}
		
		return false;
	}
	
	public void setJogo(Interface jogo) {
		this.jogo = jogo;
	}
	
	public Interface getJogo() {
		return this.jogo;
	}

	public Jogador vencedor() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public void iniciarVerificacaoMovimento() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tabuleiro.getCasaGrid(i, j).getPeca() != null && tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
					Casa destino = verificaMovimento(tabuleiro.getCasaGrid(i, j));
					if(destino != null) {
						Casa[] opcao = new Casa[2];
						opcao[0] = tabuleiro.getCasaGrid(i, j);
						opcao[1] = destino;
						listaPossiveis.add(opcao);
					}
				}
			}
		}
	}
	
	public Casa verificaMovimento(Casa casaOrigem) {
		Casa casaPossivel = null;
		int posX = casaOrigem.getPosX();
    	int posY = casaOrigem.getPosY();
    	
    			//Verifica a direcao que a peca pode movimentar
    			
    			//Se a pedra for clara
    			if(casaOrigem.getPeca().getCor() == CorPeca.CLARO) {
    			casaPossivel = null;
    			if(posX > 0 && posY > 0) {
    			//Lado Superior Esquerdo
    				//este if vira true se onde o jogador quer ir esta´ vazio e o jogador queria ir para tal casa
    			if(tabuleiro.getCasaGrid(posX-1, posY-1).getOcupada() == false) {
					casaPossivel = tabuleiro.getCasaGrid(posX-1, posY-1);
				}
    			}
    			
    			if(posX > 0 && posY < 7) {
    			//Lado Superior Direito
    			if(tabuleiro.getCasaGrid(posX-1, posY+1).getOcupada() == false) {
					casaPossivel = tabuleiro.getCasaGrid(posX-1, posY+1);
				}
    			}
    			
    			//Se a pedra for escura
    			}else {
    			if(posX < 7 && posY > 0) {
    			//Lado Inferior Esquerdo
    			if(tabuleiro.getCasaGrid(posX+1, posY-1).getOcupada() == false) {
					casaPossivel = tabuleiro.getCasaGrid(posX+1, posY-1);
				}
    			}
    			
    			if(posX < 7 && posY < 7) {
    			//Lado Inferior Direito
    			if(tabuleiro.getCasaGrid(posX+1, posY+1).getOcupada() == false) {
					casaPossivel = tabuleiro.getCasaGrid(posX+1, posY+1);
				}
    			}
    			
    			}
    			return casaPossivel;
	}
	
	public void verificaCaptura() {
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tabuleiro.getCasaGrid(i, j).getPeca() != null && tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
					Casa casaOponente = verificarCapturaCasa(tabuleiro.getCasaGrid(i, j));
					if(casaOponente != null) {
						Casa[] opcao = new Casa[2];
						opcao[0] = tabuleiro.getCasaGrid(i, j);
						opcao[1] = casaOponente;
						listaPossiveisCaptura.add(opcao);
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
public Casa verificarCapturaCasa(Casa casa) {
 		int i = casa.getPosX();
 		int j = casa.getPosY();
 		//Casa em que se pode ir depois do movimento ou captura.
 	    Casa capturaPossivel = null;
 			
 		if(tabuleiro.getCasaGrid(i, j).getCor() == CorCasa.PRETO && 
 			tabuleiro.getCasaGrid(i, j).getPeca() != null &&
 			tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
 				
 			//procura uma possivel captura na direcao Superior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorEsq(i, j);
 			if(capturaPossivel != null) {
 				
 			}
 			//procura uma possivel captura na direcao Superior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorDir(i, j);
 			if(capturaPossivel != null) {
 				
 			}
 			//procura uma possivel captura na direcao Inferior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorEsq(i, j);
 			if(capturaPossivel != null) {
 				
 			}
 			//procura uma possivel captura na direcao Inferior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorDir(i, j);
 			if(capturaPossivel != null) {
 				
 			}
 		}
 	
 	return capturaPossivel;
 }
 
 public boolean verificarPossibilidadeCapturaCasa(Casa casa) {
 	int x = casa.getPosX();
 	int y = casa.getPosY();
 	if(verificarCapturaSuperiorEsq(x,y) != null||
 		verificarCapturaSuperiorDir(x,y)!= null||
 		verificarCapturaInferiorEsq(x,y)!= null||
 		verificarCapturaInferiorDir(x,y)!= null) {
 		return true;
 	}
 	
 	return false;
 }
 
 public boolean verificarCapturaTabuleiro() {
 	for(int i = 0; i < 8; i++) {
 		for(int j =0; j < 8; j++) {
 			
 		//Casa em que se pode ir depois do movimento ou captura.
 	    Casa capturaPossivel = null;
 			
 		if(tabuleiro.getCasaGrid(i, j).getCor() == CorCasa.PRETO && 
 			tabuleiro.getCasaGrid(i, j).getPeca() != null &&
 			tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != jogo.getAtualJogador()) {
 				
 			//procura uma possivel captura na direcao Superior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorEsq(i, j);
 			if(capturaPossivel != null) {
 				return true;
 			}
 			//procura uma possivel captura na direcao Superior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaSuperiorDir(i, j);
 			if(capturaPossivel != null) {
 				return true;
 			}
 			//procura uma possivel captura na direcao Inferior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorEsq(i, j);
 			if(capturaPossivel != null) {
 				return true;
 			}
 			//procura uma possivel captura na direcao Inferior Direita. se existir um lugar, casaPossivel recebe esta casa.
 			capturaPossivel = verificarCapturaInferiorDir(i, j);
 			if(capturaPossivel != null) {
 				return true;
 			}
 		}
 			
 		}
 	}
 	
 	return false;
 }
 
 //Grupo de metodos para verificar se existe oportunidade de captura em certa direcao.
 public Casa verificarCapturaSuperiorEsq(int posX, int posY) {
 	
 	//impede de verificar captura fora do tabuleiro
 	if(posX >= 2 && posY >= 2) {
 		
 	//Se esse if for verdadeiro, a pedra nao é dama
 		if(this.tabuleiro.getCasaGrid(posX, posY).getPeca() != null) {
 	if(this.tabuleiro.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		
     	if(tabuleiro.getCasaGrid(posX-1, posY-1).getOcupada() == true && tabuleiro.getCasaGrid(posX-1, posY-1).getPeca().getJogador() == jogo.getAtualJogador()) {
 			if(tabuleiro.getCasaGrid(posX-2, posY-2).getOcupada() == false) {
 				Casa novaCasa = null;
 				novaCasa = tabuleiro.getCasaGrid(posX-2, posY-2);
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
 				if(tabuleiro.getCasaGrid(i, j) != tabuleiro.getCasaGrid(posX, posY)) {
     			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
     				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
     					if(tabuleiro.getCasaGrid(i-1, j-1).getOcupada() == false) {
     						Casa novaCasa = null;
     						novaCasa = tabuleiro.getCasaGrid(i-1, j-1);
     						return novaCasa;
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
 
 public Casa verificarCapturaSuperiorDir(int posX, int posY) {
 	//impede de verificar captura fora do tabuleiro
 	if(posX >= 2 && posY <= 5) {
 	//Se esse if for verdadeiro, a pedra nao e' dama
 		
 		if(this.tabuleiro.getCasaGrid(posX, posY).getPeca() != null) {
 	if(this.tabuleiro.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		if(tabuleiro.getCasaGrid(posX-1, posY+1).getOcupada() == true && tabuleiro.getCasaGrid(posX-1, posY+1).getPeca().getJogador() == jogo.getAtualJogador()) {
 			if(tabuleiro.getCasaGrid(posX-2, posY+2).getOcupada() == false) {
 				Casa novaCasa = null;
 				novaCasa = tabuleiro.getCasaGrid(posX-2, posY+2);
 				return novaCasa;
 			}
 		}
 	}else {
 		
 		int j = posY;
 		for(int i = posX-1; i >= 1; i--) {
 			j++;
     		if(j <= 6) {
     			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
     				if(tabuleiro.getCasaGrid(i, j) != tabuleiro.getCasaGrid(posX, posY)) {
     				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
     				if(tabuleiro.getCasaGrid(i-1, j+1).getOcupada() == false) {
     					Casa novaCasa = null;
     					novaCasa = tabuleiro.getCasaGrid(i-1, j+1);
     					return novaCasa;
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
 
 public Casa verificarCapturaInferiorEsq(int posX, int posY) {
 	//impede de verificar captura fora do tabuleiro
 	if(posX <= 5 && posY >= 2) {
 	//Se esse if for verdadeiro, a pedra nao e' dama
 		if(this.tabuleiro.getCasaGrid(posX, posY).getPeca() != null) {
 			
 		
 	if(this.tabuleiro.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		if(tabuleiro.getCasaGrid(posX+1, posY-1).getOcupada() == true && tabuleiro.getCasaGrid(posX+1, posY-1).getPeca().getJogador() == jogo.getAtualJogador()) {
 			if(tabuleiro.getCasaGrid(posX+2, posY-2).getOcupada() == false) {
 				Casa novaCasa = null;
 				novaCasa = tabuleiro.getCasaGrid(posX+2, posY-2);
 				return novaCasa;
 			}
 		}
 	}else {
 		
 		int j = posY;
 		for(int i = posX+1; i <= 6; i++) {
 			j--;
     		if(j >= 1) {
     			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
     				if(tabuleiro.getCasaGrid(i, j) != tabuleiro.getCasaGrid(posX, posY)) {
     				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
     				if(tabuleiro.getCasaGrid(i+1, j-1).getOcupada() == false) {
     					Casa novaCasa = null;
     					novaCasa = tabuleiro.getCasaGrid(i+1, j-1);
     					return novaCasa;
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
 
 public Casa verificarCapturaInferiorDir(int posX, int posY) {
 	//impede de verificar captura fora do tabuleiro
 	if(posX <= 5 && posY <= 5) {
 	//Se esse if for verdadeiro, a pedra nao e' dama
 		if(this.tabuleiro.getCasaGrid(posX, posY).getPeca() != null) {
 	if(this.tabuleiro.getCasaGrid(posX, posY).getPeca().getIsDama() == false) {
 		if(tabuleiro.getCasaGrid(posX+1, posY+1).getOcupada() == true&& tabuleiro.getCasaGrid(posX+1, posY+1).getPeca().getJogador() == jogo.getAtualJogador()) {
 			if(tabuleiro.getCasaGrid(posX+2, posY+2).getOcupada() == false) {
 				Casa novaCasa = null;
 				novaCasa = tabuleiro.getCasaGrid(posX+2, posY+2);
 				return novaCasa;
 			}
 		}
 	}else {
 		int j = posY;
 		for(int i = posX+1; i <= 6; i++) {
 			j++;
     		if(j <= 6) {
     			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
     				if(tabuleiro.getCasaGrid(i, j) != tabuleiro.getCasaGrid(posX, posY)) {
     				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogo.getAtualJogador()) {
     				if(tabuleiro.getCasaGrid(i+1, j+1).getOcupada() == false) {
     					Casa novaCasa = null;
     					novaCasa = tabuleiro.getCasaGrid(i+1, j+1);
     					return novaCasa;
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

}
