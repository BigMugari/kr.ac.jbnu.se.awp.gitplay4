package example;

import java.util.*;

public class SignUpClass {
	private Database db;
	public SignUpClass() {
		db = Database.getInstance();
	}
	
	public boolean signUp(UserInfo userInfo) {
		List values = new ArrayList();
		values.add(userInfo.getId());
		values.add(userInfo.getPassword());
		values.add(userInfo.getDateOfBirth());
		values.add(userInfo.getUsername());
		db.insert(Constants.DB_TABLE_USERSINFO, values);
		return true;
	}
}
