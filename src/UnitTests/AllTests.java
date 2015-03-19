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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseTest.class, QuestionAnswerTest.class, UserTest.class })
public class AllTests
{

}
