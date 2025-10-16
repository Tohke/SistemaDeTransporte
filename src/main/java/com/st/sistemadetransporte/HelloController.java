package com.st.sistemadetransporte;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import modelo.Veiculo;

public class HelloController {
    @FXML private Label welcomeText;


    @FXML private void selectedWithdrawals(){
        App.changeScene("TelaRetiradas.fxml");
    }

    @FXML private void selectedAddVehicles(){
        App.changeScene("TelaAdicionarVeiculo.fxml");
    }

}