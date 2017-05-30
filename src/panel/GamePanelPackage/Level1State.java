package panel.GamePanelPackage;

import java.awt.Graphics;
import main.Player;

public class Level1State extends GameState {
    
    private Player player;
    public Level1State(GameStateManager gsm) {
        super(gsm);
    }

    public void inIt() {
        player = new Player(32, 32, 10);
    }

    public void tick() {
        player.tick();
    }

    public void draw(Graphics g) {
        player.draw(g);
    }

    public void keyPressed(int k) {
        player.keyPressed(k);
    }

    public void keyReleased(int k) {
        player.keyReleased(k);
    }
}
