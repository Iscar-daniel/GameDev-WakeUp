/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.objects;

import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import id.game.main.HUD;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author sony
 */
public class Enemy_Bullet extends GameObject{
    
    public float velX;
    float jarakBull=0;
    public boolean beres=false;
    boolean isHit=false;
    public static BufferedImage imageBull;
    private ObjectHandler handler;
    public static HUD temp;
    public static boolean kenaBull=false;
    public int tipe;
    
    LinkedList<Enemy_Bullet> bullets= new LinkedList<>();
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x-5), (int)(y), (int)(24), (int)16);
    }
    
    
    
    public Enemy_Bullet(int tipe, float x, float y, ObjectHandler handler) {
        super(x, y, 16, 16, ObjectID.BULLET_ENEMY);
        this.handler = handler;
        this.tipe=tipe;
        jarakBull=0;
    }
    
    public Enemy_Bullet(float x, float y,int w, int h) {
        super(x, y, w, h, ObjectID.BULLET);
    }
    
    public boolean kenaMomon(List<GameObject> objects) {
        boolean c = false;
        for(GameObject object : objects){
            if(object.getType() == ObjectID.PLAYER){
                Player mon = (Player) object;
                if (getBounds().intersects(mon.getBounds())) { 
                    c=true;  
                    
                  
                }
                
                
            }
        }
        return c;
        
    }

    public float getJarakBull() {
        return jarakBull;
    }

    
    @Override
    public void tick(List<GameObject> objects) {
        
      
    }

    public boolean isIsHit() {
        return isHit;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(imageBull , (int) x, (int) y, null);
//        Graphics2D g = (Graphics2D) g2d;
//        g.setColor(Color.blue);
//        g.draw(getBounds());
    }
    
}
