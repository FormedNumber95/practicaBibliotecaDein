package es.aketzagonzalez.ctrl;

import java.sql.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoHistoricoPrestamo;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloHistoricoPrestamos;
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

public class PrestamosController {

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
    private Button btnPrestamos;
    
    @FXML
    private TableColumn<ModeloHistoricoPrestamos, String> colCodLibro;

    @FXML
    private TableColumn<ModeloHistoricoPrestamos, String> colDniAlumno;

    @FXML
    private TableColumn<ModeloHistoricoPrestamos, Date> colFechaDevolucion;

    @FXML
    private TableColumn<ModeloHistoricoPrestamos, Date> colFechaPrestamo;

    @FXML
    private TableColumn<ModeloHistoricoPrestamos, Integer> colIdPrestamo;
    
    @FXML
    private TableView<ModeloHistoricoPrestamos> tblHistorico;

    @FXML
    private Menu menAyuda;

    @FXML
    private TextField txtFiltro;
    
    private FilteredList<ModeloHistoricoPrestamos> filtro;
    
    private static ObservableList<ModeloHistoricoPrestamos> listaTodas;

    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblHistorico.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblHistorico.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getCodLibro().contains(txtFiltro.getText()));
    	}
    }

    @FXML
    void aniadirPrestamo(ActionEvent event) {

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
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("libros", bundle);
    }

    @FXML
    void verPrestamos(ActionEvent event) {

    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	btnPrestamos.setDisable(true);
    	colCodLibro.setCellValueFactory(new PropertyValueFactory<>("codLibro"));
    	colDniAlumno.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
    	colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
    	colIdPrestamo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	listaTodas=DaoHistoricoPrestamo.conseguirListaTodos();
    	filtro=new FilteredList<ModeloHistoricoPrestamos>(listaTodas);
    	tblHistorico.setItems(listaTodas);
    }

}
