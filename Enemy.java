import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Parent Class of all enemy objects. Contains certain universal functions.
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
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
    //function to get player x coordinate
    public int getPlayerX(){
        if (!getWorld().getObjects(Playerbody.class).isEmpty()){
            Actor reference = getWorld().getObjects(Playerbody.class).get(0);
            return reference.getX();
        } else {
            return 0;
        }
    }
    //functon to get player y coordinate
    public int getPlayerY(){
        if (!getWorld().getObjects(Playerbody.class).isEmpty()){
            Actor reference = getWorld().getObjects(Playerbody.class).get(0);
            return reference.getY();
        } else {
            return 0;
        }
    }
    //function to get time from world
    public int getTime(){
        Battleground world = (Battleground) getWorld();
        return world.getTime();
    }
}
