package es.aketzagonzalez.ctrl;

import java.sql.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoPrestamo;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloPrestamo;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DevolucionesController {

    @FXML
    private Button btnAlumnos;

    @FXML
    private Button btnDevoluciones;

    @FXML
    private Button btnDevolver;

    @FXML
    private Button btnInformes;

    @FXML
    private Button btnLibros;

    @FXML
    private Button btnPrestamos;
    
    @FXML
    private TableColumn<ModeloPrestamo, String> colCodLibro;

    @FXML
    private TableColumn<ModeloPrestamo, String> colDniAlumno;

    @FXML
    private TableColumn<ModeloPrestamo, Date> colFechaPrestamo;

    @FXML
    private TableColumn<ModeloPrestamo, Integer> colIdPrestamo;
    
    @FXML
    private TableView<ModeloPrestamo> tblDevoluciones;

    @FXML
    private Menu menAyuda;

    @FXML
    private TextField txtFiltro;
    
    private FilteredList<ModeloPrestamo> filtro;
    
    private Stage s;
    
    private static ObservableList<ModeloPrestamo> listaTodas;

    @FXML
    void accionFiltrar(ActionEvent event) {

    }

    @FXML
    void devolverLibro(ActionEvent event) {

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
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("libros", bundle);
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
    	btnDevoluciones.setDisable(true);
    	colCodLibro.setCellValueFactory(new PropertyValueFactory<>("codLibro"));
    	colDniAlumno.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
    	colIdPrestamo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	listaTodas=DaoPrestamo.conseguirListaTodos();
    	filtro = new FilteredList<ModeloPrestamo>(listaTodas);
    	tblDevoluciones.setItems(listaTodas);
    }

}
