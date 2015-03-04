//steve

package View;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JRootPane;

public class AnswerDisplay extends JPanel implements Observer
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JRootPane ansDispPane;
	
	public AnswerDisplay () {
		ansDispPane = new JRootPane();
		
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		// TODO Auto-generated method stub
		
	}

} // end class
