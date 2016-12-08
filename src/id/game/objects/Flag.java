package id.game.objects;

import id.game.core.Camera;
import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import static id.game.objects.Bullet.imageBull;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;


public class Flag extends GameObject {
    
    private ObjectHandler handler;
    public static BufferedImage[] imageFlag;
    public static boolean isTouched = false;
   
    public Flag(float x, float y, ObjectHandler handler) {
        super(x, y, 64, 64, ObjectID.FLAG);
        
        this.handler = handler;
        
        imageFlag = new BufferedImage[4];
        for (int i = 0; i < 4; i++) {
            imageFlag[i] = Game.assets.flag[i];
        }
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x), (int)640, (int)64, (int)Game.getInstance().getHeight());
    }
    
    public boolean touchPlayer(List<GameObject> objects) {
        boolean c = false;
        for(GameObject object : objects){
            if(object.getType() == ObjectID.PLAYER){
                Player player = (Player) object;
                if (getBounds().intersects(player.getBounds())) { 
                    c=true;
                    
                }
            }
        }
        return c;
    }

    @Override
    public void tick(List<GameObject> objects) {
        if (touchPlayer(objects)) {
            isTouched = touchPlayer(objects);
        }
    }

    @Override
    public void render(Graphics2D g2d) {
        if (isTouched) {
            g2d.drawImage(imageFlag[1] , (int) x, (int) y, null);
        } else g2d.drawImage(imageFlag[0] , (int) x, (int) y, null);
//        Graphics2D g = (Graphics2D) g2d;
//        g.setColor(Color.yellow);
//        g.draw(getBounds());
        
    }
    
}
