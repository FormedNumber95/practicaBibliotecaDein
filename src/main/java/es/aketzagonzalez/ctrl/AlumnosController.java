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
    
    @FXML
    private TableColumn<ModeloAlumno, String> colAp1;

    @FXML
    private TableColumn<ModeloAlumno, String> colAp2;

    @FXML
    private TableColumn<ModeloAlumno, String> colDni;

    @FXML
    private TableColumn<ModeloAlumno, String> colNombre;
    
    @FXML
    private TableView<ModeloAlumno> tblAlumnos;
    
    private FilteredList<ModeloAlumno> filtro;
    
    private static Stage s;
    
    private static ObservableList<ModeloAlumno> listaTodas;
    
    private static boolean esAniadir;

    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblAlumnos.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblAlumnos.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getNombre().contains(txtFiltro.getText()));
    	}
    }

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
			AniadirAlumnoController controller = controlador.getController();
			
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
    	btnAlumnos.setDisable(true);
    	colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
    	colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	colAp1.setCellValueFactory(new PropertyValueFactory<>("priemrApellido")); // Este nombre tiene un error tipográfico
    	colAp2.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
    	listaTodas=DaoAlumno.conseguirListaTodos();
    	filtro = new FilteredList<ModeloAlumno>(listaTodas);
    	tblAlumnos.setItems(listaTodas);
    }
    
    public static Stage getS() {
		return s;
	}
    
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    public static void setListaTodas(ObservableList<ModeloAlumno> listaTodas) {
		AlumnosController.listaTodas = listaTodas;
	}

}
