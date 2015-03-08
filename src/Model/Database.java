//sr stowe

package Model;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Database {
	
	List<QuestionAnswer> my_data;
	
	public Database()
	{
		my_data = new ArrayList<QuestionAnswer>();
		populateData();
	}

	/**
	 * Method to populate the database with the associated Excel spreadsheet. At current the spreadsheet
	 * must be named "Answers.xlsx" in order to run properly. May need to include a prompt to user to select
	 * data base file in next iteration.
	 */
	private void populateData() {
		try
        {
            FileInputStream file = new FileInputStream(new File("src/files/Answers.xlsx"));
            
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
                            //System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                        	rowContents[i] = cell.getStringCellValue(); 
                        	i++;
                            //System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }
                }
                my_data.add(new QuestionAnswer(rowContents[0],rowContents[1],rowContents[2],rowContents[3],editLevel));
                System.out.println("");
            }
            workbook.close();
            file.close();
            QuestionAnswer remove = null;
            for(QuestionAnswer cur : my_data)
            {
            	if(cur.toString().contains("KeyPhrase")){remove = cur;}
            }
            my_data.remove(remove);
            for(QuestionAnswer cur : my_data)
            {
            	System.out.println(cur.toString());
            }
            
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
		
	}
	

}
