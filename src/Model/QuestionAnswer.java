package Model;

/**
 * This class is the element that is to be stored in the database for the program. Each individual element is produced
 * from an excel spread sheet specified by the layout provided from the instructor. The data base is responsible for reading
 * in the excel spread sheet element and populating the database.
 * @author Jeremiah Stowe
 *
 */
public class QuestionAnswer {

	private String my_Category;
	private String [] my_KeyPhrases;
	private String my_SampleQuestion;
	private String my_Answer;
	private int my_EditLevel;
	
	public QuestionAnswer (String the_category, String [] the_key_phrases, String the_question, String the_answer, int the_level){		
		my_Category = the_category;
		my_KeyPhrases = the_key_phrases.clone();
		my_SampleQuestion = the_question;
		my_Answer = the_answer;
		my_EditLevel = the_level;
	}
}
