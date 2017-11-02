package br.com.poli;

import enums.CorCasa;
import enums.CorPeca;
import enums.Resultado;
import java.util.Date;

public class Jogo implements Interface{
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vencedor;
    private Tabuleiro tabuleiro;
    private Resultado resultado;
    private Date tempo;
    private int contadorJogadas;
    
    private Jogador atualJogador = null;
    
    public Jogo(Jogador jogador1, Jogador jogador2, Jogador vencedor, Tabuleiro tabuleiro,
                Resultado resultado, Date tempo){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
        this.vencedor = vencedor;
        this.resultado = resultado;
        this.tempo = tempo;
        this.contadorJogadas = 0;
    }
    
    //O primeiro metodo a ser chamado, ele configura os jogadores, define o primeiro a jogar e cria o tabuleiro.
    public void iniciarPartida(){
    	jogador1.setNome("Jogador 1");
    	jogador2.setNome("Jogador 2");
    	tabuleiro.gerarTabuleiro(jogador1, jogador2);
    	atualJogador = jogador1;
    }
    
  /*Metodo a ser chamado antes de cada jogada, ele verifica as condicoes para fim de jogo, 
   * retornando um boolean representando se o jogo acabou ou nao.*/
    public boolean isFimDeJogo(){
    	boolean jogoAcabou = false;
    	//Verifica a regra de empate.
    	if(contadorJogadas == 20) {
			resultado = Resultado.empate;
			return true;
		}
    	
    	//Verifica se ainda existem pecas do jogador. Se nao existir, declara o adversario como vencedor.
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			
    			if(tabuleiro.getCasaGrid(i, j).getPeca() != null || tabuleiro.getCasaGrid(i, j).getDama() != null) {
    				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogador1 || tabuleiro.getCasaGrid(i, j).getDama().getJogador() == jogador1) {
    					jogoAcabou = false;
    					vencedor = null;
    					break;
    				}else {
    					jogoAcabou = true;
    					resultado = Resultado.comVencedor;
    					vencedor = jogador2;
    				}
    				
    			}
    			
