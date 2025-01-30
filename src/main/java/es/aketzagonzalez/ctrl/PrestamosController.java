package es.aketzagonzalez.ctrl;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoHistoricoPrestamo;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloHistoricoPrestamos;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The Class PrestamosController.
 * @author Aketza
 * @version 1.2
 */
public class PrestamosController {

    /** The btn alumnos. */
    @FXML
    private Button btnAlumnos;

    /** The btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** The btn devoluciones. */
    @FXML
    private Button btnDevoluciones;

    /** The btn informes. */
    @FXML
    private Button btnInformes;

    /** The btn libros. */
    @FXML
    private Button btnLibros;

    /** The btn prestamos. */
    @FXML
    private Button btnPrestamos;
    
    /** The col cod libro. */
    @FXML
    private TableColumn<ModeloHistoricoPrestamos, String> colCodLibro;

    /** The col dni alumno. */
    @FXML
    private TableColumn<ModeloHistoricoPrestamos, String> colDniAlumno;

    /** The col fecha devolucion. */
    @FXML
    private TableColumn<ModeloHistoricoPrestamos, Date> colFechaDevolucion;

    /** The col fecha prestamo. */
    @FXML
    private TableColumn<ModeloHistoricoPrestamos, Date> colFechaPrestamo;
    
    /** The menu item ver manual usuario. */
    @FXML
    private MenuItem menuItemVerManualUsuario;

    /** The col id prestamo. */
    @FXML
    private TableColumn<ModeloHistoricoPrestamos, Integer> colIdPrestamo;
    
    /** The tbl historico. */
    @FXML
    private TableView<ModeloHistoricoPrestamos> tblHistorico;

    /** The grupo devueltos. */
    @FXML
    private ToggleGroup grupoDevueltos;
    
    /** The men ayuda. */
    @FXML
    private Menu menAyuda;
    
    /** The rad devueltos. */
    @FXML
    private RadioButton radDevueltos;

    /** The rad pendientes. */
    @FXML
    private RadioButton radPendientes;

    /** The rad todos. */
    @FXML
    private RadioButton radTodos;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The txt filtro dni. */
    @FXML
    private TextField txtFiltroDni;
    
    /** The menu item ver desarrolador. */
    @FXML
    private MenuItem menuItemVerDesarrolador;
    
    /** The filtro. */
    private FilteredList<ModeloHistoricoPrestamos> filtro;
    
    /** The s. */
    private static Stage s;
    
    /** The lista todas. */
    private static ObservableList<ModeloHistoricoPrestamos> listaTodas;

    /**
     * Accion filtrar.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
        tblHistorico.setItems(filtro);
        
        if (txtFiltro.getText().isEmpty() && txtFiltroDni.getText().isEmpty()) {
            tblHistorico.setItems(listaTodas);
        } else {
            String filtroCodLibro = txtFiltro.getText().trim();
            String filtroDni = txtFiltroDni.getText().trim();
            filtro.setPredicate(observacion -> {
                boolean coincideCodLibro = filtroCodLibro.isEmpty() || observacion.getCodLibro().contains(filtroCodLibro);
                boolean coincideDni = filtroDni.isEmpty() || observacion.getDni().contains(filtroDni);
                return coincideCodLibro && coincideDni;
            });
        }
    }


    /**
     * Aniadir prestamo.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void aniadirPrestamo(ActionEvent event) {
    	s=new Stage();
    	Scene scene;
		try {
			Properties connConfig =ConexionBBDD.loadProperties() ;
	        String lang = connConfig.getProperty("language");
	        Locale locale = new Locale.Builder().setLanguage(lang).build();
	        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
			FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/aniadirPrestamo.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setTitle("Nuevo Prestamo");
			s.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
        accionFiltrar(event);
        tblHistorico.refresh();
        tblHistorico.setItems(DaoHistoricoPrestamo.conseguirListaTodos());
    }

    /**
     * Ver alumnos.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verAlumnos(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("alumnos", bundle);
    }

    /**
     * Ver quien ha desarrollado la app.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verDesarrollador(ActionEvent event) {
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	al.setContentText("Desarrollador: Aketza González Rey");
    	al.showAndWait();
    }
    
    /**
     * Ver manual usuario.
     *
     * @param event the event
     */
    @FXML
    void verManualUsuario(ActionEvent event) {
    	WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Obtener la URL del archivo HTML desde resources
        URL resource = getClass().getResource("/html/manualUsuario.html");
        
        if (resource != null) {
            // Cargar el archivo HTML en el WebView
            webEngine.load(resource.toExternalForm());
        } else {
        	Alert al=new Alert(AlertType.ERROR);
        	al.setHeaderText(null);
        	al.setContentText("No se pudo encontrar el archivo HTML.");
        	al.showAndWait();
        }
    }

    /**
     * Ver devoluciones.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verDevoluciones(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("devoluciones", bundle);
    }

    /**
     * Ver informes.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verInformes(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("informes", bundle);
    }

    /**
     * Ver libros.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verLibros(ActionEvent event) {
    	Properties connConfig =ConexionBBDD.loadProperties() ;
        String lang = connConfig.getProperty("language");
        Locale locale = new Locale.Builder().setLanguage(lang).build();
        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
        Navegador.cargarVista("libros", bundle);
    }

    /**
     * Ver prestamos, vacio pero necesario para evitar errores.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verPrestamos(ActionEvent event) {

    }
    
    /**
     * Ver devueltos.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verDevueltos(ActionEvent event) {
    	listaTodas=DaoHistoricoPrestamo.conseguirListaDevueltos();
    	filtro=new FilteredList<ModeloHistoricoPrestamos>(listaTodas);
    	tblHistorico.setItems(listaTodas);
    	accionFiltrar(event);
    	tblHistorico.refresh();
    }
    
    /**
     * Ver pendientes.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verPendientes(ActionEvent event) {
    	listaTodas=DaoHistoricoPrestamo.conseguirListaNoDevueltos();
    	filtro=new FilteredList<ModeloHistoricoPrestamos>(listaTodas);
    	tblHistorico.setItems(listaTodas);
    	accionFiltrar(event);
    	tblHistorico.refresh();
    }
    
    /**
     * Ver todos.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verTodos(ActionEvent event) {
    	listaTodas=DaoHistoricoPrestamo.conseguirListaTodos();
    	filtro=new FilteredList<ModeloHistoricoPrestamos>(listaTodas);
    	tblHistorico.setItems(listaTodas);
    	accionFiltrar(event);
    	tblHistorico.refresh();
    }
    
    /**
     * Initialize.
     * @author Aketza
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
    
    /**
     * Gets the s.
     *
     * @author Aketza
     * @return the s
     */
    public static Stage getS() {
		return s;
	}

}
