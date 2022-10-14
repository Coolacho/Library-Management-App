package fundamentals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class BookListModel extends AbstractListModel<String>{
	
	private List<Book> books;
	
	public BookListModel() {
		books = new ArrayList<Book>();
	}
	
	public BookListModel(List<Book> books) {
		this.books = books;
	}

	@Override
	public int getSize() {
		return books.size();
	}

	@Override
	public String getElementAt(int index) {
		return books.get(index).getTitle();
	}

	private static final long serialVersionUID = 1L;
	
}
