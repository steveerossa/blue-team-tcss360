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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Portions of this code borrowed and modified from:
 * http://howtodoinjava.com/2013/06/19/readingwriting-excel-files-in-java-poi-tutorial/
 * For the use of basic Excel file parsing
 * @author Jeremiah
 *
 */
public class Database {
	
	private List<QuestionAnswer> my_data;
	
	public Database()
	{
		my_data = new ArrayList<QuestionAnswer>();
		populateData();
	}

	/**
	 * Returns the unique set of key phrases.
	 * @return Returns HashSet<String> of key phrases
	 */
	public TreeSet<String> getKeyPhrases()
	{
		TreeSet<String> keyPhrases = new TreeSet<String>();
		for(QuestionAnswer curr : my_data)
		{
			String temp = curr.getKeyPhrases();
			String keys[] = temp.split("\\*");
			for(String next : keys)
			{
				keyPhrases.add(next.trim());
			}
			
		}  
		return keyPhrases;
	}
	
	/**
	 * Method to populate the database with the associated Excel spreadsheet. At current the spreadsheet
	 * must be named "Answers.xlsx" in order to run properly. May need to include a prompt to user to select
	 * data base file in next iteration.
	 */
	private void populateData() {
		try
        {
			//final String dir = System.getProperty("user.dir");
            //FileInputStream file = new FileInputStream(new File("src/files/Answers.xlsx"));
            //FileInputStream file = new FileInputStream(new File(dir + "/Answers.xlsx"));
            InputStream file = this.getClass().getResourceAsStream("/Answers.xlsx");
            
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
 
            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                String [] rowContents = new String[5];
            	int editLevel = 0;
            	int i = 0;
                 
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case Cell.CELL_TYPE_NUMERIC:
                        	editLevel = (int) cell.getNumericCellValue();
                            break;
                        case Cell.CELL_TYPE_STRING:
                        	rowContents[i] = cell.getStringCellValue(); 
                        	i++;
                            break;  
                    }
                }
                my_data.add(new QuestionAnswer(rowContents[0],rowContents[1],rowContents[2],rowContents[3],editLevel));
            }
            workbook.close();
            file.close();
            //Bit to get rid of the first header line read in
            QuestionAnswer remove = null;
            for(QuestionAnswer cur : my_data)
            {
            	if(cur.getKeyPhrases().contains("KeyPhrase")){remove = cur;}
            }
            my_data.remove(remove);
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
	}
	
	/**
	 * Method to search for any words that might appear in the question answer object. All FIELDS!!!
	 * This includes the category, question, and the answer itself.
	 * @param the_phrase The phrase you want to search for
	 * @return Returns the list of objects that match the phrase searched.
	 */
	public ArrayList<QuestionAnswer> searchQuestionAnswers(String the_phrase)
	{
		ArrayList<QuestionAnswer> questions = new ArrayList<QuestionAnswer>();
		for(QuestionAnswer curr : my_data)
		{
			if(curr.getAll().toLowerCase().contains(the_phrase.toLowerCase())){
				questions.add(curr);
				}
		}
		return questions;
	}
	

}
