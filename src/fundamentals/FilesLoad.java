package fundamentals;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesLoad {
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, char[]> loadUsers() {
								
		HashMap<String, char[]> users = new HashMap<String, char[]>();
		
		if (Files.exists(Paths.get("userInfo.txt"))) {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userInfo.txt"));
				users = (HashMap<String, char[]>)ois.readObject();
				ois.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return users;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Client> loadClients() {
		
		ArrayList<Client> clients = new ArrayList<Client>();
		
		if (Files.exists(Paths.get("clients.txt"))) {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clients.txt"));
				clients = (ArrayList<Client>)ois.readObject();
				ois.close();
				Client.NEXT_ID = clients.size();
				return clients;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		Client.NEXT_ID = 0;
		return clients;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Book> loadBooks() {
		
		ArrayList<Book> books = new ArrayList<Book>();
		
		if (Files.exists(Paths.get("books.txt"))) {
			
			try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.txt"));
				books = (ArrayList<Book>)ois.readObject();
				ois.close();
				Book.NEXT_ID = Integer.valueOf(books.get(books.size()-1).getId());
				return books;
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
		}
		Book.NEXT_ID = 0;
		return books;
	}
}
