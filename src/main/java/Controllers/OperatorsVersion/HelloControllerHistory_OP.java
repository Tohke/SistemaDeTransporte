package Controllers.OperatorsVersion;

import com.st.sistemadetransporte.App;
import dao.Dao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.VehicleRent;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class HelloControllerHistory_OP {

    @FXML private TextField textFieldPlaca;
    @FXML private TableView<VehicleRent> tableViewHistorico;
    @FXML private TableColumn<VehicleRent, String> colunaMotorista;
    @FXML private TableColumn<VehicleRent, LocalDateTime> colunaRetirada;
    @FXML private TableColumn<VehicleRent, LocalDateTime> colunaDevolucao;

    private Dao<VehicleRent> registroDao = new Dao<>(VehicleRent.class);

    @FXML
    public void initialize() {

        colunaMotorista.setCellValueFactory(new PropertyValueFactory<>("nomeMotorista"));
        colunaRetirada.setCellValueFactory(new PropertyValueFactory<>("dataRetirada"));
        colunaDevolucao.setCellValueFactory(new PropertyValueFactory<>("dataDevolucao"));
    }


    @FXML
    private void buscarHistorico() {
        String placa = textFieldPlaca.getText().trim().toUpperCase();

        if (placa.isEmpty()) {
            showAlert("Erro", "Por favor, digite uma placa para buscar.");
            return;
        }

        Document filter = new Document("placaVeiculo", placa);

        List<VehicleRent> registros = registroDao.listVehiles(filter);

        if (registros.isEmpty()) {
            showAlert("Informação", "Nenhum registro encontrado para a placa: " + placa);
            tableViewHistorico.setItems(FXCollections.observableArrayList()); // Limpa a tabela
            return;
        }


        registros.sort(Comparator.comparing(VehicleRent::getDataRetirada));

        tableViewHistorico.setItems(FXCollections.observableArrayList(registros));
    }


    @FXML private void backOperatorsPage() {App.changeScene("TelaPrincipalOperador.fxml");}


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}