package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoHistoricoPrestamo;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.dao.DaoPrestamo;
import es.aketzagonzalez.model.ModeloLibro;
import es.aketzagonzalez.model.ModeloPrestamo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

/**
 * The Class DevolverPrestamoController.
 * @author Aketza
 * @version 1.0
 */
public class DevolverPrestamoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The cmb estado. */
    @FXML
    private ComboBox<String> cmbEstado;
    
    /** The tbl devoluciones. */
    private TableView<ModeloPrestamo> tblDevoluciones;

    /**
     * Accion cancelar.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	DevolucionesController.getS().close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionGuardar(ActionEvent event) {
    	ModeloLibro l=DaoLibro.conseguirPorCodigo(Integer.parseInt(tblDevoluciones.getSelectionModel().getSelectedItem().getCodLibro()));
    	DaoPrestamo.borrar(tblDevoluciones.getSelectionModel().getSelectedItem().getCodigo());
    	DaoHistoricoPrestamo.devolver(tblDevoluciones.getSelectionModel().getSelectedItem().getCodigo());
    	DaoLibro.modificar(l.getTitulo(), l.getAutor(), l.getEditorial(), cmbEstado.getSelectionModel().getSelectedItem(), 0, l.getCodigo(), l.getFotoStream());
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	al.setContentText("Prestamo devuelto correctamente");
    	al.showAndWait();
    	DevolucionesController.getS().close();
    }
    
    /**
     * Initialize.
     * @author Aketza
     */
    @FXML
    private void initialize() {
    	cmbEstado.getItems().addAll("Nuevo","Usado nuevo","Usado seminuevo","Usado estropeado","Restaurado");
    	cmbEstado.getSelectionModel().select(0);
    }
    
    /**
     * Sets the tbl devoluciones.
     *
     * @param tblDevoluciones the new tbl devoluciones
     * @author Aketza
     */
    public void setTblDevoluciones(TableView<ModeloPrestamo> tblDevoluciones) {
		this.tblDevoluciones = tblDevoluciones;
	}

}
