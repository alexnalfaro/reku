package EJERCICIOEXAMEN;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorBD 
{
	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement stmt;
	String url = "jdbc:mysql://localhost/db_viaje";
	String user = "root";
	String password = "root";
	public GestorBD()
	{
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Conexion establecida");
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			System.err.print("No se ha podido conectar a la BD");
		}
	}
	public ArrayList<String> obtenerOrigen ()
	{
		ArrayList <String> a = new ArrayList<String>();
		try {
			st = con.createStatement();
			String query = "SELECT DISTINCT origen FROM rutas;";
			rs = st.executeQuery(query);
			String origen = "";
			while (rs.next())
			{
				origen = rs.getString("origen");
				a.add(origen);				
			}
			return a;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> destinosDesde(String origen)
	{
		ArrayList <String> a = new ArrayList<String>();
		try {
			st = con.createStatement();
			String query = "SELECT destino FROM rutas WHERE origen = '"+origen+"';";
			rs = st.executeQuery(query);
			String destino = "";
			while (rs.next())
			{
				destino = rs.getString("destino");
				a.add(destino);
			}
			return a;
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
		return null;
	}
	
	public Ruta rutaOrigenDestino (String origen, String destino)
	{
		return null;
	}
	
	public boolean asientosDisponibles (Ruta R1, int nasientos)
	{
		int asientosocupados = R1.asientoscomprados;
		int capacidad = R1.B1.capacidad;
		if ((capacidad-asientosocupados)>nasientos)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean dniExistente (String DNI)
	{
		try {
			st = con.createStatement();
			String query = "SELECT dni FROM clientes;";
			rs = st.executeQuery(query);
			String rs_dni = "";
			int ndni = 0;
			while (rs.next())
			{
				rs_dni = rs.getString("dni");
				if (rs_dni.equals(DNI))
				{
					ndni++;
				}
			}
			if (ndni == 1)
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String reservar (HashMap<Ruta,Integer> a, String dni)
	{
		for (HashMap.Entry<Ruta,Integer> aux:a.entrySet())
		{
			Ruta R1 = aux.getKey();
			int nasientos = aux.getValue();
			int idruta = R1.id;
			if (asientosDisponibles(R1, nasientos) == true)
			{
				anadirReserva(dni, idruta, nasientos);
				modificarRuta(idruta, nasientos);
			}
		}
		return null;	
	}
	
	public void anadirReserva(String dni, int id, int asientos)
	{
		String query = "INSERT INTO reservas VALUES ('"+dni+"','"+id+"','"+asientos+"';";
		try {
			stmt = con.prepareStatement(query);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
	
	public void modificarRuta (int id, int asientos)
	{
		String query = "UPDATE rutas SET asientoscomprados+'"+asientos+"' WHERE idruta = '"+id+"';";
		try {
			stmt = con.prepareStatement(query);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cerrarConexion()
	{
		try {
			con.close();
			System.out.println("Conexcion cerrada correctamente");
		} catch (SQLException e) {
			// TODO Bloque catch generado automáticamente
			e.printStackTrace();
		}
	}
}
