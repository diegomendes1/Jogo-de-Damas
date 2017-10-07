package Classes;
import Enums.CorPeca;

public class Dama extends Peca{
	private boolean pecaDama;
    public Dama(CorPeca cor, Jogador jogador, boolean pecaDama){
        super(cor, jogador);
        pecaDama = true;
    }
}
