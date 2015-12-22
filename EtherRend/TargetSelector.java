import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class targetSelector here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TargetSelector extends EtherWorldActor
{
    EtherWorldActor target = null;
    final String imageFile = "TargetSelector.png";
    final GreenfootImage blankimg = new GreenfootImage(1,1);
    int alpha = 255;
    
    public TargetSelector(){}
    public TargetSelector(EtherWorldActor target){this.target = target;}
    public TargetSelector(int alpha){this.alpha = Math.min(255, Math.max(alpha, 0));}
    public TargetSelector(EtherWorldActor target, int alpha){this.target = target; this.alpha = Math.min(255, Math.max(alpha, 0));}

    
    //public void etherWorldActorAct()
    public void actExtended()
    {
        super.actExtended();
        followTarget();
    }
    
    public void addedToEtherworld()
    {
    }
    
    public void followTarget()
    {
        if(target == null)
        {
            target = PersistentStorage.playerTarget;
        }
        else
        {
            if(!target.equals(PersistentStorage.playerTarget))
            {
                target = PersistentStorage.playerTarget;
            }
        }
        if(target != null)
        {
            setCoords(target.coordX, target.coordY);
            if(target.getImage().getWidth() != getImage().getWidth() || target.getImage().getHeight() != getImage().getHeight())
            {
                setImage(imageFile);
                getImage().setTransparency(alpha);
                getImage().scale(target.getImage().getWidth(), target.getImage().getHeight());
            }
        }
        else if(!getImage().equals(blankimg))
        {
            setImage(blankimg);
        }
    }
    
    public void makeTarget(EtherWorldActor target){this.target = target;}
    public void clearTarget(){target = null;}
    public void setTransparency(int alpha){this.alpha = Math.min(255, Math.max(alpha, 0));}
}
