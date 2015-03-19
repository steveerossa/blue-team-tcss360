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

package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import Model.QuestionAnswer;

/**
 * Cell renderer class is used to customize the items that show up in the lists we use
 * in the RFPView.java class.  It allows us to specify many things including cell size,
 * highlighting colors, borders, etc.
 * 
 * @author Jeremiah
 * very minor changes by Alex
 *
 */
@SuppressWarnings("serial")
public class MyCellRenderer extends JLabel implements ListCellRenderer<QuestionAnswer> {

    private JPanel p;
//    private JLabel l;
    private JTextArea ta;

    public MyCellRenderer() {
        p = new JPanel();
        p.setLayout(new BorderLayout());
        // text
        ta = new JTextArea();
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        p.add(ta, BorderLayout.CENTER);
    }

	@Override
	public Component getListCellRendererComponent(
			JList<? extends QuestionAnswer> list, QuestionAnswer value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		ta.setText("-" + value.toString());
        int width = list.getParent().getWidth();
      
        // this is just to lure the ta's internal sizing mechanism into action
        ta.setSize(width, Short.MAX_VALUE);
  
        ta.getHeight();
        Color background;
        Color foreground;

        // check if this cell represents the current DnD drop location
        JList.DropLocation dropLocation = list.getDropLocation();
        if (dropLocation != null
                && !dropLocation.isInsert()
                && dropLocation.getIndex() == index) {

        	background = Color.RED;
            foreground = Color.WHITE;

        // check if this cell is selected
        } else if (isSelected) {
        	background = new Color(51, 153, 255);
            foreground = Color.WHITE;
            

        // unselected, and not the DnD drop location
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        };

        ta.setBackground(background);
        ta.setForeground(foreground);
        ta.revalidate();
        return p;
		
	}

}
