package Controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Operators;

public class HelloControllerSignUpOperator {


    @FXML private TextField textFieldOperatorName;
    @FXML private TextField textFieldOperatorPassword;
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

        if(dao.buscarPorChave("_id", id) == null){
            Operators newOperators = new Operators(id, name, login, password);
            dao.insert(newOperators);
            System.out.println("Operador salvo com sucesso!");
        }
        else{
            System.out.println("Erro: Operador incorreto!");
        }
    }

    @FXML protected void backMainPage(){
        App.changeScene("Tela.fxml");
    }
}
