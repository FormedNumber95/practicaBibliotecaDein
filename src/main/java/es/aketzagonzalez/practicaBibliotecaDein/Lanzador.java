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


public class Lanzador extends Application {
    private static Stage stage;

    @Override
    public void start(@SuppressWarnings("exports") Stage s) throws IOException {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        stage=s;
        stage.setResizable(false);
        try {
			ConexionBBDD db=new ConexionBBDD();
			setRoot("devolverPrestamo","ALUMNOS",bundle);
		} catch (SQLException e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("Error en la coneion a la base de datos");
			al.showAndWait();
		}
    }

    static void setRoot(String fxml,ResourceBundle bundle) throws IOException {
        setRoot(fxml,stage.getTitle(),bundle);
    }

    static void setRoot(String fxml, String title,ResourceBundle bundle) throws IOException {
        Scene scene = new Scene(loadFXML(fxml,bundle));
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml,ResourceBundle bundle) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Lanzador.class.getResource("/fxml/"+fxml + ".fxml"),bundle);
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
