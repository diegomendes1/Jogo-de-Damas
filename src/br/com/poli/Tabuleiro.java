package br.com.poli;
import enums.CorCasa;
import enums.CorPeca;

/*Classe responsavel por gerenciar o tabuleiro. Aqui se cria o tabuleiro, adiciona peca nas casas,
 * e movimenta alguma peca.*/
public class Tabuleiro {
    private Casa[][] grid;
    public Tabuleiro(){
        this.grid = new Casa[8][8];
    }
    
    public void executarMovimento(Casa casaOrigem, Casa casaDestino){
			casaDestino.setPeca(casaOrigem.getPeca());
			casaOrigem.setOcupada(false);
			casaOrigem.setPeca(null);
			casaDestino.setOcupada(true);
    }
    
    /* Adiciona cada peca em cada casa corretamente.*/
    public void gerarTabuleiro(Jogador jogador1, Jogador jogador2){
    	//dependendo do valor de 'atualBranca', a cor da casa a ser editada vai mudar.
    	boolean atualBranca = true;
    	for(int linha = 0; linha < grid.length; linha++) {
    		for(int coluna = 0;coluna < grid[linha].length; coluna++) {
    			//verificando o valor de 'linha', o metodo adiciona pecas claras, escuras ou deixa a casa sem peca.
    			//3 primeiras linhas do tabuleiro(pecas escuras)
    			if(linha <= 2) {
    				if(atualBranca == true) {
        				atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, true, false);
        			}else {
        				atualBranca = criarCasa(new Peca(CorPeca.ESCURO, jogador2), CorCasa.PRETO, linha, coluna, false, true);
        				//grid[linha][coluna].getPeca().setDama(true);
        			}
    				//as duas linhas do meio(sem peca)
    			}else if(linha <= 4) {
    				if(atualBranca == true) {
    					atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, true, false);
        			}else {
        				atualBranca = criarCasa(null, CorCasa.PRETO, linha, coluna, false, false);
        			}
    				//3 ultimas linhas do tabuleiro(pecas claras)
    			}else {
    				if(atualBranca == true) {
    					atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, true, false);
        			}else {
        				atualBranca = criarCasa(new Peca(CorPeca.CLARO, jogador1), CorCasa.PRETO, linha, coluna, false, true);
        				//grid[linha][coluna].getPeca().setDama(true);
        			}
    			}
    		}
    		/*Essa proxima condicao vai ajustar o inicio da proxima linha do tabuleiro. 
    		 * Sem isto, cada coluna ficaria com apenas uma cor de casa.*/
    		if(atualBranca == true) {
    			atualBranca = false;
    		}else {
    			atualBranca = true;
    		}
    	}
    }
    
    //Adiciona uma nova casa num certo lugar do tabuleiro(grid).
    public boolean criarCasa(Peca peca, CorCasa corCasa, int linha, int coluna, boolean atualBranca, boolean ocupada){
		grid[linha][coluna] = new Casa(corCasa, ocupada, peca, linha, coluna);
		grid[linha][coluna].setPosX(linha);
		grid[linha][coluna].setPosY(coluna);
		if(atualBranca == true) {
			atualBranca = false;
		}else {
			atualBranca = true;
		}
		return atualBranca;
    }
    
    //mostra o tabuleiro na tela.
    public void mostrarTabuleiro() {
    	//Este FOR pula algumas linhas para melhor visualizacao do tabuleiro
    	for(int i = 0; i < 10; i++) {
    		System.out.println();
    	}
    	
    	for(int i = 0; i < grid.length; i++) {
    		for(int j = 0; j <grid[i].length; j++) {
    			if(grid[i][j].getPeca() == null) {
    				System.out.print("      ");
    			}else {
    				//Este if apenas deixa o tabuleiro organizado, pode apenas deixar um dos print no codigo.
    				if(grid[i][j].getPeca().getCor() == CorPeca.ESCURO) {
    					System.out.print(grid[i][j].getPeca().getCor());
    					
    				}else {
    					System.out.print(" "+grid[i][j].getPeca().getCor());
    					
    				}
    			}
    		}
    		System.out.println();
    	}
    }
    
    public Casa getCasaGrid(int i, int j) {
    	if(grid[i][j] != null) {
    	return grid[i][j];
    	}
    	return null;
    }
    
    public Casa[][] getGrid(){
    	return grid;
    }
}