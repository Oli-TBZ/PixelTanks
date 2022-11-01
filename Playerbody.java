import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class body here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Playerbody extends Player
{
    public Playerbody(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
    }
    /**
     * Act - do whatever the body wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        drive();
        turn();
    }
}
