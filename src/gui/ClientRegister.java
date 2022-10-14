package gui;

import javax.swing.*;
import javax.swing.table.*;

import fundamentals.ClientTableModel;
import fundamentals.RegisterTemplate;

public class ClientRegister extends RegisterTemplate{

		public ClientTableModel tableModel;
	
		public ClientRegister(JPanel contentPane) {
			super(contentPane);
		}


		@Override
		protected void setTableModel() {
			if (MainFrame.clients != null) {
				tableModel = new ClientTableModel(MainFrame.clients);
			}
			else {
				tableModel = new ClientTableModel();
			}
			table.setModel(tableModel);
		}

		@Override
		protected void setTableSorter() {
			tableSorter = new TableRowSorter<ClientTableModel>(tableModel);
			table.setRowSorter(tableSorter);
		}

		@Override
		protected void invokeAddFrame() {
			new AddClientFrame();
		}

		@Override
		protected void removeSelection() {
			tableModel.removeClient(currentRow);
		}
	
		private static final long serialVersionUID = 1L;
	
}