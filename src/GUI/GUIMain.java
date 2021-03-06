package GUI;

import javax.swing.JFrame;

import javax.swing.SwingUtilities;

import stock.Store;

public class GUIMain {
	
	/**
	 * Main window to incorporate all GUI elements.
	 * @author Jonathon Meyer
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 600;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				JFrame GUI = new MainPane("Welcome to " + Store.getInstance().getName() + ".");
				GUI.setSize(WIDTH, HEIGHT);
				GUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				GUI.setVisible(true);
				GUI.setResizable(false);
			}
		});
	}
}
