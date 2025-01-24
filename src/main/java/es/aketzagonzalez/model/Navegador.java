package es.aketzagonzalez.model;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navegador {
    private static Stage stage; // El Stage principal de la aplicación

    public static void setStage(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void cargarVista(String fxml, ResourceBundle bundle) {
        try {
            FXMLLoader loader = new FXMLLoader(Navegador.class.getResource("/fxml/"+fxml + ".fxml"), bundle);
            Scene nuevaEscena = new Scene(loader.load());
            stage.setScene(nuevaEscena); // Cambia la escena
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

