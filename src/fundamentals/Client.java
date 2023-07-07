package fundamentals;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Client implements Serializable{
	
	private int id;
	private String name;
	private byte age;
	private String email;
	private String telephone;
	private String photoPath;
	private ArrayList<Book> takenBooks;
	public static int NEXT_ID;
	
	private static final long serialVersionUID = 1L;
	
	public Client(String name, byte age, String email, String telephone, String photoPath) {
		
		setId(++NEXT_ID);
		setName(name);
		setAge(age);
		setEmail(email);
		setTelephone(telephone);
		setPhoto(photoPath);
		setTakenBooks();
		
	}
	
	public Client(int id, String name, byte age, String email, String telephone, String photoPath) {
		
		setId(id);
		setName(name);
		setAge(age);
		setEmail(email);
		setTelephone(telephone);
		setPhoto(photoPath);
		setTakenBooks();
		
	}
	
	//Functions for taking and returning books
	public void takeBook(Book book) {
		book.takeBook();
		this.takenBooks.add(book);
	}
	
	public void takeBook(ArrayList<Book> books) {
		for (Book book: books) {
			takeBook(book);
		}
	}
	
	public void returnBook(Book book) {
		book.returnBook();
		this.takenBooks.remove(book);
	}
	
	public void returnBook(ArrayList<Book> books) {
		for (Book book: books) {
			returnBook(book);
		}
	}
	
	//Function for resizing the client photo to fit in a JLabel
	public static ImageIcon ResizeImage(String ImagePath, JLabel photoLabel) {
		
		ImageIcon rawImageIcon = new ImageIcon(ImagePath);
		Image rawImage = rawImageIcon.getImage();
		Image scaledImage = rawImage.getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		return scaledImageIcon;
	}
	
	//SETTERS
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAge(byte age) {
		this.age = age;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public void setPhoto(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public void setTakenBooks() {
		this.takenBooks = new ArrayList<Book>();
	}
	
	//GETTERS
	public int getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public byte getAge() {
		return this.age;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelephone() {
		return this.telephone;
	}
	
	public String getPhotoPath() {
		return this.photoPath;
	}
	
	public ArrayList<Book> getTakenBooks() {
		return this.takenBooks;
	}
	
}