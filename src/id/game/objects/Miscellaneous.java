package id.game.objects;

import id.game.core.Animation;
import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import id.game.main.Menu;
import static id.game.objects.Bullet.imageBull;
import id.game.utils.ImageLoader;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Handler;


public class Miscellaneous extends GameObject {
    private float velX;
    private float velY;
    public static boolean c = false;
    public boolean temp = false;
    private ObjectHandler handler;
    public BufferedImage img, life;

    
    
    public Miscellaneous(float x, float y, ObjectHandler handler) {
        super(x, y, 64, 64, ObjectID.MISC);
        
        this.handler = handler;
        
        img = Game.assets.other[94];
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
    
    public boolean kenaSentuh(List<GameObject> objects) {
        
        for(int i =0; i<objects.size(); i ++){
            if(objects.get(i).getType() == ObjectID.BULLET){
                Bullet bull = (Bullet) objects.get(i);
                if(getBounds().intersects(bull.getBounds())){
                   c=true;
                   
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
    
        if(kenaSentuh(objects))
        {
            img = Game.assets.other[118];
        }
        


    }

    @Override
    public void render(Graphics2D g2d) {
        
//        g2d.setColor(Color.gray);
//        g2d.fillRect((int)getX(), (int)getY(), (int)64, (int)43);
//            Graphics2D g = (Graphics2D) g2d;
//            g.setColor(Color.yellow);
//            g.draw(getBounds());


            g2d.drawImage(img , (int) x, (int) y, null);

        
        
            
        
            


        
       
    }


    
}
