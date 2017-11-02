package interfaces;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MenuScreenController {
	
	 @FXML
	 private Button botaoIniciar;
	 
	 @FXML
	 private Button botaoAjuda;
	 
	 @FXML
	 private Button botaoSair;
	 
	 @FXML
	 private CheckBox isTelaCheia;

	public MenuScreenController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	protected void alternarTelaCheia() {
		
		Stage stage = (Stage) isTelaCheia.getScene().getWindow();
		if(this.isTelaCheia.isSelected()) {
			stage.setFullScreen(true);
		}else {
			stage.setFullScreen(false);
		}
	}
	
	
	//Desejaria transformar os tres metodos em um so, mudando apenas a string do nome. Mas parece que chamar o metodo assim do fxml nao da :(
	@FXML
	protected void abrirCenaJogo(ActionEvent event) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/interfaces/TelaJogo.fxml"));

		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		Scene cenaJogo = new Scene(root);
		stage.setScene(cenaJogo);
		
	}
	
	@FXML
	protected void abrirCenaAjuda(ActionEvent event) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("/interfaces/AjudaMenu.fxml"));

		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		Scene cenaAjuda = new Scene(root);
		stage.setScene(cenaAjuda);
	}
	
	@FXML
	protected void feharJogo(ActionEvent event) throws Exception{
		Stage stage = (Stage) ((Node) event.getTarget()).getScene().getWindow();
		stage.close();
	}

}
