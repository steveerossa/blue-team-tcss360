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
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Main;
import Model.User;
//import Model.User;

/**
 * Login view class to initialize the login page.
 * @author Alex
 *
 */
public class LoginView {
	
	/*Added this JFrame field "my_mainFrame" to access elsewhere in the class
	Will need to pass control of the mainframe somehow to update to the RFP screen
	If not here somewhere else*/
	private JFrame my_mainFrame;
	
	private JLabel lblLogin;
	private JLabel lblchckAdmin;
	private JTextField userNameTF;
	private JTextField pinTF;
	private JPanel panel;
	private JButton btnLogin;
	private JCheckBox chckAdmin;
	private User temp;
	
	
	public LoginView() {
		
		lblLogin = new JLabel("Login:");
		lblchckAdmin = new JLabel("Admin?");
		userNameTF = new JTextField();
		pinTF = new JTextField();
		panel = new ImagePanel();
		btnLogin = new JButton("Login");
		chckAdmin = new JCheckBox();
		temp = new User();
	}

	public void initializeLogin(JFrame mainFrame) {
		
		//////////////////////////////////This is where I attached the reference for the main frame to this class's field
		my_mainFrame = mainFrame;
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				this.getClass().getResource("/files/title_bar_icon.png")));
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
		pinTF.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				pinTF.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(pinTF.getText() == "") {
					pinTF.setText("PIN");
				}
			}
			
		});
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				temp = new User(userNameTF.getText(), pinTF.getText(), chckAdmin.isSelected());
				if(temp.loginAttempt() == true) {
					if(chckAdmin.isSelected()) {
						AdminView adminView = new AdminView();
						adminView.initializeAdminView(my_mainFrame);
					}
					else {
						RFPView rfpView = new RFPView();
						rfpView.initialize(my_mainFrame);
					}
				};				
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
