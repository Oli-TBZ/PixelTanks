import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemyturret here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemyturret extends Enemy
{
    private Enemybody relatedTankbody;
    private Ghostturret relatedGhostturret;
    
    private int rotationspeed;
    
    private boolean firststart;
    
    private int cooldown;
    private int lastShotTime;
    private GreenfootSound shot = new GreenfootSound("shot.mp3");
     private GreenfootSound bigshot = new GreenfootSound("bigshot.mp3");
    private GreenfootImage image;
    
    public Enemyturret(Enemybody relatedTankbody, Ghostturret relatedGhostturret){
        this.relatedTankbody = relatedTankbody;
        this.relatedGhostturret = relatedGhostturret;
        this.lastShotTime = 2;
        
        this.shot.setVolume(15);
        this.bigshot.setVolume(15);
        this.firststart = true;
        
        if (relatedTankbody.getLvl() == 1){
            image = getImage();
            image.scale(image.getWidth()* 2, image.getHeight()* 2);
            image.setTransparency(0);
            setImage(image);
            
            this.cooldown = 3;
            this.rotationspeed = 1;
        }
        else if (relatedTankbody.getLvl() == 2){
            setImage("enemy_turretv2.png");
            image = getImage();
            image.scale(image.getWidth()* 2, image.getHeight()* 2);
            image.setTransparency(0);
            setImage(image);
            
            this.cooldown = 2;
            this.rotationspeed = 2;
        }
        else if (relatedTankbody.getLvl() == 3){
            setImage("enemy_turretv3.png");
            image = getImage();
            image.scale(image.getWidth()* 4, image.getHeight()* 4);
            image.setTransparency(0);
            setImage(image);
            
            this.cooldown = 2;
            this.rotationspeed = 1;
        }
    }
    
    /**
     * Act - do whatever the Enemyturret wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (relatedTankbody.getWorld() != null){
            if (firststart){
                turnTowards(getPlayerX(), getPlayerY());
                firststart = false;
                lastShotTime = getTime();
                getImage().setTransparency(255);
            }
            setLocation(relatedTankbody.getX(),relatedTankbody.getY());
            aim();
        } else {
            getWorld().removeObject(this);
        }
    }
    
    private void aim(){
        int targetRotation = relatedGhostturret.getPlayerDirection();
        relatedTankbody.setPlayerDirection(targetRotation);
        int rotationDiff = targetRotation - getRotation();
        
        if (rotationDiff < -179){
            rotationDiff += 360;
        }
        if (rotationDiff > 180){
            rotationDiff -= 360;
        }
        if (rotationDiff > 0){
            turn(rotationspeed);
        }
        else{
            turn(-rotationspeed);
        }
        if (rotationDiff < rotationspeed && rotationDiff > -rotationspeed){
            if (relatedTankbody.getLvl() == 3){
                setRotation(targetRotation);
                fire(1);       
            } else{
                setRotation(targetRotation);
                fire(0);    
            }
            
        }
    }
    private void fire(int mode){
        if (lastShotTime + cooldown <= getTime()){
            if (mode == 1){  
                Enemybullet enemybullet1 = new Enemybullet(relatedTankbody.getX(),relatedTankbody.getY(), getRotation(), mode);
                getWorld().addObject(enemybullet1,relatedTankbody.getX(),relatedTankbody.getY());
                lastShotTime = getTime();
                bigshot.play();  
            } else{
                Enemybullet enemybullet = new Enemybullet(relatedTankbody.getX(),relatedTankbody.getY(), getRotation(), mode);
                getWorld().addObject(enemybullet,relatedTankbody.getX(),relatedTankbody.getY());
                lastShotTime = getTime();
                shot.play();   
            }
        }
    }
}

