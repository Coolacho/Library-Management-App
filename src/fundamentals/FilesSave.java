package fundamentals;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class FilesSave {
	
	public static void saveUsers (HashMap<String, char[]> users) {
		
		try {	
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream("userInfo.txt"));
			oos.writeObject(users);
			oos.flush();
			oos.close();	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void saveBooks(ArrayList<Book> books) {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.txt"));
			oos.writeObject(books);
			oos.flush();
			oos.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void saveClients(ArrayList<Client> clients) {
		try {
			FileOutputStream fos = new FileOutputStream("clients.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(clients);
			oos.flush();
			oos.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
