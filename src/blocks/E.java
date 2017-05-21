package blocks;

import java.awt.Rectangle;

public class E extends Block {
    
    private boolean phase;
    private int frames;
    
    public E(Rectangle r) {
        super("E.png", r);
        phase = false;
    }
}
