package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Model.Database;
import Model.QuestionAnswer;

public class RFPView 
//				extends JFrame
{

	private JPanel contentPane;
	private JTextField searchTextField;
	private JTextArea answerTextArea;
	private Database my_database;
	private JFrame mainFrame;
	private Clipboard clipboard;
	private Stack undoStack;


	public void initialize(JFrame my_mainFrame) {
		mainFrame = my_mainFrame;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		my_database = new Database();


		/////////////////////////////////////////
		//
		//			MENU STUFF
		//
		/////////////////////////////////////////
		JMenuBar menuBar = new JMenuBar();

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

		/////////////////////////////////////////////
		//
		//			APPLYING LAYOUT TO CONTENT PANE
		//
		/////////////////////////////////////////////
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{127, 0, 226, 66, 200, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.5, 0.0, 1.2, 0.0, 1.5, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);

		/////////////////////////////////////////////
		//
		//			QUESTION SEARCH AND LIST STUFF
		//
		/////////////////////////////////////////////
		searchTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(searchTextField, gbc_textField);
		searchTextField.setColumns(10);

		final JButton btnSearch = new JButton("");
		btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 0;
		contentPane.add(btnSearch, gbc_btnSearch);

		String[] my_list = my_database.getKeyPhrases().toArray(new String[my_database.getKeyPhrases().size()]);
		final JComboBox<String> comboBox = new JComboBox<String>(my_list);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);

		final JList<QuestionAnswer> list = new JList<QuestionAnswer>();
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellWidth(400);
		list.setCellRenderer(new MyCellRenderer());
		//		GridBagConstraints gbc_list = new GridBagConstraints();
		//		gbc_list.gridwidth = 2;
		//		gbc_list.gridheight = 2;
		//		gbc_list.insets = new Insets(0, 0, 0, 5);
		//		gbc_list.fill = GridBagConstraints.BOTH;
		//		gbc_list.gridx = 0;
		//		gbc_list.gridy = 2;
		//		contentPane.add(new JScrollPane(list), gbc_list);

		////////////////////////////////////////////
		//
		//			TEXT AREA TO DISPLAY ANSWERS
		//
		////////////////////////////////////////////
		answerTextArea = new JTextArea();
		answerTextArea.setEditable(false);
		answerTextArea.setFocusable(true);
		answerTextArea.setRequestFocusEnabled(true);
		answerTextArea.setLineWrap(true);
		answerTextArea.setWrapStyleWord(true);
		answerTextArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		answerTextArea.setInheritsPopupMenu(true);		
		//		GridBagConstraints gbc_textArea = new GridBagConstraints();
		//		gbc_textArea.insets = new Insets(0, 0, 10, 5);
		//		gbc_textArea.gridheight = 3;
		//		gbc_textArea.fill = GridBagConstraints.BOTH;
		//		gbc_textArea.gridx = 2;
		//		gbc_textArea.gridy = 1;
		//		contentPane.add(answerTextArea, gbc_textArea);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(new JScrollPane(list));
		splitPane.setRightComponent(new JScrollPane(answerTextArea));
		splitPane.setDividerLocation(0.45);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0 , 10, 10);
		gbc_splitPane.gridheight = 3;
		gbc_splitPane.gridwidth = 3;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 2;
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		contentPane.add(splitPane, gbc_splitPane);


		///////////////////////////////////////////////
		//
		//			NOTES AND SELECTED QUESTIONS
		//
		///////////////////////////////////////////////
		final JScrollPane scrollPane = new JScrollPane();
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

		final JTextArea notesArea = new JTextArea();
		notesArea.setFocusable(true);
		notesArea.setRequestFocusEnabled(true);
		notesArea.setLineWrap(true);
		tabbedPane.addTab("Notes", null, notesArea, null);

		final JList<QuestionAnswer> selectedQAsList = new JList<QuestionAnswer>();
		selectedQAsList.setFixedCellWidth(400);
		final ArrayList<QuestionAnswer> selectedQsList = new ArrayList<QuestionAnswer>();
		tabbedPane.addTab("Selected Q/A's", null, selectedQAsList, null);


		///////////////////////////////////////////////////////////
		//
		//			ADDING QUESTIONS AND COPYING TO CLIPBOARD
		//
		///////////////////////////////////////////////////////////
		JButton btnAddQ = new JButton("");
		btnAddQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/addQuestionIcon.png")));
		GridBagConstraints gbc_btnAddQ = new GridBagConstraints();
		gbc_btnAddQ.anchor = GridBagConstraints.SOUTH;
		gbc_btnAddQ.insets = new Insets(0, 0, 5, 5);
		gbc_btnAddQ.gridx = 3;
		gbc_btnAddQ.gridy = 2;
		contentPane.add(btnAddQ, gbc_btnAddQ);

		JButton btnAddToClip = new JButton("");
		btnAddToClip.setIcon(new ImageIcon(RFPView.class.getResource("/files/copyToClipIcon.png")));
		GridBagConstraints gbc_btnAddToClip = new GridBagConstraints();
		gbc_btnAddToClip.anchor = GridBagConstraints.NORTH;
		gbc_btnAddToClip.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddToClip.gridx = 3;
		gbc_btnAddToClip.gridy = 3;
		contentPane.add(btnAddToClip, gbc_btnAddToClip);


		////////////////////////////////////////
		//
		//				POPUP MENU STUFF
		//
		////////////////////////////////////////
		final JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem mntmSelectAll = new JMenuItem("Select all");
		JMenuItem mntmCopy = new JMenuItem("Copy");
		JMenuItem mntmPaste = new JMenuItem("Paste...");
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		popupMenu.add(mntmSelectAll);	
		popupMenu.add(mntmCopy);
		popupMenu.add(mntmPaste);

		addPopup(answerTextArea, popupMenu);
		addPopup(notesArea, popupMenu);

		///////////////////////////////////////
		//
		//			FORMATTING THE FRAME
		//
		///////////////////////////////////////
		mainFrame.setContentPane(contentPane);
		mainFrame.setJMenuBar(menuBar);
		mainFrame.setResizable(true);
		mainFrame.setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[]{searchTextField, btnSearch, comboBox, btnAddQ, btnAddToClip}));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);



		/*******************************************************************************
		 * 
		 * 
		 * 							LISTENERS AND EVENTS
		 * 
		 * 
		 *******************************************************************************/

		//Menu listeners and actions
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

		//RFP area listeners and actions

		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				QuestionAnswer[] questionAnswerList = new QuestionAnswer[searchResults.size()];
				searchResults.toArray(questionAnswerList);
				list.setListData(questionAnswerList);
			}			
		});

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				searchTextField.setText((String) comboBox.getSelectedItem());
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				QuestionAnswer[] questionAnswerList = new QuestionAnswer[searchResults.size()];
				searchResults.toArray(questionAnswerList);
				list.setListData(questionAnswerList);
			}			
		});


		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty()){
					answerTextArea.setText("");
					answerTextArea.append(list.getSelectedValue().getAnswer());
				}
			}			
		});
		
		splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				QuestionAnswer[] questionAnswerList = new QuestionAnswer[searchResults.size()];
				searchResults.toArray(questionAnswerList);
				list.setListData(questionAnswerList);
				list.setBorder(new EmptyBorder(10,10, 10, 10));
			}			
		});

		searchTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearch.doClick();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchTextField.selectAll();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				//do nothing cause we smart
			}			
		});

		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				searchTextField.selectAll();
			}
		});

		btnAddQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectedQsList.add(list.getSelectedValue());
				QuestionAnswer[] selectedTemp = new QuestionAnswer[selectedQsList.size()];
				selectedQsList.toArray(selectedTemp);
				selectedQAsList.setListData(selectedTemp);
			}
		});

		btnAddToClip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(new StringSelection(answerTextArea.getText()), null);
			}
		});

		mntmSelectAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(popupMenu.getInvoker().equals(answerTextArea)) {
					answerTextArea.requestFocus();
					answerTextArea.selectAll();
				}
				if(popupMenu.getInvoker().equals(notesArea)) {
					notesArea.requestFocus();
					notesArea.selectAll();
				}
			}
		});

		mntmCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(popupMenu.getInvoker().equals(answerTextArea)) {
					clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(new StringSelection(answerTextArea.getSelectedText()), null);
				}
			}
		});
		
		mntmPaste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(notesArea.hasFocus()) {
					notesArea.insert(clipboard.toString(), notesArea.getCaretPosition());
				}
			}
		});
	}


	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	//	
	//			/**
	//			 * Launch the application.
	//			 */
	//			public static void main(String[] args) {
	//				EventQueue.invokeLater(new Runnable() {
	//					public void run() {
	//						try {
	//							UIManager.setLookAndFeel(
	//						            UIManager.getSystemLookAndFeelClassName());
	//							RFPView frame = new RFPView();
	//							frame.setVisible(true);
	//						} catch (Exception e) {
	//							e.printStackTrace();
	//						}
	//					}
	//				});
	//			}
	//	
	//		/**
	//		 * Create the frame.
	//		 */



	public RFPView() {

		//						setTitle("Global Business Logistics");
		//						setIconImage(Toolkit.getDefaultToolkit().getImage(RFPView.class.getResource("/files/title_bar_icon.png")));
		//						setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//						setBounds(100, 100, 706, 468);
		//						setResizable(true);
		//						
		//						JMenuBar menuBar = new JMenuBar();
		//						setJMenuBar(menuBar);
		//						
		//						JMenu mnFile = new JMenu("File");
		//						menuBar.add(mnFile);
		//						
		//						JMenuItem mntmNew = new JMenuItem("New");
		//						mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		//						mnFile.add(mntmNew);
		//						
		//						JMenuItem mntmOpen = new JMenuItem("Open...");
		//						mnFile.add(mntmOpen);
		//						
		//						JMenuItem mntmSave = new JMenuItem("Save");
		//						mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		//						mnFile.add(mntmSave);
		//						
		//						JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		//						mnFile.add(mntmSaveAs);
		//						
		//						JMenuItem mntmExit = new JMenuItem("Exit");
		//						mntmExit.addActionListener(new ActionListener() {
		//							public void actionPerformed(ActionEvent arg0) {
		//								System.exit(0);
		//							}
		//						});
		//						
		//						JMenuItem mntmLogOut = new JMenuItem("Log out");
		//						mntmLogOut.addActionListener(new ActionListener() {
		//							public void actionPerformed(ActionEvent e) {
		//								LoginView loginView = new LoginView();
		////								loginView.initializeLogin(my_mainframe);
		//							}
		//						});
		//						mnFile.add(mntmLogOut);
		//						mnFile.add(mntmExit);
		//						
		//						JMenu mnEdit = new JMenu("Edit");
		//						menuBar.add(mnEdit);
		//						
		//						JMenu mnView = new JMenu("View");
		//						menuBar.add(mnView);
		//						
		//						JMenu mnHelp = new JMenu("Help");
		//						menuBar.add(mnHelp);
		//						
		//						JMenuItem mntmHelp = new JMenuItem("Help...");
		//						mnHelp.add(mntmHelp);
		//						
		//						JMenuItem mntmAbout = new JMenuItem("About");
		//						mnHelp.add(mntmAbout);
		//						contentPane = new JPanel();
		//						contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//						setContentPane(contentPane);
		//						GridBagLayout gbl_contentPane = new GridBagLayout();
		//						gbl_contentPane.columnWidths = new int[]{127, 0, 226, 66, 200, 0};
		//						gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		//						gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		//						gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		//						contentPane.setLayout(gbl_contentPane);
		//						
		////						textField = new JTextField();
		////						GridBagConstraints gbc_textField = new GridBagConstraints();
		////						gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		////						gbc_textField.insets = new Insets(0, 0, 5, 5);
		////						gbc_textField.gridx = 0;
		////						gbc_textField.gridy = 0;
		////						contentPane.add(textField, gbc_textField);
		////						textField.setColumns(10);
		//						
		//						JButton btnSearch = new JButton("");
		//						btnSearch.addActionListener(new ActionListener() {
		//							public void actionPerformed(ActionEvent arg0) {
		//								
		//							}
		//						});
		//						btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
		//						GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		//						gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		//						gbc_btnSearch.gridx = 1;
		//						gbc_btnSearch.gridy = 0;
		//						contentPane.add(btnSearch, gbc_btnSearch);
		//						
		//						JScrollPane scrollPane = new JScrollPane();
		//						GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		//						gbc_scrollPane.fill = GridBagConstraints.BOTH;
		//						gbc_scrollPane.gridheight = 4;
		//						gbc_scrollPane.gridx = 4;
		//						gbc_scrollPane.gridy = 0;
		//						contentPane.add(scrollPane, gbc_scrollPane);
		//						
		//						JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		//						tabbedPane.setBorder(null);
		//						scrollPane.setViewportView(tabbedPane);
		//						tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		//						
		//						JTextArea notesArea = new JTextArea();
		//						tabbedPane.addTab("Notes", null, notesArea, null);
		//						
		//						JList selectedQAsList = new JList();
		//						tabbedPane.addTab("Selected Q/A's", null, selectedQAsList, null);
		//						
		//						JComboBox comboBox = new JComboBox();
		//						GridBagConstraints gbc_comboBox = new GridBagConstraints();
		//						gbc_comboBox.gridwidth = 2;
		//						gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		//						gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		//						gbc_comboBox.gridx = 0;
		//						gbc_comboBox.gridy = 1;
		//						contentPane.add(comboBox, gbc_comboBox);
		//						
		//						JTextArea textArea = new JTextArea();
		//						textArea.setInheritsPopupMenu(true);
		//						textArea.setEditable(false);
		//						textArea.setLineWrap(true);
		//						textArea.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		//						
		//						JPopupMenu popupMenu = new JPopupMenu();
		//						addPopup(textArea, popupMenu);
		//						
		//						JMenuItem mntmSelectAll = new JMenuItem("Select all");
		//						popupMenu.add(mntmSelectAll);
		//						
		//						JMenuItem mntmCopy = new JMenuItem("Copy");
		//						popupMenu.add(mntmCopy);
		//						GridBagConstraints gbc_textArea = new GridBagConstraints();
		//						gbc_textArea.insets = new Insets(0, 0, 0, 5);
		//						gbc_textArea.gridheight = 3;
		//						gbc_textArea.fill = GridBagConstraints.BOTH;
		//						gbc_textArea.gridx = 2;
		//						gbc_textArea.gridy = 1;
		//						contentPane.add(textArea, gbc_textArea);
		//						
		//						JList list = new JList();
		//						list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		//						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//						GridBagConstraints gbc_list = new GridBagConstraints();
		//						gbc_list.gridwidth = 2;
		//						gbc_list.gridheight = 2;
		//						gbc_list.insets = new Insets(0, 0, 0, 5);
		//						gbc_list.fill = GridBagConstraints.BOTH;
		//						gbc_list.gridx = 0;
		//						gbc_list.gridy = 2;
		//						contentPane.add(list, gbc_list);
		//						
		//						JButton btnAddQ = new JButton("");
		//						btnAddQ.addActionListener(new ActionListener() {
		//							public void actionPerformed(ActionEvent e) {
		//							}
		//						});
		//						btnAddQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/addQuestionIcon.png")));
		//						GridBagConstraints gbc_btnAddQ = new GridBagConstraints();
		//						gbc_btnAddQ.anchor = GridBagConstraints.SOUTH;
		//						gbc_btnAddQ.insets = new Insets(0, 0, 5, 5);
		//						gbc_btnAddQ.gridx = 3;
		//						gbc_btnAddQ.gridy = 2;
		//						contentPane.add(btnAddQ, gbc_btnAddQ);
		//						
		//						JButton btnAddToClip = new JButton("");
		//						btnAddToClip.addActionListener(new ActionListener() {
		//							public void actionPerformed(ActionEvent e) {
		//							}
		//						});
		//						btnAddToClip.setIcon(new ImageIcon(RFPView.class.getResource("/files/copyToClipIcon.png")));
		//						GridBagConstraints gbc_btnAddToClip = new GridBagConstraints();
		//						gbc_btnAddToClip.anchor = GridBagConstraints.NORTH;
		//						gbc_btnAddToClip.insets = new Insets(0, 0, 0, 5);
		//						gbc_btnAddToClip.gridx = 3;
		//						gbc_btnAddToClip.gridy = 3;
		//						contentPane.add(btnAddToClip, gbc_btnAddToClip);
		//	}


	}
}
