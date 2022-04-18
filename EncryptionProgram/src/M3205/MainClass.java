package M3205;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MainClass extends JFrame{
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IntroScreen frame = new IntroScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
