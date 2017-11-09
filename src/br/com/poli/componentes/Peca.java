package br.com.poli.componentes;
import br.com.poli.enums.CorPeca;

//Classe responsavel por guardar informacoes de uma peca.
public class Peca {
   private CorPeca cor;
   private Jogador jogador;
   private boolean isDama;
   
   public Peca(CorPeca cor, Jogador jogador){
       this.cor = cor;
       this.jogador = jogador;
       this.isDama = false;
   }
   
   public CorPeca getCor() {
	   return cor;
   }
   
   public void setDama(boolean dama) {
	   this.isDama = dama;
   }
   
   public boolean getIsDama() {
	   return isDama;
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
