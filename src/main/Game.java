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
	public static void switchPanel(int i) throws Exception {
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
	
	public static BufferedImage crop(BufferedImage src, int w, int h) {
		BufferedImage dest = src.getSubimage(0, 0, w, h);
        return dest;
    }
	
    public static BufferedImage scale(BufferedImage imageToScale, int dWidth, int dHeight) {
        BufferedImage scaledImage = null;
        if (imageToScale != null) {
            scaledImage = new BufferedImage(dWidth, dHeight, imageToScale.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(imageToScale, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
        }
        return scaledImage;
    }

}
