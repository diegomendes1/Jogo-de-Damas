package br.com.poli;

import java.util.Scanner;
import enums.CorPeca;

//Essa classe fica encarregada de criar os jogadores, coletar seus nomes e criar suas respectivas pecas.
public class Jogadores {
    private Jogador[] jogador;

	public Jogadores() {
		jogador = null;
	}
	
    public Jogador[] criarJogadores(){
      	jogador = new Jogador[2];
        	
       	for(int i = 0; i < 2; i++) {
       		jogador[i] = new Jogador();
       		System.out.print("Qual o nome do Jogador "+ (i+1) +"?: ");
       		/* Recebe o nome do jogador e adiciona para sua respectiva classe.*/
       		jogador[i].setNome(getJogadorNome());
       		System.out.println();
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
    
    /* Funcao que coleta o nome do jogador*/
    public String getJogadorNome() {
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
