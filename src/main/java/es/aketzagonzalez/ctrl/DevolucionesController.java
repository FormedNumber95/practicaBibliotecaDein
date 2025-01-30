package es.aketzagonzalez.ctrl;

import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The Class DevolucionesController.
 * @author Aketza
 * @version 1.2
 */
public class DevolucionesController {

    /** The btn alumnos. */
    @FXML
    private Button btnAlumnos;

    /** The btn devoluciones. */
    @FXML
    private Button btnDevoluciones;

    /** The btn devolver. */
    @FXML
    private Button btnDevolver;

    /** The btn informes. */
    @FXML
    private Button btnInformes;

    /** The btn libros. */
    @FXML
    private Button btnLibros;

    /** The btn prestamos. */
    @FXML
    private Button btnPrestamos;
    
    /** The menu item ver desarrolador. */
    @FXML
    private MenuItem menuItemVerDesarrolador;
    
    /** The col cod libro. */
    @FXML
    private TableColumn<ModeloPrestamo, String> colCodLibro;

    /** The col dni alumno. */
    @FXML
    private TableColumn<ModeloPrestamo, String> colDniAlumno;

    /** The col fecha prestamo. */
    @FXML
    private TableColumn<ModeloPrestamo, Date> colFechaPrestamo;

    /** The col id prestamo. */
    @FXML
    private TableColumn<ModeloPrestamo, Integer> colIdPrestamo;
    
    /** The tbl devoluciones. */
    @FXML
    private TableView<ModeloPrestamo> tblDevoluciones;

    /** The men ayuda. */
    @FXML
    private Menu menAyuda;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The menu item ver manual usuario. */
    @FXML
    private MenuItem menuItemVerManualUsuario;
    
    /** The filtro. */
    private FilteredList<ModeloPrestamo> filtro;
    
    /** The s. */
    private static Stage s;
    
    /** The lista todas. */
    private static ObservableList<ModeloPrestamo> listaTodas;

    /**
     * Accion filtrar.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblDevoluciones.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblDevoluciones.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getCodLibro().contains(txtFiltro.getText()));
    	}
    }

    /**
     * Devolver libro.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void devolverLibro(ActionEvent event) {
    	if (tblDevoluciones.getSelectionModel().getSelectedItem()!=null) {
	    	s=new Stage();
	    	Scene scene;
			try {
				Properties connConfig =ConexionBBDD.loadProperties() ;
		        String lang = connConfig.getProperty("language");
		        Locale locale = new Locale.Builder().setLanguage(lang).build();
		        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
				FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/devolverPrestamo.fxml"),bundle);
				scene = new Scene(controlador.load());
				s.setTitle("Devolver libro");
				s.setScene(scene);
				DevolverPrestamoController controller = controlador.getController();
				controller.setTblDevoluciones(tblDevoluciones);
			} catch (IOException e) {
				e.printStackTrace();
			}
	        s.setResizable(false);
	        s.initOwner(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.getStage());
	        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	        s.showAndWait();
	        accionFiltrar(event);
	        tblDevoluciones.setItems(DaoPrestamo.conseguirListaTodos());
	        tblDevoluciones.refresh();
    	}else {
    		Alert al=new Alert(AlertType.ERROR);
    		al.setHeaderText(null);
    		al.setContentText("Para poder devolver un libro primero tienes que seleccionar cual es la devolucion que quieres hacer");
    		al.showAndWait();
    	}
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
     * Ver devoluciones, vacio pero necesario para evitar errores.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verDevoluciones(ActionEvent event) {

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
     * Ver prestamos.
     *
     * @author Aketza
     * @param event the event
     */
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
     * @author Aketza
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
