package fundamentals;

import java.io.Serializable;

public class Book implements Serializable{
	
	private String title;
	private String author;
	private String id;
	private boolean taken;
	public static int NUMBER_OF_BOOKS;
	
	public Book(String title, String author) {
		setTitle(title);
		setAuthor(author);
		setId(String.valueOf(++NUMBER_OF_BOOKS));
		setTaken(false);
	}
	
	//SETTERS
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	
	//GETTERS
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getId() {
		return this.id;
	}
	
	public boolean getTaken() {
		return this.taken;
	}
	
	private static final long serialVersionUID = 1L;
	
}
