package fundamentals;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class ClientListModel extends AbstractListModel<String>{
	
	private ArrayList<Client> clients;
	
	private static final long serialVersionUID = 1L;
	
	public ClientListModel(ArrayList<Client> clients) {
		
		this.clients = clients;
		
	}

	@Override
	public int getSize() {
		return clients.size();
	}

	@Override
	public String getElementAt(int index) {
		return clients.get(index).getName();
	}
	
	public void insertClient(int rowIndex) {
		fireIntervalAdded(this, rowIndex, rowIndex);
	}
	
	public void removeClient(int rowIndex) {
		fireIntervalRemoved(this, rowIndex, rowIndex);
	}
	
}
