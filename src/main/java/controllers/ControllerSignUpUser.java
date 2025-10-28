package controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.User;
import javafx.scene.control.Alert;

public class ControllerSignUpUser {
    @FXML private TextField textFieldUserName;
    @FXML private TextField textFieldUserAddress;
    @FXML private TextField textFieldUserCNH;
    @FXML private TextField textFieldUserSector;

    @FXML protected void saveUser(){
        String name = textFieldUserName.getText();
        String address = textFieldUserAddress.getText();
        String cnh = textFieldUserCNH.getText();
        String sector = textFieldUserSector.getText();

        if(cnh.isEmpty() || name.isEmpty() || address.isEmpty() || sector.isEmpty()){
            showAlert("Erro:","Todos os campos são obrigatórios!");
            return;
        }

        //User newUser = new User(name, address, cnh, sector);
        Dao<User> dao = new Dao<>(User.class);

        if(dao.buscarPorChave("cnh", cnh) == null){
            User newUser = new User(name, address, cnh, sector);
            dao.insert(newUser);
            showAlert("Sucesso", "Motorista " + name + " salvo com sucesso!");
            clearFields();
        }
        else{
            showAlert("Erro:","Já existe um motorista cadastrado com esta CNH.");
        }
    }

    @FXML protected void backMainPage(){App.changeScene("Tela.fxml");}

    private void clearFields() {
        textFieldUserName.clear();
        textFieldUserAddress.clear();
        textFieldUserCNH.clear();
        textFieldUserSector.clear();
    }

    /** Método por IA */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
