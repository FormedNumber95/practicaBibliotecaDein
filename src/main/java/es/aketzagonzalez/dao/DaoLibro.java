package es.aketzagonzalez.dao;

import java.io.InputStream;
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
			String select="SELECT codigo,titulo,autor,editorial,estado,baja,portada FROM Libro";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloLibro animal=new ModeloLibro(rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
            	animal.setCodigo(rs.getInt("codigo"));
            	animal.fijarFoto(rs.getBinaryStream("portada"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static ObservableList<ModeloLibro> conseguirListaTodosNoBaja(){
		ObservableList<ModeloLibro>lst=FXCollections.observableArrayList();
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT codigo,titulo,autor,editorial,estado,baja,portada FROM Libro WHERE baja like 0";
			PreparedStatement pstmt = con.prepareStatement(select);
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	ModeloLibro animal=new ModeloLibro(rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
            	animal.setCodigo(rs.getInt("codigo"));
            	animal.fijarFoto(rs.getBinaryStream("portada"));
            	lst.add(animal);
            }
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lst;
	}
	
	public static ModeloLibro conseguirPorCodigo(int codigo){
		try {
			con=ConexionBBDD.getConnection();
			String select="SELECT titulo,autor,editorial,estado,baja,portada FROM Libro where codigo like ?";
			PreparedStatement pstmt = con.prepareStatement(select);
			pstmt.setInt(1,codigo);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				ModeloLibro l= new ModeloLibro(rs.getString("titulo"), rs.getString("autor"), rs.getString("editorial"), rs.getString("estado"), rs.getInt("baja"));
				l.fijarFoto(rs.getBinaryStream("portada"));
				l.setCodigo(codigo);
				return l;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void insertar(String titulo, String autor, String editorial, String edad, int baja,InputStream inputStream) {
		con=ConexionBBDD.getConnection();
		String insert="INSERT INTO Libro (titulo, autor, editorial, estado, baja,portada) VALUES(?, ?, ?, ?, ?,?);";
		try {
			PreparedStatement pstmt=con.prepareStatement(insert);
			pstmt.setString(1,titulo);
			pstmt.setString(2,autor);
			pstmt.setString(3,editorial);
			pstmt.setString(4,edad);
			pstmt.setInt(5, baja);
			pstmt.setBinaryStream(6,inputStream);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void modificar(String titulo, String autor, String editorial, String estado, int baja,int codigo,InputStream inputStream) {
		con=ConexionBBDD.getConnection();
		String update="UPDATE Libro SET titulo=?, autor=?, editorial=?,estado=?,baja=?,portada=? WHERE codigo like ?";
		try {
			PreparedStatement pstmt=con.prepareStatement(update);
			pstmt.setString(1,titulo);
			pstmt.setString(2,autor);
			pstmt.setString(3,editorial);
			pstmt.setString(4,estado);
			pstmt.setInt(5, baja);
			pstmt.setBinaryStream(6,inputStream);
			pstmt.setInt(7,codigo);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
