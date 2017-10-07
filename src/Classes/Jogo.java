package Classes;
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
    }
    
    public void jogar(Casa casaOrigem, Casa casaDestino){
        //Verificar se a jogada seria valida, pode capturar, se seria fimd e jogo, desocupar casa.
    	
    	int posX = casaOrigem.getPosX();
    	int posY = casaOrigem.getPosY();
    	int lugarParaX = casaDestino.getPosX();
    	int lugarParaY = casaDestino.getPosY();
    	
    	/* Se existir pedra na casa atual*/
    	if(casaOrigem.getOcupada() == true) {
    		/*e se a pedra for do jogador que deve jogar agora*/
    		if(casaOrigem.getPeca().getJogador() == atualJogador){
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
            			}else {
            				//MOVIMENTO IMPOSSIVEL - MOVIMENTO INVALIDO
            			}
            		}else {
            			//CAPTURAR A PECA
            			
            			
            			
            			
            			
            			capturar(casaOrigem, casaDestino);
            		}
    				
            	}else {
            		//MOVIMENTO IMPOSSIVEL - FORA DO TABULEIRO
            	}
    		}else {
    			//MOVIMENTO IMPOSSIVEL - A PEDRA SERIA DE OUTRO JOGADOR
    		}
    	}else {
    		//MOVIMENTO IMPOSSIVEL - SEM PECA PARA MOVIMENTAR
    	}
    }
    
    public void capturar(Casa casaAtual, Casa casaOponente ){
        
    }
    
    public void iniciarPartida(){
    	tabuleiro.gerarTabuleiro(jogador1, jogador2);
    	atualJogador = jogador1;
    }
}