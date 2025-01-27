package es.aketzagonzalez.ctrl;

import es.aketzagonzalez.dao.DaoAlumno;
import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.model.ModeloLibro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AniadirLibroController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<String> cmbEstado;

    @FXML
    private TextField txtAutor;

    @FXML
    private TextField txtEditorial;

    @FXML
    private TextField txtTitulo;
    
    @FXML
    private CheckBox chkBaja;
    
    private int codigo;

    @FXML
    void accionCancelar(ActionEvent event) {
    	LibrosController.getS().close();
    }

    @FXML
    void accionGuardar(ActionEvent event) {
    	String error="";
    	String autor=txtAutor.getText();
    	String editorial=txtEditorial.getText();
    	String titulo=txtTitulo.getText();
    	String estado=cmbEstado.getSelectionModel().getSelectedItem();
    	boolean existe;
    	if(txtAutor.getText().isEmpty()) {
    		error+="El campo autor es obligatorio\n";
    	}
    	if(txtEditorial.getText().isEmpty()) {
    		error+="El campo editorial es obligatorio\n";
    	}
    	if(txtTitulo.getText().isEmpty()) {
    		error+="El campo titulo es obligatorio\n";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(titulo,autor,editorial,estado);
    	if(LibrosController.isEsAniadir()) {
    		if(error.equals("")&&!existe) {
    			DaoLibro.insertar(titulo, autor, editorial, estado, 0);
    			error="Libro añadido correctamente";
    		}else {
    			if(error.equals("")) {
    				al.setAlertType(AlertType.WARNING);
    				error="El libro ya estaba en la lista";
    			}else {
    				al.setAlertType(AlertType.ERROR);
    			}
    		}
    	}else {
    		if(error.equals("")) {
    			if(chkBaja.isSelected()) {
    				DaoLibro.modificar(titulo, autor, editorial, estado, 1, codigo);
    			}else {
    				DaoLibro.modificar(titulo, autor, editorial, estado, 0, codigo);
    			}
    			error="Libro modificado correctamente";
    		}else {
    			al.setAlertType(AlertType.ERROR);
    		}
    	}
    	al.setContentText(error);
    	al.showAndWait();
    	LibrosController.setListaTodas(DaoLibro.conseguirListaTodos());
    	LibrosController.getS().close();
    }
    
    private boolean validarExistencia(String titulo, String autor, String editorial, String estado) {
		ModeloLibro lib=new ModeloLibro(titulo, autor, editorial, estado, 0);
		for(ModeloLibro l:DaoLibro.conseguirListaTodos()) {
			if(l.equals(lib)) {
				return true;
			}
		}
		return false;
	}

	/**
     * Initialize.
     */
    @FXML
    private void initialize() {
    	cmbEstado.getItems().addAll("Nuevo","Usado nuevo","Usado seminuevo","Usado estropeado","Restaurado");
    	cmbEstado.getSelectionModel().select("Nuevo");
    	if(LibrosController.isEsAniadir()) {
    		chkBaja.setSelected(false);
    		chkBaja.setDisable(true);
    	}
    	else {
    		chkBaja.setDisable(false);
    	}
    }
    
	public CheckBox getChkBaja() {
		return chkBaja;
	}
	
	public TextField getTxtTitulo() {
		return txtTitulo;
	}
	
	public TextField getTxtAutor() {
		return txtAutor;
	}
	
	public TextField getTxtEditorial() {
		return txtEditorial;
	}
    
    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
