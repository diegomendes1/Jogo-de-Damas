package damaslpoo;

//Classe principal, chama o m�todo que inicia o tabuleiro, e o jogo.
public class DamasLpoo{
        private static Tabuleiro tab;
        private Jogador[] jogador;
        
        public void fazerTabuleiro(){
            tab = new Tabuleiro();
            tab.gerarTabuleiro();
        }
        
        public void infoJogador(){
                
            //ERROS
           jogador = new Jogador[2];
            //jogador[0].setNome("Diego");
            //jogador[1].setNome("Eduarda");
            
                                //Adiciona peças aos jogadores
                for(int i = 0; i < jogador.length; i++){
                    if(i == 0){
                        for(int j = 0; j < 12; i++){
                        jogador[i].getPecas(j).setCor(CorPeca.CLARO);
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