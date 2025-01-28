package es.aketzagonzalez.ctrl;

import java.time.LocalDateTime;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoHistoricoPrestamo;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.dao.DaoPrestamo;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloLibro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;

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
    	ModeloLibro l=cmbLibro.getSelectionModel().getSelectedItem();
        LocalDateTime now = LocalDateTime.now();
    	DaoHistoricoPrestamo.insertar(cmbAlumno.getSelectionModel().getSelectedItem().getDni(), l.getCodigo(),now);
    	DaoLibro.modificar(l.getTitulo(), l.getAutor(), l.getEditorial(), l.getEstado(), 1, l.getCodigo(), l.getFotoStream());
    	DaoPrestamo.insertar(cmbAlumno.getSelectionModel().getSelectedItem().getDni(), l.getCodigo(),now,DaoHistoricoPrestamo.conseguirCodigo(cmbAlumno.getSelectionModel().getSelectedItem().getDni(), l.getCodigo(),now));
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	al.setContentText("Prestamo realizado correctamente");
    	al.showAndWait();
    	PrestamosController.getS().close();
    }
    
    /**
     * Initialize.
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
