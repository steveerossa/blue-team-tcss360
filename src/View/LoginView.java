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
package View;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import Model.Database;
import Model.FocusTraversalOnArray;
import Model.User;

/**
 * Login view class to initialize the login page.
 * @author Alex
 *
 */
public class LoginView {
	
	/**
	 * Key listener class for streamlining login user experience.//not sure why its its own class?
	 * @author Alex
	 *
	 */
	private class enterKeyLoginListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				loginActionAttempted();
			}			
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// Do nothing cause we smart
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
			// Do nothing cause we smart
		}
	}
	/**
	 * Custom JPanel to make things pretty.
	 * @author Alex
	 *
	 */
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
	private JLabel lblLogin;
	private JLabel lblchckAdmin;
	private JTextField userNameTF;
	private JTextField pinTF;
	private JPanel panel;
	private JButton btnLogin;
	private JCheckBox chckAdmin;
	
	private User temp;
	private Database database;

	private JFrame my_mainFrame;
	
	/**
	 * No arg constructor to initialize vars and objects
	 * @author Alex
	 */
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
	
	/**
	 * Helper method to add listeners to components.
	 * @author Alex
	 * Edits by Jeremiah
	 */
	private void addListeners() {
		chckAdmin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				
				chckAdmin.setBorderPainted(true);
			}
			@Override
			public void focusLost(FocusEvent arg0) {
				chckAdmin.setBorderPainted(false);
			}
		});
		chckAdmin.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					if(chckAdmin.isSelected()) {
						chckAdmin.setSelected(false);
					}
					else {
						chckAdmin.setSelected(true);
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// Do nothing cause we smart
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				// Do nothing cause we smart
			}
		});
		userNameTF.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) 
			{
				if(userNameTF.getText().isEmpty())
				{
					userNameTF.setText("Username");
				}
			}
		});
		userNameTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(userNameTF.getText().equalsIgnoreCase("Username")) {
					userNameTF.selectAll();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(userNameTF.getText().isEmpty()) {
					userNameTF.setText("Username");
					userNameTF.selectAll();
				}
			}
		});
		userNameTF.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				userNameTF.setText("");
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if(userNameTF.getText().isEmpty()) {
					userNameTF.setText("Username");
				}
			}
		});
		pinTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(pinTF.getText().toString().equalsIgnoreCase("PIN")) {
					pinTF.setText("");
					pinTF.selectAll();
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				if(pinTF.getText().isEmpty()) {
					pinTF.setText("PIN");
					pinTF.selectAll();
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
				if(pinTF.getText().isEmpty()) {
					pinTF.setText("PIN");
				}
			}
			
		});
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				loginActionAttempted();
			}
		});
		
		userNameTF.addKeyListener(new enterKeyLoginListener());
		pinTF.addKeyListener(new enterKeyLoginListener());
	}
	/**
	 * Initializes the contents of the login view and adds it to the application frame.
	 * @param mainFrame The frame to add the view to.
	 * @author Alex
	 */
	public void initializeLogin(JFrame mainFrame) {
		
		my_mainFrame = mainFrame;
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/files/title_bar_icon.png")));
		addListeners();
		
		lblLogin.setBounds(316, 140, 46, 14);
		chckAdmin.setBounds(360, 138, 20, 20);
		chckAdmin.setFocusable(true);
		lblchckAdmin.setBounds(380, 141, 46, 14);

		userNameTF.setText("Username");
		userNameTF.setBounds(316, 165, 86, 20);
		userNameTF.setColumns(10);
		userNameTF.setFocusable(true);

		pinTF.setText("PIN");
		pinTF.setBounds(316, 198, 86, 20);
		pinTF.setColumns(10);
		pinTF.setFocusable(true);
		
		btnLogin.setBounds(316, 236, 86, 20);

		panel.setBounds(0, 0, 332, 285);
		
		mainFrame.getContentPane().add(lblLogin);
		mainFrame.getContentPane().add(chckAdmin);
		mainFrame.getContentPane().add(lblchckAdmin);
		mainFrame.getContentPane().add(pinTF);
		mainFrame.getContentPane().add(userNameTF);
		mainFrame.getContentPane().add(btnLogin);
		mainFrame.getContentPane().add(panel);	
		mainFrame.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[]{panel, chckAdmin, userNameTF, pinTF, btnLogin}));
	}
	
	/*
	 * Helper method to assist in the login process.
	 * @author Alex
	 */
	private void loginActionAttempted() {
		database = new Database();
		temp = new User(userNameTF.getText(), pinTF.getText(), chckAdmin.isSelected());
				if(temp.loginAttempt() == true) {
					if(chckAdmin.isSelected()) {
						AdminView adminView = new AdminView();
						adminView.initializeAdminView(my_mainFrame, database);
					}
					else {
						RFPView rfpView = new RFPView();
						rfpView.initialize(my_mainFrame, database);
					}
				};
	}
}
