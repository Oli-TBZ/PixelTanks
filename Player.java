import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    /**
     * Act - do whatever the player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        
    }
    public void drive(){
        if (Greenfoot.isKeyDown("w")){
           move (2);        
        }
        if (Greenfoot.isKeyDown("s")){
           move (-2);
        }    
    }
    public void turn(){
         if (Greenfoot.isKeyDown("d")){
           turn (3);
        }
        if (Greenfoot.isKeyDown("a")){
           turn (-3);
        }
    }
    public void aim(){
        if (Greenfoot.isKeyDown("right")){
           turn(1);
        }
        if (Greenfoot.isKeyDown("left")){
           turn(-1);
        }
    }
}
