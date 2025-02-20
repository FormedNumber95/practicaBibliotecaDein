package es.aketzagonzalez.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * The Class ModeloLibro.
 * @author Aketza
 * @version 1.0
 */
public class ModeloLibro {

	/** The titulo. */
	private String titulo;
	
	/** The autor. */
	private String autor;
	
	/** The editorial. */
	private String editorial;
	
	/** The estado. */
	private String estado;
	
	/** The baja. */
	private BooleanProperty baja;
	
	/** The codigo. */
	private int codigo;
	
	/** The imagen. */
	private byte[] imagen;
	
	/**
	 * Instantiates a new modelo libro.
	 *
	 * @param titulo the titulo
	 * @param autor the autor
	 * @param editorial the editorial
	 * @param estado the estado
	 * @param baja the baja
	 * @author Aketza
	 */
	public ModeloLibro(String titulo, String autor, String editorial, String estado, int baja) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.estado = estado;
		this.baja = new SimpleBooleanProperty(baja==1);
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 * @author Aketza
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 * @author Aketza
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the autor.
	 *
	 * @return the autor
	 * @author Aketza
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Sets the autor.
	 *
	 * @param autor the new autor
	 * @author Aketza
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Gets the editorial.
	 *
	 * @return the editorial
	 * @author Aketza
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * Sets the editorial.
	 *
	 * @param editorial the new editorial
	 * @author Aketza
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 * @author Aketza
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 * @author Aketza
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the baja.
	 *
	 * @return the baja
	 * @author Aketza
	 */
	public BooleanProperty getBaja() {
		return baja;
	}

	/**
	 * Sets the baja.
	 *
	 * @param baja the new baja
	 * @author Aketza
	 */
	public void setBaja(BooleanProperty baja) {
		this.baja = baja;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 * @author Aketza
	 */
	public int getCodigo() {
		return codigo;
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

	/**
	 * Hash code.
	 *
	 * @return the int
	 * @author Aketza
	 */
	@Override
	public int hashCode() {
		return Objects.hash(autor, editorial, estado, titulo);
	}
	
	/**
	 * Fijar foto.
	 *
	 * @param foto the foto
	 * @author Aketza
	 */
	public void fijarFoto(InputStream foto) {
		if(foto!=null) {
			try {
				this.imagen=foto.readAllBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the foto stream.
	 *
	 * @return the foto stream
	 * @author Aketza
	 */
	public InputStream getFotoStream() {
		if(imagen==null) {
			return null;
		}
		return new ByteArrayInputStream(imagen);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 * @author Aketza
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloLibro other = (ModeloLibro) obj;
		return Objects.equals(autor, other.autor)
				&& Objects.equals(editorial, other.editorial) && Objects.equals(estado, other.estado)
				&& Objects.equals(titulo, other.titulo);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 * @author Aketza
	 */
	@Override
	public String toString() {
		return this.titulo+", "+this.autor+", "+this.editorial;
	}
}
