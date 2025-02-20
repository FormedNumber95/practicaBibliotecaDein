package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class DaoAlumno.
 * @author Aketza
 * @version 1.0
 */
public class DaoAlumno {

	/** The con. */
	private static Connection con;
	
	/**
	 * Conseguir lista todos.
	 *
	 * @return the observable list
	 * @author Aketza
	 */
	public static ObservableList<ModeloAlumno> conseguirListaTodos(){
		ObservableList<ModeloAlumno>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT dni,nombre,apellido1,apellido2 FROM Alumno";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloAlumno animal=new ModeloAlumno(rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Conseguir por dni.
	 *
	 * @param dni the dni
	 * @return the modelo alumno
	 * @author Aketza
	 */
	public static ModeloAlumno conseguirPorDni(String dni){
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT nombre,apellido1,apellido2 FROM Alumno WHERE dni like ?";
			PreparedStatement pstmt = con.prepareStatement(select);
			pstmt.setString(1,dni);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				ModeloAlumno l= new ModeloAlumno(dni, rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"));
				return l;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Insertar.
	 *
	 * @param dni the dni
	 * @param nombre the nombre
	 * @param ap1 the ap 1
	 * @param ap2 the ap 2
	 * @author Aketza
	 */
	public static void insertar(String dni, String nombre, String ap1, String ap2) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Alumno (dni, nombre, apellido1, apellido2) VALUES(?, ?, ?, ?);";
		try {
			PreparedStatement pstmt=con.prepareStatement(insert);
			pstmt.setString(1,dni);
			pstmt.setString(2,nombre);
			pstmt.setString(3,ap1);
			pstmt.setString(4,ap2);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Modificar.
	 *
	 * @param dni the dni
	 * @param nombre the nombre
	 * @param ap1 the ap 1
	 * @param ap2 the ap 2
	 * @author Aketza
	 */
	public static void modificar(String dni, String nombre, String ap1, String ap2) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Alumno SET nombre=?, apellido1=?, apellido2=? WHERE dni=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(update);
			pstmt.setString(1,nombre);
			pstmt.setString(2,ap1);
			pstmt.setString(3,ap2);
			pstmt.setString(4,dni);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
