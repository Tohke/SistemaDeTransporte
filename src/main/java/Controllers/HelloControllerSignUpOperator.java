package Controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Operators;
import javafx.scene.control.Alert; // Adicione este import
import javafx.scene.control.PasswordField;

public class HelloControllerSignUpOperator {


    @FXML private TextField textFieldOperatorName;
    @FXML private PasswordField textFieldOperatorPassword;
    @FXML private TextField textFieldOperatorLogin;

    private int id;

    @FXML protected void saveOperator(){
        String name = textFieldOperatorName.getText();
        String login = textFieldOperatorLogin.getText();
        String password = textFieldOperatorPassword.getText();


        if(login.isEmpty() || password.isEmpty()){
            System.out.println("Erro: Login e Senha são obrigatórios.");
            return;
        }

        Dao<Operators> dao = new Dao<>(Operators.class);

        if (dao.buscarPorChave("login", login) == null) {
            Operators newOperators = new Operators(0, name, login, password); // Passa ID 0, mas da para usar o object ID
            dao.insert(newOperators);
            System.out.println("Operador salvo com sucesso!");
        }
        else{
            System.out.println("Erro: Operador incorreto!");
        }
    }

    @FXML protected void backMainPage(){App.changeScene("Tela.fxml");}

    private void clearFields() {
        textFieldOperatorName.clear();
        textFieldOperatorLogin.clear();
        textFieldOperatorPassword.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
