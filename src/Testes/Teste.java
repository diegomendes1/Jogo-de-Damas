package Testes;

import damaslpoo.DamasLpoo;
import damaslpoo.Tabuleiro;

public class Teste {
    public static DamasLpoo teste;
    public static void main(String[] args) {
                teste = new DamasLpoo();
                Tabuleiro tab = new Tabuleiro();
                tab.gerarTabuleiro(teste.infoJogador());
                tab.mostrarTabuleiro();
                tab.chamarMovimento();
                tab.mostrarTabuleiro();
    }
        
}
    	
