package Model;

import org.junit.Test;

/**
 * JUnit class for the purpose of testing the database when it's initialized, getters, etc...
 * @author Jeremiah
 *
 */
public class DatabaseTest {

	@Test
	public void testDatabase() {
		
		Database testing = new Database();
		System.out.println(testing.getKeyPhrases());
		System.out.println(testing.searchQuestionAnswers("Legal")); 
	}

}
