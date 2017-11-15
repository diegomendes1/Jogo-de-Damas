package br.com.poli;

import br.com.poli.componentes.Casa;
import br.com.poli.componentes.Tabuleiro;
import br.com.poli.enums.CorCasa;
import br.com.poli.enums.CorPeca;
import br.com.poli.enums.Resultado;

public class Jogo implements Interface{
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vencedor;
    private Tabuleiro tabuleiro;
    private Resultado resultado;
    private int contadorJogadas;
    private Casa casaCapturaMultipla;
    private Jogador atualJogador;
    private int pecasCapturadasJogador1;
    private int pecasCapturadasJogador2;
    private boolean isUmJogador;
    
    public Jogo(Jogador jogador1, Jogador jogador2, Jogador vencedor, Tabuleiro tabuleiro,
                Resultado resultado, boolean isUmJogador){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
        this.vencedor = vencedor;
        this.resultado = resultado;
        this.contadorJogadas = 0;
        atualJogador = jogador1;
        pecasCapturadasJogador1 = 0;
        pecasCapturadasJogador2 = 0;
        this.casaCapturaMultipla = null;
        this.isUmJogador = isUmJogador;
        iniciarPartida();
    }
    
    //O primeiro metodo a ser chamado, ele configura os jogadores, define o primeiro a jogar e cria o tabuleiro.
    public void iniciarPartida(){
    	tabuleiro.gerarTabuleiro(jogador1, jogador2);
    	atualJogador = jogador1;
    }
    
  /*Metodo a ser chamado antes de cada jogada, ele verifica as condicoes para fim de jogo, 
   * retornando um boolean representando se o jogo acabou ou nao.*/
    public boolean isFimDeJogo(boolean desistiu){
    	vencedor = null;
    	
    	//Verifica a desistência do jogador
    	if(desistiu) {
    		if(atualJogador == jogador1) {
    			vencedor = jogador2;
    			resultado = Resultado.comVencedorJogador2;
    			return true;
    		}else {
    			vencedor = jogador1;
    			resultado = Resultado.comVencedorJogador1;
    			return true;
    		}
    	}
    	
    	//Verifica a regra de empate.
    	if(contadorJogadas >= 20) {
			resultado = Resultado.empate;
			return true;
		}
    	
    	boolean possuiPecasJogador1 = false;
    	boolean possuiPecasJogador2 = false;
    	//Verifica se ainda existem pecas do jogador. Se nao existir, declara o adversario como vencedor.
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			
    			if(tabuleiro.getCasaGrid(i, j).getPeca() != null) {
    				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogador1) {
    					possuiPecasJogador1 = true;
    				}
    			}
    			
