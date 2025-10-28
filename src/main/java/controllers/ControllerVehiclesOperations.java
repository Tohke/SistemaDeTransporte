package controllers;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;
import modelo.VehicleRent;
import modelo.Vehicle;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ControllerVehiclesOperations {

    @FXML private TextField textFieldDriverName;
    @FXML private ChoiceBox<Vehicle> vehiclesDisplay; // Definido o tipo para Vehicle

    private Dao<Vehicle> vehicleDao = new Dao<>(Vehicle.class);
    private Dao<VehicleRent> registroDao = new Dao<>(VehicleRent.class);

    private Set<String> unavailablePlates; // Para saber quais veículos estão em uso


    @FXML
    public void initialize() {
        vehiclesDisplay.setConverter(new StringConverter<Vehicle>() {
            @Override
            public String toString(Vehicle vehicle) {
                if (vehicle == null) return "Selecione um veículo...";

                String status = unavailablePlates.contains(vehicle.getPlaca())
                        ? "Indisponível"
                        : "Disponível";

                return String.format("%s - %s (%s)", vehicle.getPlaca(), vehicle.getModelo(), status);
            }

            @Override
            public Vehicle fromString(String string) {
                return null;
            }
        });

        populateVehicleChoiceBox();
    }


    private void populateVehicleChoiceBox() {
        Document activeFilter = new Document("dataDevolucao", null);
        List<VehicleRent> activeRegistros = registroDao.listVehiles(activeFilter);

        this.unavailablePlates = activeRegistros.stream()
                .map(VehicleRent::getPlacaVeiculo)
                .collect(Collectors.toSet());

        List<Vehicle> allVehicles = vehicleDao.listAll();

        vehiclesDisplay.setItems(FXCollections.observableArrayList(allVehicles));
    }


    @FXML
    protected void takeVehicle() {
        Vehicle selectedVehicle = vehiclesDisplay.getValue();
        String driverName = textFieldDriverName.getText();

        if (selectedVehicle == null) {
            showAlert("Erro de Validação", "Nenhum veículo foi selecionado.");
            return;
        }
        if (driverName == null || driverName.trim().isEmpty()) {
            showAlert("Erro de Validação", "O nome do motorista não pode estar vazio.");
            return;
        }

        if (unavailablePlates.contains(selectedVehicle.getPlaca())) {
            showAlert("Erro de Operação", "Este veículo já foi retirado e não foi devolvido.");
            return;
        }

        try {
            VehicleRent novoRegistro = new VehicleRent(
                    selectedVehicle.getPlaca(),
                    driverName,
                    LocalDateTime.now()
            );

            registroDao.insert(novoRegistro);
            showAlert("Sucesso", "Veículo " + selectedVehicle.getPlaca() + " retirado por " + driverName + ".");

            textFieldDriverName.clear();
            populateVehicleChoiceBox();

        } catch (Exception e) {
            showAlert("Erro de Banco de Dados", "Falha ao registrar a retirada: " + e.getMessage());
        }
    }


    @FXML
    protected void returnVehicle() {
        Vehicle selectedVehicle = vehiclesDisplay.getValue();

        if (selectedVehicle == null) {
            showAlert("Erro de Validação", "Nenhum veículo foi selecionado.");
            return;
        }

        if (!unavailablePlates.contains(selectedVehicle.getPlaca())) {
            showAlert("Erro de Operação", "Este veículo não consta como 'retirado'.");
            return;
        }

        try {
            Document filter = new Document("placaVeiculo", selectedVehicle.getPlaca()).append("dataDevolucao", null);

            VehicleRent registroAtivo = registroDao.searchVehicles(filter);

            if (registroAtivo == null) {
                showAlert("Erro de Inconsistência", "Não foi encontrado um registro de retirada ativo para este veículo.");
                return;
            }

            registroAtivo.setDataDevolucao(LocalDateTime.now());

            registroDao.change("_id", registroAtivo.getId(), registroAtivo);

            showAlert("Sucesso", "Veículo " + selectedVehicle.getPlaca() + " foi devolvido.");

            populateVehicleChoiceBox();

        } catch (Exception e) {
            showAlert("Erro de Banco de Dados", "Falha ao registrar a devolução: " + e.getMessage());
        }
    }


    @FXML
    protected void backVehiclesManegementsPage() {
        App.changeScene("TelaGerenciarVeiculos.fxml");
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