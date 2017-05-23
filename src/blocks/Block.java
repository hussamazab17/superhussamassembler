package blocks;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.HitBox;

public abstract class Block extends HitBox {
    
	private BufferedImage image;
	
	public Block(String s, Rectangle r) {
        super((int)r.getX(), (int)r.getY(), (int)r.getWidth(), 
                (int)r.getHeight());
        
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images", s));
		} catch (IOException e) {
		}
	}
    
    public Block(String s, int x, int y, int w, int h) {
        this(s, new Rectangle(x, y, w, h));
    }
    
    public void update() {
        
    }
    
    public BufferedImage getImage() {
        return image;
    }
}