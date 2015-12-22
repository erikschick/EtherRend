import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DataSelectModeToggle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DataSelectModeToggle extends ActorExtended
{
    KeyListener kl = PersistentStorage.keylistener;
    
    public DataSelectModeToggle()
    {
        setImage("ICON_entitymode.png");
        PersistentStorage.selectMode = "entity";
    }
    public void actExtended() 
    {
        checkClicked();
    }    
    
    public void checkClicked()
    {
        if(kl.mouseClicked(this, null))
        {
            if(PersistentStorage.selectMode.equals("tile")){ 
                setImage("ICON_entitymode.png");
                PersistentStorage.selectMode = "entity";
            }
            else if(PersistentStorage.selectMode.equals("entity")){
                setImage("ICON_tilemode.png");
                PersistentStorage.selectMode = "tile";
            }
        }
    }
    
    public void addedToWorldExtended()
    {
    }
}