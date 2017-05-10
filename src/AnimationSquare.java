
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class AnimationSquare implements MouseListener, MouseMotionListener {
	
	private Rectangle r;
	private Color current, c1, c2;
	
	public AnimationSquare(Rectangle r) {
		this.r = r;
		this.c1 = Color.GRAY;
		this.c2 = Color.YELLOW;
		this.current = c1;
	}
	
	public AnimationSquare(Rectangle r, Color c1, Color c2) {
		this.r = r;
		this.current = c1;
		this.c1 = c1;
		this.c2 = c2;
	}

	public double getX() {
		return r.getX();
	}
	
	public double getY() {
		return r.getY();
	}
	
	public double getWidth() {
		return r.getWidth();
	}
	
	public double getHeight() {
		return r.getHeight();
	}
	
	public void doAction() {
		System.out.println("This worked!");
	}
	
	public Color getColor() {
		return current;
	}
	
	private void switchCurrent() {
		if(current == c1) {
			current = c2;
			return;
		}
		current = c1;
	}
	
	private boolean isInside(Point p) {
		return p.getX() >= r.getX() && p.getY() >= r.getY() &&
				p.getX() <= r.getX() + r.getWidth() &&
				p.getY() <= r.getY() + r.getHeight();
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		if(isInside(me.getPoint())) {
			doAction();
		}
	}

	@Override
	public void mousePressed(MouseEvent me) {}

	@Override
	public void mouseReleased(MouseEvent me) {}

	@Override
	public void mouseEntered(MouseEvent me) {}

	@Override
	public void mouseExited(MouseEvent me) {}

	@Override
	public void mouseDragged(MouseEvent me) {}

	@Override
	public void mouseMoved(MouseEvent me) {
		Point p = me.getPoint();
		
		if(isInside(p)) {
			if(current == c1)
				switchCurrent();
		} else {
			if(current == c2)
				switchCurrent();
		}
	}
	
}
