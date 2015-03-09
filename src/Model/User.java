//sun & alex


package Model;

import java.util.*;

public class User {
	
	private String userName;
	private String userPin;
	private boolean isAdmin;
	public Map<String, String> userlist;
	
	
	public User (String name, String pin, boolean admin) {		
		userName = name;
		userPin = pin;
		isAdmin = admin;
		
		
	}
	//public method that return user object list of users and pins
	//
	public void UserList(){
		userlist = new HashMap<String, String>();
		userlist.put("todd", "0001");
		userlist.put("karlene", "0002");
		userlist.put("scott", "0003");
		userlist.put("cheryl", "0004");
		userlist.put("terry", "0005");
		userlist.put("martin", "0006");
		userlist.put("bobby", "0007");
		userlist.put("brian", "0008");
		userlist.put("lisa", "0009");
		userlist.put("daniel", "0010");
		userlist.put("steve", "0011");
		userlist.put("john", "0012");
		userlist.put("admin", "1234");
		
	}
	
	public String getName()
	{
		return userName;
		
	}
	
	public String getPin()
	{
		return userPin;
		
	}
}
