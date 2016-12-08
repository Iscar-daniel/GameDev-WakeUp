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
public class Win {
    BufferedImage background;
    public Button btnPlay;
    public Button btnExit;
    
    public Win(){
        background = ImageLoader.load("you_win.jpg");
        btnPlay = new Button(307, 480, "Play Again");
        btnExit = new Button(307, 540, "Exit");
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
