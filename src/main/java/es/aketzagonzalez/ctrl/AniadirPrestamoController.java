package es.aketzagonzalez.ctrl;

import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoHistoricoPrestamo;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.dao.DaoPrestamo;
import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloLibro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.view.JasperViewer;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class AniadirPrestamoController.
 * @author Aketza
 * @version 1.0
 */
public class AniadirPrestamoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb alumno. */
    @FXML
    private ComboBox<ModeloAlumno> cmbAlumno;

    /** The cmb libro. */
    @FXML
    private ComboBox<ModeloLibro> cmbLibro;

    /**
     * Accion cancelar.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	PrestamosController.getS().close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	ModeloLibro l=cmbLibro.getSelectionModel().getSelectedItem();
        LocalDateTime now = LocalDateTime.now();
    	DaoHistoricoPrestamo.insertar(cmbAlumno.getSelectionModel().getSelectedItem().getDni(), l.getCodigo(),now);
    	DaoLibro.modificar(l.getTitulo(), l.getAutor(), l.getEditorial(), l.getEstado(), 1, l.getCodigo(), l.getFotoStream());
    	DaoPrestamo.insertar(cmbAlumno.getSelectionModel().getSelectedItem().getDni(), l.getCodigo(),now,DaoHistoricoPrestamo.conseguirCodigo(cmbAlumno.getSelectionModel().getSelectedItem().getDni(), l.getCodigo(),now));
    	try {
    		ConexionBBDD db= new ConexionBBDD();
    		ModeloAlumno alum=cmbAlumno.getSelectionModel().getSelectedItem();
			InputStream reportStream =getClass().getResourceAsStream("/jasper/prestamo.jasper");
            JasperReport report = (JasperReport) JRLoader.loadObject(reportStream);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("NombreAlumno",alum.getNombre());
            parameters.put("ApellidosAlumno", alum.getPriemrApellido()+" "+alum.getSegundoApellido());
            parameters.put("DniAlumno", alum.getDni());
            parameters.put("CodigoLibro", l.getCodigo()+"");
            parameters.put("TituloLibro", l.getTitulo());
            parameters.put("AutorLibro", l.getAutor());
            parameters.put("EditorialLibro", l.getEditorial());
            parameters.put("EstadoLibro", l.getEstado());
            parameters.put("FechaActual", now.getYear()+"/"+now.getMonth()+"/"+now.getDayOfMonth());
            LocalDateTime devolver=now.plusDays(15);
            parameters.put("FechaUltimoDia",devolver.getYear()+"/"+devolver.getMonth()+"/"+devolver.getDayOfMonth());
            parameters.put("IMAGE_PATH", db.getClass().getResource("/imagenes/").toString());
            JasperPrint jprint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
            JasperViewer viewer = new JasperViewer(jprint, false);
            viewer.setVisible(true);
    	} catch (JRException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	al.setContentText("Prestamo realizado correctamente");
    	al.showAndWait();
    	PrestamosController.getS().close();
    }
    
    /**
     * Initialize.
     * @author Aketza
     */
    @FXML
    private void initialize() {
    	cmbAlumno.getItems().addAll(DaoAlumno.conseguirListaTodos());
    	if(cmbAlumno.getItems().size()>0) {
    		cmbAlumno.getSelectionModel().select(0);
    	}
    	cmbLibro.getItems().addAll(DaoLibro.conseguirListaTodosNoBaja());
    	if(cmbLibro.getItems().size()>0) {
    		cmbLibro.getSelectionModel().select(0);
    	}
    }

}
