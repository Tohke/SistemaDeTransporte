package controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Operators;

public class ControllerLoginOperator {

    @FXML private TextField textFieldLogin;
    @FXML private PasswordField passwordFieldSenha;

    private Dao<Operators> dao = new Dao<>(Operators.class);


    @FXML
    private void doLogin() {
        String login = textFieldLogin.getText();
        String senha = passwordFieldSenha.getText();

        if (login.isEmpty() || senha.isEmpty()) {
            showAlert("Erro de Validação", "Login e Senha não podem estar vazios.");
            return;
        }

        Operators operator = dao.buscarPorChave("login", login);

        if (operator != null && operator.getPassword().equals(senha)) {
            System.out.println("Login aprovado para: " + operator.getOperatorName());
            App.changeScene("TelaPrincipalOperador.fxml");
        } else {
            showAlert("Login Falhou", "Login ou senha incorretos. Tente novamente.");
        }
    }


    @FXML private void backMainPage() {App.changeScene("Tela.fxml");}

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}