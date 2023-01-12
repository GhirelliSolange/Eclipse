package Metodos;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.ResultSet;

import java.sql.Statement;

import org.testng.annotations.Test;


public class Sql {

	@Test
	public void Prueba1() throws SQLException, ClassNotFoundException {
		
		String query = "SELECT * FROM Stock";
		String nombre = null;
		String animal = null;
		String producto = null;
		String dato = null;
		
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\HD345AP\\Downloads\\sqlite-tools-win32-x86-3400000\\sqlite-tools-win32-x86-3400000\\product.db");

			Statement s = con.createStatement();
			
			con.setAutoCommit(true);
			
			s.execute(query);

			ResultSet rs = s.executeQuery(query);
			while (rs.next()) {
				
				nombre = rs.getString("Nombre");
				animal = rs.getString("Animal");
				producto = rs.getString("Producto");
				dato = nombre+ " " + animal + " " + producto;
				
				System.out.println(dato);
				int filas = rs.getRow();
				System.out.println("El numero de filas es: " + filas);
				//s.execute("SELECT COUNT (1) FROM Stock");
			
			}
			rs.close();
			s.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error " + e.getMessage());
	}
		
}
}

