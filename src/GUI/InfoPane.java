package GUI;

import java.awt.ComponentOrientation;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import stock.Item;
import stock.Store;

public class InfoPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Store myStore = Store.getInstance();
	
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
	
	JTable table = new JTable(new DefaultTableModel (new Object[0][0], columnNames));

    public JScrollPane pane = new JScrollPane(table);
	
	/**
	 * @author Jonathon Meyer
	 */
	public InfoPane() {
		
		
		
		//setLayout(new BorderLayout());
        
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

        
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipadx = 900;
        gbc.ipady = 250;
        gbc.insets = new Insets(0,0,0,0);
        
        add(pane, gbc);
	
		for (Item i : myStore.getStock().getItemSet()) {
			String name = i.getName();
			int quantity = (myStore.getStock().getQuantity(i));
			double manuCost = i.getCost();
			double sellPrice = i.getPrice();
			int reorderPoint = i.getReorderPoint();
			int reorderAmount = i.getReorderAmount();
			double temp = i.getTemp();
			
			if (temp == 11.00) {
				String noTemp = "N/A";
				Object[] itemInfo = {name, quantity, manuCost, sellPrice, reorderPoint, reorderAmount, noTemp};
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(itemInfo);
			} else {
				Object[] itemInfo = {name, quantity, manuCost, sellPrice, reorderPoint, reorderAmount, temp};
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(itemInfo);
			}
		}
	}
	
	public void updateTable() {
		for (Item i : myStore.getStock().getItemSet()) {
			String name = i.getName();
			int quantity = (myStore.getStock().getQuantity(i));
			double manuCost = i.getCost();
			double sellPrice = i.getPrice();
			int reorderPoint = i.getReorderPoint();
			int reorderAmount = i.getReorderAmount();
			double temp = i.getTemp();
			
			if (temp == 11.00) {
				String noTemp = "N/A";
				Object[] itemInfo = {name, quantity, manuCost, sellPrice, reorderPoint, reorderAmount, noTemp};
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(itemInfo);
			} else {
				Object[] itemInfo = {name, quantity, manuCost, sellPrice, reorderPoint, reorderAmount, temp};
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(itemInfo);
			}
		}
	}
}
