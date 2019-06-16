package utilidades;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class UConnection
{
	public static Connection connection = null;
	
	private UConnection()
	{
		
	}
	
	public static Connection getConnection()
	{
		if(connection==null)
		{
			Properties properties = new Properties();
		    InputStream input = null;
		    
			try
			{
				input = new FileInputStream("configuration.properties");
		        properties.load(input);
				
				String driver = properties.getProperty("key.driver");
				String url = properties.getProperty("key.url");
				String user = properties.getProperty("key.username");
				String password = properties.getProperty("key.password");
				
				Class.forName(driver);
				connection = DriverManager.getConnection(url,user,password);
			}
			catch(Exception e)
			{
				System.out.println("[getConnection] - Error: " + e.getMessage());
			}			
		}
		return connection;
	}
}