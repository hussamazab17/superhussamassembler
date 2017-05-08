
import javax.swing.JFrame;

public class Game {

	public static JFrame frame;
	
    public static void main(String[] args) throws Exception {
		frame = new JFrame("Super Hassam Assembler");
		frame.setSize(1920, 1080);
		frame.setUndecorated(true);
		
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
    }

}
