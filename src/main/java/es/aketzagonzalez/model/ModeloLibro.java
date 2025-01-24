package es.aketzagonzalez.model;

import java.util.Objects;

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
	private Boolean baja;
	
	/** The codigo. */
	private int codigo;
	
	/**
	 * Instantiates a new modelo libro.
	 *
	 * @param titulo the titulo
	 * @param autor the autor
	 * @param editorial the editorial
	 * @param estado the estado
	 * @param baja the baja
	 */
	public ModeloLibro(String titulo, String autor, String editorial, String estado, Boolean baja) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.estado = estado;
		this.baja = baja;
	}

	/**
	 * Gets the titulo.
	 *
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Sets the titulo.
	 *
	 * @param titulo the new titulo
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * Gets the autor.
	 *
	 * @return the autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Sets the autor.
	 *
	 * @param autor the new autor
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Gets the editorial.
	 *
	 * @return the editorial
	 */
	public String getEditorial() {
		return editorial;
	}

	/**
	 * Sets the editorial.
	 *
	 * @param editorial the new editorial
	 */
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	/**
	 * Gets the estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Sets the estado.
	 *
	 * @param estado the new estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Gets the baja.
	 *
	 * @return the baja
	 */
	public Boolean getBaja() {
		return baja;
	}

	/**
	 * Sets the baja.
	 *
	 * @param baja the new baja
	 */
	public void setBaja(Boolean baja) {
		this.baja = baja;
	}

	/**
	 * Gets the codigo.
	 *
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * Sets the codigo.
	 *
	 * @param codigo the new codigo
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(autor, baja, editorial, estado, titulo);
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
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
		return Objects.equals(autor, other.autor) && Objects.equals(baja, other.baja)
				&& Objects.equals(editorial, other.editorial) && Objects.equals(estado, other.estado)
				&& Objects.equals(titulo, other.titulo);
	}
}
