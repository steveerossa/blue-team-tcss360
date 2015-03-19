/* TCSS 360 Winter 2015
 * 
 * Blue Team Group Project
 * 
 * Authors: Alex Day, Jeremiah Stowe, Stuart Hamm, Steve Onyango, Chutiwat Thammasiri
 * 
 * Project Description:
 * 	This is the final product of our project for Global Business Logistics.
 * 	We focused on logging in to the program as specified with the option of logging in
 * 	as an analyst or as an administrator.  Out other main focus was the main window
 * 	that the analyst uses to select the pre-approved answers when responding to an RFP.
 */
package Model;

import java.util.*;

import javax.swing.JOptionPane;

/**
 * The user class defines user objects that store the necessary info about
 * each user, as well as contains the method used for handling a login attempt
 * and the other methods necessary for unit testing.
 * @author Alex
 *
 */

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//													NOTE:
//		Though the program still works as expected, having this many public getters and setters is not good
//		coding practice and should be returned to it's previous version which was much more secure.
//
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
public class User {

	private String userName;
	private String userPin;
	private boolean isAdmin;
	private Map<String, User> userList;

	/**
	 * Empty constructor to initialize the user class and
	 * populate the list of currently registered users.
	 * @author Alex
	 */
	public User() {
		userList();
	}

	/**
	 * Overloaded constructor to create user objects with their related info.
	 * @param name  The user's user name.
	 * @param pin	The user's pin.
	 * @param admin	A boolean to tell if the user should be allowed administrative privileges or not.
	 * @author Alex
	 */
	public User(String name, String pin, boolean admin) {		
		setName(name); // changed to setMethods
		setPin(pin);
		setAdministrator(admin);
	}

	/**
	 * Gets the administrative status.
	 * @author Steve Onyango
	 * @return isAdmin
	 */
	public boolean getAdminStatus()
	{
		return isAdmin;
	}

	/**
	 * Returns user name.
	 * @author Steve Onyango
	 * @return userName the user name
	 */
	public final String getName()
	{
		return userName;
	}

	/**
	 * Returns user pin number.
	 * @author Steve Onyango
	 * @return userPin the user pin number
	 */
	public final String getPin()
	{
		return userPin;
	} 

	/**
	 * Method to handle an attempted login by a user.
	 * 
	 * Checks to see if the user list contains a user name that matches what the user typed in.
	 * 		If those match, the pins are matched against each other.
	 * 		If those match, it checks if the user tried to login as admin.
	 * 			If they did and they have admin privileges, they are granted access.
	 * 			If they didn't, they are granted access to the analyst page.
	 * 		If the pin or user name does not match what is in the user list, no access is granted and
	 * 		the user is warned that the info was not correct or that there is no such user registered, respectively.
	 *  
	 * @author Alex
	 * 
	 * Edited by Steve Onyango. Changed userPin, userName, and isAdmin to getName, getPin, and getAdminStatus.
	 *
	 */

	public boolean loginAttempt() {
		userList();
		boolean result = false;

		String loginName = this.getName().toString();

		if(userList.containsKey(loginName)) {
			User temp = userList.get(this.getName());
			if(this.getPin().compareTo(temp.getPin()) == 0) {
				if(this.getAdminStatus() && temp.getAdminStatus()) {
					result = true;
				}
				else if(this.getAdminStatus() && !temp.getAdminStatus()) {
					JOptionPane.showMessageDialog(null, "You do not have admin privileges.");
				}
				else {
					result = true;
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Incorrect User Name or PIN, Please Try Again.");
			}
		}
		else { 
			JOptionPane.showMessageDialog(null, "No such user exists, please contact administration to register.");
		}
		return result;
	}
	/**
	 * Class sets the administrative status.
	 * @author Steve Onyango
	 * @param the_admin_status the administrative status
	 */
	public void setAdministrator(final boolean the_admin_status)
	{
		isAdmin = the_admin_status;
	}	

	/**
	 * Sets user name.
	 * @author Steve Onyango
	 * @param the_name the user name
	 */
	public final void setName(final String the_name)
	{
		userName = the_name;
	} 
	/**
	 * Sets user pin.
	 * @author Steve Onyango
	 * @param the_pin the pin number
	 */
	public final void setPin(final String the_pin)
	{
		userPin = the_pin;
	}
	/**
	 * Private method to initialize the list of users.
	 * @author Chutiwat
	 */
	private void userList(){
		userList = new HashMap<String, User>();
		userList.put("todd", new User("todd", "0001", false));
		userList.put("karlene", new User("karlene", "0002", false));
		userList.put("scott", new User("scott", "0003", false));
		userList.put("cheryl", new User("cheryl", "0004", false));
		userList.put("terry", new User("terry", "0005", false));
		userList.put("martin", new User("martin", "0006", false));
		userList.put("bobby", new User("bobby", "0007", false));
		userList.put("brian", new User("brian", "0008", false));
		userList.put("lisa", new User("lisa", "0009", false));
		userList.put("daniel", new User("daniel", "0010", false));
		userList.put("steve", new User("steve", "0011", false));
		userList.put("john", new User("john", "0012", false));
		userList.put("admin", new User("admin", "1234", true));
	}
}
