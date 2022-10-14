package fundamentals;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;

import gui.MainMenu;

public class ClientTableModel extends AbstractTableModel{
	
	private final String[] columnNames = {"Client ID", "Name", "Age", "Email", "Telephone"};
	
	private List<Client> clients;
	
	public ClientTableModel() {
		clients = new ArrayList<Client>();
	}
	
	public ClientTableModel(List<Client> clients) {
		this.clients = clients;
	}
	
	@Override
	public int getRowCount() {
		return clients.size();
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columnNames[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex) {
			case 2: return Byte.class;
			default: return String.class;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Client client = clients.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return client.getId();
			case 1: return client.getName();
			case 2: return client.getAge();
			case 3: return client.getEmail();
			case 4: return client.getTelephone();
			default: return null;
		}
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Client client = clients.get(rowIndex);
		
		switch(columnIndex) {
			case 0: client.setId((String)value); break;
			case 1: client.setName((String)value); break;
			case 2: client.setAge((byte)value); break;
			case 3: client.setEmail((String)value); break;
			case 4: client.setTelephone((String)value); break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void addClient(Client client) {
		insertClient(getRowCount(), client);
	}
	
	public void insertClient(int rowIndex, Client client) {
		clients.add(rowIndex, client);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeClient(int rowIndex) {
		clients.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
		MainMenu.RECENT_ACTIVITY_PANE.newActivity("Removed a client!");
	}
	
	public void saveClients() {
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
	
	private static final long serialVersionUID = 1L;
}
