package interfaces;
import java.net.URL;
import java.util.ResourceBundle;

import br.com.poli.Casa;
import br.com.poli.Interface;
import br.com.poli.Jogador;
import br.com.poli.Jogo;
import br.com.poli.Tabuleiro;
import enums.CorPeca;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
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
	
	Casa casaOrigem;
	Casa casaDestino;
	boolean escolhendoCasaOrigem = true;

	public TelaJogoController() {
		// TODO Auto-generated constructor stub
	}
	
	public void initialize(URL location, ResourceBundle resources) {
       // contentSelect();
		rodandoJogo();
    }
	
	@FXML
	protected void voltarMenu(ActionEvent event) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/interfaces/MenuScreen.fxml"));
		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		Scene cenaMenu = new Scene(root);
		stage.setScene(cenaMenu);
	}
	
	public void rodandoJogo() {
		tabPecas.setDisable(true);
		Jogador jogador1 = new Jogador();
		Jogador jogador2 = new Jogador();
		Tabuleiro tab = new Tabuleiro();
		tab.gerarTabuleiro(jogador1, jogador2);
		Interface jogo = new Jogo(jogador1, jogador2, null, tab, null, null);
		jogo.iniciarPartida();
		mostrarTabuleiro(jogo.getTabuleiro(), jogo);
		mostrarPecasTabuleiro(jogo.getTabuleiro());
		//mostrarPecasCadaJogador(jogo.getJogador1(), true);
		//mostrarPecasCadaJogador(jogo.getJogador2(), false);
		
		
		
		
	}
	public void checkPosicao(Tabuleiro grid) {
		this.tabuleiro.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
			for( Node source: tabuleiro.getChildren()) {
                if( source instanceof ImageView) {
                    if( source.getBoundsInParent().contains(e.getX(),  e.getY())) {
                    	System.out.println("CASA: "+ "(" + GridPane.getRowIndex(source) + "," + GridPane.getColumnIndex(source) + ")");
                    	if(escolhendoCasaOrigem == true) {
                    		setCasaOrigem(grid.getCasaGrid(GridPane.getRowIndex(source), GridPane.getColumnIndex(source)));
                    		setIsCasaOrigem(false);
                    	}else {
                    		setCasaDestino(grid.getCasaGrid(GridPane.getRowIndex(source), GridPane.getColumnIndex(source)));
                    	}
                    }
                }
		}
			
	});
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
		boolean atualBranca = true;
		tabPecas.getChildren().clear();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(tabuleiro.getGrid()[i][j].getPeca() != null) {
						if(tabuleiro.getGrid()[i][j].getPeca().getCor() == CorPeca.ESCURO) {
							ImageView imagemPecaEscura = new ImageView(imgPecaEscura);
							imagemPecaEscura.setDisable(true);
							this.tabPecas.add(imagemPecaEscura, j, i);
							
	    				}else {
	    					ImageView imagemPecaClara = new ImageView(imgPecaClara);
	    					imagemPecaClara.setDisable(true);
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
					btn.setPrefSize(64, 64);
					this.tabuleiro.add(btn, j, i);
					this.tabuleiro.add(imagem, j, i);
					atualBranca = true;
					
					btn.setOnAction((e) -> {
						if(casaOrigem == null) {
							System.out.println(GridPane.getRowIndex(btn) + ","+ GridPane.getColumnIndex(btn));
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
							if(jogo.jogar(casaOrigem, casaDestino)) {
								//atualizarCasasVazias(tabuleiro);
								//mostrarPecasTabuleiro(tabuleiro);
							}
							mostrarPecasTabuleiro(tabuleiro);
							casaOrigem = null;
							casaDestino = null;
							
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
	
	public void atualizarCasasVazias(Tabuleiro tabuleiro) {
		Casa casaOponente = tabuleiro.getCasaGrid((casaDestino.getPosX()+casaOrigem.getPosX())/2, (casaDestino.getPosY()+casaOrigem.getPosY())/2);
		if(casaOponente.getPeca() != null) {
			for (Node node : this.tabuleiro.getChildren()) {
				System.out.println(GridPane.getRowIndex(node)+","+GridPane.getColumnIndex(node));
		        if(GridPane.getRowIndex(node) == casaOponente.getPosX() && GridPane.getColumnIndex(node) == casaOponente.getPosY()) {
		           // this.tabuleiro.getChildren().remove(node);
		        	System.out.println("wow");
		        }
		    }
			this.tabuleiro.getChildren().removeAll();
		}else {
			//this.tabuleiro.getChildren().remove(casaOrigem);
		}
	}
	
	public void mostrarPecasCadaJogador(Jogador jogador, boolean isJogador1) {
		GridPane grid;
		Image img;
		if(isJogador1) {
			System.out.println("JOGADOR 1");
			grid = pecasJogador1;
			img = new Image("/resources/clara.png");
		}else {
			grid = pecasJogador2;
			System.out.println("JOGADOR 2");
			img = new Image("/resources/escura.png");
		}
		
		int posicao = 0;
		for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 2; j++) {
				ImageView imagem = new ImageView(img);
				this.tabuleiro.add(imagem, i, j);
				
				/*if(jogador.getPecas(posicao) != null) {
					System.out.println("PECA");
					ImageView imagem = new ImageView(img);
					this.tabuleiro.add(imagem, i, j);
				}else {
					System.out.println("PECA");
					ImageView imagem = new ImageView();
					this.tabuleiro.add(imagem, i, j);
				}*/
				posicao++;
			}
		}
	}

}
