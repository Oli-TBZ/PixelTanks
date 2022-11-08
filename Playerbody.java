import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass of player class. The playerbody is the main part of the playertank. Does nothing more than just move depending on keys pressed.
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
 */ 
public class Playerbody extends Player
{
    public Playerbody(){
        GreenfootImage image = getImage();
        image.scale(image.getWidth()*2, image.getHeight()*2);
        setImage(image);
        
    }
    public void act()
    {
        drive();
        turn();
    }
}
