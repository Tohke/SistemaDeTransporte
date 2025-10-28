package controllers;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;

public class ControllerVehiclesManegement {

    @FXML protected void backMainPage(){
        App.changeScene("Tela.fxml");
    }
    @FXML protected void goOperationsPage(){
        App.changeScene("TelaOperacoesDeVeiculos.fxml");
    }
    @FXML protected void goHistory(){
        App.changeScene("TelaHistorico.fxml");
    }
}
