package id.game.main;

import id.game.core.Camera;
import id.game.core.Constants;
import id.game.core.GameObject;
import id.game.core.KeyHandler;
import id.game.core.MouseHandler;
import id.game.core.ObjectHandler;
import id.game.objects.Bullet;
import id.game.objects.Enemy;
import id.game.objects.Extralife;
import id.game.objects.Miscellaneous;
import id.game.objects.Obstacle;
import id.game.objects.Player;
import id.game.objects.Rect;
import id.game.objects.Tile;
import id.game.utils.Assets;
import id.game.utils.ImageLoader;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    public static final int FPS = 80;
    public static final int WIDTH = 800;
    public static final int HEIGHT = WIDTH / 4 * 3;
    
    private boolean running = false;
    private Thread thread;
    public ObjectHandler handler;
    private KeyHandler keyHandler;
    private MouseHandler mouseHandler;
    public static Camera camera;
    public static Player player;
    public static Miscellaneous kunci;
    public static Enemy enemy;
    public Bullet bull;
    public static Rect[] pager = new Rect[5];
    BufferedImage level;
    BufferedImage map, background;
    
    public static Assets assets;
    public static boolean statusMonster=false;
    
    static Game game;
    public Menu menu;
    public HUD hud;
    public int seconds;
    
    public boolean c = false;
    
    public enum GameState{
        MAIN_MENU, GAME_PLAY;
    }
    
    public GameState currentState = GameState.MAIN_MENU;
    
    public void init() {
        // init
        menu = new Menu();
        hud = new HUD();
        handler = new ObjectHandler();
        keyHandler = new KeyHandler(handler);
        mouseHandler = new MouseHandler();
        assets = new Assets();
        camera = new Camera(100, 100);
        
        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        
        //level = ImageLoader.load("map3.png");
        map = ImageLoader.load("mapTiled.png");
        background = ImageLoader.load("back.png");
        //loadLevel();
        
        player = new Player(0, 0, handler);
        handler.addObject(player);
        
        
        
        
        
        //enemy = new Enemy(450, 0, handler);
        //enemy = new Enemy(1500, 0, handler);
        
        monster();
        
        
        ground();
        obstacle();
        
    }
    
    public void extraLIfe(){
        handler.addObject(new Extralife(3992, 1024, handler));
        handler.addObject(new Extralife(930, 832, handler));
    }
    
    public void monster(){
        handler.addObject(new Enemy(1, 450, 640, 100, handler));
        handler.addObject(new Enemy(1, 1500, 640, 100, handler));
        handler.addObject(new Enemy(2, 900, 690, 100, handler));
        handler.addObject(new Enemy(2, 2000, 940,100, handler));
        handler.addObject(new Enemy(2, 2500, 940,100, handler));
        handler.addObject(new Enemy(1, 3500, 640,100, handler));
        handler.addObject(new Enemy(2, 4200, 640,100, handler));
        handler.addObject(new Enemy(2, 4200, 1020,100, handler));
        handler.addObject(new Enemy(1, 4150, 640,100, handler));
        handler.addObject(new Enemy(2, 3300, 1020,100, handler));
    }
    
    public void ground(){
        handler.addObject(new Rect(3, 0, 1088 , 576, 192));
        handler.addObject(new Rect(3, 448, 1024 , 192, 64));
        handler.addObject(new Rect(3, 704, 960 , 128, 64));
        handler.addObject(new Rect(3, 896, 896 , 128, 64));
        handler.addObject(new Rect(3, 1088, 960 , 128, 64));
        handler.addObject(new Rect(3, 1280, 1024 , 192, 64));
        handler.addObject(new Rect(3, 1334, 1088 , 512, 192));
        handler.addObject(new Rect(3, 1984, 1088 , 64, 192));
        handler.addObject(new Rect(3, 2176, 1088 , 64, 192));
        handler.addObject(new Rect(3, 2368, 1088 , 64, 192));
        handler.addObject(new Rect(3, 2560, 1088 , 64, 192));
        handler.addObject(new Rect(3, 2816, 1088 , 384, 192));
        handler.addObject(new Rect(3, 3200, 640 , 64, 320));
        handler.addObject(new Rect(3, 3200, 1154 , 3200, 125));
        handler.addObject(new Rect(3, 3264, 896 , 448, 64));
        handler.addObject(new Rect(3, 3904, 768 , 128, 64));
        handler.addObject(new Rect(3, 3968, 832 , 64, 128));
        handler.addObject(new Rect(3, 4032, 896 , 640, 64));
        handler.addObject(new Rect(3, 4608, 832 , 64, 64));
        handler.addObject(new Rect(3, 4928, 896 , 64, 64));
        handler.addObject(new Rect(3, 5504, 832 , 896, 128));
        handler.addObject(new Rect(3, 5248, 896 , 64, 64));
        handler.addObject(new Rect(3, 6336, 640 , 64, 192));
        handler.addObject(new Rect(3, 3840, 960 , 128, 64));
        handler.addObject(new Rect(3, 3264, 128 , 3072, 128));
        handler.addObject(new Rect(3, 6336, 128 , 64, 512));
        handler.addObject(new Rect(3, 3200, 128 , 64, 512));
        handler.addObject(new Rect(3, 6272, 384 , 64, 64));
        handler.addObject(new Rect(3, 5888, 384 , 128, 64));
        handler.addObject(new Rect(3, 5632, 512 , 64, 64));
        handler.addObject(new Rect(3, 5824, 576 , 64, 64));
        handler.addObject(new Rect(3, 5824, 768 , 192, 64));
        handler.addObject(new Rect(3, 3200, 1088 , 1536, 64));
        handler.addObject(new Rect(3, 5376, 1088 , 1024, 64));
        handler.addObject(new Rect(3, 6400, 1088 , 3200, 192));
        handler.addObject(new Rect(3, 7936, 0 , 64, 896));
        handler.addObject(new Rect(3, 8000, 0 , 1600, 64));
        handler.addObject(new Rect(3, 9536, 64 , 64, 1024));
        handler.addObject(new Rect(3, 6784, 832 , 128, 64));
        handler.addObject(new Rect(3, 7232, 768 , 128, 64));
        handler.addObject(new Rect(3, 7680, 704 , 128, 64));
        handler.addObject(new Rect(3, 7872, 512 , 64, 64));
        handler.addObject(new Rect(3, 7616, 320 , 64, 64));
        handler.addObject(new Rect(3, 6912, 128 , 256, 128));
        handler.addObject(new Rect(3, 6912, 256 , 64, 192));
        handler.addObject(new Rect(3, 6976, 384 , 320, 64));
    }
    
    public void obstacle(){
        handler.addObject(new Obstacle(3, 1856, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2048, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2240, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2432, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2624, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 4736, 1088, 640, 64));
    }
    
