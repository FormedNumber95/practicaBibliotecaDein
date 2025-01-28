package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoHistoricoPrestamo;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.dao.DaoPrestamo;
import es.aketzagonzalez.model.ModeloPrestamo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class DevolverPrestamoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<ModeloPrestamo> cmbEstado;

    @FXML
    void accionCancelar(ActionEvent event) {
    	DevolucionesController.getS().close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	DaoPrestamo.borrar(cmbEstado.getSelectionModel().getSelectedItem().getCodigo());
    	DaoHistoricoPrestamo.devolver(cmbEstado.getSelectionModel().getSelectedItem().getCodigo());
    	//TODO añadir el modficar del DaoLibro, el alert y cerrar la pestaña
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbEstado.getItems().addAll(DaoPrestamo.conseguirListaTodos());
    	if(cmbEstado.getItems().size()>0) {
    		cmbEstado.getSelectionModel().select(0);
    	}
    }

}
