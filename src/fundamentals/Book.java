package fundamentals;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Book implements Serializable{
	
	private int id;
	private String title;
	private String author;
	private boolean status;
	public static int NEXT_ID;
	private LocalDateTime timeTaken;
	private LocalDateTime timeReturned;
	
	private static final long serialVersionUID = 1L;
	
	public Book(String title, String author) {
		setId(++NEXT_ID);
		setTitle(title);
		setAuthor(author);
		setStatus(false);
	}
	
	public Book(int id, String title, String author, boolean status) {
		setId(id);
		setTitle(title);
		setAuthor(author);
		setStatus(status);
	}
	
	//Functions to manage the take and return of a book
	public void takeBook() {
		setStatus(true);
		setTimeTaken();
	}
	
	public void returnBook() {
		setStatus(false);
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
	
	//SETTERS
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
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
	
	public int getId() {
		return this.id;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public LocalDateTime getTimeTaken() {
		return this.timeTaken;
	}
	
	public LocalDateTime getTimeReturned() {
		return this.timeReturned;
	}
	
}
