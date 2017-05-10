
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {

	public static JFrame frame;
	
    public static void main(String[] args) throws Exception {
		frame = new JFrame("Super Hassam Assembler");
		frame.setSize(1920, 1080);
		frame.setUndecorated(true);
		
		final AnimationSquare ANI = new AnimationSquare(new Rectangle(0, 0, 40, 40));
		
		JPanel panel = new JPanel() {		
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(ANI.getColor());
				g.drawRect((int) ANI.getX(), (int)ANI.getY(), 
						(int) ANI.getHeight(), (int) ANI.getWidth());
			}
		};
		
		panel.addMouseListener(ANI);
		panel.addMouseMotionListener(ANI);
		
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
		
		frame.add(panel);
		
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
    }

}
