
package damaslpoo;

public class Tabuleiro {
    private Casa[][] grid;
    
    
    //� necess�rio criar as casas aqui?
    public Tabuleiro(){
        this.grid = new Casa[8][8];
    }
    
    //NADA PRONTO
    public void executarMovimento(int posX, int posY){
        Casa casa = grid[posX][posY];
        if(casa.getPeca() != null){
            
        }
        
    }
    
    /* "gerarTabuleiro" anda por cada casa da vari�vel GRID, e configura para a cor da casa correta.*/
    public void gerarTabuleiro(Jogador[] jogador){
    	boolean atualBranca = true;
    	for(int linha = 0; linha < grid.length; linha++) {
    		for(int coluna = 0;coluna < grid[linha].length; coluna++) {
    			if(linha <= 2) {
    				if(atualBranca == true) {
        				
        				Peca peca = new Peca(CorPeca.CLARO, jogador[0]);
        				grid[linha][coluna] = new Casa(CorCasa.BRANCO, true, peca);
        				
        				if(grid[linha][coluna].getPeca() == null) {
        					System.out.print("noPECA");
        				}else {
        					System.out.print("  "+grid[linha][coluna].getPeca().getCor());
        				}
        				atualBranca = false;
        			}else {
        				
        				Peca peca = new Peca(CorPeca.CLARO, jogador[0]);
        				grid[linha][coluna] = new Casa(CorCasa.PRETO, true, peca);
        				
        				if(grid[linha][coluna].getPeca() == null) {
        					System.out.print("noPECA");
        				}else {
        					System.out.print("  "+grid[linha][coluna].getPeca().getCor());
        				}
        				atualBranca = true;
        			}
    			}else if(linha <= 4) {
    				if(atualBranca == true) {
        				grid[linha][coluna] = new Casa(CorCasa.BRANCO, false, null);
        				
        				if(grid[linha][coluna].getPeca() == null) {
        					System.out.print("  S-PEC");
        				}else {
        					System.out.print("  "+grid[linha][coluna].getPeca().getCor());
        				}
        				
        				atualBranca = false;
        			}else {
        				
        				grid[linha][coluna] = new Casa(CorCasa.PRETO, false, null);
        				
        				if(grid[linha][coluna].getPeca() == null) {
        					System.out.print("  S-PEC");
        				}else {
        					System.out.print(" "+grid[linha][coluna].getPeca().getCor());
        				}
        				atualBranca = true;
        			}
    			}else {
    				if(atualBranca == true) {
        				
        				Peca peca = new Peca(CorPeca.ESCURO, jogador[1]);
        				grid[linha][coluna] = new Casa(CorCasa.BRANCO, true, peca);
        				
        				if(grid[linha][coluna].getPeca() == null) {
        					System.out.print("noPECA");
        				}else {
        					System.out.print(" "+grid[linha][coluna].getPeca().getCor());
        				}
        				atualBranca = false;
        			}else {
        				
        				Peca peca = new Peca(CorPeca.ESCURO, jogador[1]);
        				grid[linha][coluna] = new Casa(CorCasa.PRETO, true, peca);
        				
        				if(grid[linha][coluna].getPeca() == null) {
        					System.out.print("noPECA");
        				}else {
        					System.out.print(" "+grid[linha][coluna].getPeca().getCor());
        				}
        				atualBranca = true;
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
}
