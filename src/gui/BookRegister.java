package gui;

import javax.swing.*;
import javax.swing.table.*;

import fundamentals.BookTableModel;
import fundamentals.RegisterTemplate;

public class BookRegister extends RegisterTemplate{
	
	public BookTableModel tableModel;
	
	public BookRegister(JPanel contentPane) {
		super(contentPane);
	}

	@Override
	protected void setTableModel() {
		if (MainFrame.books != null) {
			tableModel = new BookTableModel(MainFrame.books);
		}
		else {
			tableModel = new BookTableModel();
		}
		table.setModel(tableModel);
	}

	@Override
	protected void setTableSorter() {
		tableSorter = new TableRowSorter<BookTableModel>(tableModel);
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