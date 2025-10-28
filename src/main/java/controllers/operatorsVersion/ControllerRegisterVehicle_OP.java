package controllers.operatorsVersion;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.Vehicle;


public class ControllerRegisterVehicle_OP {


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

    @FXML private void backOperatorsPage() {App.changeScene("TelaPrincipalOperador.fxml");}

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
