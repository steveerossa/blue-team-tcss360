package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class RFPView 
//				extends JFrame
								{

	private JPanel contentPane;
	private JTextField textField;


	public void initialize(JFrame my_mainFrame) {
		my_mainFrame.setTitle("Global Business Logistics");
		my_mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(RFPView.class.getResource("/files/title_bar_icon.png")));
		my_mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		my_mainFrame.setBounds(100, 100, 706, 468);
		my_mainFrame.setResizable(true);
		
		JMenuBar menuBar = new JMenuBar();
		my_mainFrame.setJMenuBar(menuBar);
		
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
	
	JMenuItem mntmSaveAs = new JMenuItem("Save as...");
	mnFile.add(mntmSaveAs);
	
	JMenuItem mntmLogOut = new JMenuItem("Log out");
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
	gbl_contentPane.columnWidths = new int[]{127, 0, 249, 66, 154, 0};
	gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
	gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
	gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
	contentPane.setLayout(gbl_contentPane);
	
	textField = new JTextField();
	GridBagConstraints gbc_textField = new GridBagConstraints();
	gbc_textField.fill = GridBagConstraints.HORIZONTAL;
	gbc_textField.insets = new Insets(0, 0, 5, 5);
	gbc_textField.gridx = 0;
	gbc_textField.gridy = 0;
	contentPane.add(textField, gbc_textField);
	textField.setColumns(10);
	
	JButton btnSearch = new JButton("");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	});
	btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
	GridBagConstraints gbc_btnSearch = new GridBagConstraints();
	gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
	gbc_btnSearch.gridx = 1;
	gbc_btnSearch.gridy = 0;
	contentPane.add(btnSearch, gbc_btnSearch);
	
	JComboBox comboBox = new JComboBox();
	GridBagConstraints gbc_comboBox = new GridBagConstraints();
	gbc_comboBox.gridwidth = 2;
	gbc_comboBox.insets = new Insets(0, 0, 5, 5);
	gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
	gbc_comboBox.gridx = 0;
	gbc_comboBox.gridy = 1;
	contentPane.add(comboBox, gbc_comboBox);
	
	JTextArea textArea = new JTextArea();
	textArea.setEditable(false);
	textArea.setLineWrap(true);
	textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	GridBagConstraints gbc_textArea = new GridBagConstraints();
	gbc_textArea.insets = new Insets(0, 0, 0, 5);
	gbc_textArea.gridheight = 3;
	gbc_textArea.fill = GridBagConstraints.BOTH;
	gbc_textArea.gridx = 2;
	gbc_textArea.gridy = 1;
	contentPane.add(textArea, gbc_textArea);
	
	JList list = new JList();
	list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	GridBagConstraints gbc_list = new GridBagConstraints();
	gbc_list.gridwidth = 2;
	gbc_list.gridheight = 2;
	gbc_list.insets = new Insets(0, 0, 0, 5);
	gbc_list.fill = GridBagConstraints.BOTH;
	gbc_list.gridx = 0;
	gbc_list.gridy = 2;
	contentPane.add(list, gbc_list);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
	gbc_tabbedPane.gridheight = 3;
	gbc_tabbedPane.fill = GridBagConstraints.BOTH;
	gbc_tabbedPane.gridx = 4;
	gbc_tabbedPane.gridy = 1;
	contentPane.add(tabbedPane, gbc_tabbedPane);
	
	JPanel notesPanel = new JPanel();
	tabbedPane.addTab("Notes", null, notesPanel, null);
	notesPanel.setLayout(new BorderLayout(0, 0));
	
	JTextArea notesText = new JTextArea();
	notesText.setPreferredSize(new Dimension(10, 22));
	notesText.setLineWrap(true);
	notesText.setAlignmentY(Component.TOP_ALIGNMENT);
	notesText.setAlignmentX(Component.RIGHT_ALIGNMENT);
	notesPanel.add(notesText);
	
	JPanel selectedAnsPanel = new JPanel();
	tabbedPane.addTab("Selected Answers", null, selectedAnsPanel, null);
	selectedAnsPanel.setLayout(new BorderLayout(0, 0));
	
	JList selectedAnsList = new JList();
	selectedAnsPanel.add(selectedAnsList);
	
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
		
	}
	
	
	
	
	
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(
//				            UIManager.getSystemLookAndFeelClassName());
//					RFPView frame = new RFPView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
	
	
	public RFPView() {
		
//		setTitle("Global Business Logistics");
//		setIconImage(Toolkit.getDefaultToolkit().getImage(RFPView.class.getResource("/files/title_bar_icon.png")));
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 706, 468);
//		setResizable(true);
//		
//		JMenuBar menuBar = new JMenuBar();
//		setJMenuBar(menuBar);
//		
//		JMenu mnFile = new JMenu("File");
//		menuBar.add(mnFile);
//		
//		JMenuItem mntmNew = new JMenuItem("New");
//		mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
//		mnFile.add(mntmNew);
//		
//		JMenuItem mntmOpen = new JMenuItem("Open...");
//		mnFile.add(mntmOpen);
//		
//		JMenuItem mntmSave = new JMenuItem("Save");
//		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
//		mnFile.add(mntmSave);
//		
//		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
//		mnFile.add(mntmSaveAs);
//		
//		JMenuItem mntmExit = new JMenuItem("Exit");
//		mnFile.add(mntmExit);
//		
//		JMenu mnEdit = new JMenu("Edit");
//		menuBar.add(mnEdit);
//		
//		JMenu mnView = new JMenu("View");
//		menuBar.add(mnView);
//		
//		JMenu mnHelp = new JMenu("Help");
//		menuBar.add(mnHelp);
//		
//		JMenuItem mntmHelp = new JMenuItem("Help...");
//		mnHelp.add(mntmHelp);
//		
//		JMenuItem mntmAbout = new JMenuItem("About");
//		mnHelp.add(mntmAbout);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		GridBagLayout gbl_contentPane = new GridBagLayout();
//		gbl_contentPane.columnWidths = new int[]{127, 0, 249, 66, 154, 0};
//		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
//		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
//		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
//		contentPane.setLayout(gbl_contentPane);
//		
//		textField = new JTextField();
//		GridBagConstraints gbc_textField = new GridBagConstraints();
//		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
//		gbc_textField.insets = new Insets(0, 0, 5, 5);
//		gbc_textField.gridx = 0;
//		gbc_textField.gridy = 0;
//		contentPane.add(textField, gbc_textField);
//		textField.setColumns(10);
//		
//		JButton btnSearch = new JButton("");
//		btnSearch.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				
//			}
//		});
//		btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
//		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
//		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
//		gbc_btnSearch.gridx = 1;
//		gbc_btnSearch.gridy = 0;
//		contentPane.add(btnSearch, gbc_btnSearch);
//		
//		JComboBox comboBox = new JComboBox();
//		GridBagConstraints gbc_comboBox = new GridBagConstraints();
//		gbc_comboBox.gridwidth = 2;
//		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
//		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
//		gbc_comboBox.gridx = 0;
//		gbc_comboBox.gridy = 1;
//		contentPane.add(comboBox, gbc_comboBox);
//		
//		JTextArea textArea = new JTextArea();
//		textArea.setEditable(false);
//		textArea.setLineWrap(true);
//		textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//		GridBagConstraints gbc_textArea = new GridBagConstraints();
//		gbc_textArea.insets = new Insets(0, 0, 0, 5);
//		gbc_textArea.gridheight = 3;
//		gbc_textArea.fill = GridBagConstraints.BOTH;
//		gbc_textArea.gridx = 2;
//		gbc_textArea.gridy = 1;
//		contentPane.add(textArea, gbc_textArea);
//		
//		JList list = new JList();
//		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
//		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		GridBagConstraints gbc_list = new GridBagConstraints();
//		gbc_list.gridwidth = 2;
//		gbc_list.gridheight = 2;
//		gbc_list.insets = new Insets(0, 0, 0, 5);
//		gbc_list.fill = GridBagConstraints.BOTH;
//		gbc_list.gridx = 0;
//		gbc_list.gridy = 2;
//		contentPane.add(list, gbc_list);
//		
//		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
//		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
//		gbc_tabbedPane.gridheight = 3;
//		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
//		gbc_tabbedPane.gridx = 4;
//		gbc_tabbedPane.gridy = 1;
//		contentPane.add(tabbedPane, gbc_tabbedPane);
//		
//		JPanel notesPanel = new JPanel();
//		tabbedPane.addTab("Notes", null, notesPanel, null);
//		notesPanel.setLayout(new BorderLayout(0, 0));
//		
//		JTextArea notesText = new JTextArea();
//		notesText.setPreferredSize(new Dimension(10, 22));
//		notesText.setLineWrap(true);
//		notesText.setAlignmentY(Component.TOP_ALIGNMENT);
//		notesText.setAlignmentX(Component.RIGHT_ALIGNMENT);
//		notesPanel.add(notesText);
//		
//		JPanel selectedAnsPanel = new JPanel();
//		tabbedPane.addTab("Selected Answers", null, selectedAnsPanel, null);
//		selectedAnsPanel.setLayout(new BorderLayout(0, 0));
//		
//		JList selectedAnsList = new JList();
//		selectedAnsPanel.add(selectedAnsList);
//		
//		JButton btnAddQ = new JButton("");
//		btnAddQ.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnAddQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/addQuestionIcon.png")));
//		GridBagConstraints gbc_btnAddQ = new GridBagConstraints();
//		gbc_btnAddQ.anchor = GridBagConstraints.SOUTH;
//		gbc_btnAddQ.insets = new Insets(0, 0, 5, 5);
//		gbc_btnAddQ.gridx = 3;
//		gbc_btnAddQ.gridy = 2;
//		contentPane.add(btnAddQ, gbc_btnAddQ);
//		
//		JButton btnAddToClip = new JButton("");
//		btnAddToClip.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnAddToClip.setIcon(new ImageIcon(RFPView.class.getResource("/files/copyToClipIcon.png")));
//		GridBagConstraints gbc_btnAddToClip = new GridBagConstraints();
//		gbc_btnAddToClip.anchor = GridBagConstraints.NORTH;
//		gbc_btnAddToClip.insets = new Insets(0, 0, 0, 5);
//		gbc_btnAddToClip.gridx = 3;
//		gbc_btnAddToClip.gridy = 3;
//		contentPane.add(btnAddToClip, gbc_btnAddToClip);
	}

}
