import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
//import java.util.Random;

/**
 * Write a description of class ActorExtended here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class ActorExtended extends Actor
{
    double realYloc, realXloc;
    boolean remove = false;
    boolean pausable = true;
    
    //KeyListener keylistener = PersistentStorage.keylistener;
    KeyMonitor keymonitor = PersistentStorage.keymonitor;
    
    //Random randgenerator = new Random();
    public final void act()
    {
        if(pausable && !PersistentStorage.gamePaused)
        {
            actExtended();
        }
        checkRemove();
    }
    
    public double getRealX()
    {
        return realXloc;
    }
    
    public double getRealY()
    {
        return realYloc;
    }
    
    public final void addedToWorld(World world)
    {
        realXloc = getX();
        realYloc = getY();
        addedToWorldExtended();
    }
    
    public abstract void addedToWorldExtended();

    public abstract void actExtended();
    
    public boolean mouseClickedActor(Actor actorTemp)
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        int actorXradius = actorTemp.getImage().getWidth() / 2;
        int actorYradius = actorTemp.getImage().getHeight() / 2;
        if(mouse != null)
        {
            if(mouse.getX() > actorTemp.getX() - actorXradius && mouse.getX() < actorTemp.getX() + actorXradius && mouse.getY() > actorTemp.getY() - actorYradius && mouse.getY() < actorTemp.getY() + actorYradius)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean actorInWorld(Object objToLookFor)
    {
        if(objToLookFor != null)
        {
            if(getWorld().getObjects(null).contains(objToLookFor))
            {
                return true;
            }
        }
        return false;
    }
    
    public void moveDouble(double xTempMove, double yTempMove)
    {
        setRealLocation(realXloc + xTempMove, realYloc + yTempMove);
    }
    
    public void setRealLocation(double xTempLoc, double yTempLoc)
    {
        realXloc = xTempLoc;
        realYloc = yTempLoc;
        setLocation((int)realXloc, (int)realYloc);
    }
    
    /**
     * Move at a given angle in radians by a given distance.
     */
    public void moveAtAngleDouble(double angle, double rqstDistance)
    {
        double xMove = (Math.cos(angle));
        double yMove = (Math.sin(angle));
        
        setRealLocation(realXloc + (xMove * rqstDistance), realYloc + (yMove * rqstDistance));
    }
    
    public double getDistanceBetween(Actor object1, Actor object2)
    {
        if(object1 != null && object2 != null)
        {
            return Math.abs(Math.hypot(object2.getX()-object1.getX(), object2.getY()-object1.getY()));
        }
        return 0;
    }
    
    public double getAngleBetween(ActorExtended object1, ActorExtended object2)
    {
        if(object1 != null && object2 != null)
        {
            return getAngleBetween(object1.getRealX(), object1.getRealY(), object2.getRealX(), object2.getRealY());
        }
        return 0;
    }
    public double getAngleBetween(double x1, double y1, double x2, double y2)
    {
        return (Math.toDegrees(Math.atan2(y2-y1,x2-x1)));
    }
    
    public List getContactRadius(double radius, Class classType)
    {
        List objs = new ArrayList();
        for(int angle = 0; angle < 360; angle++)
        {
            List curr = getObjectsAtOffset((int)(Math.cos(angle) * radius), (int)(Math.sin(angle) * radius), classType);
            for(int i = 0; i < curr.size(); i++)
                {
                    if(!objs.contains(curr.get(i)))
                    {
                        objs.add(curr.get(i));
                    }
                }
        }
        return objs;
    }
    
    public void checkRemove()
    {
        if(remove)
        {
            removeAction();
            getWorld().removeObject(this);
        }
    }
    
    public void removeAction()
    {
    }
}
