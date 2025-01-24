package es.aketzagonzalez.model;

import java.sql.Date;
import java.util.Objects;

/**
 * The Class ModeloPrestamo.
 * @author Aketza
 * @version 1.0
 */
public class ModeloPrestamo {

	/** The codigo. */
	private int codigo;
	
	/** The dni. */
	private String dni;
	
	/** The cod libro. */
	private String codLibro;
	
	/** The fecha prestamo. */
	private Date fechaPrestamo;
	
	/**
	 * Instantiates a new modelo prestamo.
	 *
	 * @param dni the dni
	 * @param codLibro the cod libro
	 * @param fechaPrestamo the fecha prestamo
	 */
	public ModeloPrestamo(String dni, String codLibro, Date fechaPrestamo) {
		super();
		this.dni = dni;
		this.codLibro = codLibro;
		this.fechaPrestamo = fechaPrestamo;
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
	 * Gets the dni.
	 *
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}

	/**
	 * Sets the dni.
	 *
	 * @param dni the new dni
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}

	/**
	 * Gets the cod libro.
	 *
	 * @return the cod libro
	 */
	public String getCodLibro() {
		return codLibro;
	}

	/**
	 * Sets the cod libro.
	 *
	 * @param codLibro the new cod libro
	 */
	public void setCodLibro(String codLibro) {
		this.codLibro = codLibro;
	}

	/**
	 * Gets the fecha prestamo.
	 *
	 * @return the fecha prestamo
	 */
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}

	/**
	 * Sets the fecha prestamo.
	 *
	 * @param fechaPrestamo the new fecha prestamo
	 */
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(codLibro);
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
		ModeloPrestamo other = (ModeloPrestamo) obj;
		return Objects.equals(codLibro, other.codLibro);
	}
}
