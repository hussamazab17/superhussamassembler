package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import panel.GamePanel;
import panel.LevelEditorPanel;
import panel.MainMenuPanel;

public class Game {

	public static JFrame frame;
	public static Thread t;
	
    public static void main(String[] args) throws Exception {
		frame = new JFrame("Super Hassam Assembler");
		frame.setSize(1920, 1080);
		frame.setUndecorated(true);
		
		t = new Thread() {			
			public void run() {
				while(true) {
					try {
						Thread.sleep(1000 / 60);
					} catch(Exception ex) {}
					
					frame.revalidate();
					frame.repaint();
				}
			}
		};
		
		t.start();
		
		frame.setContentPane(new MainMenuPanel());
		
		new Level("Boss Level").save();
		
		frame.setVisible(true);
    }
	
	/*
		0 = MAIN
		1 = EDITOR
		2 = GAME
	*/
	public static void switchPanel(int i) {
		if(i < 0 || i > 2) return;
		
		switch(i) {
			case 0:
				frame.setContentPane(new MainMenuPanel());
				break;
			case 1:
				frame.setContentPane(new LevelEditorPanel());
				break;
			case 2:
				frame.setContentPane(new GamePanel());
				break;
		}
	}

}
