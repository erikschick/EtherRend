import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
/**
 * Write a description of class menuItem here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class menuItem extends ActorExtended
{
    expandMenu parent = null;
    public menuItem(expandMenu parent, int width, int height)
    {
        this.parent = parent;
        getImage().clear();
        setImage(getImage());
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(new Color(0, 199, 229));
        img.fillRect(2, 2, width - 4, height - 4);
        setImage(img);
    }
    
    public void actExtended()
    {
        checkParentDecay();
    }
    
    public void addedToWorldExtended()
    {
    }
    
    public void checkParentDecay()
    {
        if(!objectExists(parent))
        {
            remove = true;
        }
    }
}
