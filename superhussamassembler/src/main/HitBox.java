package main;


import java.awt.Point;
import java.awt.Rectangle;

public class HitBox extends Rectangle {
	
	public HitBox(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
	}
	
	private boolean isColliding(HitBox hb)  {
		return hb.getX() >= x && hb.getY() >= y &&
				hb.getX() <= x + width &&
				hb.getY() <= y + height;
	}
	
	private void doAction() {}
	private void doAction(HitBox hb) {}
	
}
