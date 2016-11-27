package id.game.core;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ObjectHandler {
    public LinkedList<GameObject> objects;
    public Set<Integer> keys;
    
    public ObjectHandler() {
        this.objects = new LinkedList<>();
        this.keys = new HashSet<>();
    }
    
    public synchronized void addObject(GameObject object) {
        this.objects.add(object);
    }
    
    public void removeObject(GameObject object) {
//        Iterator ite = (Iterator) object;
//        ite.remove();
        synchronized(object) {
            this.objects.remove(object);
        }
    }
    
    public void addKey(int key) {
        this.keys.add(key);
    }
    
    public void removeKey(int key) {
        this.keys.remove(key);
    }
    
    public synchronized void tick() {
        
//        Iterator<GameObject> ite = objects.iterator();
//        
//        while(ite.hasNext()) {
//               GameObject value = ite.next();
//          }
        
//        synchronized(objects) {
//            for (Iterator<GameObject> iterator = objects.iterator(); iterator.hasNext();) {
//                GameObject next = (GameObject)iterator.next();
//                next.tick(objects);
//
//            }
//        }
        
        for (GameObject object : objects) {
            object.tick(objects);
        }
    }
    
    public synchronized void render(Graphics2D g2d) {
        for (GameObject object : objects) {
            object.render(g2d);
        }
        
//        synchronized(objects) {
//            for (Iterator<GameObject> iterator = objects.iterator(); iterator.hasNext();) {
//                GameObject next = (GameObject)iterator.next();
//                next.render(g2d);
//
//            }
//        }
    }
}
