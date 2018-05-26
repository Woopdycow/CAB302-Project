package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import stock.Store;

public class TitlePane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author Jonathon Meyer
	 */
	
	public TitlePane() {
		
		Dimension size = getPreferredSize();
		size.height = 35;
		setPreferredSize(size);
		
		JLabel titleLabel = new JLabel(Store.getInstance().getName());
		Font font = new Font("Verdana", Font.BOLD, 30);
		titleLabel.setFont(font);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(titleLabel, gbc);
		
		// TODO Auto-generated constructor stub
	}
}
