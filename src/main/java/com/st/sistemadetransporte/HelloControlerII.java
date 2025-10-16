package com.st.sistemadetransporte;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modelo.Veiculo;

public class HelloControlerII {

    @FXML private ChoiceBox choiceBox;
    @FXML private ChoiceBox choiceBox2;
   // @FXML private Veiculo veiculo;

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;


    @FXML protected void backMainPage(){
        App.changeScene("Tela.fxml");
    }
}
