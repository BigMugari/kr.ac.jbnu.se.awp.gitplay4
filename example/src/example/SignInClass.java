package example;

import java.util.*;

public class SignInClass {
	private Database db;
	private String id;
	private String password;
	
	public SignInClass() {
		db = Database.getInstance();
	}
	
	public boolean SignIn(String id, String password) {
		List column = new ArrayList();
		List table = new ArrayList();
		Map currentUser;
		String idid ="id = ?";
		column.add("id");
		column.add("password");
		table.add(Constants.DB_TABLE_USERSINFO);
		currentUser = (Map) db.select(column, table, idid, id).get(0);
		System.out.println(currentUser.get("id"));
		System.out.println(currentUser.get("id").toString());
		this.id = currentUser.get("id").toString();
		this.password = currentUser.get("password").toString();
		return true;
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
}
