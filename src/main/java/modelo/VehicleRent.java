package modelo;

import org.bson.types.ObjectId;
import java.time.LocalDateTime;

public class VehicleRent {

    private ObjectId id;
    private String placaVeiculo;
    private String nomeMotorista;
    private LocalDateTime dataRetirada;
    private LocalDateTime dataDevolucao;

    // Construtor vazio (obrigatório para o DAO)
    public VehicleRent() {}

    // Construtor para facilitar a criação de novos registros
    public VehicleRent(String placaVeiculo, String nomeMotorista, LocalDateTime dataRetirada) {
        this.placaVeiculo = placaVeiculo;
        this.nomeMotorista = nomeMotorista;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = null; // Começa como nulo
    }

    // --- Getters e Setters ---

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        this.nomeMotorista = nomeMotorista;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}