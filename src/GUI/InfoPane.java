package GUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.*;

import stock.Store;

public class InfoPane extends JPanel {

	/**
	 * @author Jonathon Meyer
	 */
	public InfoPane() {
		
		Dimension size = getPreferredSize();
		size.width = 500;
		setPreferredSize(size);
		DecimalFormat decimals = new DecimalFormat(".##");
		
		JTextField field = new JTextField(100);
		field.setText("$" + decimals.format(Store.getInstance().getCapital()));
		
		JLabel label = new JLabel("Capital");
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx= 0;
		gbc.weighty = 1;
		
		gbc.gridx = 100;
		gbc.gridy = 0;
		gbc.ipadx = 100;
		gbc.ipady = 10;
		gbc.insets = new Insets(20,0,0,0);
		gbc.anchor = GridBagConstraints.PAGE_START;
		add(field, gbc);
		field.setEditable(false);
		
		gbc.gridx = 0;
		gbc.insets = new Insets(20,250,0,0);
		add(label, gbc);

	}
}
