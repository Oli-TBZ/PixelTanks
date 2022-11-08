import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class playerbullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemybullet extends Enemy{
    
    private boolean firstrun;
    private int speed;
    private int mode;

    public Enemybullet(int xLocation, int yLocation, int rotation, int mode){
        setLocation(xLocation,yLocation);
        setRotation(rotation);
        
        GreenfootImage image = getImage();
        
        this.mode = mode;
        
        if (mode == 1){
            this.speed = 5;
            image.scale(image.getWidth()*2, image.getHeight()*2);  
        } else{
            image.scale(image.getWidth()/2, image.getHeight()/2);  
            this.speed = 5;
        }
        
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
            if (mode == 1){
                move(70);
            } else {
                move(43);
            }
            getImage().setTransparency(255);
            firstrun = false;
        }
        move(speed);
        if (isAtEdge() || isTouching(Playerbody.class)){
            //removeTouching(Playerbody.class);
            getWorld().removeObject(this);
        }
    }
}
