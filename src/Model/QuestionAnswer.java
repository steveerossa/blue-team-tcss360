package Model;

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
