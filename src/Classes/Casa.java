package Classes;
import Enums.CorCasa;

//Classe responsavel por guardar as informacoes de uma casa do tabuleiro.
public class Casa {
    private CorCasa cor;
    private boolean ocupada;
    private Peca peca;
    private Dama dama;
    
    private int posX;
    private int posY;
    
    public Casa(CorCasa cor,boolean ocupada,Peca peca, int posX, int posY)
    {
        this.cor = cor;
        this.ocupada = ocupada;
        this.peca = peca;
        this.posX = posX;
        this.posY = posY;
    }
    
    //COR
    public void setCor( CorCasa cor)
    {
        this.cor = cor;
    }
    
    public CorCasa getCor(){
        return cor;
    }
    
    //OCUPADA
    public void setOcupada(boolean ocupada){
        this.ocupada = ocupada;
    }
    
    public boolean getOcupada(){
        return ocupada;
    }
    
    //PECA
    public void setPeca(Peca peca){
        this.peca = peca;
    }
    
    public Peca getPeca(){
        return peca;
    }
    
    public void setDama(Dama dama){
        this.dama = dama;
    }
    
    public Peca getDama(){
        return dama;
    }
    
    //COORDENADAS
    public void setPosX(int posX)
    {
        this.posX = posX;
    }
    
    public int getPosX(){
        return posX;
    }
    
    public void setPosY(int posY)
    {
        this.posY = posY;
    }
    
    public int getPosY(){
        return posY;
    }
}