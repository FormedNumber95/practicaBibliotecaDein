package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.model.ModeloAlumno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * The Class AniadirAlumnoController.
 * @author Aketza
 * @version 1.0
 */
public class AniadirAlumnoController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;

    /** The txt apellido 1. */
    @FXML
    private TextField txtApellido1;

    /** The txt apellido 2. */
    @FXML
    private TextField txtApellido2;

    /** The txt dni. */
    @FXML
    private TextField txtDni;

    /** The txt nombre. */
    @FXML
    private TextField txtNombre;

    /**
     * Accion cancelar.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	AlumnosController.getS().close();
    }

    /**
     * Accion guardar.
     *
     * @param event the event
     * @author Aketza
     */
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
    		if(error.equals("")) {
    			DaoAlumno.modificar(dni,nombre,ap1,ap2);
    			error="Alumno modificado correctamente";
    		}else {
    			al.setAlertType(AlertType.ERROR);
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	AlumnosController.setListaTodas(DaoAlumno.conseguirListaTodos());
    	AlumnosController.getS().close();
    }
    
    /**
     * Validar existencia del alumno.
     *
     * @param dni the dni
     * @param nombre the nombre
     * @param ap1 the ap 1
     * @param ap2 the ap 2
     * @return true, if successful
     * @author Aketza
     */
    private boolean validarExistencia(String dni, String nombre, String ap1, String ap2) {
    	ModeloAlumno al=new ModeloAlumno(dni, nombre, ap1, ap2);
    	for(ModeloAlumno a:DaoAlumno.conseguirListaTodos()) {
    		if(a.equals(al)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Gets the txt apellido 1.
     *
     * @return the txt apellido 1
     * @author Aketza
     */
    public TextField getTxtApellido1() {
		return txtApellido1;
	}
    
    /**
     * Gets the txt apellido 2.
     *
     * @return the txt apellido 2
     * @author Aketza
     */
    public TextField getTxtApellido2() {
		return txtApellido2;
	}
    
    /**
     * Gets the txt dni.
     *
     * @return the txt dni
     * @author Aketza
     */
    public TextField getTxtDni() {
		return txtDni;
	}
    
    /**
     * Gets the txt nombre.
     *
     * @return the txt nombre
     * @author Aketza
     */
    public TextField getTxtNombre() {
		return txtNombre;
	}

}
