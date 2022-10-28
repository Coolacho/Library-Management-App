package fundamentals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public final class LoginSystemUtils {

		private static HashMap<String, char[]> USERS;
		private static int NUMBER_OF_EXISTING_USERS;
		private static final String FILE_PATH = "userInfo.txt";
		
		private LoginSystemUtils() {}
						
		@SuppressWarnings("unchecked")
		public static void loadUsers() {
									
			USERS = new HashMap<String, char[]>();
			
			if (Files.exists(Paths.get(FILE_PATH))) {
				
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH));
					USERS = (HashMap<String, char[]>)ois.readObject();
					ois.close();
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			
			NUMBER_OF_EXISTING_USERS = getCurrentNumberOfUsers();
			
		}
		
		public static boolean addUser(String username, char[] password) {
			
			if (getCurrentNumberOfUsers() <= 10) {
				if (!((USERS.containsKey(username)) || (USERS.containsValue(password)))) {
					USERS.put(username, password);
					return true;
				}
			}
			
			return false;
			
		}
		
		public static boolean checkUserInfo (String username, char[] password) {
			
			boolean isCorrect = false;
			char[] correctPassword = USERS.get(username);
									
			if (Arrays.equals(password, correctPassword)) isCorrect = true;
			else isCorrect = false;
												
			return isCorrect;									
		
		}
		
		public static void saveUsers () {
			
			if (NUMBER_OF_EXISTING_USERS != getCurrentNumberOfUsers()) {
				
				try {
					
					ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(FILE_PATH));
					oos.writeObject(USERS);
					oos.flush();
					oos.close();
					
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
		public static int getCurrentNumberOfUsers() {
			return USERS.size();
		}
		
		@SuppressWarnings("unchecked")
		public static ArrayList<Client> loadClients() {
			if (Files.exists(Paths.get("clients.txt"))) {
				
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("clients.txt"));
					ArrayList<Client> clients = new ArrayList<Client>();
					clients = (ArrayList<Client>)ois.readObject();
					ois.close();
					Client.NUMBER_OF_CLIENTS = clients.size();
					return clients;
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
			Client.NUMBER_OF_CLIENTS = 0;
			return new ArrayList<Client>();
		}
		
		@SuppressWarnings("unchecked")
		public static ArrayList<Book> loadBooks() {
			if (Files.exists(Paths.get("books.txt"))) {
				
				try {
					ObjectInputStream ois = new ObjectInputStream(new FileInputStream("books.txt"));
					ArrayList<Book> books = new ArrayList<Book>();
					books = (ArrayList<Book>)ois.readObject();
					ois.close();
					Book.NUMBER_OF_BOOKS = books.size();
					return books;
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
			Book.NUMBER_OF_BOOKS = 0;
			return new ArrayList<Book>();
		}
		
}
