
//Controlador da Tela Inicial

package br.com.RosaCake.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class InicialController {

    @FXML
    private Button btnEncomenda;

    @FXML
    private Button btnInOut;

    @FXML
    private Button btnLucro;

    @FXML
    void clicouEncomenda(ActionEvent event) {

    }

    //Quando o usuário clicar no botão "InOut", o programa irá para a outra tela
    @FXML
    void clicouInOut(ActionEvent event) {
    	try {
			new ProximaTela("InOut.fxml").start(Rodar.stage);
		} catch (Exception err) {
			err.printStackTrace();
		}
    }

  //Quando o usuário clicar no botão "Lucros", o programa irá para a outra tela
    @FXML
    void clicouLucro(ActionEvent event) {
    	try {
			new ProximaTela("Lucros.fxml").start(Rodar.stage);
		} catch (Exception err) {
			err.printStackTrace();
		}
    }

}
