package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import fundamentals.Book;
import fundamentals.Client;

public class DatabaseDelete {
	
	public static boolean deleteUser(Connection connection, String username) {
		
		String query = "DELETE FROM users WHERE Username = " + username;
		
		try(Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean deleteBook(Connection connection, Book book) {
		
		String query = "DELETE FROM books WHERE BookID = " + Integer.toString(book.getId());
		
		try(Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean deleteClient(Connection connection, Client client) {
		
		String query = "DELETE FROM clients WHERE ClientID = " + Integer.toString(client.getId());
		
		try(Statement statement = connection.createStatement()) {
			statement.executeUpdate(query);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
