package br.com.RosaCake.views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Rodar extends Application {
	

	public static Stage stage;

	public static void main(String[] args) {
		launch(Rodar.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		Parent root = FXMLLoader.load(getClass().getResource("Inicial.fxml"));

		Scene scene = new Scene(root);
		scene.setFill(Color.web("#778899"));
	
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent arg0) {
		        System.exit(0);
		    }
		});
		
		Image imagem = new Image("br/com/RosaCake/imagens/RosaCake.png"); 

		Rodar.stage = primaryStage;
		stage.setScene(scene);
		stage.setTitle("Controle de Orçamento");
		stage.show();
		stage.setResizable(false); //Método para impedir a maximização da tela
		stage.getIcons().add(imagem);
		//stage.setFullScreen(true); //Deixa a tela maximizada e sem os botoes de fechar...
	}
}
