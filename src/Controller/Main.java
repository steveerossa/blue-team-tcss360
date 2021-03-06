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

package Controller;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.UIManager;

import View.LoginView;


/**
 * This class initializes the program.  It contains the main method that is looked for 
 * when the program is started which starts up the login page and initializes all other
 * necessary  parts of the program.
 * @author Alex Day
 * 
 */
public class Main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(
					            UIManager.getSystemLookAndFeelClassName());
					Main window = new Main();
					window.mainFrame.setVisible(true);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private JFrame mainFrame;


	public LoginView loginView;

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @author Alex
	 */
	private void initialize() 
	{
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setTitle("Global Business Logistics");
		mainFrame.setBounds(100, 100, 488, 329);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);

		loginView = new LoginView();
		loginView.initializeLogin(mainFrame);

	}
	
}
