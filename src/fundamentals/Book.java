package fundamentals;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Book implements Serializable{
	
	private String title;
	private String author;
	private String id;
	private boolean taken;
	public static int NUMBER_OF_BOOKS;
	private LocalDateTime timeTaken;
	private LocalDateTime timeReturned;
	
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
	
	public void setTimeTaken() {
		this.timeTaken = LocalDateTime.now();
		this.timeReturned = null;
	}
	
	public void setTimeReturned() {
		this.timeReturned = LocalDateTime.now();
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
	
	public LocalDateTime getTimeTaken() {
		return this.timeTaken;
	}
	
	public LocalDateTime getTimeReturned() {
		return this.timeReturned;
	}
	
	//Functions to manage the take and return of a book
	public void takeBook() {
		setTaken(true);
		setTimeTaken();
	}
	
	public void returnBook() {
		setTaken(false);
		setTimeReturned();
	}
	
	public String getBookStatus() {
		if (timeReturned == null) {
			return "Taken";
		}
		else if (timeReturned.isBefore(timeTaken.plusDays(14))) {
			return "Returned";
		}
		else {
			return "Overdue";
		}
	}
	
	private static final long serialVersionUID = 1L;
	
}
