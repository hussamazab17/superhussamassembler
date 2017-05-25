/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panel.GamePanelPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 *
 * @author Sam
 */
public class MenuState extends GameState{
    
    private String[] options = {"Play", "Quit"};
    private int currentSelection = 0;
    
    public MenuState(GameStateManager gsm) {
        
        super(gsm);
    }

    public void inIt() {}

    public void tick() {
        
    }

    public void draw(Graphics g) {
        for (int i = 0; i < options.length; i++ ){
            if (i == currentSelection)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.GRAY);
            
            g.setFont(new Font("Arial", Font.PLAIN, 72));
            g.drawString(options[i], GamePanel.WIDTH / 2 - 75, 700 + i * 200);
        }
    }
    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_DOWN) {
            currentSelection++;
            if (currentSelection >= options.length) 
                currentSelection = 0;
            
            System.out.println("yuh DOWN");
        }
        else if (k == KeyEvent.VK_UP) {
            currentSelection--;
            if (currentSelection < 0) 
                currentSelection = options.length - 1;
            
            System.out.println("yuh UP");
        }
        
        if (k == KeyEvent.VK_ENTER) {
            //play
            if (currentSelection == 0) {
                gsm.states.push(new Level1State(gsm));
            }
            //quit
            else if (currentSelection == 1) {
                System.exit(0);
            }
        }
    }

    public void keyReleased(int k) {
        
    }
}
