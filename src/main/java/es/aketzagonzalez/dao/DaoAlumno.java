package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoAlumno {

	private static Connection con;
	
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
	
}
