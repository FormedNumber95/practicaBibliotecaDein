package es.aketzagonzalez.ctrl;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.utilidad.Navegador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * The Class InformesController.
 * @author Aketza
 * @version 1.2
 */
public class InformesController {

    /** The btn alumnos. */
    @FXML
    private Button btnAlumnos;

    /** The btn devoluciones. */
    @FXML
    private Button btnDevoluciones;

    /** The btn graficos. */
    @FXML
    private Button btnGraficos;

    /** The btn informe calculos. */
    @FXML
    private Button btnInformeCalculos;

    /** The btn informes. */
    @FXML
    private Button btnInformes;

    /** The btn libros. */
    @FXML
    private Button btnLibros;
    
    /** The menu item ver manual usuario. */
    @FXML
    private MenuItem menuItemVerManualUsuario;

    /** The btn lista libros. */
    @FXML
    private Button btnListaLibros;

    /** The btn prestamos. */
    @FXML
    private Button btnPrestamos;

    /** The men ayuda. */
    @FXML
    private Menu menAyuda;
    
    /** The menu item ver desarrolador. */
    @FXML
    private MenuItem menuItemVerDesarrolador;

    /**
     * Generar informe calculos.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void generarInformeCalculos(ActionEvent event) {
    	try {
    		ConexionBBDD db=new ConexionBBDD();
			InputStream reportStream =getClass().getResourceAsStream("/jasper/reportCalculados.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("IMAGE_PATH", db.getClass().getResource("/imagenes/").toString());
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters,  ConexionBBDD.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
    	} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    /**
     * Generar lista libros.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void generarListaLibros(ActionEvent event) {
    	try {
    		ConexionBBDD db=new ConexionBBDD();
			InputStream reportStream =getClass().getResourceAsStream("/jasper/listaLibros.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Resource_Path", db.getClass().getResource("/jasper/").toString());
            parameters.put("IMAGE_PATH", db.getClass().getResource("/imagenes/").toString());
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters,  ConexionBBDD.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
    	} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }

    /**
     * Mostar graficos.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void mostarGraficos(ActionEvent event) {
    	try {
    		ConexionBBDD db=new ConexionBBDD();
			InputStream reportStream =getClass().getResourceAsStream("/jasper/reportGraficos.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("Resource_Path", db.getClass().getResource("/jasper/").toString());
            parameters.put("IMAGE_PATH", db.getClass().getResource("/imagenes/").toString());
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters,  ConexionBBDD.getConnection());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
    	} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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
     * Ver informes, vacio pero necesario para evitar errores.
     *
     * @author Aketza
     * @param event the event
     */
    @FXML
    void verInformes(ActionEvent event) {

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
    	btnInformes.setDisable(true);
    }

}
