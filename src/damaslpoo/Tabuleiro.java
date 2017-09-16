
package damaslpoo;

public class Tabuleiro {
    private Casa[][] grid;
    
    public Tabuleiro(){
        this.grid = new Casa[8][8];
    }
    
    public void executarMovimento(int posX, int posY){
        Casa casa = grid[posX][posY];
        if(casa.getPeca() != null){
            
        }
        
    }
    
    public void gerarTabuleiro(){
        
    }
}