//    public void addBulletMomon(){
//
//        if(c){
//            handler.addObject(Enemy.bull);
//            c=false;
//        }else{
//            
//            c=true;
//        }
//        
//    }
    

    
    public synchronized void start() {
        if (running)
            return;
        
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    @Override
    public void run() {
        init();
        
        long lastTime = System.nanoTime();
        double amountOfTicks = FPS;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        
        
    }
    
    public void tick() {
        seconds++;
        keyHandler.tick();
        switch(currentState){
            case MAIN_MENU:
                menu.tick();
                break;
            case GAME_PLAY:

                hud.tick();
                if(Game.player.getX()<=0)
                {
                    Game.player.setX(0);
                }
                
                if(Extralife.c){
                    handler.removeObject(Extralife.ex);
                    
                }
                
                if(Miscellaneous.pager1)
                {
                    handler.removeObject(pager[0]);
                    handler.removeObject(pager[1]);
                }
                
                if(Miscellaneous.pager2)
                {
                    handler.removeObject(kunci);
                    handler.removeObject(pager[2]);
                    handler.removeObject(pager[3]);
                    handler.removeObject(pager[4]);
                }
                
                if(Miscellaneous.pagerHidden)
                {
                    handler.addObject(pager[2]);
                    handler.addObject(pager[3]);
                    handler.addObject(pager[4]);
                }
                
                if(Player.kena){
                    handler.removeObject(Player.enemy);
                }
                
                
                if(Enemy.liatPlayer)
                {  
                    //enemyBullet.addFirst(bull);
                    if(seconds>=100){
                    handler.addObject(Enemy.bull); 
                    seconds=0;
                    Enemy.liatPlayer=false;
                    }
                }
                
                if(Bullet.kenaBull)
                {
                    handler.removeObject(Bullet.temp);
                    Bullet.kenaBull=false;
                }
                
                handler.tick();
                camera.tick();
                break;
        }
    }
    
    public void render() {
        // Double Buffer Render
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            bs = getBufferStrategy();
        }

        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);

        switch(currentState)
        {
            case MAIN_MENU:
                menu.render(g);
                break;
            case GAME_PLAY:
                g.setColor(new Color(48, 54, 101));
                g.fillRect(0, 0, WIDTH, HEIGHT);
                
                g.scale(0.9 , 0.9);
                
                g.translate((int)camera.getX(), (int)camera.getY());
                g.drawImage(background, 0, 640, 9600 , 640 , null);
                g.drawImage(map, 0, 0, 9600 , 1280 , null);
                
                handler.render(g);
                g.translate(-(int)camera.getX(), -(int)camera.getY());
                
                
                
                hud.render(g);
                break;
        }
        
        
        
        g.dispose();
        bs.show();
    }
    
    public void loadLevel() {
        int w = level.getWidth();
        int h = level.getHeight();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int pixel = level.getRGB(i, j);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

//                if (red == 0 && green == 255 && blue == 0) {
//                    handler.addObject(
//                        Tile.newGrass(i * 32, j * 32, 17));
//                }

                if (red == 0 && green == 255 && blue == 255) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 10));
                }
                
                if (red == 0 && green == 255 && blue == 0) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 0));
                }
                
                if (red == 255 && green == 255 && blue == 0) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 20));
                }
                
                if (red == 50 && green == 0 && blue == 50) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 5));
                }
                
                if (red == 0 && green == 50 && blue == 50) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 15));
                }
                
                if (red == 100 && green == 100 && blue == 1) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 12));
                }
                
                if (red == 1 && green == 100 && blue == 1) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 7));
                }
                
                //OTHER
                
                if (red == 0 && green == 200 && blue == 200) {
                    handler.addObject(
                        Tile.newOther(i * 64, j * 64, 63));
                }
                
                if (red == 1 && green == 1 && blue == 200) {
                    handler.addObject(
                        Tile.newOther(i * 64, j * 64, 39));
                }
                
                if (red == 0 && green == 0 && blue == 200) {
                    handler.addObject(
                        Tile.newOther(i * 64, j * 64, 51));
                }
                
                if (red == 90 && green == 40 && blue == 30) {
                    handler.addObject(
                        Tile.newGround(i * 64, j * 64, 17));
                }
                
                if (red == 192 && green == 192 && blue == 192) {
                    handler.addObject(
                        Tile.newOther(i * 64, j * 64, 70));
                }
            }
        }
    }
    
    public void playGame(){
        hud.health=100;
        hud.jmlhDarah=5;
        handler.removeObject(player);
        player = new Player(0, 640,handler);
        handler.addObject(player);
        handler.addObject(new Miscellaneous(1, 6272, 320, handler));
        handler.addObject(new Miscellaneous(3, 8064, 960, handler));
        kunci = new Miscellaneous(2, 6976, 320, handler);
        handler.addObject(kunci);
        pager[0]= new Rect(1, 5504, 960, 64, 64);
        pager[1]= new Rect(1, 5504, 1024, 64, 64);
        pager[2]= new Rect(4, 7936, 896, 64, 64);
        pager[3]= new Rect(5, 7936, 960, 64, 64);
        pager[4]= new Rect(6, 7936, 1024, 64, 64);
        handler.addObject(pager[0]);
        handler.addObject(pager[1]);
        handler.addObject(pager[2]);
        handler.addObject(pager[3]);
        handler.addObject(pager[4]);
        
        extraLIfe();
        
        currentState = GameState.GAME_PLAY;
    }
    
    public static Game getInstance(){
        return game;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("My Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        
        game = new Game();
        game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        game.requestFocus();
        game.setFocusable(true);
        
        frame.add(game);
        frame.pack();
        
//        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }
}
