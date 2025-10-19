package Controllers;

import com.st.sistemadetransporte.App;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import modelo.Vehicle;
import dao.Dao;


public class HelloControllerRegisterVehicle {


    @FXML private TextField textFieldMarca;
    @FXML private TextField textFieldModelo;
    @FXML private TextField textFieldPlaca;

    @FXML protected void saveVehicle(){
        String marca = textFieldMarca.getText();
        String modelo = textFieldModelo.getText();
        String placa = textFieldPlaca.getText();

        if (placa.isEmpty() || marca.isEmpty()) {
            showAlert("Erro de Validação", "Placa e Marca são obrigatórios.");
            return;
        }

        Dao<Vehicle> dao = new Dao<>(Vehicle.class);

        if (dao.buscarPorChave("placa", placa) == null) {
            Vehicle newVehicle = new Vehicle(placa, marca, modelo);
            dao.insert(newVehicle);

            showAlert("Sucesso", "Veículo salvo com sucesso!");
            clearFields();
        }
        else {
            showAlert("Erro de Cadastro", "Já existe um veículo com esta placa.");
        }
    }

    @FXML protected void backMainPage(){
        App.changeScene("Tela.fxml");
    }

    private void clearFields() {
        textFieldMarca.clear();
        textFieldModelo.clear();
        textFieldPlaca.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
