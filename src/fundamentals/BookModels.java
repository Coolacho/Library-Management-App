package fundamentals;

import java.sql.Connection;
import java.util.ArrayList;

import database.DatabaseDelete;
import database.DatabaseInsert;
import gui.MainMenu;

public class BookModels {
	
	private BookTableModel tableModel;
	private BookListModel listModel;
	
	public BookModels(ArrayList<Book> books) {
		
		tableModel = new BookTableModel(books);
		listModel = new BookListModel(books);
		
	}
	
	public boolean addBook(Connection connection, Book book) {
		
		if (DatabaseInsert.insertBook(connection, book)) {
			
			tableModel.addBook(book);
			listModel.addBook(book);
			return true;
			
		}
		
		return false;
	}
	
	public void removeBook(Connection connection, int rowIndex) {
		Book book = tableModel.getBookAt(rowIndex);
		if (!book.getStatus()) {
			if (DatabaseDelete.deleteBook(connection, book)) {
				tableModel.removeBook(rowIndex);
				listModel.removeBook(book);
				MainMenu.RECENT_ACTIVITY_PANE.newActivity("Removed a book!");
			}
		}
	}
	
	public BookTableModel getTableModel() {
		return tableModel;
	}
	
	public void setTableModel(BookTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public BookListModel getListModel() {
		return listModel;
	}
	
	public void setListModel(BookListModel listModel) {
		this.listModel = listModel;
	}
	
}
