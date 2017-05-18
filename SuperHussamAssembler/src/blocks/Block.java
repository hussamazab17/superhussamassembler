package blocks;

import java.awt.Color;

public abstract class Block {

	private Color color;
	
	public Block(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
}
