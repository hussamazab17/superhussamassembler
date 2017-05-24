package panel;

import blocks.A;
import blocks.Block;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JPanel;
import main.AnimationSquare;
import main.Game;

public class LevelEditorPanel extends JPanel implements KeyEventDispatcher {
	
	private Block currentBlock;
	private BlockSelectBox[] selectArr = {};
	private BlockBox[][] editorArr;
	private AnimationSquare mainMenu, save, load, menuSave, menuLoad, 
			menuCancel;
	private int xOff, yOff, size;
	private boolean menuActive, shift, isLoading;
	private String name, sOrL;
	
	public LevelEditorPanel() {
		this.currentBlock = new A(new Rectangle(0, 0, 16, 16));
		this.editorArr = new BlockBox[16][30];
		this.mainMenu = new AnimationSquare(new Rectangle(0, 0, 150, 50),
			Color.ORANGE, Color.YELLOW) {
			public void doAction() {
				Game.switchPanel(0);
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(menuActive) return;
				super.mouseClicked(me);
			}
		};
		this.save = new AnimationSquare(new Rectangle(1920 - 150, 0, 150, 50),
			Color.GREEN, Color.GREEN.darker().darker().darker()) {
			public void doAction() {
				name = "";
				menuActive = true;
				isLoading = false;
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(menuActive) return;
				super.mouseClicked(me);
			}
		};
		this.load = new AnimationSquare(new Rectangle(1920 - 150, 51, 150, 50),
			Color.CYAN, Color.BLUE) {
			public void doAction() {
				name = "";
				menuActive = true;
				isLoading = true;
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(menuActive) return;
				super.mouseClicked(me);
			}
		};
		this.menuSave = new AnimationSquare(new Rectangle(1920 / 2 - 75, 
				1080 / 2 - 50, 150, 50),
			Color.GREEN, Color.GREEN.darker().darker().darker()) {
			public void doAction() {
				try {
					save();
				} catch (Exception ex) {}
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(!menuActive) return;
				super.mouseClicked(me);
			}
		};
		this.menuLoad = new AnimationSquare(new Rectangle(1920 / 2 - 75, 
				1080 / 2 - 50, 150, 50),
			Color.CYAN, Color.BLUE) {
			public void doAction() {
				try {
					load(name);
				} catch (Exception ex) {}
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(!menuActive) return;
				super.mouseClicked(me);
			}
		};
		this.menuCancel = new AnimationSquare(new Rectangle(1920 / 2 - 75, 
				1080 / 2 + 100, 150, 50),
			Color.RED.brighter().brighter().brighter(), Color.RED) {
			public void doAction() {
                menuActive = false;
                isLoading = false;
			}
			
			@Override
			public void mouseClicked(MouseEvent me) {
				if(!menuActive) return;
				super.mouseClicked(me);
			}

		};
	
		addMouseListener(mainMenu);
		addMouseMotionListener(mainMenu);
		addMouseListener(save);
		addMouseMotionListener(save);
		addMouseListener(load);
		addMouseMotionListener(load);
		addMouseListener(menuSave);
		addMouseMotionListener(menuSave);
		addMouseListener(menuLoad);
		addMouseMotionListener(menuLoad);
		addMouseListener(menuCancel);
		addMouseMotionListener(menuCancel);
		
		this.menuActive = false;
		this.shift = false;
		this.name = "";
		this.sOrL = "";
		
		size = 48;
		xOff = (1920 - size * editorArr[0].length) / 2;
		yOff = (1080 - size * editorArr.length) / 2 - 100;
		
		KeyboardFocusManager kf = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kf.addKeyEventDispatcher(this);
		
		for(int y = 0; y < editorArr.length; y++) {
			for(int x = 0; x < editorArr[0].length; x++) {
				editorArr[y][x] = new BlockBox(new Rectangle(xOff + (x * size),
					yOff + (y * size), size, size),
                    new A((new Rectangle(xOff + (x * size),
					yOff + (y * size), size, size)))) {
						public void doAction() {
							this.setBlock(currentBlock);
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
		
		final LevelEditorPanel p = this;
		new Thread() {
			public void run() {
				while(isVisible()) {
					try {
						Thread.sleep(100);
					} catch(Exception ex) {}				
				}
				
				KeyboardFocusManager kf = KeyboardFocusManager.getCurrentKeyboardFocusManager();
				kf.removeKeyEventDispatcher(p);
			}
		}.start();
	}
 	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.DARK_GRAY);
		
		Graphics2D g2 = (Graphics2D)g;
				
		g.setColor(Color.GRAY);
		g.fillRect(xOff, yOff, 1920 - xOff * 2, 1080 - (yOff + 100) * 2);
		g.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		FontMetrics fm = g2.getFontMetrics();
		
		if(menuActive) {
			g.setColor(Color.WHITE);
			
            g.setColor(Color.LIGHT_GRAY);
		
            for(int y = 0; y < editorArr.length; y++) {
                for(int x = 0; x < editorArr[0].length; x++) {
                    g2.draw(editorArr[y][x].getRectangle());
                }
            }
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, 1920, 1080);
            
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            g.setColor(Color.GRAY);
			
			Rectangle r = new Rectangle(1920 / 2 - 250, 1080 / 2 - 300, 500, 600);
            g2.fill(r);
			
			g.setColor(Color.WHITE);
			
			g.drawString("Level Name", (int) (r.getX() + 
				r.getWidth() / 2) - 
				fm.stringWidth("Level Name") / 2, (int) (r.getY() + 10 + 
				fm.getAscent()));
			
			r = new Rectangle(1920 / 2 - 150, 1080 / 2 - 200, 300, 50);
			
			g2.fill(r);
            
			g.setColor(Color.BLACK);
			
			g.drawString(name, 1920 / 2 - fm.stringWidth(name) / 2, 
					(int) r.getY() + fm.getAscent() + 5);
			
			if(isLoading) {
				g.setColor(Color.CYAN);
				g2.fill(menuLoad.getRectangle());
				
				g.setColor(menuLoad.getColor());
				g2.draw(menuLoad.getRectangle());
				
				g.setColor(Color.WHITE);
				g.drawString("Load", 1920 / 2 - fm.stringWidth("Load") / 2,
                        (int) (menuLoad.getY() + fm.getAscent() + 5));
			} else {
				g.setColor(Color.GREEN);
				g2.fill(menuSave.getRectangle());
				
				g.setColor(menuSave.getColor());
				g2.draw(menuSave.getRectangle());
				
				g.setColor(Color.WHITE);
                g.drawString("Save", 1920 / 2 - fm.stringWidth("Save") / 2,
                        (int) (menuSave.getY() + fm.getAscent() + 5));
			}			
			
            g.setColor(Color.RED.brighter().brighter().brighter());
            g2.fill(menuCancel.getRectangle());
            
            g.setColor(menuCancel.getColor());
            g2.draw(menuCancel.getRectangle());
            
            g.setColor(Color.WHITE);
            g.drawString("Cancel", 1920 / 2 - fm.stringWidth("Cancel") / 2, 
                    (int) (menuCancel.getY() + fm.getAscent() + 5));
            
            return;
		}
		
		g.setColor(Color.ORANGE);
		g2.fill(mainMenu.getRectangle());
		
		g.setColor(mainMenu.getColor());
		g2.draw(mainMenu.getRectangle());

		g.setColor(Color.WHITE);
		g.drawString("Main Menu", (int) mainMenu.getRectangle().getWidth() / 2 - 
				fm.stringWidth("Main Menu") / 2,
				(int) mainMenu.getHeight() - fm.getAscent() / 2);
		
		g.setColor(Color.GREEN);
		g2.fill(save.getRectangle());
		
		g.setColor(save.getColor());
		g2.draw(save.getRectangle());
		
		g.setColor(Color.WHITE);
		g.drawString("Save", (int) (save.getRectangle().getX() + 
				save.getRectangle().getWidth() / 2) - 
				fm.stringWidth("Save") / 2, (int) ((int) save.getY() + 
				save.getHeight() - fm.getAscent() / 2));
		
		g.setColor(Color.CYAN);
		g2.fill(load.getRectangle());
		
		g.setColor(load.getColor());
		g2.draw(load.getRectangle());
		
		g.setColor(Color.WHITE);
		g.drawString("Load", (int) (load.getRectangle().getX() + 
				load.getRectangle().getWidth() / 2) - 
				fm.stringWidth("Load") / 2, (int) ((int) load.getY() + 
				load.getHeight() - fm.getAscent() / 2));
		
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
		if(!menuActive || ke.getID() != KeyEvent.KEY_PRESSED) return false;
		
        if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			try {
                name = name.substring(0, name.length() - 1);
            } catch(Exception ex) {}
            return false;
        }
        
        if(name.length() == 16) return false;
        
		if(shift)
			shift = false;
		if(ke.getKeyCode() == KeyEvent.VK_SHIFT)
			shift = true;
        
		if(Character.isAlphabetic(ke.getKeyChar()) ||
                Character.isDigit(ke.getKeyChar())) {
			if(shift) {
				name += ke.getKeyChar() - 32;
				return false;
			}
			name += ke.getKeyChar();
        }
		
		return false;
	}
    
    public void save() throws Exception {
        File f = new File("level", name + ".txt");
        
        if(!f.exists()) {
            f.createNewFile();
        }
        
        FileWriter fw = new FileWriter(f);
        
        for(BlockBox[] arr : editorArr) {
            for(BlockBox box : arr) {
                fw.append(box.getBlock().getClass().getSimpleName());
            }
            System.out.println();
        }
    }
    
    public void load(String s) throws Exception {
        File f = new File("level", s + ".txt");
        
        Scanner scan = new Scanner(f);
        
        while(scan.hasNextLine()) {
            
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
        
        public void setBlock(Block b) {
            this.b = b;
        }
	}
    
    class BlockSelectBox extends AnimationSquare {

		private Block b;
		
		public BlockSelectBox(Rectangle r, Block b) {
			super(r);
			this.b = b;
		}
        
        public void doAction() {
            currentBlock = b;
        }
		
	}
}