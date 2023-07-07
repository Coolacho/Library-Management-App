package gui;

import javax.swing.table.*;

import fundamentals.RegisterTemplate;
import fundamentals.BookTableModel;

public class BookRegister extends RegisterTemplate{
	
	private static final long serialVersionUID = 1L;
	
	public BookRegister(MainFrame mainFrame) {
		super(mainFrame);
	}
	
	@Override
	protected void setTableModel(MainFrame mainFrame) {
		table.setModel(mainFrame.getBookModels().getTableModel());
	}

	@Override
	protected void setTableSorter(MainFrame mainFrame) {
		tableSorter = new TableRowSorter<BookTableModel>(mainFrame.getBookModels().getTableModel());
		table.setRowSorter(tableSorter);
	}

	@Override
	protected void invokeAddFrame(MainFrame mainFrame) {
		new AddBookFrame(mainFrame);
	}

	@Override
	protected void removeSelection(MainFrame mainFrame) {
		mainFrame.getBookModels().removeBook(mainFrame.getDatabase().getConnection(), currentRow);
	}
	
}