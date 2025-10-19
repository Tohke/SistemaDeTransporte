package Controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Operators;

public class HelloControllerLogin {

    @FXML private TextField textFieldLogin;
    @FXML private PasswordField passwordFieldSenha;

    private Dao<Operators> dao = new Dao<>(Operators.class);

    /**
     * Ação do botão "Entrar".
     * Valida o login e a senha do operador no banco de dados.
     */
    @FXML
    private void doLogin() {
        String login = textFieldLogin.getText();
        String senha = passwordFieldSenha.getText();

        // 1. Validação de campos vazios
        if (login.isEmpty() || senha.isEmpty()) {
            showAlert("Erro de Validação", "Login e Senha não podem estar vazios.");
            return;
        }

        // 2. Busca o operador pelo login no banco
        Operators operator = dao.buscarPorChave("login", login);

        // 3. Valida se o operador existe e se a senha está correta
        if (operator != null && operator.getPassword().equals(senha)) {
            // Sucesso! Redireciona para a tela principal de gerenciamento
            System.out.println("Login aprovado para: " + operator.getOperatorName());
            App.changeScene("TelaGerenciarVeiculos.fxml");
        } else {
            // Falha
            showAlert("Login Falhou", "Login ou senha incorretos. Tente novamente.");
        }
    }


    @FXML private void backMainPage() {App.changeScene("Tela.fxml");}

    /**
     * Método auxiliar para exibir alertas.
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}