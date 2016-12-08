package id.game.utils;

import java.awt.image.BufferedImage;

public class Assets {
    public BufferedImage sheet1, sheet2, sheet3, sheet4, sheet5, sheet6, sheet7, sheet8, sheet9, sheet10, sheet11;
    public BufferedImage[] player = new BufferedImage[60];
    public BufferedImage[] attck = new BufferedImage[28];
    public BufferedImage[] other = new BufferedImage[132];
    public BufferedImage[] ground = new BufferedImage[25];
    public BufferedImage[] button = new BufferedImage[2];
    public BufferedImage[] bullet = new BufferedImage[3];
    public BufferedImage[] enemy = new BufferedImage[4];
    public BufferedImage[] enemyfly = new BufferedImage[4];
    public BufferedImage[] ryuKiri = new BufferedImage[8];
    public BufferedImage[] ryuKanan = new BufferedImage[8];
    public BufferedImage[] flag = new BufferedImage[4];
    
    public Assets() {
        button[0] = ImageLoader.load("button.png");
        button[1] = ImageLoader.load("button_pressed.png");
        sheet1 = ImageLoader.load("other.png");
        sheet2 = ImageLoader.load("ground.png");
        sheet3 = ImageLoader.load("character.png");
        sheet4 = ImageLoader.load("attack.png");
        sheet5 = ImageLoader.load("bullet2.png");
        sheet6 = ImageLoader.load("enemy.png");
        sheet7 = ImageLoader.load("enemyFly.png");
        sheet8 = ImageLoader.load("flag.png");
        sheet9 = ImageLoader.load("jalankiri.png");
        sheet10 = ImageLoader.load("jalankanan.png");
        sheet11 = ImageLoader.load("hadouken.png");
        
        bullet[0]=sheet5.getSubimage(0, 0, 16, 16);
        bullet[1]=sheet5.getSubimage(16, 0, 16, 16);
        bullet[2]=sheet11.getSubimage(128, 0, 128, 128);
        
        flag[0]=sheet8.getSubimage(0, 0, 64, 64);
        flag[1]=sheet8.getSubimage(64, 0, 64, 64);
        flag[2]=sheet8.getSubimage(128, 0, 64, 64);
        flag[3]=sheet8.getSubimage(192, 0, 64, 64);
        
        int x = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 12; j++) {
                other[x] = sheet1.getSubimage(i*64, j*64, 64, 64);
                x++;
            }
        }
        
        x = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                //grass[x] = sheet1.getSubimage(i*32, j*32, 32, 32);
                ground[x] = sheet2.getSubimage(i*64, j*64, 64, 64);
                x++;
            }
        }
        
        x = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 10; j++) {
                player[x] = sheet3.getSubimage(j*128, i*128, 128, 128);
                x++;
            }
        }
        
        
        x = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                attck[x] = sheet4.getSubimage(j*128, i*128, 128, 128);
                x++;
            }
        }
        
        x = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                enemy[x] = sheet6.getSubimage(j*49, i*38, 49, 38);
                enemyfly[x] = sheet7.getSubimage(j*64, i*43, 64, 43);
                x++;
            }
        }
        
        x = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 8; j++) {
                ryuKiri[x] = sheet9.getSubimage(j*128, i*128, 128, 128);
                ryuKanan[x] = sheet10.getSubimage(j*128, i*128, 128, 128);
                x++;
            }
        }
        

    }
}
