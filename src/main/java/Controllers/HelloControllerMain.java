package Controllers;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HelloControllerMain {


    //Pop-up de seleção de usuario
    @FXML
    protected void showRegistrationOptions() {

        List<String> choices = new ArrayList<>();
        choices.add("Motorista");
        choices.add("Operador (Admin)"); // Usando "Operador" como no seu PDF.


        ChoiceDialog<String> dialog = new ChoiceDialog<>("Motorista", choices);
        dialog.setTitle("Opções de Cadastro");
        dialog.setHeaderText("Por favor, escolha o tipo de usuário que deseja cadastrar.");
        dialog.setContentText("Cadastrar como:");


        Optional<String> result = dialog.showAndWait();

        result.ifPresent(choice -> {
            if (choice.equals("Motorista")) {
            goCadastrarUsuario();

                App.changeScene("TelaCadastroMotorista.fxml");
            } else if (choice.equals("Operador (Admin)")) {
                System.out.println("Usuário escolheu Operador. Redirecionando...");
                App.changeScene("TelaCadastroOperador.fxml");
            }
        });
    }

    @FXML protected void goCadastrarUsuario(){
        App.changeScene("TelaCadastrarUsuario.fxml");
    }


//    @FXML private void selectedWithdrawals(){
//        App.changeScene("TelaRetiradas.fxml");
//    }
//
//    @FXML private void selectedAddVehicles(){
//        App.changeScene("TelaAdicionarVeiculo.fxml");
//    }

}