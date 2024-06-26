package p1;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class DbConnection {
	public static Connection con;
	
	public static Statement Connection() throws SQLException,ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/TravelPlanner","root","tiger");
		Statement st=con.createStatement();
		return st;
			
		}
		
	

}
