package GUI;

import java.awt.ComponentOrientation;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import CSV.ItemReader;
import CSV.ManifestReader;
import CSV.ManifestWriter;
import delivery.CSVFormatException;
import delivery.Manifest;

import java.awt.*;

import stock.DeliveryException;
import stock.Item;
import stock.StockException;
import stock.Store;

public class InfoPane extends JPanel {
	
	/**
	 * Main GUI element containing Capital, Item table and buttons. 
	 * All event listeners are handled in this class.
	 * @author Jonathon Meyer
	 */
	private static final long serialVersionUID = 1L;
	Store myStore = Store.getInstance();
	JFileChooser chooser = new JFileChooser();
	
		public InfoPane() {
		
			
			createTable();
			
		}
	public void createTable() {
		/**
		 * 
		 */
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

	    JScrollPane pane = new JScrollPane(table);
		
		/**
		 * @author Jonathon Meyer
		 */
			
			//setLayout(new BorderLayout());
	        
			GridBagLayout layout = new GridBagLayout();
			setLayout(layout);
			GridBagConstraints gbc = new GridBagConstraints();

	        
	        gbc.fill = GridBagConstraints.NONE;
	        gbc.anchor = GridBagConstraints.CENTER;
	        gbc.ipadx = 900;
	        gbc.insets = new Insets(0,0,0,0);
	        gbc.ipady = 200;
	        
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
					model.fireTableDataChanged();
					table.repaint();
				} else {
					Object[] itemInfo = {name, quantity, manuCost, sellPrice, reorderPoint, reorderAmount, temp};
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(itemInfo);
					model.fireTableDataChanged();
					table.repaint();
				}
			}
			
			DecimalFormat decimals = new DecimalFormat(".##");
			
			JTextField field = new JTextField(100);
			field.setText("$" + decimals.format(Store.getInstance().getCapital()));
			
			JLabel label = new JLabel("Capital");
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(0,30,170,100);
			gbc.gridheight = 0;
			gbc.gridwidth = 0;
			gbc.gridx = 600;
			gbc.gridy = 400;
			gbc.ipadx = 70;
			gbc.ipady = 5;
			gbc.anchor = GridBagConstraints.PAGE_END;
			field.setEditable(false);
			
			add(field, gbc);
			
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(0,30,170,170);
			gbc.gridheight = 0;
			gbc.gridwidth = 0;
			gbc.gridx = 600;
			gbc.gridy = 400;
			gbc.ipadx = 70;
			gbc.ipady = 5;
			gbc.anchor = GridBagConstraints.PAGE_END;
			
			add(label, gbc);
			
			//GridBagLayout layout = new GridBagLayout();
			//GridBagConstraints gbc = new GridBagConstraints();
			
			JButton button1 = new JButton("Load Item Properties");
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(15,30,20,100);
			gbc.gridheight = 0;
			gbc.gridwidth = 0;
			gbc.gridx = 600;
			gbc.gridy = 400;
			gbc.ipadx = 70;
			gbc.ipady = 5;
			gbc.anchor = GridBagConstraints.PAGE_END;
			add(button1, gbc);
			
			JButton button2 = new JButton("Export Manifest");
			
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(0,30,55,100);
			//gbc.weightx = 0.5;
			//gbc.weighty = 0.5;
			gbc.gridheight = 0;
			gbc.gridwidth = 0;
			gbc.gridx = 600;
			gbc.gridy = 400;
			gbc.ipadx = 70;
			gbc.ipady = 5;
			gbc.anchor = GridBagConstraints.PAGE_END;
			add(button2, gbc);
			
			JButton button3 = new JButton("Import Manifest");
			
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(0,30,90,100);
			//gbc.weightx = 0.5;
			//gbc.weighty = 0.5;
			gbc.gridheight = 0;
			gbc.gridwidth = 0;
			gbc.gridx = 600;
			gbc.gridy = 400;
			gbc.ipadx = 70;
			gbc.ipady = 3;
			gbc.anchor = GridBagConstraints.PAGE_END;
			add(button3, gbc);
			
			JButton button4 = new JButton("Load Sales Log");
			
			gbc.anchor = GridBagConstraints.SOUTH;
			gbc.fill = GridBagConstraints.NONE;
			gbc.insets = new Insets(0,30,125,100);
			//gbc.weightx = 0.5;
			//gbc.weighty = 0.5;
			gbc.gridheight = 0;
			gbc.gridwidth = 0;
			gbc.gridx = 600;
			gbc.gridy = 400;
			gbc.ipadx = 70;
			gbc.ipady = 5;
			gbc.anchor = GridBagConstraints.PAGE_END;
			add(button4, gbc);
			
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			button1.addActionListener(new ActionListener() {
			    
				public void actionPerformed(ActionEvent e) {
					//JOptionPane.showMessageDialog (null, "Test");
					
				    int returnVal = chooser.showOpenDialog(button1);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				       System.out.println("You chose to open this file: " +
				            chooser.getSelectedFile().getName());
				       
				       ItemReader reader = new ItemReader();
				       List<Item> ItemList;
				       
						try {
							ItemList = reader.ReadItemCSV(chooser.getSelectedFile().getAbsolutePath());
							
							for (Item item : ItemList) {
						    	   myStore.getInstance().addItem(item, 0);
						    	   //System.out.println(myStore.getInstance().getItemByName("rice").getCost());
									System.out.println(item.getName() + "," + item.getCost() + "," + item.getPrice() + "," + item.getReorderPoint() + "," + item.getReorderAmount() + "," + item.getTemp() + "\n");
								}
						} catch (CSVFormatException e1) {
							//handleException("CSV Format is not correct");
							e1.printStackTrace();
						}
						
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
							field.setText("$" + decimals.format(Store.getInstance().getCapital()));
							model.fireTableDataChanged();
						}
				    }
				}
			});
			
			button2.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("Choose Export Folder...");
					chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (chooser.showSaveDialog(button2) == JFileChooser.APPROVE_OPTION) {
						try {
							ManifestWriter.CreateCSVManifest(chooser.getSelectedFile().getAbsolutePath());
							System.out.println("Exported");
						} catch (IOException e1) {
							JOptionPane.showMessageDialog(null, "Error");
							e1.printStackTrace();
						} catch (StockException e1) {
							handleException("Stock Exception");
						}
					}
					model.fireTableDataChanged();
					field.setText("$" + decimals.format(Store.getInstance().getCapital()));
				}
			});
			
			button3.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					chooser.setCurrentDirectory(new java.io.File("."));
					chooser.setDialogTitle("Select Manifest...");
					if (chooser.showOpenDialog(button3) == JFileChooser.APPROVE_OPTION) {
						
							Manifest importManifest = new Manifest();
							try {
								importManifest = ManifestReader.ReadManifestCSV(chooser.getSelectedFile().getAbsolutePath());
							} catch (CSVFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								myStore.loadManifest(importManifest);
							} catch (DeliveryException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							System.out.println("Imported.");
						}
					model.fireTableDataChanged();
					field.setText("$" + decimals.format(Store.getInstance().getCapital()));
					}
				
				
			});
			
		}
	
	public static void handleException(String errorDescription) {
		JOptionPane.showMessageDialog(null, errorDescription);
	}
	
}
