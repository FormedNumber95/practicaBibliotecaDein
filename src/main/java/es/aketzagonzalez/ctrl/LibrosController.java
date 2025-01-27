package es.aketzagonzalez.ctrl;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloLibro;
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
    private TableColumn<ModeloLibro, String> colAutor;

    @FXML
    private TableColumn<ModeloLibro, Boolean> colBaja;

    @FXML
    private TableColumn<ModeloLibro, Integer> colCod;

    @FXML
    private TableColumn<ModeloLibro, String> colEditorial;

    @FXML
    private TableColumn<ModeloLibro, String> colEstado;

    @FXML
    private TableColumn<ModeloLibro, String> colTitulo;
    
    @FXML
    private TableView<ModeloLibro> tblLibros;

    @FXML
    private Menu menAyuda;

    @FXML
    private TextField txtFiltro;
    
    private FilteredList<ModeloLibro> filtro;
    
    private static ObservableList<ModeloLibro> listaTodas;

    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblLibros.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblLibros.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getTitulo().contains(txtFiltro.getText()));
    	}
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
    	colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
    	colBaja.setCellValueFactory(new PropertyValueFactory<>("baja"));
    	colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
    	colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    	colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	listaTodas=DaoLibro.conseguirListaTodos();
    	filtro = new FilteredList<ModeloLibro>(listaTodas);
    	tblLibros.setItems(listaTodas);
    }

}
