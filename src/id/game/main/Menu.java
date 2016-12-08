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
public class Menu {
    BufferedImage background;
    public Button btnPlay;
    public Button btnExit;
    
    public Menu(){
        background = ImageLoader.load("welcome_screen.png");
        btnPlay = new Button(80, 150, "Play Game");
        btnExit = new Button(80, 210, "Exit");
    }
    
    public void tick(){
        btnPlay.tick(null);
        btnExit.tick(null);
    }
    
    public void render(Graphics2D g2d){
        g2d.drawImage(background, 0, 0, null);
        btnPlay.render(g2d);
        btnExit.render(g2d);
    }
}
