package es.aketzagonzalez.ctrl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloLibro;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * The Class LibrosController.
 * @author Aketza
 * @version 1.2
 */
public class LibrosController {

    /** The btn alumnos. */
    @FXML
    private Button btnAlumnos;

    /** The btn aniadir. */
    @FXML
    private Button btnAniadir;

    /** The btn baja. */
    @FXML
    private Button btnBaja;

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
    
    /** The col autor. */
    @FXML
    private TableColumn<ModeloLibro, String> colAutor;

    /** The col baja. */
    @FXML
    private TableColumn<ModeloLibro, Boolean> colBaja;

    /** The col cod. */
    @FXML
    private TableColumn<ModeloLibro, Integer> colCod;

    /** The col editorial. */
    @FXML
    private TableColumn<ModeloLibro, String> colEditorial;

    /** The col estado. */
    @FXML
    private TableColumn<ModeloLibro, String> colEstado;

    /** The col titulo. */
    @FXML
    private TableColumn<ModeloLibro, String> colTitulo;
    
    /** The col portada. */
    @FXML
    private TableColumn<ModeloLibro, ImageView> colPortada;
    
    /** The tbl libros. */
    @FXML
    private TableView<ModeloLibro> tblLibros;

    /** The men ayuda. */
    @FXML
    private Menu menAyuda;
    
    /** The menu item ver manual usuario. */
    @FXML
    private MenuItem menuItemVerManualUsuario;

    /** The txt filtro. */
    @FXML
    private TextField txtFiltro;
    
    /** The menu item ver desarrolador. */
    @FXML
    private MenuItem menuItemVerDesarrolador;
    
    /** The filtro. */
    private FilteredList<ModeloLibro> filtro;
    
    /** The s. */
    private static Stage s;
    
    /** The lista todas. */
    private static ObservableList<ModeloLibro> listaTodas;
    
    /** The es aniadir. */
    private static boolean esAniadir;

    /**
     * Accion filtrar.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void accionFiltrar(ActionEvent event) {
    	tblLibros.setItems(filtro);
    	if(txtFiltro.getText().isEmpty()){
    		tblLibros.setItems(listaTodas);
    	}else {
    		filtro.setPredicate(observacion -> observacion.getTitulo().contains(txtFiltro.getText()));
    	}
    }

    /**
     * Aniadir libro.
     *
     * @author Aketza
     * @param event the event
     */
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

    /**
     * Dar de baja.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void darDeBaja(ActionEvent event) {
    	if(tblLibros.getSelectionModel().getSelectedItem()!=null) {
    		ModeloLibro l=tblLibros.getSelectionModel().getSelectedItem();
    		l.setBaja(new SimpleBooleanProperty(true));
    		DaoLibro.modificar(l.getTitulo(), l.getAutor(), l.getEditorial(), l.getEstado(), 1, l.getCodigo(),l.getFotoStream());
    		accionFiltrar(event);
    		tblLibros.setItems(DaoLibro.conseguirListaTodosNoBaja());
	        tblLibros.refresh();
    	}
    }

    /**
     * Modificar libro.
     *
     * @author Aketza
     * @param event the event
     */
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
     * Ver libros, vacio pero necesario para evitar errores.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verLibros(ActionEvent event) {

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
    	btnLibros.setDisable(true);
    	colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
    	colBaja.setCellValueFactory(cellData -> cellData.getValue().getBaja());
    	colBaja.setCellFactory(CheckBoxTableCell.forTableColumn(colBaja));
    	colCod.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    	colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
    	colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    	colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
    	colPortada.setCellValueFactory(cellData -> 
        	new SimpleObjectProperty<>(convertirBytesAImageView(cellData.getValue().getFotoStream())));
    	listaTodas=DaoLibro.conseguirListaTodosNoBaja();
    	filtro = new FilteredList<ModeloLibro>(listaTodas);
    	tblLibros.setItems(listaTodas);
    }
    
    /**
     * Gets the imagen input stream.
     *
     * @author Aketza
     * @param url the url
     * @return the imagen input stream
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public static InputStream getImagenInputStream(URL url) throws IOException {
        if (url == null) {
            throw new IllegalArgumentException("La URL no puede ser nula.");
        }
        return url.openStream();
    }
    
    /**
     * Convertir bytes A image view.
     *
     * @author Aketza
     * @param fotoStream the foto stream
     * @return the image view
     */
    private ImageView convertirBytesAImageView(InputStream fotoStream) {
        if (fotoStream == null) {
            return null;
        }
        Image imagen = new Image(fotoStream);
        ImageView imageView = new ImageView(imagen);
        imageView.setFitWidth(50);  // Ajusta el tamaño según sea necesario
        imageView.setPreserveRatio(true);
        return imageView;
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
    
    /**
     * Checks if is es aniadir.
     *
     * @author Aketza
     * @return true, if is es aniadir
     */
    public static boolean isEsAniadir() {
		return esAniadir;
	}
    
    /**
     * Sets the lista todas.
     *
     * @author Aketza
     * @param listaTodas the new lista todas
     */
    public static void setListaTodas(ObservableList<ModeloLibro> listaTodas) {
		LibrosController.listaTodas = listaTodas;
	}

}
