import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class ENTITY_spawnPoint here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ENTITY_spawnPoint extends PassableEntity
{
    boolean visible = true;
    //public void entityAct()
    boolean spawnExecuted = false;
    
    public void actExtended()
    {
        super.actExtended();
        if(!spawnExecuted)
        {
            if(!remove && partOfWorld && PersistentStorage.spawnPointEnabled)
            {
                PersistentStorage.setPlayerCoords(coordX, coordY);
            }
            spawnExecuted = true;
        }
    }
    
    public void setData(List data)
    {
        if(data.get(0).equals("visible"))
        {
            visible = true;
            setImage("ENTITY_spawnPoint.png");
        }
        else if(data.get(0).equals("invisible"))
        {
            visible = false;
            getImage().clear();
        }
    }
    
    public void addedToEtherworld()
    {
        mortal = false;
        /*if(!remove && partOfWorld && PersistentStorage.spawnPointEnabled)
        {
            System.out.println("should be at spot");
            PersistentStorage.setPlayerCoords(coordX, coordY);
        }*/
    }
    
    /*public int getDamageRefresh()
    {
        return 10;
    }*/
}
