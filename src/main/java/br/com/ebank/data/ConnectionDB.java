package br.com.ebank.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

	public static Connection getConnection() {
		
		Connection con = null;
		
		String url = "jdbc:postgresql://localhost:5432/ebank";
		String user = "postgres";
		String password = "eli010612";
		
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	
}
