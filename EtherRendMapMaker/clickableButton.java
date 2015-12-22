import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class clickableButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class clickableButton extends ActorExtended
{
    KeyListener keylistener = PersistentStorage.keylistener;
    public void actExtended() 
    {
        checkClick();
    }
    
    public void checkClick()
    {
        if(keylistener.mouseClicked(this, "left"))
        {
            mouseClickAction();
        }
    }
    
    public abstract void mouseClickAction();
    
    public void addedToWorldExtended()
    {
        
    }
}
