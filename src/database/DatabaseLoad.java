package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import fundamentals.Book;
import fundamentals.Client;

public class DatabaseLoad {
	
	public static HashMap<String, char[]> loadUsers(Connection connection) {
		
		HashMap<String, char[]> users = new HashMap<String, char[]>();
		String query = "SELECT * FROM users";
		
		try(Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				users.put(rs.getString("Username"), rs.getString("Password").toCharArray());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public static ArrayList<Book> loadBooks(Connection connection) {
		
		ArrayList<Book> books = new ArrayList<Book>();
		String query = "SELECT * FROM books";
		
		try(Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				books.add(new Book(rs.getInt("BookID"), rs.getString("Title"), rs.getString("Author"), rs.getBoolean("Status")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		if (books.size() == 0) {
			Book.NEXT_ID = 1;
		}
		else {
			Book.NEXT_ID = books.get(books.size()-1).getId();
		}
		
		return books;
	}
	
	public static ArrayList<Client> loadClients(Connection connection) {
		
		ArrayList<Client> clients = new ArrayList<Client>();
		String query = "SELECT * FROM clients";
		
		try(Statement statement = connection.createStatement()) {
			ResultSet rs = statement.executeQuery(query);
			while(rs.next()) {
				clients.add(new Client(rs.getString("Name"), rs.getByte("Age"), rs.getString("Email"), rs.getString("Telephone"), rs.getString("PhotoPath")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		if (clients.size() == 0) {
			Client.NEXT_ID = 1;
		}
		else {
			Client.NEXT_ID = clients.get(clients.size()-1).getId();
		}
		
		return clients;
	}

}
