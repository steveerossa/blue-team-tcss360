package View;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Model.Database;
import Model.QuestionAnswer;

public class RFPView 
//				extends JFrame
{

	private JPanel contentPane;
	private JTextField searchTextField;
	private Database my_database;
	private ListModel listModel;
	private JFrame mainFrame;


	public void initialize(JFrame my_mainFrame) {
		mainFrame = my_mainFrame;
		my_database = new Database();
		mainFrame.setResizable(true);

		JMenuBar menuBar = new JMenuBar();
		mainFrame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open...");
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.addActionListener(new ActionListener() {
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
		mnFile.add(mntmLogOut);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmHelp = new JMenuItem("Help...");
		mnHelp.add(mntmHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		my_mainFrame.setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{127, 0, 226, 66, 200, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		searchTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(searchTextField, gbc_textField);
		searchTextField.setColumns(10);
		
		JList<QuestionAnswer> list = new JList<QuestionAnswer>();
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridwidth = 2;
		gbc_list.gridheight = 2;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 2;
		contentPane.add(list, gbc_list);

		JButton btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				listModel = new DefaultListModel<QuestionAnswer>();
				for(QuestionAnswer q : searchResults) {
					
				}
			}			
		});
		btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 0;
		contentPane.add(btnSearch, gbc_btnSearch);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridheight = 4;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 0;
		contentPane.add(scrollPane, gbc_scrollPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(null);
		scrollPane.setViewportView(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		JTextArea notesArea = new JTextArea();
		notesArea.setLineWrap(true);
		tabbedPane.addTab("Notes", null, notesArea, null);

		JList selectedQAsList = new JList();
		tabbedPane.addTab("Selected Q/A's", null, selectedQAsList, null);


		String[] my_list = my_database.getKeyPhrases().toArray(new String[my_database.getKeyPhrases().size()]);
		JComboBox<String> comboBox = new JComboBox<String>(my_list);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);

		JTextArea answerTextArea = new JTextArea();
		answerTextArea.setEditable(false);
		answerTextArea.setLineWrap(true);
		answerTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 10, 5);
		gbc_textArea.gridheight = 3;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 1;
		contentPane.add(answerTextArea, gbc_textArea);

		JButton btnAddQ = new JButton("");
		btnAddQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAddQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/addQuestionIcon.png")));
		GridBagConstraints gbc_btnAddQ = new GridBagConstraints();
		gbc_btnAddQ.anchor = GridBagConstraints.SOUTH;
		gbc_btnAddQ.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddQ.gridx = 3;
		gbc_btnAddQ.gridy = 2;
		contentPane.add(btnAddQ, gbc_btnAddQ);

		JButton btnAddToClip = new JButton("");
		btnAddToClip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddToClip.setIcon(new ImageIcon(RFPView.class.getResource("/files/copyToClipIcon.png")));
		GridBagConstraints gbc_btnAddToClip = new GridBagConstraints();
		gbc_btnAddToClip.anchor = GridBagConstraints.NORTH;
		gbc_btnAddToClip.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddToClip.gridx = 3;
		gbc_btnAddToClip.gridy = 3;
		contentPane.add(btnAddToClip, gbc_btnAddToClip);

		my_mainFrame.repaint();
	}





//
//		/**
//		 * Launch the application.
//		 */
//		public static void main(String[] args) {
//			EventQueue.invokeLater(new Runnable() {
//				public void run() {
//					try {
//						UIManager.setLookAndFeel(
//					            UIManager.getSystemLookAndFeelClassName());
//						RFPView frame = new RFPView();
//						frame.setVisible(true);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			});
//		}
//
//	/**
//	 * Create the frame.
//	 */


	/**
	 * @wbp.parser.entryPoint
	 */
	public RFPView() {

//				setTitle("Global Business Logistics");
//				setIconImage(Toolkit.getDefaultToolkit().getImage(RFPView.class.getResource("/files/title_bar_icon.png")));
//				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				setBounds(100, 100, 706, 468);
//				setResizable(true);
//				
//				JMenuBar menuBar = new JMenuBar();
//				setJMenuBar(menuBar);
//				
//				JMenu mnFile = new JMenu("File");
//				menuBar.add(mnFile);
//				
//				JMenuItem mntmNew = new JMenuItem("New");
//				mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
//				mnFile.add(mntmNew);
//				
//				JMenuItem mntmOpen = new JMenuItem("Open...");
//				mnFile.add(mntmOpen);
//				
//				JMenuItem mntmSave = new JMenuItem("Save");
//				mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
//				mnFile.add(mntmSave);
//				
//				JMenuItem mntmSaveAs = new JMenuItem("Save As...");
//				mnFile.add(mntmSaveAs);
//				
//				JMenuItem mntmExit = new JMenuItem("Exit");
//				mntmExit.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						System.exit(0);
//					}
//				});
//				
//				JMenuItem mntmLogOut = new JMenuItem("Log out");
//				mntmLogOut.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						LoginView loginView = new LoginView();
//						loginView.initializeLogin(my_mainframe);
//					}
//				});
//				mnFile.add(mntmLogOut);
//				mnFile.add(mntmExit);
//				
//				JMenu mnEdit = new JMenu("Edit");
//				menuBar.add(mnEdit);
//				
//				JMenu mnView = new JMenu("View");
//				menuBar.add(mnView);
//				
//				JMenu mnHelp = new JMenu("Help");
//				menuBar.add(mnHelp);
//				
//				JMenuItem mntmHelp = new JMenuItem("Help...");
//				mnHelp.add(mntmHelp);
//				
//				JMenuItem mntmAbout = new JMenuItem("About");
//				mnHelp.add(mntmAbout);
//				contentPane = new JPanel();
//				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//				setContentPane(contentPane);
//				GridBagLayout gbl_contentPane = new GridBagLayout();
//				gbl_contentPane.columnWidths = new int[]{127, 0, 226, 66, 200, 0};
//				gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
//				gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
//				gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
//				contentPane.setLayout(gbl_contentPane);
//				
//				textField = new JTextField();
//				GridBagConstraints gbc_textField = new GridBagConstraints();
//				gbc_textField.fill = GridBagConstraints.HORIZONTAL;
//				gbc_textField.insets = new Insets(0, 0, 5, 5);
//				gbc_textField.gridx = 0;
//				gbc_textField.gridy = 0;
//				contentPane.add(textField, gbc_textField);
//				textField.setColumns(10);
//				
//				JButton btnSearch = new JButton("");
//				btnSearch.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent arg0) {
//						
//					}
//				});
//				btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
//				GridBagConstraints gbc_btnSearch = new GridBagConstraints();
//				gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
//				gbc_btnSearch.gridx = 1;
//				gbc_btnSearch.gridy = 0;
//				contentPane.add(btnSearch, gbc_btnSearch);
//				
//				JScrollPane scrollPane = new JScrollPane();
//				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
//				gbc_scrollPane.fill = GridBagConstraints.BOTH;
//				gbc_scrollPane.gridheight = 4;
//				gbc_scrollPane.gridx = 4;
//				gbc_scrollPane.gridy = 0;
//				contentPane.add(scrollPane, gbc_scrollPane);
//				
//				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//				tabbedPane.setBorder(null);
//				scrollPane.setViewportView(tabbedPane);
//				tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
//				
//				JTextArea notesArea = new JTextArea();
//				tabbedPane.addTab("Notes", null, notesArea, null);
//				
//				JList selectedQAsList = new JList();
//				tabbedPane.addTab("Selected Q/A's", null, selectedQAsList, null);
//				
//				JComboBox comboBox = new JComboBox();
//				GridBagConstraints gbc_comboBox = new GridBagConstraints();
//				gbc_comboBox.gridwidth = 2;
//				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
//				gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
//				gbc_comboBox.gridx = 0;
//				gbc_comboBox.gridy = 1;
//				contentPane.add(comboBox, gbc_comboBox);
//				
//				JTextArea textArea = new JTextArea();
//				textArea.setEditable(false);
//				textArea.setLineWrap(true);
//				textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//				GridBagConstraints gbc_textArea = new GridBagConstraints();
//				gbc_textArea.insets = new Insets(0, 0, 0, 5);
//				gbc_textArea.gridheight = 3;
//				gbc_textArea.fill = GridBagConstraints.BOTH;
//				gbc_textArea.gridx = 2;
//				gbc_textArea.gridy = 1;
//				contentPane.add(textArea, gbc_textArea);
//				
//				JList list = new JList();
//				list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//				list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//				GridBagConstraints gbc_list = new GridBagConstraints();
//				gbc_list.gridwidth = 2;
//				gbc_list.gridheight = 2;
//				gbc_list.insets = new Insets(0, 0, 0, 5);
//				gbc_list.fill = GridBagConstraints.BOTH;
//				gbc_list.gridx = 0;
//				gbc_list.gridy = 2;
//				contentPane.add(list, gbc_list);
//				
//				JButton btnAddQ = new JButton("");
//				btnAddQ.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//					}
//				});
//				btnAddQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/addQuestionIcon.png")));
//				GridBagConstraints gbc_btnAddQ = new GridBagConstraints();
//				gbc_btnAddQ.anchor = GridBagConstraints.SOUTH;
//				gbc_btnAddQ.insets = new Insets(0, 0, 5, 5);
//				gbc_btnAddQ.gridx = 3;
//				gbc_btnAddQ.gridy = 2;
//				contentPane.add(btnAddQ, gbc_btnAddQ);
//				
//				JButton btnAddToClip = new JButton("");
//				btnAddToClip.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//					}
//				});
//				btnAddToClip.setIcon(new ImageIcon(RFPView.class.getResource("/files/copyToClipIcon.png")));
//				GridBagConstraints gbc_btnAddToClip = new GridBagConstraints();
//				gbc_btnAddToClip.anchor = GridBagConstraints.NORTH;
//				gbc_btnAddToClip.insets = new Insets(0, 0, 0, 5);
//				gbc_btnAddToClip.gridx = 3;
//				gbc_btnAddToClip.gridy = 3;
//				contentPane.add(btnAddToClip, gbc_btnAddToClip);
	}

}
