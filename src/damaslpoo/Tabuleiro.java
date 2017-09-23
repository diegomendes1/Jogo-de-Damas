package damaslpoo;
//import java.util.Random;

public class Tabuleiro {
    private Casa[][] grid;
    
    
    //É necessário criar as casas aqui?
    public Tabuleiro(){
        this.grid = new Casa[8][8];
    }
    
    //FALTA TERMINAR ALGUMAS COISAS
    public void executarMovimento(int posX, int posY,int lugarParaX,int lugarParaY){
    	
    	
    	/*Este If-else verifica se a peca deve andar de baixo pra cima(nesse caso, ladoPeca = -1) 
    	  ou de cima pra baixo(ladoPeca = +1).
    	int ladoPeca;
    	if(grid[posX][posY].getPeca().getCor() == CorPeca.CLARO) {
    		ladoPeca = -1;
    	}else {
    		ladoPeca = +1;
    	}
    	
    	//FALTA TERMINAR
    	if(posY == 0){
    		grid[posX+ladoPeca][posY+ladoPeca].setPeca(grid[posX][posY].getPeca());
    	}else if(posY == 7){
    		grid[posX+ladoPeca][posY-ladoPeca].setPeca(grid[posX][posY].getPeca());
    	}else{
    		if(new Random().nextInt(10) > 5){
    			grid[posX+ladoPeca][posY+ladoPeca].setPeca(grid[posX][posY].getPeca());
    		}else{
    			grid[posX+ladoPeca][posY-ladoPeca].setPeca(grid[posX][posY].getPeca());
    		}
    	}*/
    	
    	if(lugarParaX>=0 && lugarParaX <=7 && lugarParaY>=0 && lugarParaY<=7 && !grid[lugarParaX][lugarParaY].getOcupada())
    	{
    		if(grid[posX][posY].getPeca().getCor()== CorPeca.CLARO)
    		{
    			if(lugarParaX - posX == -1 && (lugarParaX - posX ==1 || lugarParaX - posX == -1))
    			{
    				grid[lugarParaX][lugarParaY].setPeca(grid[posX][posY].getPeca());
    				grid[posX][posY].setOcupada(false);
    				grid[posX][posY].setPeca(null);
    				grid[lugarParaX][lugarParaY].setOcupada(true);
    			}
    		}else
    		{
    			if(lugarParaX - posX == 1 && (lugarParaX - posX ==1 || lugarParaX - posX == -1))
    			{
    				grid[lugarParaX][lugarParaY].setPeca(grid[posX][posY].getPeca());
    				grid[posX][posY].setOcupada(false);
    				grid[posX][posY].setPeca(null);
    				grid[lugarParaX][lugarParaY].setOcupada(true);
    			}
    		}
    	}
    }
    
    /* "gerarTabuleiro" anda por cada casa da variavel GRID, e configura para a cor da casa correta.*/
    public void gerarTabuleiro(Jogador[] jogador){
    	
    	boolean atualBranca = true;
    	
    	for(int linha = 0; linha < grid.length; linha++) {
    		for(int coluna = 0;coluna < grid[linha].length; coluna++) {
    			if(linha <= 2) {
    				if(atualBranca == true) {
        				atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, jogador[0], true);
        			}else {
        				atualBranca = criarCasa(new Peca(CorPeca.ESCURO, jogador[0]), CorCasa.PRETO, linha, coluna, jogador[0], false);
        			}
    			}else if(linha <= 4) {
    				if(atualBranca == true) {
    					atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, jogador[0], true);
        			}else {
        				atualBranca = criarCasa(null, CorCasa.PRETO, linha, coluna, jogador[0], false);
        			}
    			}else {
    				if(atualBranca == true) {
    					atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, jogador[0], true);
        			}else {
        				atualBranca = criarCasa(new Peca(CorPeca.CLARO, jogador[1]), CorCasa.PRETO, linha, coluna, jogador[0], false);
        			}
    			}
    		}
    		System.out.println("");
    		if(atualBranca == true) {
    			atualBranca = false;
    		}else {
    			atualBranca = true;
    		}
    	}
    }
    
    public boolean criarCasa(Peca peca, CorCasa corCasa, int linha, int coluna, Jogador jogador, boolean atualBranca){
		grid[linha][coluna] = new Casa(corCasa, true, peca);
		
		if(grid[linha][coluna].getPeca() == null) {
			System.out.print("  noPECA");
		}else {
			System.out.print("  "+grid[linha][coluna].getPeca().getCor());
		}
		if(atualBranca == true) {
			atualBranca = false;
		}else {
			atualBranca = true;
		}
		return atualBranca;
    }
}