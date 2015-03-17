
//steve

package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
//import javax.swing.JToggleButton;

/**Class AnswerDisplay is a JPanel that displays the questions and answers.
 * @author Steve Onyango*/
public class AnswerDisplay extends JPanel 
{
	/**Serial Version ID. */
	private static final long serialVersionUID = 1L;
	
	/**JCombobox that has all the keys for searching database.*/
	private JComboBox<String> my_key_box ;
	
	/**JTextField to enter search key.*/
	private JTextField my_key_field;

	/**JList which contains all the pre-selection questions. */
	private JList<String> my_question_list;
	
	/**Textfield that displays the answers to be pasted into the RFP.*/
	private JTextArea my_answer_area;
	
	/**The pre-selected questions.*/
//	private String [] my_questions;
	
	/**Array of strings that contains keys for querying the database. This will
	 * be used in the JComboBox.*/
	private String [] my_keys = {"ONE","TWO","THREE"} ; // one two tree for testing
	
	
	
	
	/**The Constructor.*/
	public AnswerDisplay ()
	{
		setLayout(new BorderLayout()); // borderLayout
		
		my_answer_area  = new JTextArea();
		my_key_field  = new JTextField();
		my_question_list = new JList<>(my_keys);
		
		
		
		my_key_box = new JComboBox<String>(my_keys);
		my_key_box.addItem("String");
		my_key_box.addActionListener(my_key_box);
		this.add(queryPanel(), BorderLayout.WEST);
		this.add(answerPanel(), BorderLayout.EAST);
		this.setVisible(true);
		//add(answerPanel());
		//add("ssssssssssss" ,new JToggleButton("This Button!"));
		
		
	} // end constructor 
	
	/**JPanel that displays the answers.*/
	private JPanel answerPanel()
	{
		JPanel panel  = new JPanel(new BorderLayout());
		
		panel.add(new JLabel("Answers to Questions"), BorderLayout.NORTH);
		panel.add(my_answer_area, BorderLayout.CENTER);
		
		return panel;
		
	}
	/**JPanel that displays the Keys and Questions.*/
	private JPanel queryPanel()
	{
		
		JPanel panel  = new JPanel(new BorderLayout());
		
		JPanel northPanel  = new JPanel(new GridLayout(2,2));
		northPanel.add( new JLabel("Select key"));
		northPanel.add( new JLabel("Type key to search"));
		northPanel.add(new JScrollPane( my_key_box));
		northPanel.add(my_key_field);
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel centerPanel  = new JPanel(new BorderLayout());
		centerPanel.add(new JLabel("List of questions"), BorderLayout.NORTH);
		centerPanel.add(my_question_list, BorderLayout.CENTER);
		panel.add(centerPanel, BorderLayout.CENTER);
		repaint();
	
		return panel;
		
	}
	
	/**Testing class*/
	public static void main(String... arguments)
	{

		/*System.out.println("Working");
		//new AnswerDisplay();
		JFrame frame  = new JFrame("Frame Here");
		frame.setVisible(true);
		frame.setSize(400, 400);
		frame.add(new AnswerDisplay());
		frame.setVisible(true);*/

	}
	
	
	

	 

} // end class
