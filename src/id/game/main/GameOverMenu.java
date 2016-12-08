/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.main;

import id.game.objects.Bullet;
import id.game.objects.Button;
import id.game.utils.ImageLoader;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author sony
 */
public class GameOverMenu {
    BufferedImage background;
    public Button btnContinue;
    public Button btnMenu;
    
    public GameOverMenu(){
        background = ImageLoader.load("gameOver.png");
        btnContinue = new Button(307, 400, "Continue");
        btnMenu = new Button(307, 460, "Back to menu");
    }
    
    public void tick(){
        btnContinue.tick(null);
        btnMenu.tick(null);
    }
    
    public void render(Graphics2D g2d){
        g2d.drawImage(background, 0, 0, null);
        btnContinue.render(g2d);
        btnMenu.render(g2d);
    }
}
