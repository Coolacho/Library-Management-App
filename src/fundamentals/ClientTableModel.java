package fundamentals;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ClientTableModel extends AbstractTableModel{
	
	private ArrayList<Client> clients;
	
	private final String[] columnNames = {"Client ID", "Name", "Age", "Email", "Telephone"};
	
	private static final long serialVersionUID = 1L;
	
	public ClientTableModel(ArrayList<Client> clients) {
		
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
			case 0: return Integer.class;
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
			case 0: client.setId((int)value); break;
			case 1: client.setName((String)value); break;
			case 2: client.setAge((byte)value); break;
			case 3: client.setEmail((String)value); break;
			case 4: client.setTelephone((String)value); break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public void insertClient(int rowIndex) {
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeClient(int rowIndex) {
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
}
