package Classes;
import Enums.CorCasa;
import Enums.CorPeca;
import Enums.Resultado;
import java.util.Date;

public class Jogo {
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador vencedor;
    private Tabuleiro tabuleiro;
    private Resultado resulado;
    private Date tempo;
    private int contadorJogadas;
    
    private Jogador atualJogador;
    
    public Jogo(Jogador jogador1, Jogador jogador2, Jogador vencedor, Tabuleiro tabuleiro,
                Resultado resultado, Date tempo, int contadorJogadas, Jogador atualJogador){
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.tabuleiro = tabuleiro;
        this.vencedor = vencedor;
        this.resulado = resultado;
        this.tempo = tempo;
        this.contadorJogadas = contadorJogadas;
        this.atualJogador = atualJogador;
    }
    
    public void isFimDeJogo(){
        //Checar fim de jogo
    	boolean jogoAcabou = false;
    	for(int i = 0; i < 7; i++) {
    		for(int j = 0; j < 7 ; j++) {
    			if(tabuleiro.GetCasaGrid(i, j).getPeca() != null) {
    				if(tabuleiro.GetCasaGrid(i, j).getPeca().getJogador() == jogador1) {
    					jogoAcabou = false;
    				}else {
    					jogoAcabou = true;
    					vencedor = jogador2;
    				}
    			}else if(tabuleiro.GetCasaGrid(i, j).getDama() != null){
    				if(tabuleiro.GetCasaGrid(i, j).getDama().getJogador() == jogador1) {
    					jogoAcabou = false;
    				}else {
    					jogoAcabou = true;
    					vencedor = jogador2;
    				}
    			}
    			
    			if(tabuleiro.GetCasaGrid(i, j).getPeca() != null) {
    				if(tabuleiro.GetCasaGrid(i, j).getPeca().getJogador() == jogador2) {
    					jogoAcabou = false;
    				}else {
    					jogoAcabou = true;
    					vencedor = jogador1;
    				}
    			}else if(tabuleiro.GetCasaGrid(i, j).getDama() != null){
    				if(tabuleiro.GetCasaGrid(i, j).getDama().getJogador() == jogador2) {
    					jogoAcabou = false;
    				}else {
    					jogoAcabou = true;
    					vencedor = jogador1;
    				}
    			}
    		}
    	}
    	
    	if(jogoAcabou) {
    		//JOGO ACABOOOOOOOOOOOU
    	}else {
    		if(contadorJogadas == 20) {
    			jogoAcabou = true;
    		}
    	}
    	
    }
    
