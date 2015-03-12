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
        ta.setSize(width, 1);
      
        // this is just to lure the ta's internal sizing mechanism into action
        if (width > 0)
            ta.setSize(width, Short.MAX_VALUE);
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
        
        return p;
		
	}

}
