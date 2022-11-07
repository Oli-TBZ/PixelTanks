import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    
    public int getPlayerX(){
        if (!getWorld().getObjects(Playerbody.class).isEmpty()){
            Actor reference = getWorld().getObjects(Playerbody.class).get(0);
            return reference.getX();
        } else {
            return 0;
        }
    }
    public int getPlayerY(){
        if (!getWorld().getObjects(Playerbody.class).isEmpty()){
            Actor reference = getWorld().getObjects(Playerbody.class).get(0);
            return reference.getY();
        } else {
            return 0;
        }
    }
    
    
    public int getTime(){
        Battleground world = (Battleground) getWorld();
        return world.getTime();
    }
}
