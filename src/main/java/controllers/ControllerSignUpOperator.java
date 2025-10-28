package controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Operators;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

public class ControllerSignUpOperator {


    @FXML private TextField textFieldOperatorName;
    @FXML private PasswordField textFieldOperatorPassword;
    @FXML private TextField textFieldOperatorLogin;

    private int id;

    @FXML protected void saveOperator(){
        String name = textFieldOperatorName.getText();
        String login = textFieldOperatorLogin.getText();
        String password = textFieldOperatorPassword.getText();


        if(login.isEmpty() || password.isEmpty() || name.isEmpty()){
            showAlert("Erro:", "Nome, Login e Senha são obrigatórios.");
            return;
        }

        Dao<Operators> dao = new Dao<>(Operators.class);

        if (dao.buscarPorChave("login", login) == null) {
            Operators newOperators = new Operators(name, login, password);
            dao.insert(newOperators);
            showAlert("Sucesso!", "Operador salvo com sucesso!");
            clearFields();
        }
        else{
            showAlert("Erro:", "Já existe um operador com este login.");        }
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
