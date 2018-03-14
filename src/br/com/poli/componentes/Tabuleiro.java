package br.com.poli.componentes;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.poli.Interface;
import br.com.poli.Jogador;
import br.com.poli.damIA.AutoPlayer;
import br.com.poli.enums.CorCasa;
import br.com.poli.enums.CorPeca;

/*Classe responsavel por gerenciar o tabuleiro. Aqui se cria o tabuleiro, adiciona peca nas casas,
 * e movimenta alguma peca.*/
public class Tabuleiro implements Cloneable{
    private Casa[][] grid;
    
    private int score;
    private int[] movimento;
    private List<Tabuleiro> possibilidades;
    public Tabuleiro(){
        this.grid = new Casa[8][8];
        score = 0;
    }
    
    public void executarMovimento(int origemX, int origemY, int destinoX, int
    		destinoY){
    		Casa casaOrigem = grid[origemX][origemY];
    		Casa casaDestino = grid[destinoX][destinoY];
			casaDestino.setPeca(casaOrigem.getPeca());
			casaOrigem.setOcupada(false);
			casaOrigem.setPeca(null);
			casaDestino.setOcupada(true);
			possibilidades = new ArrayList<Tabuleiro>();
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
        				atualBranca = criarCasa(new Dama(CorPeca.ESCURO, jogador2, true), CorCasa.PRETO, linha, coluna, false, true);
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
        				atualBranca = criarCasa(new Dama(CorPeca.CLARO, jogador1, true), CorCasa.PRETO, linha, coluna, false, true);
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
 
    public int getScore() {
    	return this.score;
    }
    
    public void setScore(int score) {
    	this.score = score;
    }
    
    public void setScore(Interface jogo) {
    	int totalScore = 0;
    	
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			if(this.grid[i][j].getOcupada()) {
    				
    				if(this.grid[i][j].getPeca().getJogador() == jogo.getAtualJogador()) {
    					totalScore+= i/4;
    					if(j == 0 || j == 7) {
    						totalScore+= 2;
    					}
    					
    					if(this.grid[i][j].getPeca().getIsDama()) {
    						totalScore += 2;
    					}else {
    						totalScore += 5;
    					}
    				}else {
    					
    					totalScore-= (7-i)/4;
    					if(j == 0 || j == 7) {
    						totalScore-= 2;
    					}
    					
    					if(this.grid[i][j].getPeca().getIsDama()) {
    						totalScore -= 2;
    					}else {
    						totalScore -= 5;
    					}
    				}
    			}
    		}
    	}
    	this.score = totalScore;
    }
    
    public void setMovimento(int[] movimento) {
    	this.movimento = movimento;
    }
    
    public int[] getMovimento() {
    	return this.movimento;
    }
    
    public List<Tabuleiro> getListaPossiveis(){
    	return this.possibilidades;
    }
    
    public void limparPossibilidades() {
    	this.possibilidades.clear();
    }
    
    public void adicionarPossiblidade(Tabuleiro possibilidade) {
    	possibilidades.add(possibilidade);
    }
    
    public int getNumeroPossiveis() {
    	return this.possibilidades.size();
    }
    
    public Tabuleiro getPossibilidade(int id) {
    	return this.possibilidades.get(id);
    }
    
    public Casa getCasaGrid(int i, int j) {
    	if(this.grid[i][j] != null) {
    	return this.grid[i][j];
    	}
    	return null;
    }
    
    public Casa[][] getGrid(){
    	return this.grid;
    }
    
    public void gerarTabuleiroCaptura(AutoPlayer ia, int origemX, int origemY, int destinoX, int destinoY) {
    	Casa casaAtual = getCasaGrid(origemX, origemY);
    	Casa novaCasa = getCasaGrid(destinoX, destinoY);
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
        getCasaGrid(x, y).setPeca(null);
        getCasaGrid(x, y).setOcupada(false);
        
        int[] capMultipla = ia.verificarCapturaCasa(destinoX, destinoY, this);
        if(capMultipla != null) {
        	this.gerarTabuleiroCaptura(ia, destinoX, destinoY, capMultipla[0], capMultipla[1]);
        }
    }
    
    public void gerarTabuleiroMovimento(int origemX, int origemY, int destinoX, int destinoY){
    		Casa casaOrigem = getCasaGrid(origemX, origemY);
    		Casa casaDestino = getCasaGrid(destinoX, destinoY);
			casaDestino.setPeca(casaOrigem.getPeca());
			casaOrigem.setOcupada(false);
			casaOrigem.setPeca(null);
			casaDestino.setOcupada(true);
    }
    
    @Override
    public Tabuleiro clone() {
    	try {
    		Tabuleiro novoTab = (Tabuleiro)super.clone();
    		novoTab.possibilidades = new ArrayList<Tabuleiro>();
    		novoTab.grid = this.copiarGrid();
    		return novoTab;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
    }
    
    public Casa[][] copiarGrid(){
    	Casa[][] novoGrid = new Casa[8][8];
    	
    	for(int i = 0; i < 8; i++) {
    		for(int j = 0; j < 8; j++) {
    			novoGrid[i][j] = new Casa(this.grid[i][j]);
    			if(novoGrid[i][j].getOcupada()) {
    			novoGrid[i][j].setPeca(new Peca(this.grid[i][j].getPeca().getCor(), this.grid[i][j].getPeca().getJogador()));
    			}
    		}
    	}
    	return novoGrid;
    }
}