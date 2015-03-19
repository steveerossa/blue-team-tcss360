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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import org.eclipse.wb.swing.FocusTraversalOnArray;

/**
 * User interface for the administration to add new approved question/answer pairs.
 * 
 * @author Alex
 *
 */
public class AdminView {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFrame mainFrame;
	
	public AdminView() {
		//empty just because? can't remember why, should be fixed probably....maybe 
	}
	/**
	 * Create the frame. 
	 */
	public void initializeAdminView(JFrame my_mainFrame) {
		mainFrame = my_mainFrame;
		
		mainFrame.setResizable(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(contentPane);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewQuestion = new JLabel("New Question:");
		GridBagConstraints gbc_lblNewQuestion = new GridBagConstraints();
		gbc_lblNewQuestion.anchor = GridBagConstraints.EAST;
		gbc_lblNewQuestion.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewQuestion.gridx = 0;
		gbc_lblNewQuestion.gridy = 0;
		contentPane.add(lblNewQuestion, gbc_lblNewQuestion);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		JLabel lblApprovedAnswer = new JLabel("Approved Answer:");
		GridBagConstraints gbc_lblApprovedAnswer = new GridBagConstraints();
		gbc_lblApprovedAnswer.anchor = GridBagConstraints.EAST;
		gbc_lblApprovedAnswer.insets = new Insets(0, 0, 5, 5);
		gbc_lblApprovedAnswer.gridx = 0;
		gbc_lblApprovedAnswer.gridy = 1;
		contentPane.add(lblApprovedAnswer, gbc_lblApprovedAnswer);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridwidth = 2;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.gridx = 1;
		gbc_scrollPane_1.gridy = 1;
		contentPane.add(scrollPane_1, gbc_scrollPane_1);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		
		JLabel lblCatagory = new JLabel("Catagory:");
		GridBagConstraints gbc_lblCatagory = new GridBagConstraints();
		gbc_lblCatagory.anchor = GridBagConstraints.EAST;
		gbc_lblCatagory.insets = new Insets(0, 0, 5, 5);
		gbc_lblCatagory.gridx = 0;
		gbc_lblCatagory.gridy = 2;
		contentPane.add(lblCatagory, gbc_lblCatagory);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 2;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblKeyPhrases = new JLabel("Key Phrases:");
		GridBagConstraints gbc_lblKeyPhrases = new GridBagConstraints();
		gbc_lblKeyPhrases.anchor = GridBagConstraints.EAST;
		gbc_lblKeyPhrases.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeyPhrases.gridx = 0;
		gbc_lblKeyPhrases.gridy = 3;
		contentPane.add(lblKeyPhrases, gbc_lblKeyPhrases);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 2;
		gbc_textField_1.insets = new Insets(0, 0, 5, 0);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 3;
		contentPane.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEditLevel = new JLabel("Edit Level:");
		GridBagConstraints gbc_lblEditLevel = new GridBagConstraints();
		gbc_lblEditLevel.anchor = GridBagConstraints.EAST;
		gbc_lblEditLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblEditLevel.gridx = 0;
		gbc_lblEditLevel.gridy = 4;
		contentPane.add(lblEditLevel, gbc_lblEditLevel);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.gridwidth = 2;
		gbc_textField_2.insets = new Insets(0, 0, 5, 0);
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 4;
		contentPane.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Save Question");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnLogOut = new JButton("Log out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.dispose();
				mainFrame = new JFrame();
				mainFrame.setResizable(false);
				mainFrame.setTitle("Global Business Logistics");
				mainFrame.setBounds(100, 100, 488, 329);
				mainFrame.setLocationRelativeTo(null);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.getContentPane().setLayout(null);
				mainFrame.setVisible(true);

				LoginView loginView = new LoginView();
				loginView.initializeLogin(mainFrame);
			}
		});
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogOut.gridx = 0;
		gbc_btnLogOut.gridy = 5;
		contentPane.add(btnLogOut, gbc_btnLogOut);
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 5;
		contentPane.add(btnNewButton, gbc_btnNewButton); // 
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.anchor = GridBagConstraints.WEST;
		gbc_btnCancel.gridx = 2;
		gbc_btnCancel.gridy = 5;
		contentPane.add(btnCancel, gbc_btnCancel);
		
		mainFrame.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]
				{textPane, textPane_1, textField, textField_1, textField_2, btnLogOut, btnNewButton, btnCancel}));
		
		my_mainFrame.repaint();
	}

}
