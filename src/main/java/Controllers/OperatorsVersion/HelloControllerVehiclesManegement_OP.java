package Controllers.OperatorsVersion;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;

public class HelloControllerVehiclesManegement_OP {

    @FXML private void backOperatorsPage() {App.changeScene("TelaPrincipalOperador.fxml");}
    @FXML protected void goOperationsPage(){
        App.changeScene("TelaOperacoesDeVeiculos_OP.fxml");
    }
    @FXML protected void goHistory(){
        App.changeScene("TelaHistorico_OP.fxml");
    }
}
