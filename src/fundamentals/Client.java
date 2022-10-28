package fundamentals;

import java.awt.Image;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Client implements Serializable{
	
	private String id;
	private String name;
	private byte age;
	private String email;
	private String telephone;
	private String photoPath;
	private ArrayList<Book> booksTaken;
	public static int NUMBER_OF_CLIENTS;
	
	
	public Client(String name, byte age, String email, String telephone, String photoPath) {
		
		setId(String.valueOf(++NUMBER_OF_CLIENTS));
		setName(name);
		setAge(age);
		setEmail(email);
		setTelephone(telephone);
		setPhoto(photoPath);
		setBooksTaken();
		
	}
	
	//SETTERS
	public void setId(String id) {
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
	
	public void setBooksTaken() {
		this.booksTaken = new ArrayList<Book>();
	}
	
	//GETTERS
	public String getId() {
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
	
	public ArrayList<Book> getBooksTaken() {
		return this.booksTaken;
	}
	
	
	//Functions for taking and returning books
	public void takeBook(Book book) {
		book.takeBook();
		this.booksTaken.add(book);
	}
	
	public void returnBook(Book book) {
		book.returnBook();
		this.booksTaken.remove(book);
	}
	
	//Function for resizing the client photo to fit in a JLabel
	public static ImageIcon ResizeImage(String ImagePath, JLabel photoLabel) {
		
		ImageIcon rawImageIcon = new ImageIcon(ImagePath);
		Image rawImage = rawImageIcon.getImage();
		Image scaledImage = rawImage.getScaledInstance(photoLabel.getWidth(), photoLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		return scaledImageIcon;
	}
	
	private static final long serialVersionUID = 1L;
}