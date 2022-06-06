package EJERCICIOEXAMEN;

import java.sql.Date;

public class Ruta 
{
	int id;
	String origen;
	String destino;
	Date fechasalida;
	Date fechallegada;
	float tarifa;
	int asientoscomprados;
	Bus B1;
	
	public Ruta (int id, String o, String d, Date fsalida, Date fllegada, float t, int a, Bus b)
	{
		this.id = id;
		this.origen = o;
		this.destino = d;
		this.fechasalida = fsalida;
		this.fechallegada = fllegada;
		this.tarifa = t;
		this.asientoscomprados = a;
		this.B1 = b;
	}
	
}
