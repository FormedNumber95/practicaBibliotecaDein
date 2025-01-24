package es.aketzagonzalez.model;

import java.util.Objects;


/**
 * The Class ModeloAlumno.
 * @author Aketza
 * @version 1.0
 */
public class ModeloAlumno {

	/** The dni. */
	private String dni;
	
	/** The nombre. */
	private String nombre;
	
	/** The priemr apellido. */
	private String priemrApellido;
	
	/** The segundo apellido. */
	private String segundoApellido;
	
	/**
	 * Instantiates a new modelo alumno.
	 *
	 * @param dni the dni
	 * @param nombre the nombre
	 * @param priemrApellido the priemr apellido
	 * @param segundoApellido the segundo apellido
	 */
	public ModeloAlumno(String dni, String nombre, String priemrApellido, String segundoApellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.priemrApellido = priemrApellido;
		this.segundoApellido = segundoApellido;
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
	 * Gets the nombre.
	 *
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Sets the nombre.
	 *
	 * @param nombre the new nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Gets the priemr apellido.
	 *
	 * @return the priemr apellido
	 */
	public String getPriemrApellido() {
		return priemrApellido;
	}

	/**
	 * Sets the priemr apellido.
	 *
	 * @param priemrApellido the new priemr apellido
	 */
	public void setPriemrApellido(String priemrApellido) {
		this.priemrApellido = priemrApellido;
	}

	/**
	 * Gets the segundo apellido.
	 *
	 * @return the segundo apellido
	 */
	public String getSegundoApellido() {
		return segundoApellido;
	}

	/**
	 * Sets the segundo apellido.
	 *
	 * @param segundoApellido the new segundo apellido
	 */
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		return Objects.hash(dni);
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
		ModeloAlumno other = (ModeloAlumno) obj;
		return Objects.equals(dni, other.dni);
	}
	
}
