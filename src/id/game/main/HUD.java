package id.game.main;

import id.game.utils.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author sony
 */
public class HUD {
    public static final int Max_Health = 100;
    public static final int Max_Width_Health = 100;
    public static int jmlhDarah=5;
    
    BufferedImage[] hudHealth;
    
    public float health =  100;
    
    
    public HUD(){
        
        
        hudHealth = new BufferedImage[2];
        hudHealth[0]=ImageLoader.load("heart.png");
        hudHealth[1]=ImageLoader.load("hud_health_bar.png");
        
        
    }
    
    public void tick(){

        if(health < 0)
        {
            health =0;
        }
        
        
    }
    
    public void render(Graphics2D g){
        //Draw Heart
        
        int temp=0;
        for (int i = 0; i < jmlhDarah; i++) {
            g.drawImage(hudHealth[0], 10+temp, 10, null);
            temp+=74;
        }
        
        //Draw Text
//        g.setColor(Color.white);
//        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
//        g.drawString(String.format("%03d", (int)health), 15, 100);
        
        //Draw Bar
        float wHealth = health/Max_Health*Max_Width_Health;
//        if((int)health>0 && health<=Max_Health)g.drawImage(hudHealth[1].getSubimage(0, 0, (int)wHealth, 15), 62, 85, null);
        
        
    }
}
