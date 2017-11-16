package br.com.poli;

import br.com.poli.componentes.Peca;

//Classe responsavel por guardar informacoes de um jogador.
public class Jogador {
    private String nome;
    //Decidimos colocar as pecas em cada jogador, ao inves de criar pecas com jogadores nulos.
    private Peca[] pecas;
    private boolean isAutonomo;
    
    public Jogador(boolean isAutonomo){
        this.nome = null;
        pecas = null;
        this.isAutonomo = isAutonomo;
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
    
    public boolean getAutonomo() {
    	return this.isAutonomo;
    }
}
