package gui;

import javax.swing.*;
import javax.swing.table.*;

import fundamentals.RegisterTemplate;
import fundamentals.BookModels;

public class BookRegister extends RegisterTemplate{
	
	public BookModels.BookTableModel tableModel;
	
	public BookRegister(JPanel contentPane) {
		super(contentPane);
	}

	@Override
	protected void setTableModel() {
		tableModel = MainFrame.bookModels.tableModel;
		table.setModel(tableModel);
	}

	@Override
	protected void setTableSorter() {
		tableSorter = new TableRowSorter<BookModels.BookTableModel>(tableModel);
		table.setRowSorter(tableSorter);
	}

	@Override
	protected void invokeAddFrame() {
		new AddBookFrame();
	}

	@Override
	protected void removeSelection() {
		tableModel.removeBook(currentRow);
	}
	
	private static final long serialVersionUID = 1L;
	
}