package br.com.poli;
import enums.CorPeca;

//Classe responsavel por guardar informacoes de uma peca.
public class Peca {
   private CorPeca cor;
   private Jogador jogador;
   
   public Peca(CorPeca cor, Jogador jogador){
       this.cor = cor;
       this.jogador = jogador;
   }
   
   public CorPeca getCor() {
	   return cor;
   }
   
   public void setCor(CorPeca cor) {
	   this.cor = cor;
   }
   
   public Jogador getJogador() {
	   return jogador;
   }
   
   public void setJogador(Jogador jogador) {
	   this.jogador = jogador;
   }
   
   
}
