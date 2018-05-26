package GUI;

import java.awt.Button;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPane extends JPanel {

	public ButtonPane() {
		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		Dimension size = getPreferredSize();
		size.width = 500;
		size.height = 200;
		setPreferredSize(size);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton button1 = new JButton("Load Item Properties");
		
		//gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,740,100,0);
		gbc.fill = GridBagConstraints.BOTH;
		
		add(button1, gbc);
		
		JButton button2 = new JButton("Export Manifest");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,400,100,0);
		gbc.fill = GridBagConstraints.NONE;
		
		add(button2, gbc);
		
		JButton button3 = new JButton("Import Manifest");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,60,100,0);
		gbc.fill = GridBagConstraints.NONE;
		
		add(button3, gbc);
		
		JButton button4 = new JButton("Load Sales Log");
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,100,280);
		gbc.fill = GridBagConstraints.NONE;
		
		add(button4, gbc);
	}

}
