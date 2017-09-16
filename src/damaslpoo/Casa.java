package damaslpoo;

public class Casa {
    private CorCasa cor;
    private boolean ocupada;
    private Peca peca;
    
    public Casa(CorCasa cor,boolean ocupada,Peca peca)
    {
        this.cor = cor;
        this.ocupada = ocupada;
        this.peca = peca;
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
}