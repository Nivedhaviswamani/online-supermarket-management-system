package project;
import java.sql.*;
public class Connections1 {

	public Connection connect() {
		String url="jdbc:mysql://localhost:3306/inventory";
		String username="root";
		String password="";
		Connection connection=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection=DriverManager.getConnection(url,username,password);  
		}
		catch(Exception e)
		{
			System.out.println("error"+e);
		}
		return connection;
	}

}

/*
CREATE TABLE admin (
email VARCHAR(30) PRIMARY KEY CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$'),
password VARCHAR(30) CHECK (password REGEXP '^(?=.*[A-Za-z])(?=.*\\d).{8,}$')
);
*/