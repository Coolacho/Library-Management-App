package fundamentals;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class BookListModel extends AbstractListModel<String>{
	
	private ArrayList<Book> allBooks;
	private ArrayList<Book> selectedBooks;
	
	private static final long serialVersionUID = 1L;
	
	public BookListModel(ArrayList<Book> books) {
		
		allBooks = new ArrayList<Book>();
		for (Book book: books) {
			if (!book.getStatus()) allBooks.add(book);
		}
		selectedBooks = allBooks;
	}
	
	@Override
	public int getSize() {
		return allBooks.size();
	}

	@Override
	public String getElementAt(int index) {
		return allBooks.get(index).getTitle();
	}
	
	public void addBook(Book book) {
		insertBook(getSize(), book);
	}
	
	public void insertBook(int rowIndex, Book book) {
		allBooks.add(rowIndex, book);
		fireIntervalAdded(this, rowIndex, rowIndex);
	}
	
	public void removeBook(Book book) {
		int rowIndex = allBooks.indexOf(book);
		allBooks.remove(rowIndex);
		fireIntervalRemoved(this, rowIndex, rowIndex);
	}
	
	public ArrayList<Book> getSelectedBooks(int[] indices) {
		ArrayList<Book> selectedBooks = new ArrayList<Book>();
		for (int i: indices) {
			selectedBooks.add(allBooks.get(i));
		}
		this.selectedBooks = selectedBooks;
		return this.selectedBooks;
	}
	
	public void getAllBooks() {
		selectedBooks = allBooks;
	}
	
	public void updateAllBooks() {
		for (Book book: allBooks) {
			if (book.getStatus()) allBooks.remove(book);
		}
		getAllBooks();
	}
	
}