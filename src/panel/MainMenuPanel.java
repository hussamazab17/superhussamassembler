package panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import main.AnimationSquare;
import main.Game;

public class MainMenuPanel extends JPanel {
	
	private AnimationSquare[] squares;
	private AnimationSquare close;
	
	public MainMenuPanel() {
		squares = new AnimationSquare[] {
			new AnimationSquare(new Rectangle(1920 / 2 - 100, 1080 - 300, 200, 50), Color.LIGHT_GRAY, Color.YELLOW) {
				public void doAction() {
					try {
						Game.switchPanel(1);
					} catch (Exception ex) {}
				}
			},
			new AnimationSquare(new Rectangle(1920 / 2 - 100, 1080 - 500, 200, 50), Color.LIGHT_GRAY, Color.YELLOW) {
				public void doAction() {
					try {
						Game.switchPanel(2);
					} catch (Exception ex) {}
				}
			}
		};
		
		close = new AnimationSquare(new Rectangle(1920 - 25, 0, 25, 25)) {
			public void doAction() {
				Game.frame.setVisible(false);
				Game.frame.dispose();
			}
		};
		
		addMouseListener(close);
		addMouseMotionListener(close);
		
		for(AnimationSquare ani : squares) {
			addMouseListener(ani);
			addMouseMotionListener(ani);
		}
			
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(Color.BLACK);
		
		Graphics2D g2 = (Graphics2D)g;
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
		FontMetrics fm = g2.getFontMetrics();
		
		g.setColor(Color.DARK_GRAY);
		g.fillRect(1920 / 2 - 400, 100, 800, 1080 - 200);
		
		g.setColor(Color.YELLOW);
		g.drawString("Super Hussam", 1920 / 2 - fm.stringWidth("Super Hussam") 
				/ 2, 1080 / 2 - 375 + fm.getAscent());
		g.drawString("Assembler", 1920 / 2 - fm.stringWidth("Assembler")
				/ 2, 1080 / 2 - 275 + fm.getAscent());
		
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		fm = g2.getFontMetrics();
		
		
		for(AnimationSquare ani : squares) {
			g.setColor(Color.GRAY);
			g2.fill(ani.getRectangle());
			g.setColor(ani.getColor());
			g2.draw(ani.getRectangle());
			
			g.setColor(Color.WHITE);
			if(ani.getRectangle().getY() == 1080 - 300) {
				int x = 1920 / 2 - fm.stringWidth("Editor") / 2;
				int y = 1080 - 300 + fm.getAscent();
				
				g.drawString("Editor", x, y);
			} else {
				int x = 1920 / 2 - fm.stringWidth("Play") / 2;
				int y = 1080 - 500 + fm.getAscent();
							
				g.drawString("Play", x, y);
			}
		}
		
		g.setColor(Color.RED);
		g2.fill(close.getRectangle());
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		
		g.drawString("X", 1920 - 20, 20);
	}
}
