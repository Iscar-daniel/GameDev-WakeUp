package id.game.objects;

import id.game.core.GameObject;
import id.game.main.Game;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.List;

public class Tile extends GameObject {
    public static final int TILE_OTHER = 0;
    public static final int TILE_GROUND = 1;
    
    int kind;
    int type;
    
    public Tile(float x, float y, int kind, int type) {
        super(x, y, 64, 64, ObjectID.TILE);
        this.type = type;
        this.kind = kind;
    }
    
    public static Tile newOther(float x, float y, int type) {
        return new Tile(x, y, TILE_OTHER, type);
    }
    
    public static Tile newGround(float x, float y, int type) {
        return new Tile(x, y, TILE_GROUND, type);
    }

    @Override
    public void tick(List<GameObject> objects) {
    }

    public int getKind() {
        return kind;
    }

    @Override
    public void render(Graphics2D g2d) {
        switch(kind) {
            case TILE_OTHER:
                g2d.drawImage(Game.assets.other[type], null, (int) x, (int) y);
                break;
            case TILE_GROUND:
                g2d.drawImage(Game.assets.ground[type], null, (int) x, (int) y);
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, 64, 64);
    }
    
    
}
