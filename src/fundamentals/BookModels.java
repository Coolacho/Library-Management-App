package fundamentals;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.table.AbstractTableModel;

import gui.MainMenu;

public class BookModels {
	
	private ArrayList<Book> books;
	
	public BookTableModel tableModel;
	public BookListModel listModel;
	
	public BookModels() {
		
		books = LoginSystemUtils.loadBooks();
		tableModel = new BookTableModel();
		listModel = new BookListModel();
		
	}
	
	//++++++++++++++++++++++++++++++++++ TABLE MODEL +++++++++++++++++++++++++++++++++++++++++++
	
	public class BookTableModel extends AbstractTableModel{

		private final String[] columnNames = {"Book ID", "Title", "Author", "Taken"};
		
		public BookTableModel() {}
		
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
			listModel.addBookToList(rowIndex);
		}
		
		public void removeBook(int rowIndex) {
			books.remove(rowIndex);
			fireTableRowsDeleted(rowIndex, rowIndex);
			listModel.removeBookFromList(rowIndex);
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
	
	//+++++++++++++++++++++++++++++ LIST MODEL +++++++++++++++++++++++++++++++++++++
	
	public class BookListModel extends AbstractListModel<String>{
		
		public BookListModel() {}
		
		@Override
		public int getSize() {
			return books.size();
		}

		@Override
		public String getElementAt(int index) {
			return books.get(index).getTitle();
		}
		
		public void addBookToList(int rowIndex) {
			fireIntervalAdded(this, rowIndex, rowIndex);
		}
		
		public void removeBookFromList(int rowIndex) {
			fireIntervalRemoved(this, rowIndex, rowIndex);
		}

		private static final long serialVersionUID = 1L;
		
	}

}
