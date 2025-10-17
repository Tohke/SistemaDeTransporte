package Controllers;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import modelo.Vehicle;
import dao.Dao;

public class HelloControllerRegisterVehicle {

//    @FXML private ChoiceBox choiceBox;
//    @FXML private ChoiceBox choiceBox2;

    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;

    @FXML protected void saveVehicle(){
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        String placa = textFieldPlaca.getText();

        if(placa.isEmpty() || marca.isEmpty()){
            System.out.println("Erro: Placa e Marca são obrigatórios.");
            return;
        }

        Dao<Vehicle> dao = new Dao<>(Vehicle.class);

        if(dao.buscarPorChave("placa", placa) == null){
            Vehicle newVehicle = new Vehicle(placa, marca, modelo);
            dao.insert(newVehicle);
            System.out.println("Veículo salvo com sucesso!");
        }
        else{
            System.out.println("Erro: Veículo com a placa " + placa + " já existe.");
        }
    }

    @FXML protected void backMainPage(){
        App.changeScene("Tela.fxml");
    }
}
