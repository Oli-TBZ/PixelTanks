import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class turret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Playerturret extends Player
{
    public Playerturret(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth()* 2, image.getHeight()* 2);
        setImage(image);
    }
    /**
     * Act - do whatever the turret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        //Schauen ob es einen Playerbody hat
        if (!getWorld().getObjects(Playerbody.class).isEmpty()){
            Actor reference = getWorld().getObjects(Playerbody.class).get(0);
            setLocation(reference.getX(), reference.getY());
        }
        turn();
        aim();
    }
    
    
}
