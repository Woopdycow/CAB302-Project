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
		setPreferredSize(size);
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		JButton button1 = new JButton("Button 1");
		
		//gbc.weightx = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0,0,1,0);
		gbc.fill = GridBagConstraints.BOTH;
		
		add(button1, gbc);
	}

}
