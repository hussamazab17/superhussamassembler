package panel;
import blocks.Block;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import main.AnimationSquare;

public class LevelEditorPanel extends JPanel implements KeyEventDispatcher {
	
	private Block currentBlock;
	private BlockBox[] selectArr = {};
	private AnimationSquare[][] editorArr;
	private int xOff, yOff, size;
	private boolean menuActive, shift;
	private String name;
	
	public LevelEditorPanel() {
		this.currentBlock = null;
		this.editorArr = new AnimationSquare[50][105];
		this.menuActive = true;
		this.shift = false;
		this.name = "";
		
		
		size = 15;
		xOff = (1920 - size * editorArr[0].length) / 2;
		yOff = (1080 - size * editorArr.length) / 2 - 100;
		
		for(int y = 0; y < editorArr.length; y++) {
			for(int x = 0; x < editorArr[0].length; x++) {
				editorArr[y][x] = new AnimationSquare(new Rectangle(xOff + (x * size),
					yOff + (y * size), size, size)) {
						public void doAction() {
							//implement function here
						}
						
						public void mouseDragged(MouseEvent me) {
							if(menuActive) return;
							super.mouseDragged(me);
						}
						
						public void mouseMoved(MouseEvent me) {
							if(menuActive) return;
							super.mouseMoved(me);
						}
						
						public void mouseClicked(MouseEvent me) {
							if(menuActive) return;
							super.mouseMoved(me);
						}
							 
					};
				this.addMouseListener(editorArr[y][x]);
				this.addMouseMotionListener(editorArr[y][x]);
			}
		}
	}
 	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.DARK_GRAY);
		
		Graphics2D g2 = (Graphics2D)g;
				
		g.setColor(Color.GRAY);
		g.fillRect(xOff, yOff, 1920 - xOff * 2, 1080 - (yOff + 100) * 2);
		
		if(menuActive) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
			
			g.drawString(name, 20, 20);
		}
		
		g.setColor(Color.LIGHT_GRAY);
		
		for(int y = 0; y < editorArr.length; y++) {
			for(int x = 0; x < editorArr[0].length; x++) {
				if(editorArr[y][x].mouseIsInside()) continue;
				g2.draw(editorArr[y][x].getRectangle());
			}
		}
		
		g.setColor(Color.YELLOW);
		
		for(int y = 0; y < editorArr.length; y++) {
			for(int x = 0; x < editorArr[0].length; x++) {
				if(!editorArr[y][x].mouseIsInside()) continue;
				g2.draw(editorArr[y][x].getRectangle());
			}
		}
		
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent ke) {
		return false;
	}

	class BlockBox extends AnimationSquare {

		private Block b;
		
		public BlockBox(Rectangle r, Block b) {
			super(r);
			this.b = b;
		}
		
		public Block getBlock() {
			return b;
		}
		
	}
}
