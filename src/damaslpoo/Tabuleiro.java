package damaslpoo;
import java.util.Scanner;
import Enums.CorCasa;
import Enums.CorPeca;

public class Tabuleiro {
    private Casa[][] grid;
    
    public Tabuleiro(){
        this.grid = new Casa[8][8];
    }
    
    public boolean VerificarCasa(int lugarParaX, int lugarParaY, int posX, int posY) {
    	boolean result;
    	if(lugarParaX>=0 && lugarParaX <=7 && lugarParaY>=0 && lugarParaY<=7 && !grid[lugarParaX][lugarParaY].getOcupada()){
    		result = true;
    	}else {
    		result = false;
    	}
    	return result;
    }
    
    public void executarMovimento(int posX, int posY,int lugarParaX,int lugarParaY){
    	if(VerificarCasa(lugarParaX, lugarParaY, posX, posY))
    	{
    		if(grid[posX][posY].getPeca() != null) {
    			if(grid[posX][posY].getPeca().getCor()== CorPeca.CLARO)
        		{
        			if(lugarParaX - posX == -1 && (lugarParaY - posY ==1 || lugarParaY - posY == -1))
        			{
        				grid[lugarParaX][lugarParaY].setPeca(grid[posX][posY].getPeca());
        				grid[posX][posY].setOcupada(false);
        				grid[posX][posY].setPeca(null);
        				grid[lugarParaX][lugarParaY].setOcupada(true);
        			}
        		}else
        		{
        			if(lugarParaX - posX == 1 && (lugarParaY - posY ==1 || lugarParaY - posY == -1))
        			{
        				grid[lugarParaX][lugarParaY].setPeca(grid[posX][posY].getPeca());
        				grid[posX][posY].setOcupada(false);
        				grid[posX][posY].setPeca(null);
        				grid[lugarParaX][lugarParaY].setOcupada(true);
        			}
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
        				atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, null, true, false);
        			}else {
        				atualBranca = criarCasa(new Peca(CorPeca.ESCURO, jogador[0]), CorCasa.PRETO, linha, coluna, jogador[0], false, true);
        			}
    			}else if(linha <= 4) {
    				if(atualBranca == true) {
    					atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, null, true, false);
        			}else {
        				atualBranca = criarCasa(null, CorCasa.PRETO, linha, coluna, null, false, false);
        			}
    			}else {
    				if(atualBranca == true) {
    					atualBranca = criarCasa(null, CorCasa.BRANCO, linha, coluna, null, true, false);
        			}else {
        				atualBranca = criarCasa(new Peca(CorPeca.CLARO, jogador[1]), CorCasa.PRETO, linha, coluna, jogador[0], false, true);
        			}
    			}
    		}
    		if(atualBranca == true) {
    			atualBranca = false;
    		}else {
    			atualBranca = true;
    		}
    	}
    }
    
    public boolean criarCasa(Peca peca, CorCasa corCasa, int linha, int coluna, Jogador jogador, boolean atualBranca, boolean ocupada){
		grid[linha][coluna] = new Casa(corCasa, ocupada, peca);
		if(atualBranca == true) {
			atualBranca = false;
		}else {
			atualBranca = true;
		}
		return atualBranca;
    }
    
    //mostra o tabuleiro na tela
    public void mostrarTabuleiro() {
    	for(int i = 0; i < 3; i++) {
    		System.out.println();
    	}
    	
    	for(int i = 0; i < grid.length; i++) {
    		for(int j = 0; j <grid[i].length; j++) {
    			if(grid[i][j].getPeca() == null) {
    				System.out.print("     ");
    			}else {
    				System.out.print("("+ i + "," + j+")");
    				/*
    				//Este if apenas deixa o tabuleiro organizado, pode apenas deixar um dos print no codigo.
    				if(grid[i][j].getPeca().getCor() == CorPeca.ESCURO) {
    					System.out.print(grid[i][j].getPeca().getCor());
    				}else {
    					System.out.print(" "+grid[i][j].getPeca().getCor());
    				}*/
    				
    			}
    		}
    		System.out.println("");
    	}
    }
    
    //chama o movimento da peca
    public void chamarMovimento() {
    	int intVetores[] = new int[4];
    	for(int i = 0; i < 4; i++) {
    		System.out.print("Digite o numero "+ i + ": ");
    		Scanner scan = new Scanner(System.in);
    		intVetores[i] = scan.nextInt();
    		//scan.close();
    	}
    	
    	executarMovimento(intVetores[0], intVetores[1], intVetores[2], intVetores[3]);
    }
}