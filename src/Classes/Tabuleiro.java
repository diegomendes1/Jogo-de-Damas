package Classes;
import java.util.Scanner;
import Enums.CorCasa;
import Enums.CorPeca;

/*Classe responsavel por gerenciar o tabuleiro. Aqui se cria o tabuleiro, adiciona peca nas casas,
 * e movimenta alguma peca.*/
public class Tabuleiro {
    private Casa[][] grid;
    
    public Tabuleiro(){
        this.grid = new Casa[8][8];
    }
    
    //Verifica se o movimento nao ultrapassa as barreiras do tabuleiro, e se a casa possui outra peca.
   /* public boolean VerificarCasa(int lugarParaX, int lugarParaY, int posX, int posY) {
    	boolean result;
        if(grid[posX][posY].getOcupada() == true){
        	if(lugarParaX>=0 && lugarParaX <=7 && lugarParaY>=0 && lugarParaY<=7 && !grid[lugarParaX][lugarParaY].getOcupada()){
        		result = true;
        	}else {
        		System.out.println("MOVIMENTO INVALIDO: de " + "(" +posX + ","+posY+") para (" + lugarParaX +","+ lugarParaY+").");
        		result = false;
        	}
    	return result;
        }else{
            System.out.println("MOVIMENTO INVALIDO: de " + "(" +posX + ","+posY+") para (" + lugarParaX +","+ lugarParaY+").");
            return false;
        }
    }
    
    //move uma peca escolhida para uma outra casa, tambem escolhida.
    public void executarMovimento(Casa casaAtual, Casa novaCasa, Jogador atualJogador){
    	int posX = casaAtual.getPosX();
    	int posY = casaAtual.getPosY();
    	int lugarParaX = novaCasa.getPosX();
    	int lugarParaY = novaCasa.getPosY();
    	
    	if(grid[posX][posY].getPeca().getJogador() == atualJogador) {
    		if(VerificarCasa(lugarParaX, lugarParaY, posX, posY))
    		{
    			if(grid[posX][posY].getPeca() != null) {
    				int direcaoPeca;
    				//dependendo da cor da peca, a variavel direcaoPeca vai mudar de valor.
    				if(grid[posX][posY].getPeca().getCor()== CorPeca.CLARO){
    					direcaoPeca = -1;
    				}else {
    					direcaoPeca = 1;
    				}
    			
    				if(lugarParaX - posX == direcaoPeca && (lugarParaY - posY ==1 || lugarParaY - posY == -1))
    				{
    					grid[lugarParaX][lugarParaY].setPeca(grid[posX][posY].getPeca());
    					grid[posX][posY].setOcupada(false);
    					grid[posX][posY].setPeca(null);
    					grid[lugarParaX][lugarParaY].setOcupada(true);
    				}
    			}
    		}
    	}
    }*/
    
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
		if(atualBranca == true) {
			atualBranca = false;
		}else {
			atualBranca = true;
		}
		return atualBranca;
    }
    
    //mostra o tabuleiro na tela.
    public void mostrarTabuleiro() {
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
    
    //chama o movimento da peca, coletando os 4 ints para compor os dois vetores, um para cada casa.
    public void chamarMovimento(Jogador atualJogador) {
    	int intVetores[] = new int[4];
    	for(int i = 0; i < 4; i++) {
    		System.out.print("Digite o numero "+ i+1 + ": ");
    		Scanner scan = new Scanner(System.in);
    		intVetores[i] = scan.nextInt();
    		//scan.close();
    	}
    	//executarMovimento(intVetores[0], intVetores[1], intVetores[2], intVetores[3], atualJogador);
    }
    
    public Casa GetCasaGrid(int i, int j) {
    	return grid[i][j];
    }
}