import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ghostturret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ghostturret extends Enemy
{
    private Enemybody relatedTankBody;
    
    public Ghostturret(Enemybody tankbody){
        relatedTankBody = tankbody;
    }
    
    /**
     * Act - do whatever the ghostturret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (relatedTankBody.getWorld() != null){
            setLocation(relatedTankBody.getX(), relatedTankBody.getY());
            turnTowards(getPlayerX(), getPlayerY());
        } else{
            getWorld().removeObject(this);
        }
    }
    
    public int getPlayerDirection(){
        return getRotation();
    }
}
