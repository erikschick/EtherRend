import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
public class HitBox extends ActorExtended
{
    ActorExtended parent = null;
    public HitBox(ActorExtended parent, int x, int y)
    {
        this.parent = parent;
        setImage(new GreenfootImage(x, y));
    }
    
    public void actExtended()
    {
        if(!actorInWorld(parent))
        {
            remove = true;
        }
    }
    
    public void addedToWorldExtended()
    {
    }
}
