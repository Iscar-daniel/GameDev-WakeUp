package id.game.objects;

import id.game.core.Animation;
import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import id.game.main.Menu;
import static id.game.objects.Bullet.imageBull;
import static id.game.objects.Miscellaneous.c;
import id.game.utils.ImageLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Handler;


public class Extralife extends GameObject {
    private float velX;
    private float velY;
    public static boolean c = false;
    public boolean temp = false;
    private ObjectHandler handler;
    public BufferedImage img, life;
    public static Extralife ex;
    
    
    public Extralife(float x, float y, ObjectHandler handler) {
        super(x, y, 64, 64, ObjectID.EXTRALIFE);
        
        this.handler = handler;
        
        life = ImageLoader.load("extralife.png");

        
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
    
    public boolean extra(List<GameObject> objects) {
        
        for(int i =0; i<objects.size(); i ++){
            if(objects.get(i).getType() == ObjectID.PLAYER){
                Player pla = (Player) objects.get(i);
                if(getBounds().intersects(pla.getBoundsAll())){
                   c=true;
                   ex=this;
                   if(Game.getInstance().hud.health<100)
                    {
                        Game.getInstance().hud.health+=20;
                        Game.getInstance().hud.jmlhDarah+=1;
                        
                    }
                   //handler.removeObject(this);
                }
                
                
            }
        }
        return c;
        
    }
    
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x), (int)(y), (int)(64), (int)64);
    }
    
    

    @Override
    public void tick(List<GameObject> objects) {

        if(extra(objects))
        {
            
            
        }else{
            c=false;
        }


    }

    @Override
    public void render(Graphics2D g2d) {
        
//        g2d.setColor(Color.gray);
//        g2d.fillRect((int)getX(), (int)getY(), (int)64, (int)43);
//            Graphics2D g = (Graphics2D) g2d;
//            g.setColor(Color.yellow);
//            g.draw(getBounds());


            g2d.drawImage(life , (int) x, (int) y, 64, 64, null);
        
            


        
       
    }


    
}
