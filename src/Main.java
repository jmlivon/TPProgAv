import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/claseprogav";
		String sql = "Select * From persona where dni = ?";
		List<Persona> personas = new ArrayList();
		
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
	}
}
