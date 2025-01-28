package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloAlumno;
import es.aketzagonzalez.model.ModeloHistoricoPrestamos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoHistoricoPrestamo {

private static Connection con;
	
	public static ObservableList<ModeloHistoricoPrestamos> conseguirListaTodos(){
		ObservableList<ModeloHistoricoPrestamos>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id_prestamo,dni_alumno,codigo_libro,fecha_prestamo,fecha_devolucion FROM Historico_prestamo";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloHistoricoPrestamos animal=new ModeloHistoricoPrestamos(rs.getString("dni_alumno"), rs.getString("codigo_libro"), rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"));
            	animal.setCodigo(rs.getInt("id_prestamo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static int conseguirCodigo(String dni, int codigoLibro,LocalDateTime now){
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id_prestamo FROM Historico_prestamo WHERE dni_alumno like ? AND codigo_libro like ? AND fecha_prestamo like ?";
			PreparedStatement pstmt = con.prepareStatement(select);
			pstmt.setString(1, dni);
			pstmt.setInt(2, codigoLibro);
			LocalDateTime truncatedNow = now.truncatedTo(ChronoUnit.SECONDS);
	        pstmt.setTimestamp(3, Timestamp.valueOf(truncatedNow));
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				 return rs.getInt("id_prestamo");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("NO TENDRIAS QUE VER ESO");
		return -1;
	}
	
	public static void insertar(String dni, int codigoLibro,LocalDateTime now) {
	    con = ConexionBBDD.getConnection();
	    String insert = "INSERT INTO Historico_prestamo (dni_alumno, codigo_libro, fecha_prestamo) VALUES(?, ?, ?)";
	    try {
	        PreparedStatement pstmt = con.prepareStatement(insert);
	        pstmt.setString(1, dni);
	        pstmt.setInt(2, codigoLibro);
	        LocalDateTime truncatedNow = now.truncatedTo(ChronoUnit.SECONDS);
	        pstmt.setTimestamp(3, Timestamp.valueOf(truncatedNow));
	        pstmt.executeUpdate();
	        con.commit();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void devolver(int codigo) {
		 con = ConexionBBDD.getConnection();
		 String update="UPDATE Historico_prestamo SET fecha_devolucion=? WHERE id_prestamo like ?;";
		 try {
			 PreparedStatement pstmt = con.prepareStatement(update);
			 LocalDateTime now=LocalDateTime.now();
			 LocalDateTime truncatedNow = now.truncatedTo(ChronoUnit.SECONDS);
			 pstmt.setTimestamp(1, Timestamp.valueOf(truncatedNow));
			 pstmt.setInt(2, codigo);
			 pstmt.executeUpdate();
		 } catch (SQLException e) {
		        e.printStackTrace();
		 }
	}
	
	public static ObservableList<ModeloHistoricoPrestamos> conseguirListaNoDevueltos(){
		ObservableList<ModeloHistoricoPrestamos>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id_prestamo,dni_alumno,codigo_libro,fecha_prestamo,fecha_devolucion FROM Historico_prestamo WHERE fecha_devolucion IS NULL";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloHistoricoPrestamos animal=new ModeloHistoricoPrestamos(rs.getString("dni_alumno"), rs.getString("codigo_libro"), rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"));
            	animal.setCodigo(rs.getInt("id_prestamo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static ObservableList<ModeloHistoricoPrestamos> conseguirListaDevueltos(){
		ObservableList<ModeloHistoricoPrestamos>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id_prestamo,dni_alumno,codigo_libro,fecha_prestamo,fecha_devolucion FROM Historico_prestamo WHERE fecha_devolucion IS NOT NULL";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloHistoricoPrestamos animal=new ModeloHistoricoPrestamos(rs.getString("dni_alumno"), rs.getString("codigo_libro"), rs.getDate("fecha_prestamo"), rs.getDate("fecha_devolucion"));
            	animal.setCodigo(rs.getInt("id_prestamo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}

	
}
