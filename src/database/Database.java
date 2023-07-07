package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private Connection connection;
	private final static String URL = "jdbc:mariadb://localhost:3306/library management app";
	private final static String USER = "LibraryManagementAppClient";
	private final static String PASSWORD = "N3p6tILu";
	
	public boolean initConnection() {
		try {
			setConnection(DriverManager.getConnection(getURL(), getUSER(), getPASSWORD()));
			System.out.println("Successfully connected to database");
			return true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public boolean backUp() {
		
		//There is a syntax error in the SQL Statement. Fix it.
		String query = "BACKUP DATABASE library management app TO DISK = 'DBbackup.bak'";
		
		try(Statement statement = getConnection().createStatement()) {
			return statement.execute(query);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public String getURL() {
		return URL;
	}

	public String getUSER() {
		return USER;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}
	
}
