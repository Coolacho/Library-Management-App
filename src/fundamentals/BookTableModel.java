package fundamentals;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;

import gui.MainMenu;

public class BookTableModel extends AbstractTableModel{

	private final String[] columnNames = {"Book ID", "Title", "Author", "Taken"};
	
	private List<Book> books;
	
	public BookTableModel() {
		books = new ArrayList<Book>();
	}
	
	public BookTableModel(List<Book> books) {
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
			case 3: return book.getTaken();
			default: return null;
		}
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		
		switch(columnIndex) {
			case 0: book.setId((String)value); break;
			case 1: book.setTitle((String)value); break;
			case 2: book.setAuthor((String)value); break;
			case 3: book.setTaken((Boolean)value); break;
		}
		
		fireTableCellUpdated(rowIndex, columnIndex);
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
		MainMenu.RECENT_ACTIVITY_PANE.newActivity("Removed a book!");
	}
	
	public void saveBooks() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.txt"));
			oos.writeObject(books);
			oos.flush();
			oos.close();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = 1L;
}