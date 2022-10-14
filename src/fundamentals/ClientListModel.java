package fundamentals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class ClientListModel extends AbstractListModel<String>{

	private List<Client> clients;
	
	public ClientListModel() {
		clients = new ArrayList<Client>();
	}
	
	public ClientListModel(List<Client> clients) {
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

	private static final long serialVersionUID = 1L;
	
}
