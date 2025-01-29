package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloPrestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Class DaoPrestamo.
 * @author Aketza
 * @version 1.0
 */
public class DaoPrestamo {

/** The con. */
private static Connection con;
	
	/**
	 * Conseguir lista todos.
	 *
	 * @return the observable list
	 * @author Aketza
	 */
	public static ObservableList<ModeloPrestamo> conseguirListaTodos(){
		ObservableList<ModeloPrestamo>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id_prestamo,dni_alumno,codigo_libro,fecha_prestamo FROM Prestamo";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloPrestamo animal=new ModeloPrestamo(rs.getString("dni_alumno"), rs.getString("codigo_libro"), rs.getDate("fecha_prestamo"));
            	animal.setCodigo(rs.getInt("id_prestamo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	/**
	 * Insertar.
	 *
	 * @param dni the dni
	 * @param codigoLibro the codigo libro
	 * @param now the now
	 * @param codigo the codigo
	 * @author Aketza
	 */
	public static void insertar(String dni, int codigoLibro, LocalDateTime now,int codigo) {
	    con = ConexionBBDD.getConnection();
	    String insert = "INSERT INTO Prestamo (id_prestamo,dni_alumno, codigo_libro, fecha_prestamo) VALUES(?,?, ?, ?)";
	    try {
	        PreparedStatement pstmt = con.prepareStatement(insert);
	        pstmt.setInt(1, codigo);
	        pstmt.setString(2, dni);
	        pstmt.setInt(3, codigoLibro);
	        pstmt.setTimestamp(4, Timestamp.valueOf(now));
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * Borrar.
	 *
	 * @param codigo the codigo
	 * @author Aketza
	 */
	public static void borrar(int codigo) {
		String delete="DELETE FROM Prestamo WHERE id_prestamo like ?";
		try {
	        PreparedStatement pstmt = con.prepareStatement(delete);
	        pstmt.setInt(1, codigo);
	        pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
}
