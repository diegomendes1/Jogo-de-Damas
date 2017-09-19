
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
    public void gerarTabuleiro(){
    	boolean anteriorBranca = false;
    	for(int linha = 0; linha < grid.length; linha++) {
    		for(int coluna = 0;coluna < grid[linha].length; coluna++) {
    			if(anteriorBranca == false) {
    				Jogador[] jogador = new Jogador[3];
    				//Cria um jogador novo, devemos mudar e usar dois jogadores criados antes.
    				jogador[0] = new Jogador();
    				//cria uma pe�a.
    				Peca peca = new Peca(CorPeca.ESCURO, jogador[0]);
                                //finalmnte, cria uma casa.
    				grid[linha][coluna] = new Casa(CorCasa.BRANCO, false, peca);
    				
    				/*Imprime informa��es da casa. 
    				 * depois de [coluna], vc pode inserir algumas coisas para mudar a impress�o.
    				 * 
    				 * Para mostrar a cor da casa, adicione .getCor()
    				 * 
    				 * Para mostrar o nome do dono da peca, digite .getPeca().getJogador().getNome()
    				 * 
    				 * Sim, mostrar o nome do jogador n�o est� correto, mas pelo menos conseguimos adicionar
    				 * um nome para o jogador, certo minha amiga?*/
    				System.out.print(" "+grid[linha][coluna].getPeca().getJogador().getNome());
    				anteriorBranca = true;
    			}else {
    				
    				Jogador jogador = new Jogador();
				Peca peca = new Peca(CorPeca.CLARO, jogador);
    				grid[linha][coluna] = new Casa(CorCasa.PRETO, false, peca);
    				
    				System.out.print(" "+grid[linha][coluna].getPeca().getCor());
    				anteriorBranca = false;
    			}
    			
    		}
    		System.out.println("");
    		if(anteriorBranca == false) {
    			anteriorBranca = true;
    		}else {
    			anteriorBranca = false;
    		}
    	}
    }
}
