package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloPrestamo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoPrestamo {

private static Connection con;
	
	public static ObservableList<ModeloPrestamo> conseguirListaTodos(){
		ObservableList<ModeloPrestamo>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT id_prestamo,dni_alumno,codigo_libro,fecha_prestamo FROM Prestamo";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloPrestamo animal=new ModeloPrestamo(rs.getString("dni_alumno"), rs.getString("cod_libro"), rs.getDate("fecha_prestamo"));
            	animal.setCodigo(rs.getInt("id_prestamo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
