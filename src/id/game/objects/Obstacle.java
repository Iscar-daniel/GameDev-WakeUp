package id.game.objects;

import id.game.core.GameObject;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

public class Obstacle extends GameObject {
    int width, height;
    int type;
    
    public Obstacle(int type, float x, float y, int w, int h) {
        super(x, y, w, h, ObjectID.OBSTACLE);
        this.type = type;
        this.width = w;
        this.height = h;
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
//        if (type == 3) {
//            g2d.setColor(Color.red);
//            g2d.drawRect((int)getX(), (int)getY(), w, h);
//        }
    }
}
