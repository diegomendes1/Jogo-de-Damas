package damaslpoo;

//Classe principal, chama o metodo que inicia o tabuleiro, e o jogo.
public class DamasLpoo{
        private static Tabuleiro tab;
        
        public void fazerTabuleiro(){
            tab = new Tabuleiro();
            tab.gerarTabuleiro();
        }
        
        public void infoJogador(){
            //ERROS
        	Jogador[] jogador = new Jogador[2];
        	
        	for(int i = 0; i < 2; i++) {
        		jogador[0] = new Jogador();
        	}
            
           //Adiciona pecas aos jogadores
                for(int i = 0; i < jogador.length; i++){
                    if(i == 0){
                        for(int j = 0; j < 12; i++){
                        jogador[i].getPecas(j);
                        jogador[i].getPecas(j).setJogador(jogador[i]);
                        }
                    }else{
                        for(int j = 0; j < 12; i++){
                        jogador[i].getPecas(j).setCor(CorPeca.ESCURO);
                        jogador[i].getPecas(j).setJogador(jogador[i]);
                        }
                    }
                    
                }
                
	}
}