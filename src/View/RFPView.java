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

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Controller.Main;
import Model.Database;
import Model.FocusTraversalOnArray;
import Model.QuestionAnswer;

/**
 * Main draft construction user interface class where approved question/answer pairs
 * can be selected and manipulated.
 * @author Alex
 * Additions and edits made by Jeremiah, Chutiwat, and Stuart
 *
 */
public class RFPView {

	/*
	 * Custom JPanel to make things pretty.
	 * @author Alex
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
		}

		@Override
		protected void paintComponent(Graphics g) {		

			final Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			final AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);
			g2d.setComposite(ac);
			super.paintComponent(g2d);
			g2d.drawImage(image, this.getWidth()/2 - image.getWidth()/2, this.getHeight()/2 - image.getHeight()/2, null);  
			final AlphaComposite ac2 = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
			g2d.setComposite(ac2);
			mainFrame.repaint();
		}
	}
	private JPanel contentPane;
	private JTextField searchTextField;
	private JTextArea answerTextArea;
	private Database my_database;
	private JFrame mainFrame;
	private Clipboard clipboard;
	private ImagePanel imagePanel;
	private JFileChooser myJFC;
	private boolean fileSelected;
	private JTextArea notesArea = new JTextArea();
	
	private ArrayList<QuestionAnswer> selectedQsList = new ArrayList<QuestionAnswer>();
	private JList<QuestionAnswer> selectedQAsList = new JList<QuestionAnswer>();


	/*
	 * @author Alex
	 */
	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
				mainFrame.repaint();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
				mainFrame.repaint();
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
				mainFrame.repaint();
			}
		});
	}

	/*
	 * @author Alex
	 */
	public void initialize(JFrame my_mainFrame, Database database) {

		/*
		 * Initialize variables
		 */
		fileSelected = false;
		myJFC = new JFileChooser();
		mainFrame = my_mainFrame;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		my_database = database;		

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
		mntmNew.setToolTipText("Create a new RFP response.");
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open...");
		mntmOpen.setToolTipText("Open a previously saved response.");
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mntmSave.setToolTipText("Save current response progress.");
		mnFile.add(mntmSave);

		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mnFile.add(mntmSaveAs);

		JMenuItem mntmLogOut = new JMenuItem("Log out");
		mntmLogOut.setToolTipText("Return to login page.");
		mnFile.add(mntmLogOut);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.setToolTipText("Exit the program.");
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

		//search text field
		searchTextField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		contentPane.add(searchTextField, gbc_textField);
		searchTextField.setColumns(10);

		//search button
		final JButton btnSearch = new JButton("");
		btnSearch.setToolTipText("Search by any key word.");
		btnSearch.setIcon(new ImageIcon(RFPView.class.getResource("/files/searchIcon.png")));
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.insets = new Insets(0, 0, 5, 5);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 0;
		contentPane.add(btnSearch, gbc_btnSearch);

		//drop down list
		String[] my_list = my_database.getKeyPhrases().toArray(new String[my_database.getKeyPhrases().size()]);
		final JComboBox<String> comboBox = new JComboBox<String>(my_list);
		comboBox.setMaximumRowCount(30);
		comboBox.setToolTipText("Select a catagory.");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 1;
		contentPane.add(comboBox, gbc_comboBox);

		//list to selected QAs from
		final JList<QuestionAnswer> list = new JList<QuestionAnswer>();
		list.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		list.setLayoutOrientation(JList.VERTICAL);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFixedCellWidth(400);
		list.setCellRenderer(new MyCellRenderer());


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
		answerTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		answerTextArea.setInheritsPopupMenu(true);
		answerTextArea.setOpaque(false);
		answerTextArea.setMinimumSize(new Dimension(225, 0));
		answerTextArea.setPreferredSize(new Dimension(400, 0));
		
		imagePanel = new ImagePanel();
		imagePanel.setLayout(new BorderLayout());
		imagePanel.setMinimumSize(answerTextArea.getMinimumSize());
		imagePanel.setPreferredSize(answerTextArea.getPreferredSize());
		imagePanel.add(answerTextArea, BorderLayout.CENTER);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		splitPane.setLeftComponent(new JScrollPane(list));

		/*Playing with these two lines below, the scroll pane breaks the centering of the logo and makes
		 * the formatting a bit awkward where you can't read the answer very well because the scroll pane
		 * centers the contents so you can't see the first word. Bypassed by just adding the imagePanel directly*/
		
		/*Revision has been made and unnecessary components have been removed. As of 3/18/15, components should
		 * resize, scale, and behave as expected now. 
		 * 
		 * I believe a scroll pane could be implemented in case of longer answers in the future. It looks
		 * like the main issue was some components having preferred sizes set while others used default
		 * sizes.  Now that some of the components have reasonable preferred sizes set and other related
		 * components are now dependent on each other, things appear to behave properly. I also added weight
		 * to the split panes grid bag constraints, but am unsure the effect this really has. I have yet to 
		 * test on anything other than Windows 7, though. --Alex
		 */
		splitPane.setRightComponent(imagePanel);
		splitPane.setContinuousLayout(true);

		splitPane.setDividerLocation(0.45);
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.insets = new Insets(0, 0 , 10, 10);
		gbc_splitPane.weightx = 1;
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

		final JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		tabbedPane.setBorder(null);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.weightx = 0.1;
		gbc_tabbedPane.gridheight = 4;
		gbc_tabbedPane.gridwidth = 1;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 4;
		gbc_tabbedPane.gridy = 0;

		notesArea.setFocusable(true);
		notesArea.setRequestFocusEnabled(true);
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		tabbedPane.addTab("Notes", null, new JScrollPane(notesArea), null);


		selectedQAsList.setCellRenderer(new MyCellRenderer());
		tabbedPane.addTab("Selected Q/A's", null, new JScrollPane(selectedQAsList), null);
		contentPane.add(tabbedPane, gbc_tabbedPane);


		///////////////////////////////////////////////////////////
		//
		//			ADDING QUESTIONS AND COPYING TO CLIPBOARD
		//
		///////////////////////////////////////////////////////////

		//Button to add currently selected question to analyst's list
		JButton btnAddQ = new JButton("");
		btnAddQ.setToolTipText("Add current Q/A to your response list.");
		btnAddQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/addQuestionIcon.png")));

		//Button to remove currently selected question from analyst's list
		JButton btnRemoveQ = new JButton("");
		btnRemoveQ.setToolTipText("Remove currently selected Q/A from your list.");
		btnRemoveQ.setIcon(new ImageIcon(RFPView.class.getResource("/files/removeQ.png")));

		//Button to copy the contents of the answer display area to the system's clip board 
		JButton btnAddToClip = new JButton("");
		btnAddToClip.setToolTipText("Copy answer text to clip board.");
		btnAddToClip.setIcon(new ImageIcon(RFPView.class.getResource("/files/copyToClipIcon.png")));

		//Toolbar to contain the 3 buttons above to improve formatting and reduce code size
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setFloatable(false);
		toolBar.add(btnAddQ);
		toolBar.add(btnRemoveQ);
		toolBar.add(btnAddToClip);

		//Now only 1 GridBagConstraints needs to be defined with the use of the tool bar
		GridBagConstraints gbc_toolbar = new  GridBagConstraints();
		gbc_toolbar.anchor = GridBagConstraints.SOUTH;
		gbc_toolbar.gridx = 3;
		gbc_toolbar.gridy = 2;

		//add toolbar with its buttons to the content pane according to the grid bag constraints.
		contentPane.add(toolBar, gbc_toolbar);

		////////////////////////////////////////
		//
		//				POPUP MENU STUFF
		//
		////////////////////////////////////////

		//new popup menu
		final JPopupMenu popupMenu = new JPopupMenu();

		// menu items
		JMenuItem mntmSelectAll = new JMenuItem("Select all");
		JMenuItem mntmCopy = new JMenuItem("Copy");
		JMenuItem mntmPaste = new JMenuItem("Paste...");
		JMenuItem mntmUndo = new JMenuItem("Undo");

		//keyboard shortcuts
		mntmSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));

		//populate menu
		popupMenu.add(mntmSelectAll);	
		popupMenu.add(mntmCopy);
		popupMenu.add(mntmPaste);
		popupMenu.add(mntmUndo);

		//add popup menu to proper components with helper method that adds mouse listeners respectively
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
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);


		/*******************************************************************************
		 * 
		 * 
		 * 							LISTENERS AND EVENTS
		 * 
		 * 
		 *******************************************************************************/

		//Menu listeners and actions
		/*
		 * @author Alex
		 */
		mntmLogOut.addActionListener(new ActionListener() {
			@Override
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

		/*
		 * @author Stuart
		 */
		mntmSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		
		/*
		 * @author Stuart
		 */
		mntmSaveAs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				saveAs();
			}
		});
		
		/*
		 * @author Stuart
		 */
		mntmOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});

		/*
		 * @author Stuart
		 */
		mntmNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				newRFP();
			}
		});

		//RFP area listeners and actions
		/*
		 * @author Jeremiah
		 */
		btnSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				QuestionAnswer[] questionAnswerList = new QuestionAnswer[searchResults.size()];
				searchResults.toArray(questionAnswerList);
				list.setListData(questionAnswerList);
			}			
		});

		/*
		 * @author Jeremiah
		 */
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				searchTextField.setText((String) comboBox.getSelectedItem());
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				QuestionAnswer[] questionAnswerList = new QuestionAnswer[searchResults.size()];
				searchResults.toArray(questionAnswerList);
				list.setListData(questionAnswerList);
			}			
		});

		/*
		 * @author Jeremiah
		 */
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty()){
					selectedQAsList.clearSelection();
					answerTextArea.setText("");
					answerTextArea.setText(list.getSelectedValue().getAnswer());
					mainFrame.repaint();
				}
			}			
		});

		/*
		 * @author Jeremiah
		 */
		splitPane.addPropertyChangeListener(JSplitPane.DIVIDER_LOCATION_PROPERTY, new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				ArrayList<QuestionAnswer> searchResults = my_database.searchQuestionAnswers(searchTextField.getText());
				QuestionAnswer[] questionAnswerList = new QuestionAnswer[searchResults.size()];
				searchResults.toArray(questionAnswerList);
				list.setListData(questionAnswerList);
				list.setBorder(new EmptyBorder(4, 4, 4, 4));
				mainFrame.repaint();
			}			
		});

		/*
		 * @author Jeremiah
		 */
		searchTextField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					btnSearch.doClick();
					mainFrame.repaint();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					searchTextField.selectAll();
					mainFrame.repaint();
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				//do nothing cause we smart
			}			
		});

		/*
		 * @author Jeremiah
		 */
		searchTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				searchTextField.selectAll();
				mainFrame.repaint();
			}
		});

		//QuestionAnswer manipulation and view listeners
		/*
		 * @author Jeremiah
		 */
		btnAddQ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!list.isSelectionEmpty()) {
					selectedQsList.add(list.getSelectedValue());
					QuestionAnswer[] selectedTemp = new QuestionAnswer[selectedQsList.size()];
					selectedQsList.toArray(selectedTemp);
					selectedQAsList.setListData(selectedTemp);
					mainFrame.repaint();
				}
			}
		});

		/*
		 * @author Stuart
		 * edits by Alex 
		 */
		btnRemoveQ.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!selectedQAsList.isSelectionEmpty()) {
					int i = selectedQAsList.getSelectedIndex();
					selectedQsList.remove(i);
					QuestionAnswer[] selectedTemp = new QuestionAnswer[selectedQsList.size()];
					selectedQsList.toArray(selectedTemp);
					selectedQAsList.setListData(selectedTemp);
					mainFrame.repaint();
				}
			}
		});

		/*
		 * @author Alex
		 */
		selectedQAsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(!selectedQAsList.isSelectionEmpty()){
					list.clearSelection();
					answerTextArea.setText("");
					answerTextArea.setText(selectedQAsList.getSelectedValue().getAnswer());
					mainFrame.repaint();
				}
			}			
		});

		/*
		 * @author Alex
		 */
		btnAddToClip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

				if(answerTextArea.getSelectedText() == null) {
					answerTextArea.selectAll();
					clipboard.setContents(new StringSelection(answerTextArea.getSelectedText()), null);
				}
				else {
					clipboard.setContents(new StringSelection(answerTextArea.getSelectedText()), null);
				}
			}
		});

		//popup menu listeners
		/*
		 * Action listener for the select all menu item in the pop up menu.
		 * @author Alex
		 */
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
				mainFrame.repaint();
			}
		});

		/*
		 * Action listener for the copy menu item in the pop up menu.
		 * @author Alex
		 */
		mntmCopy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(popupMenu.getInvoker().equals(answerTextArea)) {
					clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(new StringSelection(answerTextArea.getSelectedText()), null);
				}
				mainFrame.repaint();
			}
		});

		/*
		 * Action listener for the paste menu item in the pop up menu.
		 * @author Alex
		 */
		mntmPaste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(popupMenu.getInvoker().equals(notesArea)) {
					String result = "";
					clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					Transferable contents = clipboard.getContents(null);
					boolean hasTransferableText =
							(contents != null) && contents.isDataFlavorSupported(DataFlavor.stringFlavor);
					if (hasTransferableText) {
						try {
							result = (String)contents.getTransferData(DataFlavor.stringFlavor);
							notesArea.insert(result, notesArea.getCaretPosition());
						}
						catch (UnsupportedFlavorException | IOException e1){
							//do nothing cause we smart
						}
					}
				}
				mainFrame.repaint();
			}
		});

		/*
		 * @author Alex
		 */
		mntmUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(notesArea.hasFocus()) {
					//TODO
					//////////////////////////////////////////////ALEX FINISH THIS! maybe?
				}
			}
		});

		/*
		 * Help menu to show that user can email us for assistance.
		 * @author Chutiwat
		 */
		mntmHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent H) {				

				JOptionPane.showMessageDialog(null, 
						"For assistant: Please contact blueteamtcss360@gmail.com", 
						"Help",
						JOptionPane.PLAIN_MESSAGE);				
			}
		});

		/*
		 * Show our site for about us and allow the user to click to open the site
		 * @author Chutiwat
		 */
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent A) {
				Object[] options = {"Open Webpage", "Cancel"};

				int n = JOptionPane.showOptionDialog(null, 
						"We are BlueTeam\n" 
						+ "For more information please visit our website\n" 
						+ "https://sites.google.com/site/blueteamtcss360/", 
						"About",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE,
						null,
						options,
						options[0]);	
				if (n == JOptionPane.YES_OPTION){
					try{
						Desktop desktop = java.awt.Desktop.getDesktop();
						URI oURL = new URI("https://sites.google.com/site/blueteamtcss360/");
						desktop.browse(oURL);
					}
					catch(Exception e){
						e.printStackTrace();
					}					
				}
			}
		});		
	}

	/*
	 * Private helper method to load a previously saves list
	 * of selected questions and the notes made for a specific RFP.
	 * @author Stuart
	 */
	private void load() {
		int completed;
		completed = myJFC.showOpenDialog(null);
		if (completed == JFileChooser.APPROVE_OPTION) {
			try {
				Scanner in = new Scanner(myJFC.getSelectedFile());
				int count = in.nextInt();
				ArrayList<QuestionAnswer> QAlist = new ArrayList<QuestionAnswer>();
				for(int i = 0; i < count; i++) {
					String[] QA = {"", "", "", ""};
					String last = "";
					int edit;
					last = in.nextLine();
					while(!last.contains("=====")) {
						QA[0] += last + "\n";
						last = in.nextLine();
					}
					last = in.nextLine();
					while(!last.contains("=====")) {
						QA[1] += last+ "\n";
						last = in.nextLine();
					}
					last = in.nextLine();
					while(!last.contains("=====")) {
						QA[2] += last+ "\n";
						last = in.nextLine();
					}
					last = in.nextLine();
					while(!last.contains("=====")) {
						QA[3] += last+ "\n";
						last = in.nextLine();
					}
					edit = in.nextInt();
					QAlist.add(new QuestionAnswer(QA[0], QA[1], QA[2], QA[3], edit));
				}
				StringBuilder sb = new StringBuilder();
				while(in.hasNextLine()) {
					sb.append(in.nextLine());
				}
				selectedQsList = QAlist;
				QuestionAnswer[] selectedTemp = new QuestionAnswer[selectedQsList.size()];
				selectedQsList.toArray(selectedTemp);
				selectedQAsList.setListData(selectedTemp);
				notesArea.setText(sb.toString());
				mainFrame.repaint();
				in.close();
			} catch (FileNotFoundException e) {
				JOptionPane.showMessageDialog(null, "invalid file");
				load();
			}
			fileSelected = true;
		}
	}

	/*
	 * @author Stuart
	 */
	private void newRFP() {
		fileSelected = false;
		selectedQsList = new ArrayList<QuestionAnswer>();
		QuestionAnswer[] selectedTemp = {};
		selectedQAsList.setListData(selectedTemp);
		notesArea.setText("");
		mainFrame.repaint();
	}


	/*
	 * Private helper method to add the popup menu to specific components in the view.
	 * @author Stuart
	 */
	private void save() {
		if(fileSelected) {
			StringBuilder sb = new StringBuilder();
			try {
				PrintStream out = new PrintStream(myJFC.getSelectedFile());
				sb.append(selectedQsList.size());
				sb.append("\n");
				for(int i=0; i < selectedQsList.size(); i++) {
					sb.append(selectedQsList.get(i).getCategory());
					sb.append("\n=====\n");
					sb.append(selectedQsList.get(i).getKeyPhrases());
					sb.append("\n=====\n");
					sb.append(selectedQsList.get(i).getQuestion());
					sb.append("\n=====\n");
					sb.append(selectedQsList.get(i).getAnswer());
					sb.append("\n=====\n");
					sb.append(selectedQsList.get(i).getEditLvl());
					sb.append("\n");
				}
				sb.append(notesArea.getText());
				out.print(sb.toString());
				out.close();
			} catch (FileNotFoundException e) {
				saveAs();
			}
		} else {
			saveAs();
		}
	}

	/*
	 * Private helper method to save the selected questions and
	 * notes made about a specific RFP.
	 * @author Stuart
	 */
	private void saveAs() {
		int completed;
		completed = myJFC.showOpenDialog(null);
		if (completed == JFileChooser.APPROVE_OPTION) {
			fileSelected = true;
			save();
		}
	}
}
