package es.aketzagonzalez.ctrl;

import java.io.InputStream;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * The Class InformesController.
 * @author Aketza
 * @version 1.1
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
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
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
     * @param event the event
     * @author Aketza
     */
    @FXML
    void verDesarrollador(ActionEvent event) {
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	al.setContentText("Desarrollador: Aketza González Rey");
    	al.showAndWait();
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
     * Ver informes, vacio pero necesario para evitar errores.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void verInformes(ActionEvent event) {

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
    	btnInformes.setDisable(true);
    }

}
