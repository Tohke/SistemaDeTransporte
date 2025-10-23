package com.st.sistemadetransporte;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Falta arrumar o cadastro de operador FEITO
 * Criar uma tela inicial para escolher as operações do operador FEITO
 * erro no login de operador -> falta a tela geral do operador FEITO
 *<p>
 * Fazer o registro
 * mensagem de confirmar cadastro de operador
 *<p>
 * erro de segurança: transformar o cadastro de operador apenas possível na tela principal de um operador já logado
 * erro de logica: voltar sempre leva para a tela de login/cadastro
 *<p>
 * Motorista:
 * CNH 1234-56
 *<p>
 * Operador:
 * Nome: Joao Francisco
 * Login: joao_f
 * senha: jfps05
 *<p>
 * Carro:
 * Marca: Hyundai
 * Modelo: Creta
 * Placa: ABCD-01
 * <p>
 * */

public class App extends Application {

    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws IOException {

        mainStage = primaryStage;


        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Tela.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        mainStage.setTitle("Tela Principal");
        mainStage.setScene(scene);
        mainStage.show();
    }



    //Método feito por IA
    public static void changeScene(String fxmlFile) {
        try {
            // Carrega o novo FXML.
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(App.class.getResource(fxmlFile)));
            // Cria uma nova cena com o conteúdo carregado.
            Scene newScene = new Scene(newRoot);
            // Define a nova cena na janela principal, trocando a tela.
            mainStage.setScene(newScene);

            // Opcional: Atualiza o título da janela com base na tela carregada.
            switch (fxmlFile) {
                case "Tela.fxml":
                    mainStage.setTitle("Tela Principal");
                    break;
                case "TelaRetiradas.fxml":
                    mainStage.setTitle("Tela de Retiradas");
                    break;
                case "TelaAdicionarVeiculo.fxml":
                    mainStage.setTitle("Tela de Adicionar Veiculos");
                    break;
                case "TelaCadastrarUsuario.fxml":
                    mainStage.setTitle("Tela de Cadastro de Usuário");
                    break;
                case "TelaLoginOperador.fxml":
                    mainStage.setTitle("Login do Operador");
                    break;
                case "TelaGerenciarVeiculos.fxml":
                    mainStage.setTitle("Gerenciamento da Frota");
                    break;
                case "TelaHistorico.fxml":
                    mainStage.setTitle("Histórico de Uso de Veículos");
                    break;
            }

        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo FXML: " + fxmlFile);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
//        DaoTest dt = new DaoTest();
//        dt.testInserir();
//        dt.testListar();
    }
}