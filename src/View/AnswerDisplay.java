//steve

package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**Class AnswerDisplay is a JPanel that displays the questions and answers.*/
public class AnswerDisplay extends JPanel 
{
	/**Serial Version ID. */
	private static final long serialVersionUID = 1L;
	
	/**JCombobox that has all the keys for searching database.*/
	private JComboBox<String> my_key_box ;
	
	/**Textfield that displays the answers to be pasted into the RFP.*/
	private JTextArea my_answer_field;
	
	/**The pre-selected questions.*/
	private String [] my_questions;
	
	/**Array of strings that contains keys for querying the database.*/
	private String [] my_keys;
	
	/**JList which contains all the pre-selection questions. */
	private JList<String> my_question_list;
	/**JTextField to enter search key.*/
	private JTextField my_field;
	
	/**The Constructor.*/
	public AnswerDisplay ()
	{
		my_answer_field  = new JTextArea();
		my_field  = new JTextField();
		my_question_list = new JList<>();
		
		
		my_key_box = new JComboBox<String>(my_keys);
		my_key_box.addItem("String");
		my_key_box.addActionListener(my_key_box);
		this.add(questionPanel(), BorderLayout.WEST);
		this.add(answerPanel(), BorderLayout.EAST);
		
		
	} // end constructor 
	
	/**JPanel that displays the answers.*/
	private JPanel answerPanel()
	{
		JPanel panel  = new JPanel();
		panel.add("Answer to Questions", my_answer_field);
		
		return panel;
		
	}
	/**JPanel that displays the Key and Questions.*/
	private JPanel questionPanel()
	{
		
		JPanel panel  = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JPanel northPanel  = new JPanel(new GridLayout(1,2));
		northPanel.add("Select Keys form Menu", my_key_box);
		northPanel.add("Type Key to Search", my_field);
		panel.add(northPanel, BorderLayout.NORTH);
		
		JPanel southPanel  = new JPanel();
		southPanel.add("List of Questions.\n " +
				"Click on Question in List to reveal answer.", my_question_list);
		panel.add(southPanel, BorderLayout.NORTH);
		
		
		
		
		return panel;
		
	}
	
	/**Testing class*/
	public static void main(String... arguments)
	{
		System.out.println("Working");
	}// end main
	
	
	

	 

} // end class

