package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloLibro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class AniadirPrestamoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<ModeloAlumno> cmbAlumno;

    @FXML
    private ComboBox<ModeloLibro> cmbLibro;

    @FXML
    void accionCancelar(ActionEvent event) {
    	PrestamosController.getS().close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	
    }
    
    /**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbAlumno.getItems().addAll(DaoAlumno.conseguirListaTodos());
    	cmbLibro.getItems().addAll(DaoLibro.conseguirListaTodosNoBaja());
    }

}
