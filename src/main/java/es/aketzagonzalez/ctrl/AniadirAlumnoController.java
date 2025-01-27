package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.model.ModeloAlumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AniadirAlumnoController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField txtApellido1;

    @FXML
    private TextField txtApellido2;

    @FXML
    private TextField txtDni;

    @FXML
    private TextField txtNombre;

    @FXML
    void accionCancelar(ActionEvent event) {
    	AlumnosController.getS().close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	String error="";
    	String dni=txtDni.getText();
    	String nombre=txtNombre.getText();
    	String ap1=txtApellido1.getText();
    	String ap2=txtApellido2.getText();
    	boolean existe;
    	if(txtDni.getText().isEmpty()) {
    		error+="El campo dni es obligatorio\n";
    	}
    	if(txtNombre.getText().isEmpty()) {
    		error+="El campo nombre es obligatorio\n";
    	}
    	if(txtApellido1.getText().isEmpty()) {
    		error+="El campo primer apellido es obligatorio\n";
    	}
    	if(txtApellido2.getText().isEmpty()) {
    		error+="El campo segundo apellido es obligatorio\n";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(dni, nombre, ap1, ap2);
    	if(AlumnosController.isEsAniadir()) {
    		if(error.equals("")&&!existe) {
    			DaoAlumno.insertar(dni,nombre,ap1,ap2);
    			error="Alumno añadido correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="El alumno ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}else {
    		
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	AlumnosController.setListaTodas(DaoAlumno.conseguirListaTodos());
    	AlumnosController.getS().close();
    }
    
    private boolean validarExistencia(String dni, String nombre, String ap1, String ap2) {
    	ModeloAlumno al=new ModeloAlumno(dni, nombre, ap1, ap2);
    	for(ModeloAlumno a:DaoAlumno.conseguirListaTodos()) {
    		if(a.equals(al)) {
    			return true;
    		}
    	}
    	return false;
    }

}
