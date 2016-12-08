/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.core;

import id.game.main.Game;
import static id.game.main.Game.GameState.INTRO;
import static id.game.main.Game.GameState.INTRO2;
import static id.game.main.Game.GameState.MAIN_MENU;
import id.game.objects.Button;
import id.game.utils.ImageLoader;
import java.awt.Menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author sony
 */
public class MouseHandler extends MouseAdapter{

    @Override
    public void mouseReleased(MouseEvent me) {
        if(Game.getInstance().menu.btnPlay.isHover() && Game.getInstance().currentState == Game.GameState.MAIN_MENU)
        {
            Game.getInstance().currentState=Game.GameState.INTRO;
            Game.getInstance().menu.btnPlay.setToNormal();
            //Game.getInstance().playGame();
        }
        
        if(Game.getInstance().menu.btnExit.isHover() && Game.getInstance().currentState == Game.GameState.MAIN_MENU)
        {
            System.exit(0);
        }
        
        if(Game.getInstance().intro.btnPrev.isHover() && Game.getInstance().currentState==Game.GameState.INTRO){
            
                Game.getInstance().currentState=Game.GameState.MAIN_MENU;
                Game.getInstance().intro.btnPrev.setToNormal();
        }
        
        if(Game.getInstance().intro.btnPrev.isHover() && Game.getInstance().currentState==Game.GameState.INTRO2){
                Game.getInstance().currentState=Game.GameState.INTRO;
                Game.getInstance().intro.btnPrev.setToNormal();
         } 
            
        
        
         if(Game.getInstance().currentState==Game.GameState.INTRO && Game.getInstance().intro.btnNext.isHover()){
                System.out.println("Intro2");
                Game.getInstance().currentState=Game.GameState.INTRO2;
                Game.getInstance().intro.btnNext.setToNormal();
         }

         if(Game.getInstance().intro.btnNext.isHover() && Game.getInstance().currentState==Game.GameState.INTRO2){
                Game.getInstance().currentState=Game.GameState.LEVEL1;
                Game.getInstance().intro.btnNext.setToNormal();
         } 
         
         //level
        if(Game.getInstance().currentState==Game.GameState.LEVEL1 && Game.getInstance().levelImage.btnNext.isHover()){
               Game.getInstance().playGame();
               Game.getInstance().levelImage.btnNext.setToNormal();
        }

        if(Game.getInstance().currentState==Game.GameState.LEVEL2 && Game.getInstance().levelImage.btnNext.isHover()){
               Game.getInstance().hud.health=100;
               Game.lvl=2;
               Game.map=ImageLoader.load("level2.png");
               Game.getInstance().playGame(); //playgame level 2
               Game.getInstance().levelImage.btnNext.setToNormal();
        }
       
        
        if(Game.getInstance().gameOverMenu.btnContinue.isHover() && Game.getInstance().currentState == Game.GameState.GAME_OVER)
        {
            Game.getInstance().playGame();
        }
        
        if(Game.getInstance().gameOverMenu.btnMenu.isHover() && Game.getInstance().currentState == Game.GameState.GAME_OVER)
        {
            Game.getInstance().currentState = Game.GameState.MAIN_MENU;
        }
        
        
        if(Game.getInstance().win.btnPlay.isHover() && Game.getInstance().currentState == Game.GameState.WIN)
        {
            Game.lvl=1;
            Game.map=ImageLoader.load("mapTiled.png");
            Game.getInstance().currentState = Game.GameState.MAIN_MENU;
        }
        
        if(Game.getInstance().win.btnExit.isHover() && Game.getInstance().currentState == Game.GameState.WIN)
        {
            System.exit(0);
        }
    }
    
}
