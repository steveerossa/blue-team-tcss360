/* TCSS 360 Winter 2015
 * GBL project
 * This class sets up the login view for the program.
 */
package View;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Main;
import Model.User;

/**
 * Login view class to initialize the login page.
 * @author Alex
 *
 */
public class LoginView {
	//////////////////////////////////Added this JFrame field to access elsewhere in the class
	private JFrame my_mainFrame;
	private JLabel lblLogin;
	private JLabel lblchckAdmin;
	private JTextField userNameTF;
	private JTextField pinTF;
	private JPanel panel;
	private JButton btnLogin;
	private JCheckBox chckAdmin;
	
	
	public LoginView() {
		
		lblLogin = new JLabel("Login:");
		lblchckAdmin = new JLabel("Admin?");
		userNameTF = new JTextField();
		pinTF = new JTextField();
		panel = new ImagePanel();
		btnLogin = new JButton("Login");
		chckAdmin = new JCheckBox();
		
	}

	public void initializeLogin(JFrame mainFrame) {
		
		//////////////////////////////////This is where I attached the reference to the main frame to the class field
		my_mainFrame = mainFrame;
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				Main.class.getResource("/files/title_bar_icon.png")));
		addListeners();
		
		lblLogin.setBounds(316, 140, 46, 14);
		chckAdmin.setBounds(360, 138, 20, 20);
		lblchckAdmin.setBounds(380, 141, 46, 14);

		userNameTF.setText("Username");
		userNameTF.setBounds(316, 165, 86, 20);
		userNameTF.setColumns(10);

		pinTF.setText("PIN");
		pinTF.setBounds(316, 198, 86, 20);
		pinTF.setColumns(10);
		
		btnLogin.setBounds(316, 236, 86, 20);

		panel.setBounds(0, 0, 332, 285);
		
		mainFrame.getContentPane().add(lblLogin);
		mainFrame.getContentPane().add(chckAdmin);
		mainFrame.getContentPane().add(lblchckAdmin);
		mainFrame.getContentPane().add(pinTF);
		mainFrame.getContentPane().add(userNameTF);
		mainFrame.getContentPane().add(btnLogin);
		mainFrame.getContentPane().add(panel);	
	}
	
	private void addListeners() {
		userNameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(userNameTF.getText().isEmpty()) {
					userNameTF.setText("UserName");
				}
			}
		});
		userNameTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(userNameTF.getText().equalsIgnoreCase("Username")) {
					userNameTF.setText("");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(userNameTF.getText().isEmpty()) {
					userNameTF.setText("Username");
				}
			}
		});
		pinTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(pinTF.getText().isEmpty()) {
					pinTF.setText("PIN");

				}
			}
		});

		pinTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pinTF.getText().toString().equalsIgnoreCase("PIN")) {
					pinTF.setText("");
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(pinTF.getText().isEmpty()) {
					pinTF.setText("PIN");
				}
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				User temp = new User(userNameTF.getText(), pinTF.getText(), chckAdmin.isSelected());
				//////////////////////////////////////////////////////////////////////////////////// Jeremiah Added this nonsense
				//Pretty sure we don't want this to be right here but since the user is created here locally
				//either the authentication check has to happen here or we have to pass the user somewhere else
				//Once it gets past this point we need a way to launch the actual RFP. 
				temp.UserList();//The way User is written you have to create the list of acceptable users.
				//The next line checks to make sure the typed data matches the user list
				if(temp.userlist.containsKey(temp.getName())&& temp.userlist.get(temp.getName()).equals(temp.getPin())){
					//Rather than this println replace with the action that creates the RFP
					//System.out.println("Authenticated");
					//I added the my_mainFrame field so that it could be accessed from inside the class
					//Again this is a design choice we either need to move it all outside the class and have
					//all this happen in the Login model class or just do it all here.
					my_mainFrame.getContentPane().removeAll();
					my_mainFrame.setBounds(100, 100, 1024, 720);
					my_mainFrame.getContentPane().add(new RFPView().rootPane);
					}
				else{
					Toolkit.getDefaultToolkit().beep();
					JOptionPane.showMessageDialog(panel,
						    "Either Username or Pin are incorrect.",
						    "Login Failed",
						    JOptionPane.ERROR_MESSAGE);
				}
				////////////////////////////////////////////////////////////////////////////////////
			}
		});
	}
	@SuppressWarnings("serial")
	private class ImagePanel extends JPanel{

		private BufferedImage image;

		public ImagePanel() {
			try {                
				image = ImageIO.read(Main.class.getResource("/files/smaller.png"));
			} catch (IOException ex) {
				//just toss it cause we smart
			}
			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(image, 0, 0, null);         
		}
	}
}
