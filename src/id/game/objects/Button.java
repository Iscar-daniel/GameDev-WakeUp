/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.objects;

import id.game.core.GameObject;
import id.game.main.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

/**
 *
 * @author sony
 */
public class Button extends GameObject{
    
    String text;

    @Override
    public Rectangle getBounds() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public enum State{
        NORMAL, HOVER
    }
    
    public State currentState = State.NORMAL;
    
    public Button(float x, float y, String text) {
        super(x, y, 190, 49, ObjectID.BUTTON);
        this.text = text;
    }
    
    public Button(float x, float y,int w, int h, String text) {
        super(x, y, w, h, ObjectID.BUTTON);
        this.text = text;
    }

    public boolean isHover(){
        return currentState==State.HOVER;
    }
    
    public boolean isNormal(){
        return currentState==State.NORMAL;
    }
    
    public void setToHover(){
        currentState = State.HOVER;
    }
    
    public void setToNormal(){
        currentState = State.NORMAL;
    }
    
    @Override
    public void tick(List<GameObject> objects) {
       Point mpos = Game.getInstance().getMousePosition();
       
       if(mpos != null){
           if(getBound().contains(mpos)){
               setToHover();
           }else{
               setToNormal();
           }
       }
    }

    @Override
    public void render(Graphics2D g2d) {
        int fixedX = (w/2)-(text.length() * 15)/2;
        int fixedY = (int) y+30;
        if(isHover())
        {
            g2d.drawImage(Game.getInstance().assets.button[1], (int)x, (int)y+4, null);
            fixedY+=4;
        }else{
            g2d.drawImage(Game.getInstance().assets.button[0] , (int) x, (int) y, null);
        }
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
        
        g2d.drawString(text, x+fixedX, fixedY);
    }
    
}
