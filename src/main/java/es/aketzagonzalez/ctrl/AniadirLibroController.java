package es.aketzagonzalez.ctrl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import es.aketzagonzalez.dao.DaoLibro;
import es.aketzagonzalez.model.ModeloLibro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.WritablePixelFormat;
import javafx.stage.FileChooser;

/**
 * The Class AniadirLibroController.
 * @author Aketza
 * @version 1.0
 */
public class AniadirLibroController {

    /** The btn cancelar. */
    @FXML
    private Button btnCancelar;

    /** The btn guardar. */
    @FXML
    private Button btnGuardar;
    
    /** The btn seleccionar imagen. */
    @FXML
    private Button btnSeleccionarImagen;

    /** The cmb estado. */
    @FXML
    private ComboBox<String> cmbEstado;

    /** The txt autor. */
    @FXML
    private TextField txtAutor;

    /** The txt editorial. */
    @FXML
    private TextField txtEditorial;

    /** The txt titulo. */
    @FXML
    private TextField txtTitulo;
    
    /** The img seleccionada. */
    @FXML
    private ImageView imgSeleccionada;
    
    /** The chk baja. */
    @FXML
    private CheckBox chkBaja;
    
    /** The codigo. */
    private int codigo;

    /**
     * Accion cancelar.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void accionCancelar(ActionEvent event) {
    	LibrosController.getS().close();
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
    	String autor=txtAutor.getText();
    	String editorial=txtEditorial.getText();
    	String titulo=txtTitulo.getText();
    	String estado=cmbEstado.getSelectionModel().getSelectedItem();
    	InputStream portada=null;
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
    	portada=getImageInputStream(imgSeleccionada);
    	if(portada==null) {
    		error+="La imagen es obligatoria\n";
    	}
    	Alert al=new Alert(AlertType.INFORMATION);
    	al.setHeaderText(null);
    	existe=validarExistencia(titulo,autor,editorial,estado);
    	if(LibrosController.isEsAniadir()) {
    		if(error.equals("")&&!existe) {
    			DaoLibro.insertar(titulo, autor, editorial, estado, 0,portada);
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
    				DaoLibro.modificar(titulo, autor, editorial, estado, 1, codigo,portada);
    			}else {
    				DaoLibro.modificar(titulo, autor, editorial, estado, 0, codigo,portada);
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
    
    /**
     * Validar existencia del libro.
     *
     * @param titulo the titulo
     * @param autor the autor
     * @param editorial the editorial
     * @param estado the estado
     * @return true, if successful
     * @author Aketza
     */
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
     * Elegir imagen.
     *
     * @param event the event
     * @author Aketza
     */
    @FXML
    void elegirImagen(ActionEvent event) {
    	 FileChooser fileChooser = new FileChooser();
         FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("Archivos JPG (*.jpg)", "*.jpg");
         FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("Archivos PNG (*.png)", "*.png");
         fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG); 
         File file = fileChooser.showOpenDialog(((Node)event.getSource()).getScene().getWindow());
         if(file!=null) {
 	      imgSeleccionada.setImage(new Image(file.toURI().toString()));
 	      imgSeleccionada.setFitWidth(100);
 	      imgSeleccionada.setFitHeight(100);
         }
    }
    
    /**
     * Gets the image input stream.
     *
     * @param imageView the image view
     * @return the image input stream
     * @author Aketza
     */
    public static InputStream getImageInputStream(ImageView imageView) {
        Image image = imageView.getImage();
        if (image == null) {
            return null;
        }

        // Obtener las dimensiones de la imagen
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Crear un BufferedImage con el mismo tamaño que la imagen de JavaFX
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Obtener el lector de píxeles de la imagen de JavaFX
        PixelReader pixelReader = image.getPixelReader();
        if (pixelReader != null) {
            // Leer píxeles de la imagen y escribirlos en BufferedImage
            int[] pixels = new int[width * height];
            pixelReader.getPixels(0, 0, width, height, WritablePixelFormat.getIntArgbInstance(), pixels, 0, width);
            bufferedImage.setRGB(0, 0, width, height, pixels, 0, width);
        }

        try {
            // Escribir el BufferedImage en un ByteArrayOutputStream
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", outputStream);
            outputStream.flush();

            // Convertir el ByteArrayOutputStream a InputStream
            return new ByteArrayInputStream(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

	/**
     * Initialize.
     * @author Aketza
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
    
	/**
	 * Gets the chk baja.
	 *
	 * @return the chk baja
	 * @author Aketza
	 */
	public CheckBox getChkBaja() {
		return chkBaja;
	}
	
	/**
	 * Gets the txt titulo.
	 *
	 * @return the txt titulo
	 * @author Aketza
	 */
	public TextField getTxtTitulo() {
		return txtTitulo;
	}
	
	/**
	 * Gets the txt autor.
	 *
	 * @return the txt autor
	 * @author Aketza
	 */
	public TextField getTxtAutor() {
		return txtAutor;
	}
	
	/**
	 * Gets the txt editorial.
	 *
	 * @return the txt editorial
	 * @author Aketza
	 */
	public TextField getTxtEditorial() {
		return txtEditorial;
	}
    
    /**
     * Sets the codigo.
     *
     * @param codigo the new codigo
     * @author Aketza
     */
    public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

}
