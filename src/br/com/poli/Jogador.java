package br.com.poli;

//Classe responsavel por guardar informacoes de um jogador.
public class Jogador {
    private String nome;
    //Decidimos colocar as pecas em cada jogador, ao inves de criar pecas com jogadores nulos.
    private Peca[] pecas;
    
    public Jogador(){
        this.nome = null;
        pecas = null;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome) {
    	this.nome = nome;
    }
    
    public Peca getPecas(int j){
        return this.pecas[j];
    }
    
    public void setPecas(Peca peca, int j){
        this.pecas[j] = peca;
    }
}
