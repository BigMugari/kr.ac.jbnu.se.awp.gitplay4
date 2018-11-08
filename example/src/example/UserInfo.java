package example;
import java.util.*;
import java.text.*;

public class UserInfo {
	private String id;
	private String password;
	private String dateOfBirth;
	private String username;
	
	public UserInfo(String id, String password, String dateOfBirth, String username) {
		this.id = id;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.username = username;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password; 
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
