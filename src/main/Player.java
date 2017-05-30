package main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import panel.GamePanelPackage.GamePanel;

public class Player extends Rectangle {
	
	private Rectangle r;
	private int maxHp;
        private int currentHp;
        
        private boolean right = false, left = false;
	public Player(int width, int height, int hp) {
		setBounds(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2, width, height);
                currentHp = hp;
                maxHp = hp;
	}
        
        public void tick() {
            if (right) {
                x++;
            }
            
            if (left) {
                x--;
            }
        }
        
        public void draw(Graphics g) { 
            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);
        }
        
        public void keyPressed(int k) {
            if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) right = true;
            if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) left = true;
        }
        
        public void keyReleased(int k) {
            if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) right = false;
            if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT) left = false;
        }
	public boolean dispatchKeyEvent(KeyEvent ke) {
		System.out.println(ke.getKeyChar() + " " + ke.getKeyCode());
		return false;
	}

}
