package br.com.poli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/br/com/poli/interfaces/MenuScreen.fxml"));
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
