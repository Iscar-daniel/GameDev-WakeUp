package id.game.main;

import id.game.core.AudioLoader;
import id.game.core.Camera;
import id.game.core.Constants;
import id.game.core.GameObject;
import id.game.core.KeyHandler;
import id.game.core.MouseHandler;
import id.game.core.ObjectHandler;
import id.game.objects.Bullet;
import id.game.objects.Enemy;
import id.game.objects.Extralife;
import id.game.objects.Flag;
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
    public static BufferedImage map, background;
    public Intro intro;
    public static Assets assets;
    public static AudioLoader sMenu;
    public static boolean statusMonster=false;
    
    static Game game;
    public Menu menu;
    public HUD hud;
    public Win win;
    public Level levelImage;
    public GameOverMenu gameOverMenu;
    public int seconds;
    
    public static int lvl=1;
    
    public boolean c = false;
    
    public enum GameState{
        MAIN_MENU, GAME_PLAY, GAME_OVER,INTRO,INTRO2, WIN,LEVEL1,LEVEL2;
    }
    
    public GameState currentState = GameState.MAIN_MENU;

//    public void setCurrentState(GameState currentState) {
//        this.currentState = currentState;
//    }
    
    public void init() {
        // init
        menu = new Menu();
        hud = new HUD();
        gameOverMenu = new GameOverMenu();
        levelImage = new Level();
        win = new Win();
        handler = new ObjectHandler();
        keyHandler = new KeyHandler(handler);
        mouseHandler = new MouseHandler();
        assets = new Assets();
        camera = new Camera(100, 100);
        intro=new Intro();
        addKeyListener(keyHandler);
        addMouseListener(mouseHandler);
        
        sMenu= new AudioLoader();
        sMenu.load("../assets/sound/background2.wav");
        
        //level = ImageLoader.load("map3.png");
        if(lvl==2){
            map=ImageLoader.load("level2.png");
        }else{
        map = ImageLoader.load("mapTiled.png");
        }
        background = ImageLoader.load("back.png");
        
        player = new Player(0, 0, handler);
        handler.addObject(player);
        
        
        
    }
    
    public void extraLIfe(){
        handler.addObject(new Extralife(3992, 1024, handler));
        handler.addObject(new Extralife(930, 832, handler));
    }
    
    public void monster(){
        handler.addObject(new Enemy(2, 896, 768,25, handler));
        handler.addObject(new Enemy(2, 7104, 320, 25, handler));
        handler.addObject(new Enemy(2, 2304, 960,25, handler));
        handler.addObject(new Enemy(2, 7488, 576,25, handler));
        handler.addObject(new Enemy(2, 5952, 1024,25, handler));
        handler.addObject(new Enemy(1, 3456, 832,25, handler));
        handler.addObject(new Enemy(1, 4288, 832, 25, handler));
        handler.addObject(new Enemy(2, 4288, 1024, 25, handler));
        handler.addObject(new Enemy(2, 5091, 746,25, handler));
        handler.addObject(new Enemy(1, 7296, 1024,25, handler));
        
        
        
        handler.addObject(new Enemy(3, 9000, 640,200, handler));
    }

    
    public void ground(){
        handler.addObject(new Rect(3, 0, 1088 , 576, 192, handler));
        handler.addObject(new Rect(3, 448, 1024 , 192, 64, handler));
        handler.addObject(new Rect(3, 704, 960 , 128, 64, handler));
        handler.addObject(new Rect(3, 896, 896 , 128, 64, handler));
        handler.addObject(new Rect(3, 1088, 960 , 128, 64, handler));
        handler.addObject(new Rect(3, 1280, 1024 , 192, 64, handler));
        handler.addObject(new Rect(3, 1334, 1088 , 512, 192, handler));
        handler.addObject(new Rect(3, 1984, 1088 , 64, 192, handler));
        handler.addObject(new Rect(3, 2176, 1088 , 64, 192, handler));
        handler.addObject(new Rect(3, 2368, 1088 , 64, 192, handler));
        handler.addObject(new Rect(3, 2560, 1088 , 64, 192, handler));
        handler.addObject(new Rect(3, 2816, 1088 , 384, 192, handler));
        handler.addObject(new Rect(3, 3200, 640 , 64, 320, handler));
        handler.addObject(new Rect(3, 3200, 1154 , 3200, 125, handler));
        handler.addObject(new Rect(3, 3264, 896 , 448, 64, handler));
        handler.addObject(new Rect(3, 3904, 768 , 128, 64, handler));
        handler.addObject(new Rect(3, 3968, 832 , 64, 128, handler));
        handler.addObject(new Rect(3, 4032, 896 , 640, 64, handler));
        handler.addObject(new Rect(3, 4608, 832 , 64, 64, handler));
        handler.addObject(new Rect(3, 4928, 896 , 64, 64, handler));
        handler.addObject(new Rect(3, 5504, 832 , 896, 128, handler));
        handler.addObject(new Rect(3, 5248, 896 , 64, 64, handler));
        handler.addObject(new Rect(3, 6336, 640 , 64, 192, handler));
        handler.addObject(new Rect(3, 3840, 960 , 128, 64, handler));
        handler.addObject(new Rect(3, 3264, 128 , 3072, 128, handler));
        handler.addObject(new Rect(3, 6336, 128 , 64, 512, handler));
        handler.addObject(new Rect(3, 3200, 128 , 64, 512, handler));
        handler.addObject(new Rect(3, 6272, 384 , 64, 64, handler));
        handler.addObject(new Rect(3, 5888, 384 , 128, 64, handler));
        handler.addObject(new Rect(3, 5632, 512 , 64, 64, handler));
        handler.addObject(new Rect(3, 5824, 576 , 64, 64, handler));
        handler.addObject(new Rect(3, 5824, 768 , 192, 64, handler));
        handler.addObject(new Rect(3, 3200, 1088 , 1536, 64, handler));
        handler.addObject(new Rect(3, 5376, 1088 , 1024, 64, handler));
        handler.addObject(new Rect(3, 6400, 1088 , 3200, 192, handler));
        handler.addObject(new Rect(3, 7936, 0 , 64, 896, handler));
        handler.addObject(new Rect(3, 8000, 0 , 1600, 64, handler));
        handler.addObject(new Rect(3, 9536, 64 , 64, 1024, handler));
        handler.addObject(new Rect(3, 6784, 832 , 128, 64, handler));
        handler.addObject(new Rect(3, 7232, 768 , 128, 64, handler));
        handler.addObject(new Rect(3, 7680, 704 , 128, 64, handler));
        handler.addObject(new Rect(3, 7872, 512 , 64, 64, handler));
        handler.addObject(new Rect(3, 7616, 320 , 64, 64, handler));
        handler.addObject(new Rect(3, 6912, 128 , 256, 128, handler));
        handler.addObject(new Rect(3, 6912, 256 , 64, 192, handler));
        handler.addObject(new Rect(3, 6976, 384 , 320, 64, handler));
    }
    
    
    public void obstacle(){
        handler.addObject(new Obstacle(3, 1856, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2048, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2240, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2432, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 2624, 1216, 128, 64));
        handler.addObject(new Obstacle(3, 4736, 1088, 640, 64));
    }
    
    public void monster2(){
                handler.addObject(new Enemy(1,833,964,50, handler));
                handler.addObject(new Enemy(1, 2370, 642, 50, handler));
                handler.addObject(new Enemy(1, 2934, 457, 50, handler));
                handler.addObject(new Enemy(1, 1793, 325, 50, handler));
                handler.addObject(new Enemy(1, 3956, 510, 50, handler));
                handler.addObject(new Enemy(2, 3831, 382, 50, handler));
                handler.addObject(new Enemy(1, 2877, 956, 50, handler));
                handler.addObject(new Enemy(3, 1981, 959, 300, handler));
                
    }

    public void ground2(){
         handler.addObject(new Rect(3, 0, 1024, 1280, 64, handler));
         handler.addObject(new Rect(3, 1407, 960, 128, 64,handler));
         handler.addObject(new Rect(3, 1601, 831, 64, 64,handler));
         handler.addObject(new Rect(3, 1665, 831, 128, 64,handler));
         handler.addObject(new Rect(3, 1665, 768, 128, 64,handler));
         handler.addObject(new Rect(3, 1726, 704, 64, 64,handler));
         handler.addObject(new Rect(3, 1790, 704, 768, 64,handler));
         handler.addObject(new Rect(3, 1725, 896, 64, 128,handler));
          handler.addObject(new Rect(3, 2612, 586, 203, 56,handler));
          handler.addObject(new Rect(3, 2871, 521, 199, 56,handler));
          handler.addObject(new Rect(3, 3133, 452, 195, 63,handler));
          handler.addObject(new Rect(3, 1603, 395, 1088, 32,handler));
          handler.addObject(new Rect(3, 3510, 252, 1024, 64,handler));
          handler.addObject(new Rect(3, 3512, 575, 1024, 128,handler));
          handler.addObject(new Rect(3, 4470, 318, 64, 256,handler));
          handler.addObject(new Rect(3, 2685, 1019, 832, 64,handler));
         handler.addObject(new Rect(3, 1725, 1024, 960, 64,handler));
          handler.addObject(new Rect(3, 3511, 704, 64, 384,handler));
    
    }

    public void obstacle2(){
        handler.addObject(new Obstacle(3, 1283, 1083, 448, 64));
    }

    
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
                Flag.isTouched = false;
                break;
            case INTRO :
                intro.tick();
                break;
            case INTRO2 :
                intro.tick();
                break;
            case LEVEL1 :
                levelImage.tick();
                break;
            case LEVEL2 :
                levelImage.tick();
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
                    System.out.println("Buka Pager");
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
                
                
                if(Enemy.liatPlayerKiri)
                {  
                    //enemyBullet.addFirst(bull);
                    if(seconds>=100){
                    
                    handler.addObject(Enemy.bull); 
                    seconds=0;
                    Enemy.liatPlayerKiri=false;
                    }
                }
                
                if(Enemy.liatPlayerKanan)
                {  
                    //enemyBullet.addFirst(bull);
                    if(seconds>=100){
                    handler.addObject(Enemy.bull); 
                    seconds=0;
                    Enemy.liatPlayerKanan=false;
                    }
                }
                
                if(Bullet.kenaBull)
                {
                    handler.removeObject(Bullet.temp);
                    Bullet.kenaBull=false;
                }
                
                if(Bullet.knPlayer)
                {
                    
                    hud.health-=20;
                    if(hud.jmlhDarah<0){
                        hud.jmlhDarah=0;
                    }else{
                        hud.jmlhDarah-=1;
                    }
                    
                    handler.removeObject(Bullet.bullEnemy);
                    Bullet.knPlayer=false;
                    
                    if(hud.health==0)
                    {
                        sMenu.stop();
                        handler.removeObject(player);
                        Game.getInstance().currentState = Game.GameState.GAME_OVER;
                    }
                    
                    
                    
                }
                
                handler.tick();
                camera.tick();
                break;
            case GAME_OVER:
                gameOverMenu.tick();
                break;
            case WIN:
                win.tick();
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
            case INTRO :
                intro.render(g);
                System.out.println(currentState);
                break;
            case INTRO2 :
                intro.render(g);
                System.out.println(currentState);
                break;
            case MAIN_MENU:
                menu.render(g);
                //System.out.println(currentState);
                break;
            case LEVEL1 :
                levelImage.render(g);
                break;
            case LEVEL2 :
                levelImage.render(g);
                break;
            case GAME_PLAY:
                g.setColor(new Color(48, 54, 101));
                g.fillRect(0, 0, WIDTH, HEIGHT);
                
                g.scale(0.9 , 0.9);
                
                g.translate((int)camera.getX(), (int)camera.getY());
                
                //g.drawImage(background, 0, 640, 9600 , 640 , null);
                if(lvl==1)
                g.drawImage(map, 0, 0, 9600 , 1280 , null);
                else if(lvl==2)
                    g.drawImage(map, 0,0, 6708, 1351,null);
                handler.render(g);
                g.translate(-(int)camera.getX(), -(int)camera.getY());
                
                
                
                hud.render(g);
                break;
            case GAME_OVER:
                gameOverMenu.render(g);
                break;
            case WIN:
                win.render(g);
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
        handler.objects.clear();
        
        if(lvl==1)
        {
            handler.addObject(new Flag(2825, 1024, handler));

            monster();

            ground();
            obstacle();
            //Checkpoint
            if (Flag.isTouched) {
                player = new Player(2825, 640,handler);
            }
            else player = new Player(0, 640,handler);

            //player = new Player(0, 640,handler);
            handler.addObject(player);
            handler.addObject(new Miscellaneous(1, 6272, 320, handler));
            handler.addObject(new Miscellaneous(3, 8064, 960, handler));

            if(kunci!=null)
            {
                handler.removeObject(kunci);
            }

            kunci = new Miscellaneous(2, 6976, 320, handler);
            handler.addObject(kunci);
            for (int i = 0; i < pager.length; i++) {
                if(pager[i]!=null)
                {
                    handler.removeObject(pager[i]);
                }


            }
            pager[0]= new Rect(1, 5504, 960, 64, 64, handler);
            pager[1]= new Rect(1, 5504, 1024, 64, 64, handler);
            pager[2]= new Rect(4, 7936, 896, 64, 64, handler);
            pager[3]= new Rect(5, 7936, 960, 64, 64, handler);
            pager[4]= new Rect(6, 7936, 1024, 64, 64, handler);

            handler.addObject(pager[0]);
            handler.addObject(pager[1]);
            handler.addObject(pager[2]);
            handler.addObject(pager[3]);
            handler.addObject(pager[4]);

        extraLIfe();    
        }
        
        if(lvl==2)
        {
            
              
//            removeGround();
            player = new Player(0, 0,handler);
                ground2();
                monster2();
                obstacle2();
                //player = new Player(0, 640,handler);
                handler.addObject(player);

        }
        
        
        currentState = GameState.GAME_PLAY;
        sMenu.play2();
        System.out.println("playgame!");
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
