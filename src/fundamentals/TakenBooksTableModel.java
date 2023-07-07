package fundamentals;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TakenBooksTableModel extends AbstractTableModel{
	
	ArrayList<Book> takenBooks;
	
	private final String[] columnNames = {"№", "Book", "Status"};
	
	public TakenBooksTableModel(ArrayList<Book> takenBooks) {
		this.takenBooks = takenBooks;
	}

	@Override
	public int getRowCount() {
		return takenBooks.size();
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
			default: return String.class;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book takenBook = takenBooks.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return rowIndex+1;
			case 1: return takenBook.getTitle();
			case 2: return takenBook.getBookStatus();
			default: return null;
		}
	}
	
	private static final long serialVersionUID = 1L;
	
}
