package id.game.objects;

import id.game.core.GameObject;
import id.game.main.Game;
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
    
    public Rect(int type, float x, float y, int w, int h) {
        super(x, y, w, h, ObjectID.BLOCK);
        this.type = type;
        this.width = w;
        this.height = h;
        
        img = Game.assets.other[84];
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, w, h);
    }

    @Override
    public void tick(List<GameObject> objects) {
        
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
    }
}
