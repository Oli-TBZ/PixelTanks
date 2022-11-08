import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Subclass of enemy class. The enemyturret follows the X coordinates of the related Tankbody to stay in position. It receives the playerdirection
 * the ghostturret class and tries to match that angle and fire at player.
 * 
 * @OliverAmmann & @SenthilNagendran
 * Stable Version 1.1
 */
public class Enemyturret extends Enemy
{
    private Enemybody relatedTankbody;
    private Ghostturret relatedGhostturret;
    
    private int rotationspeed;

    private int cooldown;
    private int lastShotTime;
    
    private boolean firststart;
    
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
        
        //set certain variables depending on level
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
        //if statement to check if relatedBody is still alive or otherwise remove the turret as well
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
    
    //complicated function to aim at player and fire when in line of sight with a certain margin of error. 
    //It gets the playerdirection from the ghostturret
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
    //create new enemybullet instance and fire it towards player. Change size and sound of bullet depending on mode (level)
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

