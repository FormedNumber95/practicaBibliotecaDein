package es.aketzagonzalez.practicaBibliotecaDein;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.utilidad.Navegador;


public class Lanzador extends Application {
    private static Stage stage;

    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
    	Navegador.setStage(s);
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        stage=s;
        stage.setResizable(false);
        try {
			ConexionBBDD db=new ConexionBBDD();
			Navegador.cargarVista("alumnos", bundle);
			stage.show();
		} catch (SQLException e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("Error en la coneion a la base de datos");
			al.showAndWait();
		}
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getStage() {
		return stage;
	}

}
