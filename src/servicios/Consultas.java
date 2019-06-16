package servicios;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;

import anotaciones.Columna;
import utilidades.UBean;
import utilidades.UConnection;

public class Consultas {
	
	public static void Guardar(Object o) {
		
		Connection conn = UConnection.getConnection();
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		Field IdTraido;
		
		ArrayList<Field> attrs = UBean.getAttrs(o);
		
		
	}

}
