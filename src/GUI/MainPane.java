package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainPane extends JFrame {

	private TitlePane titlePane;
	private InfoPane infoPane;
	private ButtonPane buttonPane;
	
	/**
	 * @author Jonathon Meyer
	 */
	
	public MainPane(String title) throws HeadlessException {
		super(title);
		
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
		
		titlePane = new TitlePane();
		infoPane = new InfoPane();
		buttonPane = new ButtonPane();
		
		Container window = getContentPane();
		
		window.add(titlePane, BorderLayout.NORTH);
		window.add(infoPane, BorderLayout.CENTER);
		//window.add(buttonPane, BorderLayout.SOUTH);
		
	}


	public void run() {
		// TODO Auto-generated method stub

	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
