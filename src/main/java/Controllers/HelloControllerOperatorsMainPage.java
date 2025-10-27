package Controllers;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;

public class HelloControllerOperatorsMainPage {

    @FXML private void goOperacoesVeiculos() {App.changeScene("TelaOperacoesDeVeiculos_OP.fxml");}
    @FXML private void goHistorico() {App.changeScene("TelaHistorico_OP.fxml");}
    @FXML private void goCadastrarVeiculo() {App.changeScene("TelaAdicionarVeiculo_OP.fxml");}
    @FXML private void goCadastrarMotorista() {App.changeScene("TelaCadastrarUsuario_OP.fxml");}
    @FXML private void goCadastrarOperador() {App.changeScene("TelaCadastrarOperador_OP.fxml");}
    @FXML private void doLogout() {App.changeScene("Tela.fxml");}
}