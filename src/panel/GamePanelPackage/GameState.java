package panel.GamePanelPackage;

import java.awt.Graphics;

/**
 *
 * @author Sam
 */
public abstract class GameState {
    
    public GameStateManager gsm;
    
    protected GameState(GameStateManager gsm) {
        this.gsm = gsm;
        inIt();
    }
    
    public abstract void inIt();
    
    public abstract void tick();
    public abstract void draw(Graphics g);
    public abstract void keyPressed(int k);
    public abstract void keyReleased(int k);
}