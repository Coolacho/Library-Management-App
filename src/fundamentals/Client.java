package fundamentals;

import java.io.Serializable;

public class Client implements Serializable{
	
	private String id;
	private String name;
	private byte age;
	private String email;
	private String telephone;
	private String photoPath;
	public static int NUMBER_OF_CLIENTS;
	
	
	public Client(String name, byte age, String email, String telephone, String photoPath) {
		
		setId(String.valueOf(++NUMBER_OF_CLIENTS));
		setName(name);
		setAge(age);
		setEmail(email);
		setTelephone(telephone);
		setPhoto(photoPath);
		
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
	
	private static final long serialVersionUID = 1L;
}