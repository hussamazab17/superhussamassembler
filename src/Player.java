
import java.awt.KeyEventDispatcher;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Player implements KeyEventDispatcher {
	
	private Rectangle r;
	
	public Player(Rectangle r) {
		this.r = r;
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent ke) {
		System.out.println(ke.getKeyChar() + " " + ke.getKeyCode());
		return false;
	}

}
