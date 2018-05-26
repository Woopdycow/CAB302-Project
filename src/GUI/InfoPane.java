package GUI;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.*;

import stock.Store;

public class InfoPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @author Jonathon Meyer
	 */
	public InfoPane() {
		
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Dimension size = getPreferredSize();
		size.width = 500;
		setPreferredSize(size);
		DecimalFormat decimals = new DecimalFormat(".##");
		
		JTextField field = new JTextField(100);
		field.setText("$" + decimals.format(Store.getInstance().getCapital()));
		
		JLabel label = new JLabel("Capital");
		
		String[] columnNames = {"Name",
				"Quantity",
				"Manu. Cost",
				"Sell Price",
				"Reorder Point",
				"Reorder Amount",
				"Temperature"};

		Object[][] data = {
				{"Ice Cream", new Integer(3),
					new Double(50.22), new Double(55.00), new Integer(11), new Integer(25), new Double(-18)}
		};
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.weightx = 0.5;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 100;
		gbc.gridheight = 100;
		gbc.gridwidth = 50;
		gbc.gridx = 250;
		gbc.gridy = 350;
		gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.insets = new Insets(0,700,0,80);
		add(field, gbc);
		field.setEditable(false);
		
		//gbc.gridx = 0;
		gbc.weightx = 0;
		//gbc.gridy = 20;
		//gbc.anchor = GridBagConstraints.PAGE_START;
		gbc.ipadx = 0;
		gbc.insets = new Insets(0,780,0,0);
		add(label, gbc);
		
		gbc.gridx = 250;
		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 200));
		table.setFillsViewportHeight(true);
		table.setMinimumSize(new Dimension(200,200));
		
		JScrollPane scrollPane = new JScrollPane(table);
		gbc.gridx = 250;
		gbc.gridy = 350;
		gbc.gridheight = 300;
		gbc.ipady = 300;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets = new Insets(100,100,0,100);
		add(scrollPane, gbc);
		
		GridBagConstraints gbx = new GridBagConstraints();
		
		
		JButton button1 = new JButton("Button 1");
		
		//gbc.weightx = 0.5;
		gbx.gridx = 0;
		gbx.gridy = 900;
		gbx.ipadx = 0;
		gbx.ipady = 0;
		gbx.gridheight = 50;
		gbx.insets = new Insets(0,0,1,0);
		gbx.fill = GridBagConstraints.BOTH;
		gbx.anchor = GridBagConstraints.PAGE_END;
		
		add(button1, gbx);
		
		JButton button2 = new JButton("Button 2");
		
		add(button2, gbx);
		
	}
}
