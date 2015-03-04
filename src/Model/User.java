//sun & alex

package Model;

public class User {
	
	private String userName;
	private String userPin;
	private boolean isAdmin;
	
	public User (String name, String pin, boolean admin) {
		userName = name;
		userPin = pin;
		isAdmin = admin;
	}

}
