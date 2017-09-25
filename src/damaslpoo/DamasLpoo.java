package damaslpoo;
import java.util.Scanner;

import Enums.CorPeca;


//Classe principal, chama o metodo que inicia o tabuleiro, e o jogo.
public class DamasLpoo{
	private static Tabuleiro tab;
    private Jogador[] jogador;
        
    /*public void fazerTabuleiro(){
        tab = new Tabuleiro();
        tab.gerarTabuleiro(jogador);
    }*/
        
    public Jogador[] infoJogador(){
        //ERROS
      	jogador = new Jogador[2];
        	
       	for(int i = 0; i < 2; i++) {
       		jogador[i] = new Jogador();
       		/* ESSA LINHA CHAMA O METODO QUE PEDE O NOME DO JOGADOR 
       		 * ATRAVES DO SCANNER. SERA RETIRADO NO FUTURO.*/
       		jogador[i].setNome(setJogadorNome());
       		
       	}
            
        //Adiciona pecas aos jogadores
        for(int i = 0; i < 2; i++){
        	if(i == 0){
        		for(int j = 0; j < 12; j++){
                  	jogador[i].setPecas(new Peca(CorPeca.CLARO, jogador[i]), j);
                }
            }else{
                for(int j = 0; j < 12; j++){
                   	jogador[i].setPecas(new Peca(CorPeca.ESCURO, jogador[i]), j);
                }
            }                               
        }
        return jogador;
        
    }
    
    /* PEGA O NOME DO JOGADOR*/
    public String setJogadorNome() {
    	Scanner scan = new Scanner(System.in);
    	String nome = scan.nextLine();
    	/* Deveria fechar usando o metodo abaixo, mas causa algum bug desconhecido*/
    	//scan.close();
    	return nome;
    }
    
    public Jogador getJogador(int i) {
    	return jogador[i];
    }
}