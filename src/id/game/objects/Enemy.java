package id.game.objects;

import id.game.core.Animation;
import id.game.core.AudioLoader;
import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import static id.game.main.HUD.Max_Health;
import static id.game.main.HUD.Max_Width_Health;
import id.game.main.Menu;
import id.game.utils.ImageLoader;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.logging.Handler;


public class Enemy extends GameObject {
    private float velX;
    private float velY;
    static int stopPos = 20;
    
    private AudioLoader hadouken;
    
    float jalanKanan=0;
    float jalanKiri=0;
    int max = 0;
    boolean test=true;
    public static boolean liatPlayerKiri = false;
    public static boolean liatPlayerKanan = false;
    public static float xEnemy = 0;
    public static float yEnemy = 0;
    
    public static final float GRAVITY = 0.3f;
    public static final int MAX_GRAVITY = 10;

    int arah;
    
    private ObjectHandler handler;
    public static Bullet bull;
    public int tipe;
    public int darah;
    public int tempDarah;

    Animation ene, enefly, ryuKiri, ryuKanan; //kalau diam, ada animasinya sendiri
    
    BufferedImage[] hudHealth;
    
    public Enemy(int tipe, float x, float y,int darah, ObjectHandler handler) {
        super(x, y, 128, 128, ObjectID.ENEMY);
        
        this.handler = handler;
        this.tipe=tipe;
        this.darah=darah;
        
        this.tempDarah = darah;
        hudHealth = new BufferedImage[1];
        hudHealth[0]=ImageLoader.load("hud_health_bar.png");
        
        BufferedImage[] enemys = new BufferedImage[3];
        for(int i = 0; i < 3; i++){
            enemys[i] = Game.assets.enemy[i];
        }
        
        BufferedImage[] enemysfly = new BufferedImage[3];
        for(int i = 0; i < 3; i++){
            enemysfly[i] = Game.assets.enemyfly[i];
        }
        
        BufferedImage[] bossKiri = new BufferedImage[8];
        BufferedImage[] bossKanan = new BufferedImage[8];
        for(int i = 0; i < 8; i++){
            bossKiri[i] = Game.assets.ryuKiri[i];
            bossKanan[i] = Game.assets.ryuKanan[i];
        }
        
//        hadouken= new AudioLoader();
//        hadouken.load("../assets/sound/hadouryu.wav");

        ene = new Animation(8,enemys);
        enefly = new Animation(8,enemysfly);
        ryuKiri = new Animation(5, bossKiri);
        ryuKanan = new Animation(5, bossKanan);
        
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
    
    public boolean inGround(List<GameObject> objects) {
        boolean c = false;
        for(int i =0; i<objects.size(); i ++){
            if(objects.get(i).getType() == ObjectID.BLOCK){
                Rect rec = (Rect) objects.get(i);
                if(getBounds().intersects(rec.getBounds())){
                   y = rec.getY() - 40;
                   velY = 0;
                   
                   falling = false;               
                   jumping = false;
                }else{
                    falling = true;
                }
                
                
            }
        }
        return c;
        
    }
    
    public boolean spawnBulletKiri(List<GameObject> objects) {
        
        for(GameObject object : objects){
            if(object.getType() == ObjectID.PLAYER){
                Player pla = (Player) object;
                if (getBoundsLeft().intersects(pla.getBoundsAll())) { 
                    liatPlayerKiri=true;
                    xEnemy=x;
                    yEnemy=y;
                    
                }
                
                
            }
        }
        return liatPlayerKiri;
        
    }
    
    public boolean spawnBulletKanan(List<GameObject> objects) {
        
        for(GameObject object : objects){
            if(object.getType() == ObjectID.PLAYER){
                Player pla = (Player) object;
                if (getBoundsRight().intersects(pla.getBoundsAll())) { 
                    liatPlayerKanan=true;
                    xEnemy=x;
                    yEnemy=y;
                    Bullet.imageBull=Game.getInstance().assets.bullet[2];
                }
                
                
            }
        }
        return liatPlayerKanan;
        
    }
    
    
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x), (int)(y-50), (int)(49), (int)88);
    }
    
    public Rectangle getBoundsBottom() {
        return new Rectangle((int)(x+((49/4))), (int)(y+49/2), (int)(49/2), (int)40/4);
    }
    
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x+((49/4))), (int)y, (int)(49/2), (int)40/4);
    }
    
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x+40), (int)(y), (int)300, (int)38);
    }
    
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x-300, (int)y, (int)300, (int)38);
    }
    

    @Override
    public void tick(List<GameObject> objects) {
        
        if(tipe==2)
        {
            falling=false;
        }
        
        if (falling || jumping) {
            
            velY += GRAVITY;
            //System.out.println(falling);
            if (velY > MAX_GRAVITY) {
                velY = MAX_GRAVITY;
                System.out.println(velY);
            }
        }
        
        if (inGround(objects)) {
            System.out.println("ok");
        }
        
        
        if(spawnBulletKiri(objects)){
            
            bull = new Bullet(2, xEnemy, yEnemy, handler);
            bull.velX=-5f;
            if(tipe!=3)
            {
                bull.imageBull=Game.getInstance().assets.bullet[0];
            }
            
            
            
//                    handler.addObject(bull);
        }
        
        if(spawnBulletKanan(objects)){
            
            bull = new Bullet(2, xEnemy, yEnemy, handler);
            bull.velX=5f;
            if(tipe!=3)
            {
                bull.imageBull=Game.getInstance().assets.bullet[1];
            }
            
            
            
//                    handler.addObject(bull);
        }
        
        
        //System.out.println(jalanKanan);
        if(tipe!=3)
        {
            
            if(test)
            {
                jalanKanan+=1; 
                x += 5;
                if(jalanKanan==max)
                {
                    test=false;
                    jalanKanan=0;
                }
            }else{
                jalanKiri+=1;
                x-=5;
                if(jalanKiri==max)
                {
                    test=true;
                    jalanKiri=0;
                }
            }
        }else
        {
            if(Miscellaneous.pagerHidden)
            {
                if(Game.player.getX()<x)
                {
                    arah=1;
                    x-=3;
                }else
                {
                    arah=2;
                    x+=3;
                }
            }
            
        }
        y += velY;
        
        ene.runAnimation();
        enefly.runAnimation();
        ryuKiri.runAnimation();
        ryuKanan.runAnimation();
    }

    @Override
    public void render(Graphics2D g2d) {
        
//        g2d.setColor(Color.gray);
//        g2d.fillRect((int)getX(), (int)getY(), (int)64, (int)43);
        Graphics2D g = (Graphics2D) g2d;
//        g.setColor(Color.yellow);
//        g.draw(getBounds());
//        g.setColor(Color.blue);
//        g.draw(getBoundsRight());
        
        //Draw Text
        
        g2d.setColor(Color.white);
        g2d.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
        g2d.drawString(String.format("%03d/%03d", (int)darah, (int)tempDarah), (int)x-5, (int)y-25);
        
        //Draw Bar
//        float wHealth = darah/Max_Health*Max_Width_Health;
//        if((int)darah>0 && darah<=Max_Health)g2d.drawImage(hudHealth[0].getSubimage(0, 0, (int)wHealth, 15), (int)x, (int)y, null);

        if(tipe==1)
        {
            ene.drawAnimation(g2d, (int)x, (int)y);
            max = 30;
        }
        
        if(tipe==2)
        {
            enefly.drawAnimation(g2d, (int)x, (int)y);
            max = 50;
        }
        
        if(tipe==1)
        {
            ene.drawAnimation(g2d, (int)x, (int)y);
            max = 30;
        }
        
        if(tipe==3)
        {

            //bull.imageBull=Game.getInstance().assets.bullet[2];
            
            if(arah==1)
            {
                ryuKiri.drawAnimation(g2d, (int)x, (int)y-120, 256, 256);
            }else{
                ryuKanan.drawAnimation(g2d, (int)x, (int)y-120, 256, 256);
            }
            
        }

        
       
    }


    
}
