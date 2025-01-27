package es.aketzagonzalez.ctrl;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloLibro;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    
    private static Stage s;
    
    private static ObservableList<ModeloLibro> listaTodas;
    
    private static boolean esAniadir;

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
    	esAniadir=true;
    	s=new Stage();
    	Scene scene;
		try {
			Properties connConfig =ConexionBBDD.loadProperties() ;
	        String lang = connConfig.getProperty("language");
	        Locale locale = new Locale.Builder().setLanguage(lang).build();
	        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
			FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/aniadirLibro.fxml"),bundle);
			scene = new Scene(controlador.load());
			s.setTitle("Nuevo Libro");
			s.setScene(scene);
			AniadirLibroController controller = controlador.getController();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        s.setResizable(false);
        s.initOwner(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.getStage());
        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
        s.showAndWait();
        accionFiltrar(event);
        tblLibros.refresh();
    }

    @FXML
    void darDeBaja(ActionEvent event) {
    	if(tblLibros.getSelectionModel().getSelectedItem()!=null) {
    		ModeloLibro l=tblLibros.getSelectionModel().getSelectedItem();
    		l.setBaja(new SimpleBooleanProperty(true));
    		DaoLibro.modificar(l.getTitulo(), l.getAutor(), l.getEditorial(), l.getEstado(), 1, l.getCodigo());
    		accionFiltrar(event);
	        tblLibros.refresh();
    	}
    }

    @FXML
    void modificarLibro(ActionEvent event) {
    	esAniadir=false;
    	if(tblLibros.getSelectionModel().getSelectedItem()!=null) {
	    	s=new Stage();
	    	Scene scene;
			try {
				Properties connConfig =ConexionBBDD.loadProperties() ;
		        String lang = connConfig.getProperty("language");
		        Locale locale = new Locale.Builder().setLanguage(lang).build();
		        ResourceBundle bundle = ResourceBundle.getBundle("idiomas/lang", locale);
				FXMLLoader controlador = new FXMLLoader(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.class.getResource("/fxml/aniadirLibro.fxml"),bundle);
				scene = new Scene(controlador.load());
				s.setTitle("Modificar Libro");
				s.setScene(scene);
				AniadirLibroController controller = controlador.getController();
				ModeloLibro l=tblLibros.getSelectionModel().getSelectedItem();
				controller.setCodigo(l.getCodigo());
				controller.getChkBaja().setSelected(l.getBaja().get());
				controller.getTxtAutor().setText(l.getAutor());
				controller.getTxtEditorial().setText(l.getEditorial());
				controller.getTxtTitulo().setText(l.getTitulo());
			} catch (IOException e) {
				e.printStackTrace();
			}
	        s.setResizable(false);
	        s.initOwner(es.aketzagonzalez.practicaBibliotecaDein.Lanzador.getStage());
	        s.initModality(javafx.stage.Modality.WINDOW_MODAL);
	        s.showAndWait();
	        accionFiltrar(event);
	        tblLibros.refresh();
    	}
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
    	colBaja.setCellValueFactory(cellData -> cellData.getValue().getBaja());
    	colBaja.setCellFactory(CheckBoxTableCell.forTableColumn(colBaja));
    	colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
    	colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    	colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	listaTodas=DaoLibro.conseguirListaTodosNoBaja();
    	filtro = new FilteredList<ModeloLibro>(listaTodas);
    	tblLibros.setItems(listaTodas);
    }
    
    public static Stage getS() {
		return s;
	}
    
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    public static void setListaTodas(ObservableList<ModeloLibro> listaTodas) {
		LibrosController.listaTodas = listaTodas;
	}

}
