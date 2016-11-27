/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.core;

import id.game.main.Game;
import id.game.objects.Button;
import java.awt.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author sony
 */
public class MouseHandler extends MouseAdapter{

    @Override
    public void mouseReleased(MouseEvent me) {
        if(Game.getInstance().menu.btnPlay.isHover())
        {
            Game.getInstance().playGame();
        }
        
        if(Game.getInstance().menu.btnExit.isHover())
        {
            System.exit(0);
        }
    }
    
}
