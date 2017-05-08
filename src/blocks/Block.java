package blocks;

import java.awt.Color;
import java.awt.Image;

public abstract class Block {

	private Color color;
	private Image image;	
	
	public Block(Color color) {
		this.color = color;
	}
	
	public Block(Image img) {
		image = img;
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
