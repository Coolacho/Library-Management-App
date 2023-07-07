package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fundamentals.Book;
import fundamentals.Client;

public class DatabaseInsert {
	
	public static boolean insertUser(Connection connection, String username, char[] password) {
		
		String query = "INSERT INTO users(Username, Password) VALUES(" + username + ", " + password.toString() + ")";
		
		try(Statement statement = connection.prepareStatement(query)) {
			statement.executeUpdate(query);
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean insertBook(Connection connection, Book book) {
		
		String query = "INSERT INTO books(Title, Author) VALUES(?, ?)";
		
		try(PreparedStatement prepStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setString(1, book.getTitle());
			prepStatement.setString(2, book.getAuthor());
			prepStatement.addBatch();
			prepStatement.executeBatch();
			ResultSet rs = prepStatement.getGeneratedKeys();
			rs.next();
			book.setId(rs.getInt(1));
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean insertClient(Connection connection, Client client) {
		
		String query = "INSERT INTO clients(Name, Age, Email, Telephone, Photopath) VALUES(?, ?, ?, ?, ?)";
		
		try(PreparedStatement prepStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			prepStatement.setString(1, client.getName());
			prepStatement.setByte(2, client.getAge());
			prepStatement.setString(3, client.getEmail());
			prepStatement.setString(4, client.getTelephone());
			prepStatement.setString(5, client.getPhotoPath());
			prepStatement.addBatch();
			prepStatement.executeBatch();
			ResultSet rs = prepStatement.getGeneratedKeys();
			rs.next();
			client.setId(rs.getInt(1));
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
