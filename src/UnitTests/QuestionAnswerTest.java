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

import Model.QuestionAnswer;

/**
 * JUnit test class to test the QuestionAnswer.java class.
 * 
 * @author Steve Onyango
 * @version Mar 16, 2015
 */
public class QuestionAnswerTest 
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

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		my_question_answer = new QuestionAnswer(my_category, my_key_phrases, my_sample_question, 
				my_answer, my_edit_level);
 	}
 
	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception 
	{
		System.out.println("End of Tests");
	}

	/**
	 * Test method for {@link Model.QuestionAnswer#getEditLvl()}.
	 */
	@Test
	public void testEditLvl()
	{
		assertEquals("Edit levels not same.", my_edit_level, my_question_answer.getEditLvl());
	}

	/**
	 * Test method for {@link Model.QuestionAnswer#getAll()}.
	 */
	@Test
	public void testGetAll()
	{
		final String string = my_category + " " + my_key_phrases + " " + 
							my_sample_question + " " + my_answer + " " + my_edit_level;
		assertEquals("GetAll method failed.",string, my_question_answer.getAll());
		
	}

	/**
	 * Test method for {@link Model.QuestionAnswer#getAnswer()}.
	 */
	@Test
	public void testGetAnswer()
	{
		assertEquals("Answers not same.",my_answer, my_question_answer.getAnswer());
	}

	/**
	 * Test method for {@link Model.QuestionAnswer#getCategory()}.
	 */
	@Test
	public void testGetCategory() 
	{
		assertEquals("Categories not same.",my_category, my_question_answer.getCategory());
	}

	/**
	 * Test method for {@link Model.QuestionAnswer#getKeyPhrases()}.
	 */
	@Test
	public void testGetKeyPhrases() 
	{
		assertEquals("Phrases not equal.",my_key_phrases, my_question_answer.getKeyPhrases());
	}

	/**
	 * Test method for {@link Model.QuestionAnswer#getQuestion()}.
	 */
	@Test
	public void testGetQuestion()
	{
		assertEquals("Questions not same.",my_sample_question, my_question_answer.getQuestion());
	}
 
	/**
	 * Test method for {@link Model.QuestionAnswer#QuestionAnswer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, int)}.
	 */
	@Test
	public void testQuestionAnswer()
	{
		System.out.println("Already Implemented above.");
	}
	/**
	 * Test method for {@link Model.QuestionAnswer#toString()}.
	 */
	@Test
	public void testToString()
	{
		// same as above
		assertEquals("toString method failed.",my_sample_question, my_question_answer.toString());
	}
}
