package id.game.core;

import id.game.main.Game;
import id.game.main.HUD;
import id.game.objects.Bullet;
import id.game.objects.Tile;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import id.game.core.AudioLoader;

public class KeyHandler extends KeyAdapter {
    public ObjectHandler handler;
    public Bullet bull;
    public LinkedList<Bullet> bullets=new LinkedList<>();
    public Graphics2D g2d;
    boolean cek=false;
    int jarakPeluru=0;
    int i=0;
    private AudioLoader jump,sword,gun;
    
    
    
    public KeyHandler(ObjectHandler handler) {
        this.handler = handler;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        super.keyReleased(e);
        gun= new AudioLoader();
        gun.load("../assets/sound/lasergun.wav");
        handler.removeKey(e.getKeyCode());
        
        if (e.getKeyCode() == KeyEvent.VK_LEFT ||
            e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Game.player.setVelX(0);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_UP ||
            e.getKeyCode() == KeyEvent.VK_DOWN) {
            Game.player.setVelY(0);
        }
        
        if (e.getKeyCode() == KeyEvent.VK_A) {
            Game.player.isAttack = false;
        }
        
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gun.play();
//            if(i<5)
//            {
                if(bullets.size()<5){
                Game.player.isShooting = true;
                Game.player.peluru = true;

                bull = new Bullet(1, Game.player.getX()+100, Game.player.getY()+(115/2), handler);
                if(Game.player.stopPos==20)
                {
                    bull.velX=10f;
                    bull.imageBull=Game.getInstance().assets.bullet[0];
                }else {
                    bull.velX=-10f;
                    bull.imageBull=Game.getInstance().assets.bullet[1];
                }
                bullets.add(bull);
                handler.addObject(bullets.getLast());
                cek=true;
                }
//                i++;
//            }
            
//            System.out.println("a");
//            if(bull.beres)
//            {
//                handler.remObject(bull);
//            }
            
        }
        
        if (e.getKeyCode() == KeyEvent.VK_EQUALS) {
            Game.getInstance().hud.health+=20;
        }
            
        if (e.getKeyCode() == KeyEvent.VK_MINUS) {
            Game.getInstance().hud.health-=5;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        jump= new AudioLoader();
        jump.load("../assets/sound/jump.wav");
        sword= new AudioLoader();
        sword.load("../assets/sound/sword_hit.wav");
        
        
        handler.addKey(e.getKeyCode());
    }
    
     boolean cekRangePeluru(Bullet bull){
        if(bull.getJarakBull()==300)
            {
                //handler.removeObject(bullets.removeFirst());
                return true;
                //i-=1;
               
            }else{
                return false;
            }
            
            
    }
    
    public void tick() {
      
//        for (Bullet bullet : bullets) {
//            if(cekRangePeluru(bull)){
//            handler.removeObject(bullets.removeFirst());
//            }
//            
//            
//        }
        for (int j = 0; j < bullets.size(); j++) {
            Bullet get = bullets.get(j);
            if(cekRangePeluru(get))
                handler.removeObject(bullets.removeFirst());
            if(get.isIsHit())
                handler.removeObject(bullets.remove(j));
        }
        
//        if(cek)
//        {
//            
//            if(jarakPeluru==50)
//            {
//                jarakPeluru=0;
//                handler.removeObject(bullets.removeFirst());
//                
//                //i-=1;
//               
//            }else{
//                jarakPeluru+=1;
//            }
//            
//            if(bullets.size()==0)
//            {
//                cek=false;
//            }
//        }
        
        if (handler.keys.contains(KeyEvent.VK_LEFT)) {
            Game.player.setVelX(-5);
        }
        
        if (handler.keys.contains(KeyEvent.VK_RIGHT)) {
            Game.player.setVelX(5);
        }
        
//        if (handler.keys.contains(KeyEvent.VK_UP)) {
//            if(Game.getInstance().currentState == Game.GameState.GAME_PLAY)
//            {
//                Game.player.setVelY(-3);
//            }else{
//                Game.getInstance().menu.btnPlay.setToHover();
//            }
//            
//        }
//        
//        if (handler.keys.contains(KeyEvent.VK_DOWN)) {
//            Game.player.setVelY(3);
//        }
        
        
         
         if (handler.keys.contains(KeyEvent.VK_A)) {
            sword.play();
            Game.player.isAttack = true;
        }
         
       if (handler.keys.contains(KeyEvent.VK_SPACE)&& !Game.player.isJumping()) {
            jump.play();
            Game.player.setJumping(true);
            Game.player.setVelY(-12);
            
        }
         
       
         
    }
}
