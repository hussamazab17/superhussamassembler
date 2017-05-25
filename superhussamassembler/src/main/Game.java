package main;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import panel.GamePanelPackage.GamePanel;
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
    
    public static BufferedImage scale(BufferedImage sbi, int imageType, int dWidth, int dHeight, double fWidth, double fHeight) {
        BufferedImage dbi = null;
            if(sbi != null) {
                dbi = new BufferedImage(dWidth, dHeight, imageType);
                Graphics2D g = dbi.createGraphics();
                AffineTransform at = AffineTransform.getScaleInstance(fWidth, fHeight);
                g.drawRenderedImage(sbi, at);
            }
        return dbi;
    }

}
