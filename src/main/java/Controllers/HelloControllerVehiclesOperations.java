package Controllers;

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

public class HelloControllerVehiclesOperations {

    @FXML private TextField textFieldDriverName;
    @FXML private ChoiceBox<Vehicle> vehiclesDisplay; // Definido o tipo para Vehicle

    private Dao<Vehicle> vehicleDao = new Dao<>(Vehicle.class);
    private Dao<VehicleRent> registroDao = new Dao<>(VehicleRent.class);

    private Set<String> unavailablePlates; // Para saber quais veículos estão em uso

    /**
     * Método especial do JavaFX que é chamado automaticamente quando a tela é carregada.
     * Ideal para inicializar componentes, como o ChoiceBox.
     */
    @FXML
    public void initialize() {
        // Configura como o veículo será exibido no ChoiceBox
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
                // Não é necessário para este caso
                return null;
            }
        });

        // Carrega os veículos no ChoiceBox
        populateVehicleChoiceBox();
    }

    /**
     * Busca os veículos no banco e atualiza a lista no ChoiceBox,
     * identificando os que estão disponíveis ou não.
     */
    private void populateVehicleChoiceBox() {
        // 1. Encontra placas de veículos que estão atualmente em uso (sem data de devolução)
        Document activeFilter = new Document("dataDevolucao", null);
        List<VehicleRent> activeRegistros = registroDao.listVehiles(activeFilter);

        // Cria um conjunto de placas indisponíveis para checagem rápida
        this.unavailablePlates = activeRegistros.stream()
                .map(VehicleRent::getPlacaVeiculo)
                .collect(Collectors.toSet());

        // 2. Busca todos os veículos cadastrados
        List<Vehicle> allVehicles = vehicleDao.listAll();

        // 3. Adiciona os veículos ao ChoiceBox
        vehiclesDisplay.setItems(FXCollections.observableArrayList(allVehicles));
    }

    /**
     * Ação do botão "Retirar".
     * Valida os campos e, se tudo estiver OK, registra uma nova retirada.
     */
    @FXML
    protected void takeVehicle() {
        Vehicle selectedVehicle = vehiclesDisplay.getValue();
        String driverName = textFieldDriverName.getText();

        // --- Validações Básicas ---
        if (selectedVehicle == null) {
            showAlert("Erro de Validação", "Nenhum veículo foi selecionado.");
            return;
        }
        if (driverName == null || driverName.trim().isEmpty()) {
            showAlert("Erro de Validação", "O nome do motorista não pode estar vazio.");
            return;
        }

        // --- Validação de Negócio ---
        if (unavailablePlates.contains(selectedVehicle.getPlaca())) {
            showAlert("Erro de Operação", "Este veículo já foi retirado e não foi devolvido.");
            return;
        }

        // --- Processo de Retirada ---
        try {
            VehicleRent novoRegistro = new VehicleRent(
                    selectedVehicle.getPlaca(),
                    driverName,
                    LocalDateTime.now()
            );

            registroDao.insert(novoRegistro);
            showAlert("Sucesso", "Veículo " + selectedVehicle.getPlaca() + " retirado por " + driverName + ".");

            // Limpa os campos e atualiza a lista
            textFieldDriverName.clear();
            populateVehicleChoiceBox(); // Atualiza o status para "Indisponível"

        } catch (Exception e) {
            showAlert("Erro de Banco de Dados", "Falha ao registrar a retirada: " + e.getMessage());
        }
    }

    /**
     * Ação do botão "Retornar".
     * Valida e, se OK, registra a data de devolução no registro de uso.
     */
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
            // Encontra o registro de uso ATIVO (sem data de devolução) para este veículo
            Document filter = new Document("placaVeiculo", selectedVehicle.getPlaca()).append("dataDevolucao", null);

            VehicleRent registroAtivo = registroDao.searchVehicles(filter);

            if (registroAtivo == null) {
                // Segurança extra, embora o 'unavailablePlates' já deva ter pego isso
                showAlert("Erro de Inconsistência", "Não foi encontrado um registro de retirada ativo para este veículo.");
                return;
            }

            // Atualiza o registro com a data e hora da devolução
            registroAtivo.setDataDevolucao(LocalDateTime.now());

            // Salva a alteração no banco usando o ID do registro
            // O método change() do DAO substitui o documento inteiro
            registroDao.change("_id", registroAtivo.getId(), registroAtivo);

            showAlert("Sucesso", "Veículo " + selectedVehicle.getPlaca() + " foi devolvido.");

            // Atualiza a lista para "Disponível"
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