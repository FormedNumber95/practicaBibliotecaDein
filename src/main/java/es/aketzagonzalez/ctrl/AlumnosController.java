package es.aketzagonzalez.ctrl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * The Class AlumnosController.
 * @author Aketza
 * @version 1.0
 */
public class AlumnosController {

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

    /** The btn modificar. */
    @FXML
    private Button btnModificar;

    /** The btn prestamos. */
    @FXML
    private Button btnPrestamos;

    /** The men ayuda. */
    @FXML
    private Menu menAyuda;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The col ap 1. */
    @FXML
    private TableColumn<ModeloAlumno, String> colAp1;

    /** The col ap 2. */
    @FXML
    private TableColumn<ModeloAlumno, String> colAp2;

    /** The col dni. */
    @FXML
    private TableColumn<ModeloAlumno, String> colDni;

    /** The col nombre. */
    @FXML
    private TableColumn<ModeloAlumno, String> colNombre;
    
    /** The tbl alumnos. */
    @FXML
    private TableView<ModeloAlumno> tblAlumnos;
    
    /** The filtro. */
    private FilteredList<ModeloAlumno> filtro;
    
    /** The s. */
    private static Stage s;
    
    /** The lista todas. */
    private static ObservableList<ModeloAlumno> listaTodas;
    
    /** The es aniadir. */
    private static boolean esAniadir;

    /**
     * Accion filtrar, para filtrar al pulsar "enter" sobre el textarea del filtro.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblAlumnos.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblAlumnos.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getNombre().contains(txtFiltro.getText()));
    	}
    }

    /**
     * Aniadir alumno.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void aniadirAlumno(ActionEvent event) {
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
		try {
			Properties connConfig =ConexionBBDD.loadProperties() ;
	        String lang = connConfig.getProperty("language");
	        Locale locale = new Locale.Builder().setLanguage(lang).build();
	        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
			FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/aniadirAlumnos.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setTitle("Nuevo Alumno");
			s.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
        accionFiltrar(event);
        tblAlumnos.refresh();
    }

    /**
     * Modificar alumno seleccionado.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void modificarAlumno(ActionEvent event) {
    	esAniadir=false;
    	if(tblAlumnos.getSelectionModel().getSelectedItem()!=null) {
	    	s=new Stage();
	    	Scene scene;
			try {
				Properties connConfig =ConexionBBDD.loadProperties() ;
		        String lang = connConfig.getProperty("language");
		        Locale locale = new Locale.Builder().setLanguage(lang).build();
		        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
				FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/aniadirAlumnos.fxml"),bundle);
				scene = new Scene(controlador.load());
				s.setTitle("Modificar Alumno");
				s.setScene(scene);
				AniadirAlumnoController controller = controlador.getController();
				controller.getTxtApellido1().setText(tblAlumnos.getSelectionModel().getSelectedItem().getPriemrApellido());
				controller.getTxtApellido2().setText(tblAlumnos.getSelectionModel().getSelectedItem().getSegundoApellido());
				controller.getTxtDni().setText(tblAlumnos.getSelectionModel().getSelectedItem().getDni());
				controller.getTxtDni().setDisable(true);
				controller.getTxtNombre().setText(tblAlumnos.getSelectionModel().getSelectedItem().getNombre());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        s.setResizable(false);
	        s.initOwner(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.getStage());
	        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	        s.showAndWait();
	        accionFiltrar(event);
	        tblAlumnos.refresh();
    	}
    }

    /**
     * Ver alumnos, vacio pero necesario para evitar errores.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void verAlumnos(ActionEvent event) {
    	
    }

    /**
     * Ver quien ha desarrollado la app.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void verAyuda(ActionEvent event) {

    }

    /**
     * Ver devoluciones.
     *
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
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
    	btnAlumnos.setDisable(true);
    	colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	colAp1.setCellValueFactory(new PropertyValueFactory<>("priemrApellido")); // Este nombre tiene un error tipográfico
    	colAp2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
    	listaTodas=DaoAlumno.conseguirListaTodos();
    	filtro = new FilteredList<ModeloAlumno>(listaTodas);
    	tblAlumnos.setItems(listaTodas);
    }
    
    /**
     * Gets the s.
     *
     * @return the s
     * @author Aketza
     */
    public static Stage getS() {
		return s;
	}
    
    /**
     * Checks if is es aniadir.
     *
     * @return true, if is es aniadir
     * @author Aketza
     */
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    /**
     * Sets the lista todas.
     *
     * @param listaTodas the new lista todas
     * @author Aketza
     */
    public static void setListaTodas(ObservableList<ModeloAlumno> listaTodas) {
		AlumnosController.listaTodas = listaTodas;
	}

}
