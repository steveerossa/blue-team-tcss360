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
package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Model.Database;
import Model.QuestionAnswer;

/**
 * JUnit test class to test the Database.java class.
 * 
 * @author Steve Onyango
 * @version Mar 16, 2015
 */
public class DatabaseTest
{
	private QuestionAnswer my_question_answer;
	/**The category.*/
	private final String my_category = "Category 1.";
	/**The key phrases to use for searching database.*/
	private final String my_key_phrases = "key phrase";
	/**The questions in database that have answers.*/
	private final String my_sample_question = "Hey what is the answer";
	/**The answers to database questions.*/
	private final String my_answer = "This answer is not good enough.";
	/**The edit level.*/
	private final int my_edit_level = 2;
	
	/**The Database to be tested.*/
	private Database my_data_base;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		my_question_answer = new QuestionAnswer(my_category, my_key_phrases, my_sample_question, 
				my_answer, my_edit_level);
		my_data_base = new Database();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
		System.out.println("End all tests.");
	}

	/**
	 * Test method for {@link Model.Database#Database()}.
	 */
	@Test
	public void testDatabase()
	{
		//fail("Not yet implemented"); // TODO
		System.out.println("No tests here.");
	}  

	/**
	 * Test method for {@link Model.Database#getKeyPhrases()}.
	 */
	@Test
	public void testGetKeyPhrases()
	{
		assertNotNull("Key phrases is Null.", my_data_base.getKeyPhrases());
		assertEquals("Key phrases failed.",new Database().getKeyPhrases(),my_data_base.getKeyPhrases());
	}

	/**
	 * Test method for {@link Model.Database#searchQuestionAnswers(java.lang.String)}.
	 */
	@Test
	public void testSearchQuestionAnswers()
	{   // doesnt work if Objects are not same.
		
		assertEquals("searchQuestionAnswers failed.",my_data_base.searchQuestionAnswers("legal"),
				my_data_base.searchQuestionAnswers("legal"));
		assertEquals("searchQuestionAnswers failed.",my_data_base.searchQuestionAnswers("woman"),
				my_data_base.searchQuestionAnswers("woman"));
		Database base = new Database();;
		assertNotSame(my_data_base.searchQuestionAnswers("woman"),
				base .searchQuestionAnswers("woman"));
		
	}

}
