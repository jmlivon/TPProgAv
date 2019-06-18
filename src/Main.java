import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import servicios.Consultas;
import utilidades.UBean;

public class Main {
	
	public static void main(String[] args) {
		
		/* Pruebo SET
		Persona p = new Persona("Juan");
		System.out.println("Nombre1: " + p.getNombre());
		UBean.goSet(p, "nombre", "Pedro");
		System.out.println("Nombre2: " + p.getNombre());
		*/
		
		/* Guardar
		Persona p = new Persona("Pablo");
		try {
			Consultas.Guardar(p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/* Modificar
		Persona p = new Persona("Omar");	
		p.setDni(38491334);
		Consultas.Modificar(p);		
		*/
		
		// Eliminar
		Persona b = new Persona();
		b.setDni(38491335);
		Consultas.Eliminar(b);		
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/claseprogav";
		String sql = "Select * From persona where dni = ?";
		List<Persona> personas = new ArrayList<Persona>();
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,"root","");
			System.out.println("Conectó");
			
			//Statement st = con.createStatement();
			//st.execute("Insert into persona (dni,nombre) values(38491332,'Jorge')");
			
			PreparedStatement pS = con.prepareStatement(sql);
			pS.setInt(1, 38491330);
			
			// Para hacer INSERTS, DELETES
			//pS.execute();
			
			// Para hacer SELECTS (cuando tienen resultado)
			ResultSet rs = pS.executeQuery();
			while(rs.next()) {
				Persona p = new Persona();
				p.setDni(rs.getInt("dni"));
				p.setNombre(rs.getString("nombre"));
				personas.add(p);
			}
			
			for(int i = 0; i < personas.size(); i++)
			{
				System.out.println(personas.get(i).toString());
			}
			
			con.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}*/
}
