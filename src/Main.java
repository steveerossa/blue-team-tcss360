import java.awt.EventQueue;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



public class Main {

	private JFrame mainFrame;
	private JTextField userNameTF;
	private JTextField pinTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Global Business Logistics");
		mainFrame.setBounds(100, 100, 450, 300);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(176, 76, 46, 14);
		mainFrame.getContentPane().add(lblLogin);
		
		userNameTF = new JTextField();
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
		userNameTF.setText("Username");
		userNameTF.setBounds(186, 101, 86, 20);
		mainFrame.getContentPane().add(userNameTF);
		userNameTF.setColumns(10);
		
		pinTF = new JTextField();
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
		pinTF.setBounds(186, 144, 86, 20);
		mainFrame.getContentPane().add(pinTF);
		pinTF.setColumns(10);
		
		JLabel lblPin = new JLabel("Pin:");
		lblPin.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPin.setBounds(130, 147, 46, 14);
		mainFrame.getContentPane().add(lblPin);
	}
}
