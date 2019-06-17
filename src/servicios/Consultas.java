package servicios;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;


import java.sql.PreparedStatement;
//import com.mysql.jdbc.Statement;
//import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import anotaciones.Tabla;
import anotaciones.Dni;
import anotaciones.Columna;
import utilidades.UBean;
import utilidades.UConnection;

public class Consultas {
	
	public static Object Guardar(Object o) throws SQLException {
		
		Connection conn = UConnection.getConnection();
		Object dni = null;
		Field dniObtenido = null;
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		ArrayList<Field> attrs = UBean.getAttrs(o);
		
		for(Field f : attrs) {
			
			if(f.getAnnotation(Columna.class) != null)
				columnas.add(f.getAnnotation(Columna.class));
			
			if(f.getAnnotation(Dni.class) != null)
				dniObtenido=f;
		}
		
		Class clase = o.getClass();
		Tabla tabla = (Tabla) clase.getAnnotation(Tabla.class);
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO "+ tabla.nombre() + " (");
		
		for(Columna c : columnas)
		{
			sb.append(c.nombre() + ",");
		}
		
		sb.replace(sb.length()-1,sb.length(),") VALUES (");
		
		for(Field f : attrs)
		{
			if(f.getAnnotation(Columna.class) != null)
			{
				Object valor = UBean.goGet(o,f.getName());
				if(valor.getClass().equals(String.class))
				{
					sb.append("'").append(valor.toString()).append("'").append(",");
				}
				else
				{
					sb.append(valor).append(",");
				}
			}
		}	
		
		sb.replace(sb.length()-1, sb.length(),")");
		System.out.println(sb.toString());
		
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.execute();
			
			PreparedStatement ps2 = conn.prepareStatement("SELECT "
			+ dniObtenido.getName() + " FROM " + tabla.nombre() + " ORDER BY "
					+ "" + dniObtenido.getName() +
			" DESC LIMIT 1");
            ResultSet rs = ps2.executeQuery();
            
            while(rs.next()){
                dni = rs.getObject(1);
                System.out.println(dni);
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		conn.close();
		return dni;
	}
	
	public static void Modificar(Object o) {
		
		Connection conn = UConnection.getConnection();
		Field dniObtenido = null;
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		ArrayList<Field> attrs = UBean.getAttrs(o);
		
		for(Field f : attrs)
		{
			if(f.getAnnotation(Columna.class) != null)
				columnas.add(f.getAnnotation(Columna.class));
			
			if(f.getAnnotation(Dni.class) != null)
				dniObtenido=f;
		}
		
		Class clase = o.getClass();
		Tabla tabla = (Tabla) clase.getAnnotation(Tabla.class);
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE "+ tabla.nombre() + " SET ");
		
		for(Field f : attrs)
		{
			if(f.getAnnotation(Columna.class)!= null)
			{
				sb.append(f.getAnnotation(Columna.class).nombre()).append(" = ").append("'")
				.append(UBean.goGet(o, f.getName())).append("'").append(",");
			}
		}
		
		sb.replace(sb.length()-1, sb.length(),"");
		
		sb.append(" WHERE " + dniObtenido.getName()).append(" = ")
		.append(UBean.goGet(o,dniObtenido.getName()));
		
		System.out.println(sb.toString());
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(sb.toString());
			ps.execute();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void Eliminar(Object o) {
		
		Connection conn = UConnection.getConnection();
		Class clase = o.getClass();
		
		Tabla tabla = (Tabla) clase.getAnnotation(Tabla.class);
		Field dniObtenido = null;
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		ArrayList<Field> attrs = UBean.getAttrs(o);
		
		for(Field f : attrs)
		{
			if(f.getAnnotation(Columna.class) != null)
				columnas.add(f.getAnnotation(Columna.class));
			
			if(f.getAnnotation(Dni.class) != null)
				dniObtenido=f;
		}
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement("DELETE FROM " + tabla.nombre() +
					" WHERE " + dniObtenido.getName() + " = " + UBean.goGet(o,dniObtenido.getName()));
			ps.execute();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Object ObtenerPorDni(Class c, Object o) {
		
		Connection conn = UConnection.getConnection();
		
		ArrayList<Columna> columnas = new ArrayList<Columna>();
		Tabla tabla = (Tabla) c.getAnnotation(Tabla.class);
		
		Field dniObtenido = null;
		Object obj = null;
		
		Constructor[] constructors = c.getConstructors();
		
		for(Constructor con : constructors)
		{
			if(con.getParameterCount()==0)
			{
				try {
					obj = con.newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		ArrayList<Field> attrs = UBean.getAttrs(obj);
		
		for(Field f : attrs)
		{
			if(f.getAnnotation(Columna.class) != null)
				columnas.add(f.getAnnotation(Columna.class));
			
			if(f.getAnnotation(Dni.class) != null)
				dniObtenido=f;
		}
		
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement
					("SELECT * FROM " + tabla.nombre() +
							" WHERE " + dniObtenido.getName() + " = " + o);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				for(Field f : attrs)
				{
					if(f.getAnnotation(Columna.class) != null)
					{
						UBean.goSet(obj,f.getName(), rs.getObject(f.getAnnotation(Columna.class).nombre()));
					}
				}
			}
			
			UBean.goSet(obj,dniObtenido.getName(),o);
			System.out.println(obj);
			
			return obj;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