    public void jogar(Casa casaOrigem, Casa casaDestino){
        //Verificar se a jogada seria valida, pode capturar, se seria fimd e jogo, desocupar casa.
    	//Casa em que se pode ir depois do movimento ou captura.
    	Casa casaPossivel = null;
    	//Casa para ser capturada.
    	Casa casaOponente = null;
    	int posX = casaOrigem.getPosX();
    	int posY = casaOrigem.getPosY();
    	int lugarParaX = casaDestino.getPosX();
    	int lugarParaY = casaDestino.getPosY();
    	
    	/* Se existir pedra na casa atual*/
    	if(casaOrigem.getOcupada() == true) {
    		/*e se a pedra for do jogador que deve jogar agora*/
    		if(casaOrigem.getPeca().getJogador() == atualJogador){
    			
    			if(verificarDama(casaOrigem)) {
    				//MOVIMENTO E CAPTURA DA DAMA
    				casaPossivel = verificarCapturaDama(posX, posY);
    				if(casaPossivel != null) {
    					
    					//Achar um modo para verificar em qual casa estaria o oponente para a dama capturar, e esta seria a casaOponente
    					
        				capturar(casaOrigem, casaOponente, casaPossivel);
        				contadorJogadas = 0;
        			}else {
    				if(lugarParaX>=0 && lugarParaX <=7 && lugarParaY>=0 && lugarParaY<=7){
    					
    					
    					
    					
    					
        				/*Se a peca destino esta vazia*/
                		if(casaDestino.getOcupada() == false) {
                			//APENAS MOVER A PECA
                			int direcaoPeca;
                			//dependendo da cor da peca, a variavel direcaoPeca vai mudar de valor.
                			if(casaOrigem.getPeca().getCor()== CorPeca.CLARO){
                				direcaoPeca = -1;
                			}else {
                				direcaoPeca = 1;
                			}
                			//se a pedra pode ser alcancada
                			if(lugarParaX - posX == direcaoPeca && (lugarParaY - posY ==1 || lugarParaY - posY == -1)){
                			tabuleiro.executarMovimento(casaOrigem, casaDestino);
                			contadorJogadas++;
                			}else {
                				//MOVIMENTO IMPOSSIVEL - MOVIMENTO INVALIDO
                			}
                		}else {
                			//MOVIMENTO INVALIDO - MOVIMENTO NA MESMA CASA DE UMA PECA INIMIGA
                		}
        				
                		
                		
                		
                		
                		
                	}else {
                		//MOVIMENTO IMPOSSIVEL - FORA DO TABULEIRO
                	}
        		}
    			}else {
    			//Se possui alguma peca em volta
    				
    			//A casaPossivel vai receber a casa onde o jogador pode ir, e que se possui um oponente em potencial
    			casaPossivel = verificarCaptura(posX, posY);
    			if(casaPossivel!= null) {
    				//Encontra a casa a ser capturada
    				casaOponente = tabuleiro.GetCasaGrid((casaOrigem.getPosX() + casaPossivel.getPosX())/2, (casaOrigem.getPosY() + casaPossivel.getPosY())/2);
    				
    				capturar(casaOrigem, casaOponente, casaPossivel);
    				contadorJogadas = 0;
    			}else {
    				// Se nao, executa o movimento normalmente.
    				
    			/*se o movimento nao sai do tabuleiro*/
    			if(lugarParaX>=0 && lugarParaX <=7 && lugarParaY>=0 && lugarParaY<=7){
    				/*Se a peca destino esta vazia*/
            		if(casaDestino.getOcupada() == false) {
            			//APENAS MOVER A PECA
            			int direcaoPeca;
            			//dependendo da cor da peca, a variavel direcaoPeca vai mudar de valor.
            			if(casaOrigem.getPeca().getCor()== CorPeca.CLARO){
            				direcaoPeca = -1;
            			}else {
            				direcaoPeca = 1;
            			}
            			//se a pedra pode ser alcancada
            			if(lugarParaX - posX == direcaoPeca && (lugarParaY - posY ==1 || lugarParaY - posY == -1)){
            			tabuleiro.executarMovimento(casaOrigem, casaDestino);
            			contadorJogadas = 0;
            			}else {
            				//MOVIMENTO IMPOSSIVEL - MOVIMENTO INVALIDO
            			}
            		}else {
            			//WOW
            		}
    				
            	}else {
            		//MOVIMENTO IMPOSSIVEL - FORA DO TABULEIRO
            	}
    		}
    			
    		}
    		}else {
    			//MOVIMENTO IMPOSSIVEL - A PEDRA SERIA DE OUTRO JOGADOR
    		}
    	}else {
    		//MOVIMENTO IMPOSSIVEL - SEM PECA PARA MOVIMENTAR
    	}
    }
    
    public Casa verificarCaptura(int posX, int posY) {
    	Casa novaCasa = null;
    	if(tabuleiro.GetCasaGrid(posX-1, posY-1).getOcupada() == true && tabuleiro.GetCasaGrid(posX-1, posY-1).getPeca().getJogador() != atualJogador) {
			if(tabuleiro.GetCasaGrid(posX-2, posY-2).getOcupada() == false) {
				//Casa novaCasa = new Casa(null, false, null, 0, 0);
				novaCasa = tabuleiro.GetCasaGrid(posX-2, posY-2);
				//return novaCasa;
			}
		}else if(tabuleiro.GetCasaGrid(posX-1, posY+1).getOcupada() == true&& tabuleiro.GetCasaGrid(posX-1, posY+1).getPeca().getJogador() != atualJogador) {
			if(tabuleiro.GetCasaGrid(posX-2, posY+2).getOcupada() == false) {
				//Casa novaCasa = new Casa(null, false, null, 0, 0);
				novaCasa = tabuleiro.GetCasaGrid(posX-2, posY-2);
				//return novaCasa;
			}
		}else if(tabuleiro.GetCasaGrid(posX+1, posY-1).getOcupada() == true&& tabuleiro.GetCasaGrid(posX+1, posY-1).getPeca().getJogador() != atualJogador) {
			if(tabuleiro.GetCasaGrid(posX+2, posY-2).getOcupada() == false) {
				//Casa novaCasa = new Casa(null, false, null, 0, 0);
				novaCasa = tabuleiro.GetCasaGrid(posX-2, posY-2);
				//return novaCasa;
			}
		}else if(tabuleiro.GetCasaGrid(posX+1, posY+1).getOcupada() == true&& tabuleiro.GetCasaGrid(posX+1, posY+1).getPeca().getJogador() != atualJogador) {
			if(tabuleiro.GetCasaGrid(posX+2, posY+2).getOcupada() == false) {
				//Casa novaCasa = new Casa(null, false, null, 0, 0);
				novaCasa = tabuleiro.GetCasaGrid(posX-2, posY-2);
				//return novaCasa;
			}
		}else {
			novaCasa = null;
		}
    	
    	return novaCasa;
    }
    
