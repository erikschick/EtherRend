import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class saveButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class saveButton extends clickableButton
{    
    public void mouseClickAction()
    {
        EtherWorld world = (EtherWorld) getWorld();
        if(PersistentStorage.currentFile == null)
        {
            world.saveWorld(true);
        }
        else
        {
            world.saveWorld(false);
        }
        PersistentStorage.etherworld.addObject(new NOTFCN_tooltip(300, "File saved"), 0, 0);
    }
}
