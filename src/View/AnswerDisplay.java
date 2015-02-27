//steve

package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JRootPane;

public class AnswerDisplay implements Observer{
	JRootPane ansDispPane;
	
	public AnswerDisplay () {
		ansDispPane = new JRootPane();
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
