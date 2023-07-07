package fundamentals;

import java.sql.Connection;
import java.util.Arrays;
import java.util.HashMap;

import database.DatabaseDelete;
import database.DatabaseInsert;

public class UserSystem {

		private HashMap<String, char[]> users;
		
		public UserSystem(HashMap<String, char[]> users) {
			this.users = users;
		}
						
		public boolean addUser(Connection connection, String username, char[] password) {
			
			if (users.size() <= 10) {
				if (DatabaseInsert.insertUser(connection, username, password)) {
					users.put(username, password);
					return true;
				}
			}
			
			return false;
			
		}
		
		public boolean removeUser(Connection connection, String username, char[] password) {
			
			if (DatabaseDelete.deleteUser(connection, username)) {
				return users.remove(username, password);
			}
			
			return false;
		}
		
		public boolean checkUserInfo (String username, char[] password) {
			
			boolean isCorrect = false;
			char[] correctPassword = users.get(username);
									
			if (Arrays.equals(password, correctPassword)) isCorrect = true;
			else isCorrect = false;
												
			return isCorrect;									
		
		}

		public HashMap<String, char[]> getUsers() {
			return users;
		}

		public void setUsers(HashMap<String, char[]> users) {
			this.users = users;
		}
		
		
}
