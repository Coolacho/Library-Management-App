package fundamentals;

import java.sql.Connection;
import java.util.ArrayList;

import database.DatabaseDelete;
import database.DatabaseInsert;
import gui.MainMenu;

public class ClientModels {
	
	private ArrayList<Client> clients;
	private ClientTableModel tableModel;
	private ClientListModel listModel;
	
	public ClientModels(ArrayList<Client> clients) {
		
		this.clients = clients;
		tableModel = new ClientTableModel(clients);
		listModel = new ClientListModel(clients);
		
	}
	
	public boolean addClient(Connection connection, Client client) {
		
		if (DatabaseInsert.insertClient(connection, client)) {
			
			clients.add(client);
			tableModel.insertClient(clients.size()-1);
			listModel.insertClient(clients.size()-1);
			return true;
		}
		
		return false;
	}
	
	public void removeClient(Connection connection, int rowIndex) {
		
		if (DatabaseDelete.deleteClient(connection, getClientAt(rowIndex))) {
			
			clients.remove(rowIndex);
			tableModel.removeClient(rowIndex);
			listModel.removeClient(rowIndex);
			MainMenu.RECENT_ACTIVITY_PANE.newActivity("Removed a client!");
			
		}
	}
	
	public Client getClientAt(int rowIndex) {
		return clients.get(rowIndex);
	}
	
	public ArrayList<Client> getClients() {
		return clients;
	}

	public void setClients(ArrayList<Client> clients) {
		this.clients = clients;
	}

	public ClientTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(ClientTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public ClientListModel getListModel() {
		return listModel;
	}

	public void setListModel(ClientListModel listModel) {
		this.listModel = listModel;
	}
	
}