    			if(tabuleiro.getCasaGrid(i, j).getPeca() != null) {
    				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == jogador2) {
    					possuiPecasJogador2 = true;
    				}
    			}
    		}
    	}
    	
    	if(possuiPecasJogador1 == true && possuiPecasJogador2 == true) {
    		return false;
    	}else if(possuiPecasJogador1 == false){
    		vencedor = jogador2;
    		resultado = Resultado.comVencedorJogador2;
    		return true;
    	}else if(possuiPecasJogador2 == false) {
    		vencedor = jogador1;
    		resultado = Resultado.comVencedorJogador1;
    		return true;
    	}
    	
    	return true;
    }
    
    /*Metodo principal, recebe duas casas e verifica o movimento ou captura de uma casa para outra.
    funciona independente das variaveis do sistema, como o jogadorAtual.*/
    public boolean jogar(Casa casaOrigem, Casa casaDestino) throws MovimentoInvalidoException,  CapturaInvalidaException{
    	//Casa em que se pode ir depois do movimento ou captura.
    	Casa casaPossivel = null;
    	//Casa para ser capturada.
    	Casa casaOponente = null;
    	
    	//Cria 4 ints que representam as coordenadas da casa, para facilitar os calculos
    	int posX = casaOrigem.getPosX();
    	int posY = casaOrigem.getPosY();
    	int lugarParaX = casaDestino.getPosX();
    	int lugarParaY = casaDestino.getPosY();
    	
    	if(casaDestino != null){
    	// Se existir pedra na casa atual
    	if(casaOrigem.getOcupada() == true) {
    		
    		//e se a pedra for do jogador que deve jogar agora
    		if(casaOrigem.getPeca().getJogador() == atualJogador){
    			
    			//Para verificar a captura, tanto a dama quanto a pedra comum sao verificadas da mesma maneira, 
    			//Mas nos metodos de verificacao fora de jogar(), cada tipo possui seu jeito de capturar.
    			
    			if(verificarCapturaTabuleiro()) {
        			casaOponente = verificarCapturaCasa(casaOrigem, casaDestino);
        				
        			//Se o jogador escolheu uma captura possivel
        			if(casaOponente != null) {
        				if(casaCapturaMultipla == null) {
        					capturar(casaOrigem.getPosX(), casaOrigem.getPosY(),/*casaOponente, */casaDestino.getPosX(), casaDestino.getPosY());
        					if(verificarPossibilidadeCapturaCasa(casaDestino)) {
        						casaCapturaMultipla = casaDestino;
        						return false;
        					}
        				}else {
        					if(casaOrigem == casaCapturaMultipla) {
        						capturar(casaOrigem.getPosX(), casaOrigem.getPosY(),/*casaOponente, */casaDestino.getPosX(), casaDestino.getPosY());
            					if(verificarPossibilidadeCapturaCasa(casaDestino)) {
            						casaCapturaMultipla = casaDestino;
            					}else {
            						casaCapturaMultipla = null;
            					}
        					}
        				}
        				
        				if(casaDestino.getPeca().getCor() == CorPeca.CLARO) {
        					if(casaDestino.getPosX() == 0) {
        						casaDestino.getPeca().setDama(true);
        					}
        				}else {
        					if(casaDestino.getPosX() == 7) {
        						casaDestino.getPeca().setDama(true);
        					}
        				}
        				if(!isUmJogador) {
        				if(atualJogador == jogador1) {
        					pecasCapturadasJogador2++;
    	    				atualJogador = jogador2;
    	    			}else {
    	    				pecasCapturadasJogador1++;
    	    				atualJogador = jogador1;
    	    			}
        				}
        				contadorJogadas = 0;
        				return true;
        			}else {
        				//Existe capturas mas o jogador não quis capturar
        				throw new CapturaInvalidaException("Ainda Existe Captura");
        				//return false;
        			}
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
    			    				if(i != posX && j != posY && tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
    			    					break;
    			    				}
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		//Verifica movimento da dama pro lado Superior Direito
    			    		j = posY-1;
    			    		for(int i = posX; i >= 0; i--) {
    			    			j++;
    			    			if(j <= 7) {
    			    				if(i != posX && j != posY && tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
    			    					break;
    			    				}
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		//Verifica movimento da dama pro lado Inferior Esquerdo
    			    		j = posY+1;
    			    		for(int i = posX; i <= 7; i++) {
    			    			j--;
    			    			if(j >= 0) {
    			    				if(i != posX && j != posY && tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
    			    					break;
    			    				}
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		//Verifica movimento da dama pro lado Inferior Direito
    			    		j = posY-1;
    			    		for(int i = posX; i <= 7; i++) {
    			    			j++;
    			    			if(j <= 7) {
    			    				if(i != posX && j != posY && tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
    			    					break;
    			    				}
    			        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == false && tabuleiro.getCasaGrid(i, j) == casaDestino) {
    			        					casaPossivel = tabuleiro.getCasaGrid(i, j);
    			        					break;
    			        				}
    			        			}
    			        		}
    			    		
    			    		
    			    		//Se casaPossivel for null, significa que nenhuma oportunidade de movimento pro jogador.
    			    		if(casaPossivel == null) {
    			    			return false;
    			    		}else {
    			    			tabuleiro.executarMovimento(casaOrigem.getPosX(), casaOrigem.getPosY(), casaDestino.getPosX(), casaDestino.getPosY());
    			    			if(!isUmJogador) {
    			    			if(atualJogador == jogador1) {
    			    				atualJogador = jogador2;
    			    			}else {
    			    				atualJogador = jogador1;
    			    			}
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
        			    			tabuleiro.executarMovimento(casaOrigem.getPosX(), casaOrigem.getPosY(), casaDestino.getPosX(), casaDestino.getPosY());
        			    			
        			    			if(casaDestino.getPeca().getCor() == CorPeca.CLARO) {
    			    					if(casaDestino.getPosX() == 0) {
    			    						casaDestino.getPeca().setDama(true);
    			    					}
    			    				}else {
    			    					if(casaDestino.getPosX() == 7) {
    			    						casaDestino.getPeca().setDama(true);
    			    					}
    			    				}
        			    			
        			    			//Verifica se a pedra e´ dama. se nao for, verifica se pode se tornar, e a torna uma dama.
        			    			
        			    			//Atualiza quem e´ o proximo jogador para fazer a jogada.
        			    			if(!isUmJogador) {
        			    			if(atualJogador == jogador1) {
        			    				atualJogador = jogador2;
        			    			}else {
        			    				atualJogador = jogador1;
        			    			}
        			    			}
        			    			contadorJogadas = 0;
        			    			return true;
        			    		}
    	            		}else {
    	                		throw new MovimentoInvalidoException("Casa Ocupada");
    	                	}
    	            	}else {
    	            		//MOVIMENTO IMPOSSIVEL - FORA DO TABULEIRO
    	            		return false;
    	            	}
   					}
    			}
    		}else {
    			throw new MovimentoInvalidoException("Pedra de Outro Jogador");
    		}
    	}else {
    		//MOVIMENTO IMPOSSIVEL - SEM PECA PARA MOVIMENTAR
    		return false;
    	}
    	
    }else {
    	//Codigo morto, mas a etapa tres pedia essa verificacao, entao decidimos mante-las.
    	throw new MovimentoInvalidoException("Casa destino invalida");
    }
    	return false;
    }
    
    public Casa verificarCapturaCasa(Casa casa, Casa casaDestino) {
    		int i = casa.getPosX();
    		int j = casa.getPosY();
    		Casa casaOponente = null;
    		
    		//Casa em que se pode ir depois do movimento ou captura.
    	    Casa capturaPossivel = null;
    			
    		if(tabuleiro.getCasaGrid(i, j).getCor() == CorCasa.PRETO && 
    			tabuleiro.getCasaGrid(i, j).getPeca() != null &&
    			tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == atualJogador) {
    				
    			//procura uma possivel captura na direcao Superior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
    			capturaPossivel = verificarCapturaSuperiorEsq(i, j);
    			if(capturaPossivel != null) {
    				if(capturaPossivel == casaDestino) {
    				casaOponente = tabuleiro.getCasaGrid(capturaPossivel.getPosX()+1, capturaPossivel.getPosY()+1);
    				}
    			}
    			//procura uma possivel captura na direcao Superior Direita. se existir um lugar, casaPossivel recebe esta casa.
    			capturaPossivel = verificarCapturaSuperiorDir(i, j);
    			if(capturaPossivel != null) {
    				if(capturaPossivel == casaDestino) {
    				casaOponente = tabuleiro.getCasaGrid(capturaPossivel.getPosX()+1, capturaPossivel.getPosY()-1);
    				}
    			}
    			//procura uma possivel captura na direcao Inferior Esquerda. se existir um lugar, casaPossivel recebe esta casa.
    			capturaPossivel = verificarCapturaInferiorEsq(i, j);
    			if(capturaPossivel != null) {
    				if(capturaPossivel == casaDestino) {
    				casaOponente = tabuleiro.getCasaGrid(capturaPossivel.getPosX()-1, capturaPossivel.getPosY()+1);
    				}
    			}
    			//procura uma possivel captura na direcao Inferior Direita. se existir um lugar, casaPossivel recebe esta casa.
    			capturaPossivel = verificarCapturaInferiorDir(i, j);
    			if(capturaPossivel != null) {
    				if(capturaPossivel == casaDestino) {
    				casaOponente = tabuleiro.getCasaGrid(capturaPossivel.getPosX()-1, capturaPossivel.getPosY()-1);
    				}
    			}
    		}
    	
    	return casaOponente;
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
    			tabuleiro.getCasaGrid(i, j).getPeca().getJogador() == atualJogador) {
    				
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
    		for(int i = posX-1; i >= 1; i--) {
    			j--;
    			if(j >= 1) {
    				if(tabuleiro.getCasaGrid(i, j) != tabuleiro.getCasaGrid(posX, posY)) {
        			if(tabuleiro.getCasaGrid(i, j).getOcupada() == true) {
        				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != atualJogador) {
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
    		if(tabuleiro.getCasaGrid(posX-1, posY+1).getOcupada() == true && tabuleiro.getCasaGrid(posX-1, posY+1).getPeca().getJogador() != atualJogador) {
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
        				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != atualJogador) {
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
    		if(tabuleiro.getCasaGrid(posX+1, posY-1).getOcupada() == true&& tabuleiro.getCasaGrid(posX+1, posY-1).getPeca().getJogador() != atualJogador) {
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
        				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != atualJogador) {
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
    		if(tabuleiro.getCasaGrid(posX+1, posY+1).getOcupada() == true&& tabuleiro.getCasaGrid(posX+1, posY+1).getPeca().getJogador() != atualJogador) {
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
        				if(tabuleiro.getCasaGrid(i, j).getPeca().getJogador() != atualJogador) {
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
    
    //Captura a peca.
    public void capturar(int origemX, int origemY, int destinoX, int destinoY){
    	Casa casaAtual = tabuleiro.getCasaGrid(origemX, origemY);
    	Casa novaCasa = tabuleiro.getCasaGrid(destinoX, destinoY);
        novaCasa.setPeca(casaAtual.getPeca());
        novaCasa.setOcupada(true);
        casaAtual.setPeca(null);
        casaAtual.setOcupada(false);
        int x, y;
        if((casaAtual.getPosX() - novaCasa.getPosX()) < 0) {
        	x = (novaCasa.getPosX())-1;
        }else {
        	x = (novaCasa.getPosX())+1;
        }
        
        if((casaAtual.getPosY() - novaCasa.getPosY()) < 0) {
        	y = (novaCasa.getPosY())-1;
        }else {
        	y = (novaCasa.getPosY())+1;
        }
        tabuleiro.getCasaGrid(x, y).setPeca(null);
        tabuleiro.getCasaGrid(x, y).setOcupada(false);
    }
    
    //Retorna true se a peca e´ Dama.
    public boolean verificarDama(Casa casaAtual){
    	if(casaAtual.getPeca().getIsDama()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public Casa getCasaCapturaMultipla() {
    	return this.casaCapturaMultipla;
    }
    
    public int getJogadas() {
    	return this.contadorJogadas;
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
    
    public void setAtualJogador(Jogador jogador) {
    	this.atualJogador = jogador;
    }
    
    public Jogador getVencedor() {
    	return vencedor;
    }
    
    public Resultado getResultado() {
    	return resultado;
    }
    
    public int getPecasCapturadas1() {
    	return this.pecasCapturadasJogador1;
    }
    
    public int getPecasCapturadas2() {
    	return this.pecasCapturadasJogador2;
    }
}