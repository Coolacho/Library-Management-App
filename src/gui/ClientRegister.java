package gui;

import javax.swing.table.*;

import fundamentals.RegisterTemplate;
import fundamentals.ClientTableModel;

public class ClientRegister extends RegisterTemplate{
	
		private static final long serialVersionUID = 1L;
	
		public ClientRegister(MainFrame mainFrame) {
			super(mainFrame);
		}
		
		@Override
		protected void setTableModel(MainFrame mainFrame) {
			table.setModel(mainFrame.getClientModels().getTableModel());
		}

		@Override
		protected void setTableSorter(MainFrame mainFrame) {
			tableSorter = new TableRowSorter<ClientTableModel>(mainFrame.getClientModels().getTableModel());
			table.setRowSorter(tableSorter);
		}

		@Override
		protected void invokeAddFrame(MainFrame mainFrame) {
			new AddClientFrame(mainFrame);
		}

		@Override
		protected void removeSelection(MainFrame mainFrame) {
			mainFrame.getClientModels().removeClient(mainFrame.getDatabase().getConnection(), currentRow);
		}
}