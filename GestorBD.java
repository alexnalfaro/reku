package EJERCICIOEXAMEN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestorBD 
{
	Connection con;
	Statement st;
	ResultSet rs;
	String url = "jdbc:mysql://localhost/ejercicioexamen";
	String user = "root";
	String password = "root";
	public GestorBD()
	{
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conectado");
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			System.out.println("No se ha podido conectar a la BD");
		}		
	}
	
	public ArrayList <String> obtenerOrigen ()
	{
		ArrayList <String> origenes = new ArrayList<String>();
		try {
			st = con.createStatement();
			String query = "SELECT DISTINCT origen FROM rutas;";
			rs = st.executeQuery(query);
			String origen = "";
			while (rs.next())
			{
				origen = rs.getString("origen");
				origenes.add(origen);
			}
			return origenes;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList <String> destinosDesde (String origen)
	{
		ArrayList <String> destinos = new ArrayList <String>();
		try {
			st = con.createStatement();
			String query = "SELECT destino FROM rutas WHERE origen = '"+origen+"';";
			rs = st.executeQuery(query);
			String destino ="";
			String itinerario = "";
			while (rs.next())
			{
				destino = rs.getString("destino");
				itinerario = origen+"-"+destino;
				destinos.add(itinerario);
			}
			return destinos;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}
	
	public void cerrarConexion ()
	{
		try {
			con.close();
			System.out.println("Sesion cerrada");
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}