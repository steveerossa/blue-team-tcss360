package View;

import javax.swing.JRootPane;
import java.awt.GridBagLayout;
import javax.swing.JRadioButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RFPView {
	
	JRootPane rootPane;
	
	public RFPView() {
		rootPane = new JRootPane();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		rootPane.getContentPane().setLayout(gridBagLayout);
		
		JRadioButton rdbtnBusiness = new JRadioButton("Business");
		GridBagConstraints gbc_rdbtnBusiness = new GridBagConstraints();
		gbc_rdbtnBusiness.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnBusiness.gridx = 0;
		gbc_rdbtnBusiness.gridy = 0;
		rootPane.getContentPane().add(rdbtnBusiness, gbc_rdbtnBusiness);
		
		JRadioButton rdbtnTechnical = new JRadioButton("Technical");
		GridBagConstraints gbc_rdbtnTechnical = new GridBagConstraints();
		gbc_rdbtnTechnical.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnTechnical.gridx = 1;
		gbc_rdbtnTechnical.gridy = 0;
		rootPane.getContentPane().add(rdbtnTechnical, gbc_rdbtnTechnical);
		
		JLabel lblFilter = new JLabel("Filter:");
		GridBagConstraints gbc_lblFilter = new GridBagConstraints();
		gbc_lblFilter.insets = new Insets(0, 0, 5, 5);
		gbc_lblFilter.gridx = 0;
		gbc_lblFilter.gridy = 1;
		rootPane.getContentPane().add(lblFilter, gbc_lblFilter);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 2;
		rootPane.getContentPane().add(panel, gbc_panel);
	}

}
