package damaslpoo;

//Classe principal, chama o método que inicia o tabuleiro, e o jogo.
public class DamasLpoo{
	public static void main(String[] args) {
		Tabuleiro tab = new Tabuleiro();
		tab.gerarTabuleiro();
	}
}