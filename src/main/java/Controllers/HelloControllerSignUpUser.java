package Controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.User;
import modelo.Vehicle;

public class HelloControllerSignUpUser {
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
            System.out.println("Erro: Campos vazios!");
        }

        //User newUser = new User(name, address, cnh, sector);
        Dao<User> dao = new Dao<>(User.class);

        if(dao.buscarPorChave("cnh", cnh) == null){
            User newUser = new User(name, address, cnh, sector);
            dao.insert(newUser);
            System.out.println("Usuario salvo com sucesso!");
        }
        else{
            System.out.println("Erro: usuário já existe!");

        }
    }

    @FXML protected void backMainPage(){
        App.changeScene("Tela.fxml");
    }

}
