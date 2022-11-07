import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class playerbullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Playerbullet extends Player{
    
    private boolean firstrun;

    public Playerbullet(int xLocation, int yLocation, int rotation){
        setLocation(xLocation,yLocation);
        setRotation(rotation);
        
        GreenfootImage image = getImage();
        image.scale(image.getWidth()/ 2, image.getHeight()/ 2);
        image.setTransparency(0);
        setImage(image);
        
        //set as first run
        firstrun = true;
    }
    
    /**
     * Act - do whatever the playerbullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (firstrun){
            move(43);
            getImage().setTransparency(255);
            firstrun = false;
        }
        move(10);
        if (isAtEdge() || isTouching(Enemybody.class)){
            if (isTouching(Enemybody.class)){
                //
            }
            removeTouching(Enemybody.class);
            getWorld().removeObject(this);
        }
    }
}