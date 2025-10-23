package Controllers;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;

public class HelloControllerOperatorsMainPage {

    @FXML private void goOperacoesVeiculos() {App.changeScene("TelaOperacoesDeVeiculos.fxml");}
    @FXML private void goHistorico() {App.changeScene("TelaHistorico.fxml");}
    @FXML private void goCadastrarVeiculo() {App.changeScene("TelaAdicionarVeiculo.fxml");}
    @FXML private void goCadastrarMotorista() {App.changeScene("TelaCadastrarUsuario.fxml");}
    @FXML private void goCadastrarOperador() {App.changeScene("TelaCadastrarOperador.fxml");}
    @FXML private void doLogout() {App.changeScene("Tela.fxml");}
}