package com.st.sistemadetransporte;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


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

    /**
     *  Método estático para trocar a cena (tela) da janela principal.
     * Pode ser chamado de qualquer controller.
     * @param fxmlFile O nome do arquivo FXML da nova tela (ex: "TelaRetiradas.fxml").
     */

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
            if (fxmlFile.equals("Tela.fxml")) {
                mainStage.setTitle("Tela Principal");
            } else if (fxmlFile.equals("TelaRetiradas.fxml")) {
                mainStage.setTitle("Tela de Retiradas");
            } else if(fxmlFile.equals("TelaAdicionarVeiculo.fxml")){
                mainStage.setTitle("Tela de Adicionar Veiculos");
            } else if(fxmlFile.equals("TelaCadastrarUsuario.fxml")){
                mainStage.setTitle("Tela de Cadastro de Usuário");
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