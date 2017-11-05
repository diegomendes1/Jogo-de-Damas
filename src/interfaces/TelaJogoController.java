package interfaces;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.poli.Casa;
import br.com.poli.Interface;
import br.com.poli.Jogador;
import br.com.poli.Jogo;
import br.com.poli.Tabuleiro;
import enums.CorPeca;
import enums.Resultado;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TelaJogoController implements Initializable{
	
	@FXML
	GridPane tabuleiro;
	
	@FXML
	GridPane tabPecas;
	
	@FXML
	GridPane pecasJogador1;
	
	@FXML
	GridPane pecasJogador2;
	
	@FXML
	Label qualJogador;
	
	@FXML 
	Label tempoQuePassou;
	
	@FXML
	AnchorPane gameOverMenu;
	
	@FXML
	Text textoResultado;
	
	@FXML
	Text jogadorVencedor;
	
	@FXML
	Text tempoPassado;
	
	@FXML
	Label contadorJogadas;
	
	Casa casaOrigem;
	Casa casaDestino;
	boolean escolhendoCasaOrigem = true;
	int pecasCapturadasJogador1;
	int pecasCapturadasJogador2;
	boolean fimDeJogo = false;
	
	String jogador1Nome;
	String jogador2Nome;

	public TelaJogoController(String jogador1, String jogador2) {
		this.jogador1Nome = jogador1;
		this.jogador2Nome = jogador2;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		gameOverMenu.setVisible(false);
		this.contadorJogadas.setText(" ");
		long inicioTempo = System.currentTimeMillis();
		
		new AnimationTimer() {
			long minPassado;
			@Override
			public void handle(long agora) {
				if(!fimDeJogo) {
				long tempoPassado = System.currentTimeMillis() - inicioTempo;
				minPassado = tempoPassado/60000;
				tempoQuePassou.setText("Tempo de Jogo: " + tempoPassado/60000 + ":"+ (tempoPassado/1000 - minPassado*60));
				}
			}
	
		}.start();
		
		rodandoJogo();
    }
	
	@FXML
	protected void voltarMenu(ActionEvent event) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/interfaces/MenuScreen.fxml"));
		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		Scene cenaMenu = new Scene(root);
		stage.setScene(cenaMenu);
	}
	
	@FXML
	protected void abrirCenaJogo(ActionEvent event) throws Exception{
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/interfaces/TelaJogo.fxml"));
		
		TelaJogoController controller = new TelaJogoController(jogador1Nome, jogador2Nome);
		fxmlloader.setController(controller);
		Parent root = (Parent)fxmlloader.load();
		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		Scene cenaJogo = new Scene(root);
		stage.setScene(cenaJogo);
	}
	
	public void rodandoJogo() {
		tabPecas.setDisable(true);
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		jogador1.setNome(jogador1Nome);
		jogador2.setNome(jogador2Nome);
		
		Tabuleiro tab = new Tabuleiro();
		tab.gerarTabuleiro(jogador1, jogador2);
		Interface jogo = new Jogo(jogador1, jogador2, null, tab, null, null);
		pecasCapturadasJogador1 = 0;
		pecasCapturadasJogador2 = 0;
		jogo.iniciarPartida();
		mostrarTabuleiro(jogo.getTabuleiro(), jogo);
		mostrarPecasTabuleiro(jogo.getTabuleiro());
		mostrarPecasCapturadas(true, pecasJogador1, pecasCapturadasJogador1);
		mostrarPecasCapturadas(false, pecasJogador2, pecasCapturadasJogador2);
		this.qualJogador.setText("Jogue, " + jogo.getJogador1().getNome());
	}
	
	
	public void setCasaOrigem(Casa casa) {
		this.casaOrigem = casa;
	}
	
	public void setCasaDestino(Casa casa) {
		this.casaDestino = casa;
	}
	
	public void setIsCasaOrigem(boolean opcao) {
		this.escolhendoCasaOrigem = opcao;
	}
	
	public void mostrarPecasTabuleiro(Tabuleiro tabuleiro) {
		Image imgPecaClara = new Image("/resources/clara.png");
		Image imgPecaEscura = new Image("/resources/escura.png");
		Image imgPecaClaraDama = new Image("/resources/claraDama.png");
		Image imgPecaEscuraDama = new Image("/resources/escuraDama.png");
		
		DropShadow dropShadow = new DropShadow();
		 dropShadow.setRadius(5.0);
		 dropShadow.setOffsetX(3.0);
		 dropShadow.setOffsetY(3.0);
		 dropShadow.setColor(Color.color(0, 0, 0));
		 
		boolean atualBranca = true;
		tabPecas.getChildren().clear();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tabuleiro.getGrid()[i][j].getPeca() != null) {
						if(tabuleiro.getGrid()[i][j].getPeca().getCor() == CorPeca.ESCURO) {
							ImageView imagemPecaEscura = null;
							if(!tabuleiro.getGrid()[i][j].getPeca().getIsDama()) {
							imagemPecaEscura = new ImageView(imgPecaEscura);
							}else {
								imagemPecaEscura = new ImageView(imgPecaEscuraDama);
							}
							imagemPecaEscura.setDisable(true);
							imagemPecaEscura.setEffect(dropShadow);
							this.tabPecas.add(imagemPecaEscura, j, i);
							
	    				}else {
	    					ImageView imagemPecaClara = null;
	    					if(!tabuleiro.getGrid()[i][j].getPeca().getIsDama()) {
								imagemPecaClara = new ImageView(imgPecaClara);
								}else {
									imagemPecaClara = new ImageView(imgPecaClaraDama);
								}
	    					imagemPecaClara.setDisable(true);
	    					imagemPecaClara.setEffect(dropShadow);
	    					this.tabPecas.add(imagemPecaClara, j, i);
	    				}
					}
				}
			
			
		}
	}
	
	public void mostrarTabuleiro(Tabuleiro tabuleiro, Interface jogo) {
		Image imgCasaBranca = new Image("/resources/white.png");
		Image imgCasaPreta = new Image("/resources/black.png");
		Image imgCasaSelecionadaP1 = new Image("/resources/casaSelecionadaP1.png");
		Image imgCasaSelecionadaP2 = new Image("/resources/casaSelecionadaP2.png");
		
		boolean atualBranca = true;
		for(int i = 0; i < 8; i++) {
			for(int j =0; j < 8; j++) {
				
				
				//FASE DE TESTES
				///////////
				ColumnConstraints cc = new ColumnConstraints();
			    cc.setHgrow(Priority.ALWAYS);
			    this.tabuleiro.getColumnConstraints().add(cc);
			    
			    RowConstraints rc = new RowConstraints();
			    rc.setVgrow(Priority.ALWAYS);
			    this.tabuleiro.getRowConstraints().add(rc);
			    ///////////
			    
				if(atualBranca) {
					
					ImageView imagem = new ImageView(imgCasaBranca);
					this.tabuleiro.add(imagem, j, i);
					atualBranca = false;
				}else {
					
					ImageView imagem = new ImageView(imgCasaPreta);
					imagem.setDisable(true);
					Button btn = new Button();
					btn.setPrefSize(60,60);
					btn.setStyle("-fx-focus-color: transparent;");
					this.tabuleiro.add(btn, j, i);
					this.tabuleiro.add(imagem, j, i);
					atualBranca = true;
					
					btn.setOnAction((e) -> {
						if(casaOrigem == null) {
							casaOrigem = tabuleiro.getCasaGrid(GridPane.getRowIndex(btn), GridPane.getColumnIndex(btn));
							if(jogo.getAtualJogador() == jogo.getJogador1()) {
							ImageView imagemSelecionada = new ImageView(imgCasaSelecionadaP2);
							this.tabPecas.add(imagemSelecionada, casaOrigem.getPosY(), casaOrigem.getPosX());
							}else {
								ImageView imagemSelecionada = new ImageView(imgCasaSelecionadaP1);
								this.tabPecas.add(imagemSelecionada, casaOrigem.getPosY(), casaOrigem.getPosX());
							}
							
						}else {
							casaDestino = tabuleiro.getCasaGrid(GridPane.getRowIndex(btn), GridPane.getColumnIndex(btn));
							jogo.jogar(casaOrigem, casaDestino);
							mostrarPecasTabuleiro(tabuleiro);
							atualizarPecasCapturadas(jogo);
							mostrarPecasCapturadas(true, pecasJogador1, pecasCapturadasJogador1);
							mostrarPecasCapturadas(false, pecasJogador2, pecasCapturadasJogador2);
							this.qualJogador.setText("Jogue, " + jogo.getAtualJogador().getNome());
							if(jogo.getJogadas() <= 0) {
								this.contadorJogadas.setText(" ");
							}else {
								this.contadorJogadas.setText(20 - (jogo.getJogadas()) + " Jogadas para empate");
							}
							casaOrigem = null;
							casaDestino = null;
							if(jogo.isFimDeJogo()) {
								fimDeJogo = true;
								qualJogador.setVisible(false);
								gameOverMenu.setVisible(true);
								tempoPassado.setText(tempoQuePassou.getText());
								if(jogo.getResultado() == Resultado.empate) {
									textoResultado.setVisible(false);
									jogadorVencedor.setText("EMPATE");
								}else if(jogo.getResultado() == Resultado.comVencedorJogador1 ||
										jogo.getResultado() == Resultado.comVencedorJogador2) {
									jogadorVencedor.setText(jogo.getVencedor().getNome());
								}
							}
						}
					});
				}
			}
			
			if(atualBranca) {
				atualBranca = false;
			}else {
				atualBranca = true;
			}
		}
		
	}
	
	public void mostrarPecasCapturadas(boolean isJogador1, GridPane grid, int totalPecas) {
		Image imgPecaClara = new Image("/resources/clara.png");
		Image imgPecaEscura = new Image("/resources/escura.png");
		int pecasMostradas = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				if(pecasMostradas < totalPecas) {
					pecasMostradas++;
					if(isJogador1) {
						ImageView imagem = new ImageView(imgPecaClara);
						imagem.setFitHeight(48);
						imagem.setFitWidth(48);
						grid.add(imagem, j, i);
					}else {
						ImageView imagem = new ImageView(imgPecaEscura);
						imagem.setFitHeight(48);
						imagem.setFitWidth(48);
						grid.add(imagem, j, i);
					}
					
				}
			}
		}
	}
	
	public void atualizarPecasCapturadas(Interface jogo) {
		pecasCapturadasJogador1 = jogo.getPecasCapturadas1();
		pecasCapturadasJogador2 = jogo.getPecasCapturadas2();
	}
}
