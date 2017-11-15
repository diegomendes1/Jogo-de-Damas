package br.com.poli.componentes;
import br.com.poli.Jogador;
import br.com.poli.enums.CorPeca;

public class Dama extends Peca{
	private boolean pecaDama;
    public Dama(CorPeca cor, Jogador jogador, boolean pecaDama){
        super(cor, jogador);
        pecaDama = true;
    }
    
    public void setDamaBool(boolean pecaDama){
        this.pecaDama = pecaDama;
    }
    
    public boolean getDamaBool(){
        return pecaDama;
    }
}
