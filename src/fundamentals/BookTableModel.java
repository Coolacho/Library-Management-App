package fundamentals;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class BookTableModel extends AbstractTableModel{

	private ArrayList<Book> books;
	
	private final String[] columnNames = {"Book ID", "Title", "Author", "Taken"};
	
	private static final long serialVersionUID = 1L;
	
	public BookTableModel(ArrayList<Book> books) {
		
		this.books = books;
		
	}
	
	@Override
	public int getRowCount() {
		return books.size();
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
			case 3: return Boolean.class;
			default: return String.class;
		}
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		
		switch(columnIndex) {
			case 0: return book.getId();
			case 1: return book.getTitle();
			case 2: return book.getAuthor();
			case 3: return book.getStatus();
			default: return null;
		}
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		
		switch(columnIndex) {
			case 0: book.setId((Integer)value); break;
			case 1: book.setTitle((String)value); break;
			case 2: book.setAuthor((String)value); break;
			case 3: book.setStatus((Boolean)value); break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
	}
	
	public Book getBookAt(int rowIndex) {
		return books.get(rowIndex);
	}
	
	public void addBook(Book book) {
		insertBook(getRowCount(), book);
	}
	
	public void insertBook(int rowIndex, Book book) {
		books.add(rowIndex, book);
		fireTableRowsInserted(rowIndex, rowIndex);
	}
	
	public void removeBook(int rowIndex) {
		books.remove(rowIndex);
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public ArrayList<Book> getBooks() {
		return books;
	}
	
}
