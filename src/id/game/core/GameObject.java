package id.game.core;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

public abstract class GameObject {

    public enum ObjectID{
        PLAYER, TILE, BUTTON, BULLET, ENEMY, BLOCK, OBSTACLE, MISC, EXTRALIFE
    }
    
    protected float x, y;
    protected int w, h;
    protected ObjectID type;
    public boolean falling = true, jumping = false;
    
    public GameObject(float x, float y, int w, int h, ObjectID type) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    public abstract void tick(List<GameObject> objects);
    public abstract void render(Graphics2D g2d);
    public abstract Rectangle getBounds();
    
    public Rectangle getBound() {
        return new Rectangle((int) x, (int) y, w, h);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
    public boolean isFalling() {
        return falling;
    }
    
    public void setFalling(boolean falling) {
        this.falling = falling;
    }
    
    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
    
    

    public ObjectID getType() {
        return type;
    }

    public void setType(ObjectID type) {
        this.type = type;
    }
    
    
}
