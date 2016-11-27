/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.objects;

import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
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
public class Bullet extends GameObject{
    
    public float velX;
    float jarakBull=0;
    public boolean beres=false;
    boolean isHit=false;
    public static BufferedImage imageBull;
    private ObjectHandler handler;
    public Enemy temp;
    LinkedList<Bullet> bullets= new LinkedList<>();
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x-5), (int)(y), (int)(24), (int)16);
    }
    
    
    
    public Bullet(float x, float y, ObjectHandler handler) {
        super(x, y, 16, 16, ObjectID.BULLET);
        this.handler = handler;
        jarakBull=0;
    }
    
    public Bullet(float x, float y,int w, int h) {
        super(x, y, w, h, ObjectID.BULLET);
    }
    
    public boolean kenaMomon(List<GameObject> objects) {
        boolean c = false;
        for(GameObject object : objects){
            if(object.getType() == ObjectID.ENEMY){
                Enemy mon = (Enemy) object;
                if (getBounds().intersects(mon.getBounds())) { 
                    c=true;
                    temp=mon;
                    temp.darah-=25;
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
        
        if(kenaMomon(objects)){
//            handler.removeObject(this);
            if(temp.darah==0)
            {
                handler.removeObject(temp);
            }
            
            isHit=true;
        }
        
        
        
        x+=velX;
            
        jarakBull+=1;
//        if(jarakBull==50)
//        {
//            handler.removeObject(this);
//            jarakBull=0;
//        }
//        System.out.println("jumlah peluru="+objects.size());
    }

    public boolean isIsHit() {
        return isHit;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.drawImage(imageBull , (int) x, (int) y, null);
        Graphics2D g = (Graphics2D) g2d;
        g.setColor(Color.blue);
        g.draw(getBounds());
    }
    
}
