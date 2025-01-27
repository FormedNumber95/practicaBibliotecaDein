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
            	ModeloLibro animal=new ModeloLibro(rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
            	animal.setCodigo(rs.getInt("codigo"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static void insertar(String titulo, String autor, String editorial, String edad, int baja) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Libro (titulo, autor, editorial, estado, baja) VALUES(?, ?, ?, ?, ?);";
		try {
			PreparedStatement pstmt=con.prepareStatement(insert);
			pstmt.setString(1,titulo);
			pstmt.setString(2,autor);
			pstmt.setString(3,editorial);
			pstmt.setString(4,edad);
			pstmt.setInt(5, baja);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificar(String titulo, String autor, String editorial, String edad, int baja,int codigo) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Libro SET titulo=?, autor=?, editorial=?,estado=?,baja=? WHERE codigo=?";
		try {
			PreparedStatement pstmt=con.prepareStatement(update);
			pstmt.setString(1,titulo);
			pstmt.setString(2,autor);
			pstmt.setString(3,editorial);
			pstmt.setString(4,edad);
			pstmt.setInt(5, baja);
			pstmt.setInt(6,codigo);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
