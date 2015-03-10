//stuart

package Model;



public class Login {

	public void loginAttempt(String userName, String pin, boolean isAdmin) {
		// TODO Auto-generated method stub

	}

//	////////////////////////////////////////////////////////////////////////////////////Jeremiah Added this nonsense
//	/*Pretty sure we don't want this to be right here but since 
//	 *the user class is created here locally with the login details 
//	 *then either the authentication check has to happen here or we 
//	 *have to pass the user somewhere else like to the login model
//	 *Once it gets past this point we need a way to launch the actual RFP.*/ 
//
//	temp.UserList();//The way User is written you have to create the list of acceptable users.
//
//	//The next line checks to make sure the typed data matches the user list
//	if(temp.userlist.containsKey(temp.getName())&& temp.userlist.get(temp.getName()).equals(temp.getPin())){
//		//Rather than this println replace with the action that creates the RFP
//		//System.out.println("Authenticated");
//		//I added the my_mainFrame field so that it could be accessed from inside the class
//		//Again this is a design choice we either need to move it all outside the class and have
//		//all this happen in the Login model class or just do it all here.
//		my_mainFrame.getContentPane().removeAll();
//		my_mainFrame.setBounds(100, 100, 1024, 720);
//		my_mainFrame.getContentPane().add(new RFPView().rootPane);
//	}
//	else{
//		Toolkit.getDefaultToolkit().beep();
//		JOptionPane.showMessageDialog(panel,
//				"Either Username or Pin are incorrect.",
//				"Login Failed",
//				JOptionPane.ERROR_MESSAGE);
//	}
//	///////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
