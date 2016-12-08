package id.game.objects;

import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import static id.game.objects.Bullet.bullEnemy;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Rect extends GameObject {
    int width, height;
    int type;
    public BufferedImage img;
    public BufferedImage[] pagerBoss = new BufferedImage[3];
    private ObjectHandler handler;
    public static LinkedList<Rect> listRect= new LinkedList<>();
    public static Rect[] kotak = new Rect[1000];
    public static boolean remove=false;
    public int id=1;
    
    public Rect(int type,float x, float y, int w, int h, ObjectHandler handler) {
        super(x, y, w, h, ObjectID.BLOCK);
        this.type = type;
        this.width = w;
        this.height = h;
    //    this.id=id;
        this.handler = handler;
        
        img = Game.assets.other[84];
        pagerBoss[0] = Game.assets.other[41];
        pagerBoss[1] = Game.assets.other[5];
        pagerBoss[2] = Game.assets.other[112];
    }

    public int getId() {
        return id;
    }
    
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, w, h);
    }
    
    public boolean kenaPlayer(List<GameObject> objects) {
        boolean c = false;
        int i=0;
        for(GameObject object : objects){
            if(object.getType() == ObjectID.BLOCK){
                kotak[i] = (Rect)object;
                remove=true;
                i++;
            }
        }
        return c;
        
    }

    @Override
    public void tick(List<GameObject> objects) {
        if(Game.lvl==2)
        {
            kenaPlayer(objects);
        }
        
    }

    @Override
    public void render(Graphics2D g2d) {
//            Graphics2D g = (Graphics2D) g2d;
//            g.setColor(Color.yellow);
//            g.draw(getBounds());
            
            if(type==1)
            {
                g2d.drawImage(img , (int) x, (int) y, null);
            }
            
            if(type==4)
            {
                g2d.drawImage(pagerBoss[0] , (int) x, (int) y, null);
            }
            
            if(type==5)
            {
                g2d.drawImage(pagerBoss[1] , (int) x, (int) y, null);
            }
            
            if(type==6)
            {
                g2d.drawImage(pagerBoss[2] , (int) x, (int) y, null);
            }
    }
}