    public Casa verificarCapturaDama(int posX, int posY) {
    	Casa novaCasa = null;
    	//Em direcao para casa (0,0)
    	
    	for(int i = posX; i <= 0; i--) {
    		for(int j = posY; j <= 0; j--) {
    			if(tabuleiro.GetCasaGrid(i, j).getOcupada() == true && tabuleiro.GetCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
    				if(tabuleiro.GetCasaGrid(posX-1, posY-1).getOcupada() == false) {
    					//Casa novaCasa = new Casa(null, false, null, 0, 0);
    					novaCasa = tabuleiro.GetCasaGrid(posX-1, posY-1);
    					//return novaCasa;
    					break;
    				}
    			}
    		}
    	}
    	
    	for(int i = posX; i <= 0; i--) {
    		for(int j = posY; j <= 7; j++) {
    			if(tabuleiro.GetCasaGrid(i, j).getOcupada() == true && tabuleiro.GetCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
    				if(tabuleiro.GetCasaGrid(posX-1, posY+1).getOcupada() == false) {
    					//Casa novaCasa = new Casa(null, false, null, 0, 0);
    					novaCasa = tabuleiro.GetCasaGrid(posX-1, posY+1);
    					//return novaCasa;
    					break;
    				}
    			}
    		}
    	}
    	
    	for(int i = posX; i <= 7; i++) {
    		for(int j = posY; j <= 0; j--) {
    			if(tabuleiro.GetCasaGrid(i, j).getOcupada() == true && tabuleiro.GetCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
    				if(tabuleiro.GetCasaGrid(posX+1, posY-1).getOcupada() == false) {
    					//Casa novaCasa = new Casa(null, false, null, 0, 0);
    					novaCasa = tabuleiro.GetCasaGrid(posX+1, posY-1);
    					//return novaCasa;
    					break;
    				}
    			}
    		}
    	}
    	
    	for(int i = posX; i <= 7; i++) {
    		for(int j = posY; j <= 7; j++) {
    			if(tabuleiro.GetCasaGrid(i, j).getOcupada() == true && tabuleiro.GetCasaGrid(j, j).getPeca().getJogador() != atualJogador) {
    				if(tabuleiro.GetCasaGrid(posX+1, posY+1).getOcupada() == false) {
    					//Casa novaCasa = new Casa(null, false, null, 0, 0);
    					novaCasa = tabuleiro.GetCasaGrid(posX+1, posY+1);
    					//return novaCasa;
    					break;
    				}
    			}
    		}
    	}
    	return null;
    }
    
    public void capturar(Casa casaAtual, Casa casaOponente, Casa novaCasa){
        novaCasa.setPeca(casaAtual.getPeca());
        novaCasa.setOcupada(true);
        casaAtual.setPeca(null);
        casaAtual.setOcupada(false);
        casaOponente.setPeca(null);
        casaOponente.setOcupada(false);
    }
    
    public void iniciarPartida(){
    	tabuleiro.gerarTabuleiro(jogador1, jogador2);
    	atualJogador = jogador1;
    }
    
    public boolean verificarDama(Casa casaAtual)
    {
    	boolean resultado = false;
    	if(casaAtual.getDama() != null) {
    		resultado =  true;
    	}else {
         if(casaAtual.getCor() == CorCasa.BRANCO) {
        	 if(casaAtual.getPosX() == 0) {
        		 casaAtual.setPeca(null);
        		 casaAtual.setDama(new Dama(CorPeca.CLARO, jogador1, true));
        		 resultado = true;
        	 }
         }else {
        	 if(casaAtual.getPosX() == 7) {
        		 casaAtual.setPeca(null);
        		 casaAtual.setDama(new Dama(CorPeca.ESCURO, jogador2, true));
        		 resultado = true;
        	 }
         }
    	}
         return resultado;
    }
    
    public Tabuleiro getTabuleiro() {
    	return this.tabuleiro;
    }
}