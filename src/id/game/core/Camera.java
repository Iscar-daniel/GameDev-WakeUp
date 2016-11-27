package id.game.core;

import id.game.main.Game;

public class Camera {
    private float x;
    private float y;
    private int velX;
    private int velY;

    public Camera(float x, float y) {
        this.x = x;
        this.y = y;
        this.velX = 0;
        this.velY = 0;
    }
    
    public void tick() {
        
        x = - (int)Game.player.getX() + Game.WIDTH / 2;
        y = - (int)Game.player.getY() + Game.HEIGHT / 2;
        
        if(x>=0)
        {
            x=0;
        }
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

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
    
    
}
