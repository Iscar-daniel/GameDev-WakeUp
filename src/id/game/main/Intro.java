/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.main;

import static id.game.main.Game.GameState.INTRO;
import static id.game.main.Game.GameState.INTRO2;
import id.game.objects.Bullet;
import id.game.objects.Button;
import id.game.utils.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author sony
 */
public class Intro {
    BufferedImage background;
    public Button btnPrev;
    public Button btnNext;
   // static String filename="intro_1.png";
    
    public Intro(){
        
       // background = ImageLoader.load(filename);
        btnPrev = new Button(10, 460, "Prev");
        btnNext = new Button(600, 460, "Next");
    }
    
    public void tick(){
        
        btnPrev.tick(null);
        btnNext.tick(null);
    }
    
    public void render(Graphics2D g2d){
        if(Game.getInstance().currentState==Game.GameState.INTRO)
        {
            background = ImageLoader.load("intro_1.png");
        }
        
        if(Game.getInstance().currentState==Game.GameState.INTRO2)
        {
            background = ImageLoader.load("intro_2.png");
        }
        
        
        g2d.drawImage(background, 0, 0, null);
        btnPrev.render(g2d);
        btnNext.render(g2d);
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g2d.setColor(Color.BLACK);
    }
}
