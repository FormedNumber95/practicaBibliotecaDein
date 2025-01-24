package es.aketzagonzalez.model;

import java.sql.Date;
import java.util.Objects;

/**
 * The Class ModeloHistoricoPrestamos.
 * @author Aketza
 * @version 1.0
 */
public class ModeloHistoricoPrestamos extends ModeloPrestamo{

	/** The fecha devolucion. */
	private Date fechaDevolucion;

	/**
	 * Instantiates a new modelo historico prestamos.
	 *
	 * @param dni the dni
	 * @param codLibro the cod libro
	 * @param fechaPrestamo the fecha prestamo
	 * @param fechaDevolucion the fecha devolucion
	 */
	public ModeloHistoricoPrestamos(String dni, String codLibro, Date fechaPrestamo, Date fechaDevolucion) {
		super(dni, codLibro, fechaPrestamo);
		this.fechaDevolucion = fechaDevolucion;
	}

	/**
	 * Gets the fecha devolucion.
	 *
	 * @return the fecha devolucion
	 */
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	/**
	 * Sets the fecha devolucion.
	 *
	 * @param fechaDevolucion the new fecha devolucion
	 */
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fechaDevolucion);
		return result;
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
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ModeloHistoricoPrestamos other = (ModeloHistoricoPrestamos) obj;
		return Objects.equals(fechaDevolucion, other.fechaDevolucion);
	}
	
	
}
