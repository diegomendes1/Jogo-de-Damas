package br.com.poli;

import enums.CorPeca;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/interfaces/MenuScreen.fxml"));
		Scene cena = new Scene(root);
		primaryStage.setScene(cena);
		primaryStage.show();
		primaryStage.setResizable(false);
		primaryStage.setFullScreenExitHint("");
		primaryStage.setTitle("Jogo de Damas");
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
/*package br.com.poli;

import java.util.Date;
import java.util.Scanner;

import enums.CorCasa;
import enums.CorPeca;
import enums.Resultado;
import javafx.application.Application;

//Apenas para teste, esta classe testa todos os metodos principais.
public class seriaMain {
	public static void main(String[] args) {
		//Classe Casa que recebe uma cor, um boolean pra saber se esta ocupada, e uma peca.
		Casa casaBranca = new Casa(CorCasa.BRANCO, false, null, 0, 0);
		Casa casaPreta = new Casa(CorCasa.PRETO, false, null, 0, 0);
				
		//Classe Peca, que recebe uma cor e um jogador.
		Peca pecaClara = new Peca(CorPeca.CLARO, null);
		Peca pecaEscura = new Peca(CorPeca.ESCURO, null);
		
		Jogo jogo = new Jogo(new Jogador(), new Jogador(), new Jogador(), new Tabuleiro(), null, new Date());
		jogo.iniciarPartida();
		
		System.out.println();
		System.out.println("O Jogador 1 se chama "+ jogo.getJogador1().getNome()+",");
		System.out.println("e o jogador 2 se chama "+ jogo.getJogador2().getNome()+".");
		
		System.out.println();
		
		//Agora, vamos mostrar o jogo funcionando
		
		//Mostra o tabuleiro pela primeira vez, dizendo a cor da peca
		jogo.getTabuleiro().mostrarTabuleiro();
		
		//Mostra quem deve jogar agora
		System.out.println();
		System.out.println("Vez do " + jogo.getAtualJogador().getNome());
		System.out.println();
		
		//Este while controla todo o jogo, vai repetindo as jogadas ate que alguma condicao de fim de jogo seja feita
		while(!jogo.isFimDeJogo()) {
			int intVetores[] = new int[4];
			
			//Neste FOR, digite as coordenadas X e Y de onde a pedra esta, e o X e Y de onde quer mover ou capturar outra pedra.
	    	for(int i = 0; i < 4; i++) {
	    		System.out.print("Digite o numero "+ i+ ": ");
	    		Scanner scan = new Scanner(System.in); //Ao tentar usar o scan.close(); , o jogo buga.
	    		intVetores[i] = scan.nextInt();
	    	}
	    	
	    	//Chama o metodo jogar(), que recebe as coordenadas recebidas anteriormente, acha as casas correspondentes.
	    	//Retorna true se conseguiu realizar a captura ou movimento, retorna falso se falhou.
	    	if(jogo.jogar(jogo.getTabuleiro().getCasaGrid(intVetores[0], intVetores[1]), jogo.getTabuleiro().getCasaGrid(intVetores[2], intVetores[3]))) {
	    	
	    		/*O metodo jogar() faz tudo sozinho, sem a necessidade de modificar nada. ele nao depende de outro metodo,
	    		 * entao ajuda no encapsulamento. Ele deixa tudo pronto para uma proxima chamada deste metodo, ou do fim do jogo.
	    		
	    		jogo.getTabuleiro().mostrarTabuleiro();
	    		System.out.println();
	    		System.out.println("Vez do " + jogo.getAtualJogador().getNome());
	    		System.out.println();
	    	}else {
	    		/*Se jogar() retornar false, entao precisa jogar de novo. A jogada recomeca sem precisar modificar nada, apenas
	    		 * coletar as coordenadas e chamar de novo o metodo jogar()
	    		 
	    		jogo.getTabuleiro().mostrarTabuleiro();
	    		System.out.println();
	    		System.out.println("Jogue outra vez, " + jogo.getAtualJogador().getNome());
	    		System.out.println();
	    	}
		}
		if(jogo.getResultado() == Resultado.empate) {
			System.out.println("O jogo acabou com um EMPATE");
		}else {
			System.out.println("O jogo acabou e o vencedor foi o "+ jogo.getVencedor());
		}
	}
}*/
