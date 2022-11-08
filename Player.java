import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent Class of all enemy objects. Contains certain universal functions and movement functions (keypressed).
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
 */
public class Player extends Actor
{
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
           turn (2);
           //adjustAngle(-1);
        }
        if (Greenfoot.isKeyDown("a")){
           turn (-2);
           //adjustAngle(-1);
        }
    }
    public void aim(){
        if (Greenfoot.isKeyDown("right")){
           turn(2);
           //adjustAngle(2);
        }
        if (Greenfoot.isKeyDown("left")){
           turn(-2);
           //adjustAngle(-2);
        }
    }
    //return coordinates of playerbody
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
