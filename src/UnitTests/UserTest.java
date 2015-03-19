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
package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.User;

/**
 * JUnit test class to test the User.java class.
 * 
 * @author Steve Onyango
 * @version Mar 16, 2015
 */
public class UserTest 
{
	/**The User Object to be tested.*/
	private User my_user;
	/**The pin number of User.*/
	private final String my_pin = "5678";
	/**The username of User.*/
	private final String my_username = "steve";
	/**The Administrative status of User.*/
	private final boolean my_admin_status = true;
	

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		my_user = new User(my_username,my_pin,my_admin_status );
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		System.out.println("End testing.");
	}

	/**
	 * Test method for {@link Model.User#getAdminStatus()}.
	 */
	@Test
	public void testGetAdminStatus() 
	{
		assertEquals("The Administrative status do not match.", my_admin_status, my_user.getAdminStatus());
	}

	/**
	 * Test method for {@link Model.User#getName()}.
	 */
	@Test
	public void testGetName()
	{
		// already tested above
		assertEquals("The username do not match.", my_username, my_user.getName());
		 
	}

	/**
	 * Test method for {@link Model.User#getPin()}.
	 */
	@Test
	public void testGetPin() 
	{
		assertEquals("The pin numbers do not match.", my_pin, my_user.getPin()); // a retest
	}
 
	/**
	 * Test method for {@link Model.User#loginAttempt()}.
	 */
	@Test
	public void testLoginAttempt()
	{
		 
		assertFalse("Login succesful", my_user.loginAttempt()); 
		assertFalse("Login failed", new User("todd", "0091", false).loginAttempt());
		assertTrue("Login failed", new User("todd", "0001", false).loginAttempt());
		assertFalse("Login failed", new User("todd", "0001", true).loginAttempt());
		assertTrue("Login failed", new User("admin", "1234", true).loginAttempt()); 
		assertTrue("Login failed", new User("karlene", "0002", false).loginAttempt()); 
		assertTrue("Login failed", new User("admin", "1234", false).loginAttempt()); 
		
	}

	/**
	 * Test method for {@link Model.User#setAdministrator(boolean)}.
	 */
	@Test
	public void testSetAdministrator() 
	{
		my_user.setAdministrator(false);
		assertFalse("Admin status is TRUE", my_user.getAdminStatus());
		my_user.setAdministrator(true);
		assertTrue("Admin status is FALSE", my_user.getAdminStatus());
		 
	}

	/**
	 * Test method for {@link Model.User#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName()
	{
		User user = new User("mike", "0111",false);
		user.setName("fred");
		assertEquals("Userames not same", "fred", user.getName());
	}

	/**
	 * Test method for {@link Model.User#setPin(java.lang.String)}.
	 */
	@Test
	public void testSetPin() 
	{
		my_user.setPin("0911");
		assertEquals("Pin numbers do not match", "0911", my_user.getPin());
	}

	/**
	 * Test method for {@link Model.User#User()}.
	 */
	@Test
	public void testUser()
	{
		// Nothing to test in default constructor
	}

	/**Tests parameterized User constructor.
	 * Test method for {@link Model.User#User(java.lang.String, java.lang.String, boolean)}.
	 */
	@Test
	public void testUserStringStringBoolean() 
	{
		assertEquals("The username do not match.", my_username, my_user.getName());
		assertEquals("The pin numbers do not match.", my_pin, my_user.getPin());
		assertEquals("The Administrative status do not match.", my_admin_status, my_user.getAdminStatus());
	}

}