    			if(tabuleiro.getCasaGrid(i, j).getPeca() != null || tabuleiro.getCasaGrid(i, j).getDama() != null) {
    				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogador2 || tabuleiro.getCasaGrid(i, j).getDama().getJogador() == jogador2) {
    					jogoAcabou = false;
    					vencedor = null;
    					break;
    				}else {
    					jogoAcabou = true;
    					resultado = Resultado.comVencedor;
    					vencedor = jogador1;
    				}
    				
    			}
    			
    			/*if(tabuleiro.getCasaGrid(i, j).getPeca() != null) {
    				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogador1) {
    					jogoAcabou = false;
    					vencedor = null;
    					break;
    				}else {
    					jogoAcabou = true;
    					resultado = Resultado.comVencedor;
    					vencedor = jogador2;
    				}
    			}else if(tabuleiro.getCasaGrid(i, j).getDama() != null){
    				if(tabuleiro.getCasaGrid(i, j).getDama().getJogador() == jogador1) {
    					jogoAcabou = false;
    					vencedor = null;
    					break;
    				}else {
    					jogoAcabou = true;
    					resultado = Resultado.comVencedor;
    					vencedor = jogador2;
    				}
    			}
    			
    			if(tabuleiro.getCasaGrid(i, j).getPeca() != null) {
    				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogador2) {
    					jogoAcabou = false;
    					vencedor = null;
    					break;
    				}else {
    					jogoAcabou = true;
    					vencedor = jogador1;
    				}
    			}else if(tabuleiro.getCasaGrid(i, j).getDama() != null){
    				if(tabuleiro.getCasaGrid(i, j).getDama().getJogador() == jogador2) {
    					jogoAcabou = false;
    					vencedor = null;
    					break;
    				}else {
    					jogoAcabou = true;
    					vencedor = jogador1;
    				}
    			}*/
    		}
    	}
    	return jogoAcabou;
    	
    }
    
    /*Metodo principal, recebe duas casas e verifica o movimento ou captura de uma casa para outra.
    funciona independente das variaveis do sistema, como o jogadorAtual.*/
    public boolean jogar(Casa casaOrigem, Casa casaDestino){
    	System.out.println("JOGANDO");
    	//Se o jogo acabou
    	//if(!isFimDeJogo()) {
    	//Casa em que se pode ir depois do movimento ou captura.
    	Casa casaPossivel = null;
    	
    	//Casa para ser capturada.
    	Casa casaOponente = null;
    	
    	//Cria 4 ints que representam as coordenadas da casa, para facilitar os calculos
    	int posX = casaOrigem.getPosX();
    	int posY = casaOrigem.getPosY();
    	int lugarParaX = casaDestino.getPosX();
    	int lugarParaY = casaDestino.getPosY();
    	// Se existir pedra na casa atual
    	if(casaOrigem.getOcupada() == true) {
    		//e se a pedra for do jogador que deve jogar agora
    		if(casaOrigem.getPeca().getJogador() == atualJogador){
    			
    			//Para verificar a captura, tanto a dama quanto a pedra comum sao verificadas da mesma maneira, 
    			//Mas nos metodos de verificacao fora de jogar(), cada tipo possui seu jeito de capturar.
    			
    			
    			boolean existeCaptura1 = false;
    			boolean existeCaptura2 = false;
    			boolean existeCaptura3 = false;
    			boolean existeCaptura4 = false;
    			
    			//procura uma possivel captura na direcao Superior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
    			casaPossivel = verificarCapturaSuperiorEsq(posX, posY);
    			
    			//Se existir um lugar para capturar
    			if(casaPossivel != null) {
    				//Se o jogador escolheu esse lugar para capturar
    				if(casaPossivel == casaDestino) {
    					//Encontra a casa em que o oponente a ser capturado estaria.
    					casaOponente = tabuleiro.getCasaGrid(casaPossivel.getPosX()+1, casaPossivel.getPosY()+1);
    				}else {
    					//Se o jogador nao escolheu, deixa salvo que ele poderia ter escolhido. s
    					existeCaptura1 = true;
    				}
    			}
    			//procura uma possivel captura na direcao Superior Direita. se existir um lugar, casaPossivel recebe esta casa.
    			casaPossivel = verificarCapturaSuperiorDir(posX, posY);
    			if(casaPossivel != null) {
    				if(casaPossivel == casaDestino) {
    					casaOponente = tabuleiro.getCasaGrid(casaPossivel.getPosX()+1, casaPossivel.getPosY()-1);
    				}else {
    					existeCaptura2 = true;
    				}
    			}
    			//procura uma possivel captura na direcao Inferior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
    			casaPossivel = verificarCapturaInferiorEsq(posX, posY);
    			if(casaPossivel != null) {
   					if(casaPossivel == casaDestino) {
   						casaOponente = tabuleiro.getCasaGrid(casaPossivel.getPosX()-1, casaPossivel.getPosY()+1);
   					}else {
   						existeCaptura3 = true;
   					}
    			}
    			//procura uma possivel captura na direcao Inferior Direita. se existir um lugar, casaPossivel recebe esta casa.
    			casaPossivel = verificarCapturaInferiorDir(posX, posY);
    			if(casaPossivel != null) {
   					if(casaPossivel == casaDestino) {
   						casaOponente = tabuleiro.getCasaGrid(casaPossivel.getPosX()-1, casaPossivel.getPosY()-1);
   					}else {
   						existeCaptura4 = true;
   					}
   				}
    				
    			//Se o jogador escolheu uma captura possivel
    			if(casaOponente != null) {
    				capturar(casaOrigem, casaOponente, casaDestino);
    				if(atualJogador == jogador1) {
	    				atualJogador = jogador2;
	    			}else {
	    				atualJogador = jogador1;
	    			}
    				contadorJogadas = 0;
    				return true;
    			}else {
   					/*se nao, verifica se realmente existe alguma captura possivel. Se alguma for true, o o metodo jogar acaba
    				pois o jogador deveria ter escolhi alguma captura que existia.*/
    				
    				
    				//Se exsite captura, o jogador deve capturar. faz o jogar() retornar falso, pedindo novos comandos.
   					if(existeCaptura1 == true) {
   						return false;
   					}else if(existeCaptura2 == true) {
   						return false;
   					}else if(existeCaptura3 == true) {
   						return false;
   					}else if(existeCaptura4 == true) {
   						return false;
   					}else {
   						
   						//Se não existe capturas, faz o movimento da dama e/ou pedra normal
    					if(verificarDama(casaOrigem)) {
    						casaPossivel = null;
    						
    						//Verifica movimento da dama pro lado Superior Esquerdo
    						/*Este for todo estranho foi necessario para andar na diagonal pelo tabuleiro, um simples for
    						 * nao resolveria*/
    						int j = posY+1;
    			    		for(int i = posX; i >= 0; i--) {
    			    			j--;
    			    			if(j >= 0) {
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					System.out.println("OK");
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		//Verifica movimento da dama pro lado Superior Direito
    			    		j = posY-1;
    			    		for(int i = posX; i >= 0; i--) {
    			    			j++;
    			    			if(j <= 7) {
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					System.out.println("OK");
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		//Verifica movimento da dama pro lado Inferior Esquerdo
    			    		j = posY+1;
    			    		for(int i = posX; i <= 7; i++) {
    			    			j--;
    			    			if(j >= 0) {
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					System.out.println("OK");
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		//Verifica movimento da dama pro lado Inferior Direito
    			    		j = posY-1;
    			    		for(int i = posX; i <= 7; i++) {
    			    			j++;
    			    			if(j <= 7) {
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					System.out.println("OK");
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		
    			    		
    			    		//Se casaPossivel for null, significa que nenhuma oportunidade de movimento pro jogador.
    			    		if(casaPossivel == null) {
    			    			return false;
    			    		}else {
    			    			tabuleiro.executarMovimento(casaOrigem, casaDestino);
    			    			if(atualJogador == jogador1) {
    			    				atualJogador = jogador2;
    			    			}else {
    			    				atualJogador = jogador1;
    			    			}
    			    			contadorJogadas++;
    			    		}
    			    		
    			    		
    			    	//Se nao for dama, o movimento pode ser verificado a seguir
    	    			}else {
    	    			//Limita o espaco para ser comido
    	    			if(lugarParaX>=0 && lugarParaX <=7 && lugarParaY>=0 && lugarParaY<=7){
    	    				/*Se a peca destino esta vazia*/
    	            		if(casaDestino.getOcupada() == false) {
    	            			
    	            			//Verifica a direcao que a peca pode movimentar
    	            			
    	            			//Se a pedra for clara
    	            			if(casaOrigem.getPeca().getCor() == CorPeca.CLARO) {
    	            			casaPossivel = null;
    	            			if(posX > 0 && posY > 0) {
    	            			//Lado Superior Esquerdo
    	            				//este if vira true se onde o jogador quer ir esta´ vazio e o jogador queria ir para tal casa
    	            			if(tabuleiro.getCasaGrid(posX-1, posY-1).getOcupada() == false && tabuleiro.getCasaGrid(posX-1, posY-1) == casaDestino) {
		        					casaPossivel = tabuleiro.getCasaGrid(posX-1, posY-1);
		        				}
    	            			}
    	            			
    	            			if(posX > 0 && posY < 7) {
    	            			//Lado Superior Direito
    	            			if(tabuleiro.getCasaGrid(posX-1, posY+1).getOcupada() == false && tabuleiro.getCasaGrid(posX-1, posY+1) == casaDestino) {
		        					casaPossivel = tabuleiro.getCasaGrid(posX-1, posY+1);
		        				}
    	            			}
    	            			
    	            			//Se a pedra for escura
    	            			}else {
    	            			if(posX < 7 && posY > 0) {
    	            			//Lado Inferior Esquerdo
    	            			if(tabuleiro.getCasaGrid(posX+1, posY-1).getOcupada() == false && tabuleiro.getCasaGrid(posX+1, posY-1) == casaDestino) {
		        					casaPossivel = tabuleiro.getCasaGrid(posX+1, posY-1);
		        				}
    	            			}
    	            			
    	            			if(posX < 7 && posY < 7) {
    	            			//Lado Inferior Direito
    	            			if(tabuleiro.getCasaGrid(posX+1, posY+1).getOcupada() == false && tabuleiro.getCasaGrid(posX+1, posY+1) == casaDestino) {
		        					casaPossivel = tabuleiro.getCasaGrid(posX+1, posY+1);
		        				}
    	            			}
    	            			
    	            			}
    	            			//Se nao existir movimento possivel
    	            			if(casaPossivel == null) {
        			    			return false;
        			    		}else {
        			    			//Executa o movimento
        			    			tabuleiro.executarMovimento(casaOrigem, casaDestino);
        			    			
        			    			//Verifica se a pedra e´ dama. se nao for, verifica se pode se tornar, e a torna uma dama.
        			    			if(!verificarDama(casaDestino)) {
        			    				if(casaDestino.getCor() == CorCasa.BRANCO) {
        			    					if(casaDestino.getPosX() == 0) {
        			    						casaDestino.setPeca(null);
        			    						casaDestino.setDama(new Dama(CorPeca.CLARO, jogador1, true));
        			    						System.out.println("Virou DAMA");
        			    					}
        			    				}else {
        			    					if(casaDestino.getPosX() == 7) {
        			    						casaDestino.setPeca(null);
        			    						casaDestino.setDama(new Dama(CorPeca.ESCURO, jogador2, true));
        			    						System.out.println("Virou DAMA");
        			    					}
        			    				}
        			    			}
        			    			//Atualiza quem e´ o proximo jogador para fazer a jogada.
        			    			if(atualJogador == jogador1) {
        			    				atualJogador = jogador2;
        			    			}else {
        			    				atualJogador = jogador1;
        			    			}
        			    			contadorJogadas = 0;
        			    			return true;
        			    		}
    	            		}else {
    	            			//MOVIMENTO INVALIDO - TENTANDO SE MOVER PARA UM LUGAR OCUPADO
    	            			return false;
    	            		}
    	    			
    	    				
    	            	}else {
    	            		//MOVIMENTO IMPOSSIVEL - FORA DO TABULEIRO
    	            		return false;
    	            	}
    	    			
    	    			}
   					}
    			}
    				
    		}else {
    			//MOVIMENTO IMPOSSIVEL - A PEDRA SERIA DE OUTRO JOGADOR
    			return false;
    		}
    	}else {
    		//MOVIMENTO IMPOSSIVEL - SEM PECA PARA MOVIMENTAR
    		return false;
    	}
    	return false;
    	
    	//significa que o jogo acabou
    	//}else {
    	//	return false;
    	//}
    }
    
    //Grupo de metodos para verificar se existe oportunidade de captura em certa direcao.
    public Casa verificarCapturaSuperiorEsq(int posX, int posY) {
    	//impede de verificar captura fora do tabuleiro
    	if(posX > 1 && posY > 1) {
    	//Se esse if for verdadeiro, a pedra nao é dama
    	if(this.tabuleiro.getCasaGrid(posX, posY).getDama() == null) {
        	if(tabuleiro.getCasaGrid(posX-1, posY-1).getOcupada() == true && tabuleiro.getCasaGrid(posX-1, posY-1).getPeca().getJogador() != atualJogador) {
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
    		for(int i = posX; i > 1; i--) {
    			j--;
    			if(j > 1) {
        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true && tabuleiro.getCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
        				if(tabuleiro.getCasaGrid(i-1, j-1).getOcupada() == false) {
        					Casa novaCasa = null;
        					novaCasa = tabuleiro.getCasaGrid(i-1, j-1);
        					return novaCasa;
        				}
        			}
        		}
        	}
    	}
    }else {
    	return null;
    }
    	return null;
    }
    
    public Casa verificarCapturaSuperiorDir(int posX, int posY) {
    	//impede de verificar captura fora do tabuleiro
    	if(posX > 1 && posY < 6) {
    	//Se esse if for verdadeiro, a pedra nao e' dama
    	if(this.tabuleiro.getCasaGrid(posX, posY).getDama() == null) {
    		if(tabuleiro.getCasaGrid(posX-1, posY+1).getOcupada() == true && tabuleiro.getCasaGrid(posX-1, posY+1).getPeca().getJogador() != atualJogador) {
    			if(tabuleiro.getCasaGrid(posX-2, posY+2).getOcupada() == false) {
    				Casa novaCasa = null;
    				novaCasa = tabuleiro.getCasaGrid(posX-2, posY+2);
    				return novaCasa;
    			}
    		}
    	}else {
    		int j = posY;
    		for(int i = posX; i > 1; i--) {
        		if(j < 6) {
        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true && tabuleiro.getCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
        				if(tabuleiro.getCasaGrid(i-1, j+1).getOcupada() == false) {
        					Casa novaCasa = null;
        					novaCasa = tabuleiro.getCasaGrid(i-1, j+1);
        					return novaCasa;
        				}
        			}
        		}
        	}
    	}
    	}else {
    		return null;
    	}
    	return null;
    }
    
    public Casa verificarCapturaInferiorEsq(int posX, int posY) {
    	//impede de verificar captura fora do tabuleiro
    	if(posX < 6 && posY > 1) {
    	//Se esse if for verdadeiro, a pedra nao e' dama
    	if(this.tabuleiro.getCasaGrid(posX, posY).getDama() == null) {
    		if(tabuleiro.getCasaGrid(posX+1, posY-1).getOcupada() == true&& tabuleiro.getCasaGrid(posX+1, posY-1).getPeca().getJogador() != atualJogador) {
    			if(tabuleiro.getCasaGrid(posX+2, posY-2).getOcupada() == false) {
    				Casa novaCasa = null;
    				novaCasa = tabuleiro.getCasaGrid(posX+2, posY-2);
    				return novaCasa;
    			}
    		}
    	}else {
    		int j = posY;
    		for(int i = posX; i < 6; i++) {
        		if(j > 1) {
        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true && tabuleiro.getCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
        				if(tabuleiro.getCasaGrid(i+1, j-1).getOcupada() == false) {
        					Casa novaCasa = null;
        					novaCasa = tabuleiro.getCasaGrid(i+1, j-1);
        					return novaCasa;
        				}
        			}
        		}
        	}
    	}
    	}else {
    		return null;
    	}
    	return null;
    }
    
    public Casa verificarCapturaInferiorDir(int posX, int posY) {
    	//impede de verificar captura fora do tabuleiro
    	if(posX < 6 && posY < 6) {
    	//Se esse if for verdadeiro, a pedra nao e' dama
    	if(this.tabuleiro.getCasaGrid(posX, posY).getDama() == null) {
    		if(tabuleiro.getCasaGrid(posX+1, posY+1).getOcupada() == true&& tabuleiro.getCasaGrid(posX+1, posY+1).getPeca().getJogador() != atualJogador) {
    			if(tabuleiro.getCasaGrid(posX+2, posY+2).getOcupada() == false) {
    				Casa novaCasa = null;
    				novaCasa = tabuleiro.getCasaGrid(posX+2, posY+2);
    				return novaCasa;
    			}
    		}
    	}else {
    		int j = posY;
    		for(int i = posX; i < 6; i++) {
        		if(j < 6) {
        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true && tabuleiro.getCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
        				if(tabuleiro.getCasaGrid(i+1, j+1).getOcupada() == false) {
        					Casa novaCasa = null;
        					novaCasa = tabuleiro.getCasaGrid(i+1, j+1);
        					return novaCasa;
        				}
        			}
        		}
    		}
    	}
    	}else {
    		return null;
    	}
    	return null;
    }
    
    //Captura a peca.
    public void capturar(Casa casaAtual, Casa casaOponente, Casa novaCasa){
        novaCasa.setPeca(casaAtual.getPeca());
        novaCasa.setOcupada(true);
        casaAtual.setPeca(null);
        casaAtual.setOcupada(false);
        casaOponente.setPeca(null);
        casaOponente.setOcupada(false);
    }
    
    //Retorna true se a peca e´ Dama.
    public boolean verificarDama(Casa casaAtual){
    	if(casaAtual.getDama() != null) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public Tabuleiro getTabuleiro() {
    	return this.tabuleiro;
    }
    
    public Jogador getJogador1() {
    	return jogador1;
    }
    
    public Jogador getJogador2() {
    	return jogador2;
    }
    
    public Jogador getAtualJogador() {
    	return atualJogador;
    }
    
    public Jogador getVencedor() {
    	return vencedor;
    }
    
    public Resultado getResultado() {
    	return resultado;
    }
}