import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Battleground here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Battleground extends World
{
    /**
     * Constructor for objects of class Battleground.
     * 
     */
    public Battleground()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1);
        prepare();
    }
    public void prepare()
    {
        Playerbody playerbody = new Playerbody();
        addObject(playerbody, 200, 200);
        Playerturret playerturret = new Playerturret();
        addObject(playerturret, 200, 200);
    
    }
}