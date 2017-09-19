package damaslpoo;

public class Jogador {
    private String nome;
    private Peca[] pecas;
    
    public Jogador(){
        this.nome = "TESTE";
        pecas = new Peca[12];
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome) {
    	this.nome = nome;
    }
    
    public Peca getPecas(int j){
        return pecas[j];
    }
    
    /*public void setPecas(Peca peca, int j){
        this.pecas[j] = peca;
    }*/
    

}
