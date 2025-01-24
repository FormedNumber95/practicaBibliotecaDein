package es.aketzagonzalez.ctrl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.practicaBibliotecaDein.Lanzador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AlumnosController {

    @FXML
    private Button btnAlumnos;

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnDevoluciones;

    @FXML
    private Button btnInformes;

    @FXML
    private Button btnLibros;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnPrestamos;

    @FXML
    private Menu menAyuda;

    @FXML
    private TextField txtFiltro;
    
    private static Stage s;

    @FXML
    void accionFiltrar(ActionEvent event) {

    }

    @FXML
    void aniadirAlumno(ActionEvent event) {

    }

    @FXML
    void modificarAlumno(ActionEvent event) {

    }

    @FXML
    void verAlumnos(ActionEvent event) {
    	
    }

    @FXML
    void verAyuda(ActionEvent event) {

    }

    @FXML
    void verDevoluciones(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/devoluciones.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(Lanzador.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    @FXML
    void verInformes(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/informes.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(Lanzador.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    @FXML
    void verLibros(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/libros.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(Lanzador.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }

    @FXML
    void verPrestamos(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Node source = (Node) event.getSource();
	    Stage stage = (Stage) source.getScene().getWindow();
	    stage.close();
        s=new Stage();
    	Scene scene;
    	try {
			 FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/prestamos.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setScene(scene);
    	} catch (IOException e) {
			e.printStackTrace();
		}
    	s.setResizable(false);
	     s.initOwner(Lanzador.getStage());
	     s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	     s.showAndWait();
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	btnAlumnos.setDisable(true);
    }

}
