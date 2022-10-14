package gui;

import javax.swing.*;
import javax.swing.table.*;

import fundamentals.RegisterTemplate;
import fundamentals.ClientModels;

public class ClientRegister extends RegisterTemplate{

		public ClientModels.ClientTableModel tableModel;
	
		public ClientRegister(JPanel contentPane) {
			super(contentPane);
		}


		@Override
		protected void setTableModel() {
			tableModel = MainFrame.clientModels.tableModel;
			table.setModel(tableModel);
		}

		@Override
		protected void setTableSorter() {
			tableSorter = new TableRowSorter<ClientModels.ClientTableModel>(tableModel);
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