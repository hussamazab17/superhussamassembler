package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import panel.GamePanel;
import panel.LevelEditorPanel;
import panel.MainMenuPanel;

public class Game {

	public static JFrame frame;
	
    public static void main(String[] args) throws Exception {
		frame = new JFrame("Super Hassam Assembler");
		frame.setSize(1920, 1080);
		frame.setUndecorated(true);
		
		Thread t = new Thread() {
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000 / 60);
					} catch(Exception ex) {}
					
					frame.repaint();
				}
			}
		};
		
		t.start();
		
		frame.add(new MainMenuPanel());
		
		frame.setVisible(true);
    }
	
	/*
		0 = MAIN
		1 = EDITOR
		2 = GAME
	*/
	public static void switchPanel(int i) {
		if(i < 0 || i > 2) return;
		
		frame.removeAll();
		
		switch(i) {
			case 0:
				frame.add(new MainMenuPanel());
				break;
			case 1:
				frame.add(new LevelEditorPanel());
				break;
			case 2:
				frame.add(new GamePanel());
				break;
		}
		
		frame.validate();
		frame.setVisible(true);
	}

}
