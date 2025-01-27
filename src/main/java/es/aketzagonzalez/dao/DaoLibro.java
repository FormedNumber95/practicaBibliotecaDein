package es.aketzagonzalez.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.aketzagonzalez.db.ConexionBBDD;
import es.aketzagonzalez.model.ModeloLibro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DaoLibro {

private static Connection con;
	
	public static ObservableList<ModeloLibro> conseguirListaTodos(){
		ObservableList<ModeloLibro>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT codigo,titulo,autor,editorial,estado,baja FROM Libro";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloLibro animal=new ModeloLibro(rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja")==1);
            	animal.setCodigo(rs.getInt("codigo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
}
