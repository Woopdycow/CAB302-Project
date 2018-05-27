package GUI;

import java.awt.Button;

import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import CSV.ItemReader;
import CSV.ManifestReader;
import CSV.ManifestWriter;
import delivery.CSVFormatException;
import stock.DeliveryException;
import delivery.Manifest;
import stock.Item;
import stock.StockException;
import stock.Store;

public class ButtonPane extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	InfoPane infoPane = new InfoPane();

	public ButtonPane() {
		
		EventListenerList listenerList = new EventListenerList();
		
		Store myStore = Store.getInstance();
		JFileChooser chooser = new JFileChooser();
		
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Dimension size = getPreferredSize();
		size.width = 500;
		size.height = 200;
		setPreferredSize(size);
		
		DecimalFormat decimals = new DecimalFormat(".##");
		
		JTextField field = new JTextField(100);
		field.setText("$" + decimals.format(Store.getInstance().getCapital()));
		
		JLabel label = new JLabel("Capital");
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton button1 = new JButton("Load Item Properties");
		JButton button2 = new JButton("Export Manifest");
		JButton button3 = new JButton("Import Manifest");
		JButton button4 = new JButton("Load Sales Log");
		
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
						handleException("CSV Format is not correct");
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
							fireButtonEvent(new ButtonEvent(this, itemInfo));
						} else {
							Object[] itemInfo = {name, quantity, manuCost, sellPrice, reorderPoint, reorderAmount, temp};
							fireButtonEvent(new ButtonEvent(this, itemInfo));
							
						}
					
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
				}
		});
		
		//gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,740,100,0);
		gbc.fill = GridBagConstraints.NONE;
		
		//add(button1, gbc);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,400,100,0);
		gbc.fill = GridBagConstraints.NONE;
		
		//add(button2, gbc);
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,60,100,0);
		gbc.fill = GridBagConstraints.NONE;
		
		//add(button3, gbc);
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,100,280);
		gbc.fill = GridBagConstraints.NONE;
		
		//add(button4, gbc);
		
		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.insets = new Insets(200,960,100,0);
		add(label, gbc);
		
		gbc.insets = new Insets(200,850,100,0);
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.ipadx = 100;
		add(field, gbc);
		field.setEditable(false);
		
		
	}
	
	public static void handleException(String errorDescription) {
		JOptionPane.showMessageDialog(null, errorDescription);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void fireButtonEvent(ButtonEvent event) {
		Object[] listeners = listenerList.getListenerList();
		
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ButtonListener.class) {
				((ButtonListener)listeners[i+1]).buttonEventHappened(event);
			}
		}
	}
	
	public void addButtonListener(ButtonListener listener) {
		listenerList.add(ButtonListener.class, listener);
	}

	

}
