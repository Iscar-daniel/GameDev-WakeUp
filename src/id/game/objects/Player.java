package id.game.objects;

import id.game.core.Animation;
import id.game.core.GameObject;
import id.game.core.ObjectHandler;
import id.game.main.Game;
import id.game.main.Menu;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;
import id.game.core.AudioLoader;


public class Player extends GameObject {
    private float velX;
    private float velY;
    public boolean isShooting=false;
    public boolean isAttack=false;
    public boolean peluru=false;
    
    public int stopPos = 20;
    
    public static final float GRAVITY = 0.3f;
    public static final int MAX_GRAVITY = 10;
    
    public static boolean kena = false;
    
    private ObjectHandler handler;
    public Bullet bull;
    public static Enemy enemy;
    private AudioLoader tMonster;
   
    int detik=0;
    
    Animation rightStay;
    Animation leftStay;
    Animation walkRightAnim;
    Animation walkLeftAnim;
    Animation ShootAnimKanan;
    Animation ShootAnimKiri;
    Animation AttckAnimKanan;
    Animation AttckAnimKiri;
    Animation JumpingKanan;
    Animation JumpingKiri;
    
    public Player(float x, float y,ObjectHandler handler) {
        super(x, y, 128, 128, ObjectID.PLAYER);
        
        this.handler = handler;
        
        BufferedImage[] rightStayArr = new BufferedImage[10];
        for(int i = 0; i < 10; i++){
            rightStayArr[i] = Game.assets.player[20+i];
        }
        BufferedImage[] leftStayArr = new BufferedImage[10];
        for(int i = 0; i < 10; i++){
            leftStayArr[i] = Game.assets.player[30+i];
        }
        BufferedImage[] walkRightArr = new BufferedImage[8];
        for(int i = 0; i < 8; i++){
            walkRightArr[i] = Game.assets.player[i];
        }
        BufferedImage[] walkLeftArr = new BufferedImage[8];
        for(int i = 0; i < 8; i++){
            walkLeftArr[i] = Game.assets.player[10+i];
        }
        
        BufferedImage[] jumpRight = new BufferedImage[10];
        for(int i = 0; i < 10; i++){
            jumpRight[i] = Game.assets.player[40+i];
        }
        
        BufferedImage[] jumpLeft = new BufferedImage[10];
        for(int i = 0; i < 10; i++){
            jumpLeft[i] = Game.assets.player[50+i];
        }
        
        BufferedImage[] meleeRightArr = new BufferedImage[7];
        for(int i = 0; i < 7; i++){
            meleeRightArr[i] = Game.assets.attck[i];
        }
        
        BufferedImage[] meleeLeftArr = new BufferedImage[7];
        for(int i = 0; i < 7; i++){
            meleeLeftArr[i] = Game.assets.attck[7+i];
        }
        
        BufferedImage[] shootRightArr = new BufferedImage[3];
        for(int i = 0; i < 3; i++){
            shootRightArr[i] = Game.assets.attck[14+i];
        }
        
        BufferedImage[] shootLeftArr = new BufferedImage[3];
        for(int i = 0; i < 3; i++){
            shootLeftArr[i] = Game.assets.attck[21+i];
        }
        
        
        
        rightStay = new Animation(5,rightStayArr);
        leftStay = new Animation(5,leftStayArr);
        walkRightAnim = new Animation(5,walkRightArr);
        walkLeftAnim = new Animation(5,walkLeftArr);
        ShootAnimKanan = new Animation(5,shootRightArr);
        ShootAnimKiri = new Animation(5,shootLeftArr);
        AttckAnimKanan = new Animation(5,meleeRightArr);
        AttckAnimKiri = new Animation(5,meleeLeftArr);
        JumpingKanan = new Animation(8, jumpRight);
        JumpingKiri = new Animation(8, jumpLeft);
        tMonster= new AudioLoader();
        tMonster.load("../assets/sound/hurt.wav");
       
        
       
        
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
    
    
    public boolean inBlock(List<GameObject> objects) { //buat gravity, biar selalu diatas ground
        boolean c = false;
        for(int i =0; i<objects.size(); i ++){
            if(objects.get(i).getType() == ObjectID.BLOCK){
                Rect r = (Rect) objects.get(i);
                if(getBounds().intersects(r.getBounds())){
                   y = r.getY() - 120;
                   velY = 0;
                   falling = false;
                   jumping = false;
                }else{
                    falling = true;
                }
                
                if (getBoundsTop().intersects(r.getBounds())) {
                    y = r.getY() + 128;
                    velY = 0;
                    
                }
                
                if (getBoundsRight().intersects(r.getBounds())) {
                    x = r.getX() - 128;
                }
                
                if (getBoundsLeft().intersects(r.getBounds())) {
                    x = r.getX() + r.width;
                }
                
                //System.out.println(r.width);
            }
        }
        return c;
        
    }
    
    public boolean inOther(List<GameObject> objects) {
        boolean c = false;
        for(int i =0; i<objects.size(); i ++){
            if(objects.get(i).getType() == ObjectID.OBSTACLE){
                Obstacle ob = (Obstacle) objects.get(i);
                if(getBoundsLeft().intersects(ob.getBounds())){
                    y = ob.getY()-64;
                    falling=false;
                    jumping=false;
                    c = true;
                }else{
                    c = false || c;
                }
            }
        }
        return c;
        
    }
    
    public boolean touchMonster(List<GameObject> objects) {
        boolean c = false;
        for(GameObject object : objects){
            if(object.getType() == ObjectID.ENEMY){
                Enemy ene = (Enemy) object;
                if (getBoundsAll().intersects(ene.getBounds())) { 
                    c=true;
                    tMonster.play();
                }
                
                
            }
        }
        
        return c;
        
    }
    
    
    public boolean attackKanan(List<GameObject> objects) {
        
        for(GameObject object : objects){
            if(object.getType() == ObjectID.ENEMY){
                Enemy ene = (Enemy) object;
                if (getBoundsRight().intersects(ene.getBounds())) { 
                    kena=true;
                    enemy=ene;
                }
                
                
            }
        }
        return kena;
        
    }
    
    public boolean attackKiri(List<GameObject> objects) {
        
        for(GameObject object : objects){
            if(object.getType() == ObjectID.ENEMY){
                Enemy ene = (Enemy) object;
                if (getBoundsLeft().intersects(ene.getBounds())) { 
                    kena=true;
                    enemy=ene;
                }
                
                
            }
        }
        return kena;
        
    }
        
    public boolean extraLife(List<GameObject> objects) {
        boolean c = false;
        for(GameObject object : objects){
            if(object.getType() == ObjectID.MISC){
                Miscellaneous misc = (Miscellaneous) object;
                if (getBoundsAll().intersects(misc.getBounds())) { 
                    c=true;
                    handler.removeObject(misc);
                }
                
                
            }
        }
        return c;
        
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle((int)(x+((128/4))), (int)(y+120/2), (int)(128/2), (int)121/2);
    }
    
    public Rectangle getBoundsTop() {
        return new Rectangle((int)(x+((128/4))), (int)y, (int)(128/2), (int)128/2);
    }
    
    public Rectangle getBoundsRight() {
        return new Rectangle((int)(x+((128/5)*4)), (int)(y+((int)(128/6)/2)), (int)128/5, (int)(128-(128/3)));
    }
    
    public Rectangle getBoundsLeft() {
        return new Rectangle((int)x, (int)(y+((int)(128/6)/2)), (int)128/5, (int)(128-(128/3)));
    }
    
    public Rectangle getBoundsAll() {
        return new Rectangle((int)(x+((128/4))), (int)(y), (int)(150/3), (int)128);
    }
    

    @Override
    public void tick(List<GameObject> objects) {
        
        
//        if(extraLife(objects))
//        {
//            System.out.println("HELLO WORLD");
//        }

        if (falling || jumping) {
            
            velY += GRAVITY;
            //System.out.println(falling);
            if (velY > MAX_GRAVITY) {
                velY = MAX_GRAVITY;
                System.out.println(velY);
            }
        }
        
        if (inBlock(objects)) {
            System.out.println("ok");
        }else if(inOther(objects)){
            System.out.println("not ok");
            Game.getInstance().hud.health=0;
            Game.getInstance().hud.jmlhDarah=0;
            Game.getInstance().currentState = Game.GameState.GAME_OVER;
        }else if(inBlock(objects))
        {
            System.out.println("Di BLOCK");
        }
        
        if(isAttack)
        {
            if(stopPos==20)
            {
                if(attackKanan(objects))
                {
                    //kosong
                }
            }
            
            
            if(stopPos==30)
            {
                if(attackKiri(objects))
                {
                    //kosong
                }
            }
            
        }
        
        
        if(touchMonster(objects))
        {
            detik+=1;
            //System.out.println("Detik: "+detik);
            if(detik==1)
            {
                Game.getInstance().hud.health-=20;
            }
            if(detik==100)
            {
                detik=0;
            }
            
            //DARAHHHHHHHHHHHHHHHHHHHHHH
            if(Game.getInstance().hud.health==80)
            {
                Game.getInstance().hud.jmlhDarah=4;
            }else if(Game.getInstance().hud.health==60)
            {
                Game.getInstance().hud.jmlhDarah=3;
            }else if(Game.getInstance().hud.health==40)
            {
                Game.getInstance().hud.jmlhDarah=2;
            }else if(Game.getInstance().hud.health==20)
            {
                Game.getInstance().hud.jmlhDarah=1;
            }
            if(Game.getInstance().hud.health==0)
            {
                Game.getInstance().hud.jmlhDarah=0;
                Game.getInstance().currentState = Game.GameState.GAME_OVER;
            }
            
        }else{
            detik=0;
        }
        
        x += velX;
        y += velY;
        
        rightStay.runAnimation();
        leftStay.runAnimation();
        walkRightAnim.runAnimation();
        walkLeftAnim.runAnimation();
        ShootAnimKanan.runAnimation();
        ShootAnimKiri.runAnimation();
        AttckAnimKanan.runAnimation();
        AttckAnimKiri.runAnimation();
        JumpingKanan.runAnimation();
        JumpingKiri.runAnimation();
    }

    @Override
    public void render(Graphics2D g2d) {
        
        
//        if(peluru)
//        {
//            bull.tick(null);
//            bull.render(g2d);
//        }else{
//            bull.setX(x+100);
//            bull.setY(y+(115/2));
//            bull.jarakBull=0;
//            handler.remObject(bull);
//        }
        
        Graphics2D g = (Graphics2D) g2d;
//        g.setColor(Color.yellow);
//        g.draw(getBoundsAll());
        
        if(velX>0){
            walkRightAnim.drawAnimation(g2d, (int)x, (int)y);
            stopPos = 20;
        }else if(velX<0){
            walkLeftAnim.drawAnimation(g2d, (int)x, (int)y);
            stopPos = 30;
        }else if(isShooting){
            
            if(stopPos==20)
            {
                ShootAnimKanan.drawAnimation(g2d,(int)x,(int)y);
                isShooting=false;
                
            }
            
            if(stopPos==30)
            {
                ShootAnimKiri.drawAnimation(g2d,(int)x,(int)y);
                isShooting=false;
            }
            
        }else if(isAttack){
            
            
            if(stopPos==20)
            {
                AttckAnimKanan.drawAnimation(g2d,(int)x,(int)y);
                g.setColor(Color.blue);
                g.draw(getBoundsRight());
            }
            
            if(stopPos==30)
            {
                AttckAnimKiri.drawAnimation(g2d,(int)x,(int)y);
                g.setColor(Color.red);
                g.draw(getBoundsLeft());
            }
        }else if(isJumping()){
            if(stopPos==20)
            {
                JumpingKanan.drawAnimation(g2d,(int)x,(int)y);
            }
            
            if(stopPos==30)
            {
                JumpingKiri.drawAnimation(g2d,(int)x,(int)y);
            }    
        }else{
            //g2d.drawImage(Game.assets.player[stopPos], null, (int)x, (int)y);
            if(stopPos==20)
            {
                rightStay.drawAnimation(g2d, (int)x, (int)y);
            }else if(stopPos==30){
                leftStay.drawAnimation(g2d, (int)x, (int)y);
            }
        }
       
    }


    
}
