package es.aketzagonzalez.ctrl;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

public class LibrosController {

    @FXML
    private Button btnAlumnos;

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnBaja;

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

    @FXML
    void accionFiltrar(ActionEvent event) {

    }

    @FXML
    void aniadirLibro(ActionEvent event) {

    }

    @FXML
    void darDeBaja(ActionEvent event) {

    }

    @FXML
    void modificarLibro(ActionEvent event) {

    }

    @FXML
    void verAlumnos(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("alumnos", bundle);
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
        Navegador.cargarVista("devoluciones", bundle);
    }

    @FXML
    void verInformes(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("informes", bundle);
    }

    @FXML
    void verLibros(ActionEvent event) {

    }

    @FXML
    void verPrestamos(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("prestamos", bundle);
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	btnLibros.setDisable(true);
    }

}
