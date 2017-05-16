package panel;
import blocks.Block;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;
import main.AnimationSquare;

public class LevelEditorPanel extends JPanel {
	
	private Block currentBlock;
	private BlockBox[] selectArr = {};
	private AnimationSquare[][] editorArr;
	private int xOff, yOff, size;
	
	public LevelEditorPanel() {
		this.currentBlock = null;
		this.editorArr = new AnimationSquare[50][105];
		
		size = 15;
		xOff = (1920 - size * editorArr[0].length) / 2;
		yOff = (1080 - size * editorArr.length) / 2 - 100;
		
		for(int y = 0; y < editorArr.length; y++) {
			for(int x = 0; x < editorArr[0].length; x++) {
				editorArr[y][x] = new AnimationSquare(new Rectangle(xOff + (x * size),
					yOff + (y * size), size, size));
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
