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

package Model;

/**
 * This class is the element that is to be stored in the database for the program. Each individual element is produced
 * from an excel spread sheet specified by the layout provided from the instructor. The data base is responsible for reading
 * in the excel spread sheet element and populating the database.
 * 
 * @author Jeremiah Stowe
 *
 */
public class QuestionAnswer {

	private String my_Category;
	private String my_KeyPhrases;
	private String my_SampleQuestion;
	private String my_Answer;
	private int my_EditLevel;
	
	public QuestionAnswer (String the_category, String the_key_phrases, String the_question, String the_answer, int the_level){		
		my_Category = the_category;
		my_KeyPhrases = the_key_phrases;
		my_SampleQuestion = the_question;
		my_Answer = the_answer;
		my_EditLevel = the_level;
	}
	
	public String getKeyPhrases()
	{
		return my_KeyPhrases;
	}
	
	public String getAnswer()
	{
		return my_Answer;
	}
	
	public String getCategory()
	{
		return my_Category;
	}
	
	public String getQuestion()
	{
		return my_SampleQuestion;
	}
	public int getEditLvl()
	{
		return my_EditLevel;
	}
	/**
	 * Returns all of the text fields in one string.
	 * @return
	 */
	public String getAll()
	{
		return my_Category + " " + my_KeyPhrases + " " + my_SampleQuestion + " " + my_Answer + " " + my_EditLevel;
	}
	
	@Override
	public String toString()
	{
		return my_SampleQuestion;
		
	}
}
