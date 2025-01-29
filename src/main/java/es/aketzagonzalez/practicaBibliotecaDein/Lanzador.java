package es.aketzagonzalez.practicaBibliotecaDein;

import javafx.application.Application;
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

/**
 * The Class Lanzador.
 * @author Aketza
 * @version 1.0
 */
public class Lanzador extends Application {
    
    /** The stage. */
    private static Stage stage;

    /**
     * Start.
     *
     * @param s the s
     * @throws IOException Signals that an I/O exception has occurred.
     * @author Aketza
     */
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
			new ConexionBBDD();
			Navegador.cargarVista("alumnos", bundle);
			stage.show();
		} catch (SQLException e) {
			Alert al=new Alert(AlertType.ERROR);
			al.setHeaderText(null);
			al.setContentText("Error en la coneion a la base de datos");
			al.showAndWait();
		}
    }

    /**
     * The main method.
     *
     * @param args the arguments
     * @author Aketza
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /**
     * Gets the stage.
     *
     * @return the stage
     * @author Aketza
     */
    public static Stage getStage() {
		return stage;
	}

}
