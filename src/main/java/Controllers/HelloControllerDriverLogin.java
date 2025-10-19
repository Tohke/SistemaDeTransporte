package Controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.User;

public class HelloControllerDriverLogin {

    @FXML private TextField textFieldCNH;

    private final Dao<User> dao = new Dao<>(User.class);

    @FXML
    private void doLoginMotorista() {
        String cnh = textFieldCNH.getText();

        if (cnh.isEmpty()) {
            showAlert("Erro de Validação", "O campo CNH não pode estar vazio.");
            return;
        }

        // Busca o motorista pela CNH
        User motorista = dao.buscarPorChave("cnh", cnh);

        if (motorista != null) {
            // Sucesso!
            System.out.println("Login aprovado para motorista: " + motorista.getUserName());

            // NOTA: O PDF não especifica uma tela para o motorista.
            // Por enquanto, vamos apenas redirecionar para a tela principal (Tela.fxml).
            // Se você criar um "Dashboard do Motorista", mude a linha abaixo.
            showAlert("Sucesso", "Bem-vindo, " + motorista.getUserName() + "!");
            App.changeScene("Tela.fxml");

        } else {
            // Falha
            showAlert("Login Falhou", "CNH não encontrada em nosso cadastro.");
        }
    }

    @FXML
    private void backMainPage() {
        App.changeScene("Tela.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}