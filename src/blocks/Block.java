package blocks;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class Block extends Rectangle {

	private Color color;
	private Image image;
	
	public Block(Color color) {
		this.color = color;
	}
	
	public Block(String s) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("images", s));
		} catch (IOException e) {
		}

	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image img) {
		image = img;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	
	
}
