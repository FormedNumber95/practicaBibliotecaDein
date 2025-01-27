package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
