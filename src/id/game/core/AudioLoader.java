/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.game.core;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
/**
 *
 * @author user
 */
public class AudioLoader 
{
    Clip clip;
    public void load(String path){
        try
        {
            URL url = getClass().getResource(path);
            AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            AudioFormat baseFormat = ais.getFormat();
            AudioFormat decodeFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), baseFormat.getChannels()*2 ,baseFormat.getSampleRate() ,false);
            AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat,ais);
            
            clip = AudioSystem.getClip();
            clip.open(dais);
            
            
        }catch(Exception e){;
            e.printStackTrace();
        }
    }
    
    public void play2(){
        if(clip == null) return;
        clip.start(); //sendiri
        clip.stop();
        clip.setFramePosition(0);
        clip.loop(Clip.LOOP_CONTINUOUSLY); //untuk backsiund jd di loop
    }
    
    public void play() {
        if (clip == null) {
            return;
        }
        clip.start();
        LineListener listener = new LineListener() {
            public void update(LineEvent event) {
                if (event.getType() != Type.STOP) {
                    return;
                }
            }
        };
        clip.addLineListener(listener);
    }
    
    
    public void stop(){
        if(clip.isRunning()) clip.stop();
    }
    
    public void close(){
        stop();
        clip.close();
    }
    
}
