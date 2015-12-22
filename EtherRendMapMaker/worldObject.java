import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class worldObject here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class worldObject extends EtherWorldActor
{
    boolean partOfWorld = true;
    boolean lockToCursor = false;
    String saveString = null;
    tileHighlight followerHighlight = null;
    
    KeyListener keylistener = PersistentStorage.keylistener;
    
    public void etherWorldActorAct()
    {
        checkRightClick();
        followMouse();
        checkHighlight();
    }
    
    private void followMouse()
    {
        if(lockToCursor)
        {
            coordX = keylistener.getMouseX() - etherworld.cameraX;
            coordY = keylistener.getMouseY() - etherworld.cameraY;
            coordX = Math.round(coordX / PersistentStorage.tileSize) * PersistentStorage.tileSize;
            coordY = Math.round(coordY / PersistentStorage.tileSize) * PersistentStorage.tileSize;
            if(keylistener.mouseClicked(this, "left"))
            {
                placeInWorld(false);
            }
            else if(keylistener.mouseClicked(this, "right"))
            {
                placeInWorld(true);
            }
            else if(keylistener.mouseClicked(this, "middle"))
            {
                PersistentStorage.activeObject = null;
                remove = true;
            }
        }
    }
    
    private void checkHighlight()
    {
        if(PersistentStorage.activeObject == this && followerHighlight == null)
        {
            followerHighlight = new tileHighlight(this);
            etherworld.addObject(followerHighlight, getX(), getY());
        }
    }
    
    private void checkRightClick()
    {
        if(PersistentStorage.activeWindow == null)
        {
            if(partOfWorld && keylistener.mouseClicked(this, "right") && PersistentStorage.selectMode.equals(getObjectType()))
            {
                //PersistentStorage.etherworld.addObject(new expandMenu(getDataTitles(), getDataData(), getX(), getY()), getX(), getY());
                PersistentStorage.etherworld.addObject(new expandMenu(this, getX(), getY()), getX(), getY());
            }
        }
    }
     
    public void setData(List<String> data)
    {
    }
    
    public List getDataTitles()
    {
        return new ArrayList();
    }
    
    public List getDataData()
    {
        return new ArrayList();
    }
    
    private void placeInWorld(boolean anotherObject)
    {
        lockToCursor = false;
        partOfWorld = true;
        if(PersistentStorage.activeObject != null) PersistentStorage.activeObject.nowPartOfWorld();
        List<worldObject> objectsInWorld = etherworld.getObjects(worldObject.class);
        for(int i = 0; i < objectsInWorld.size(); i++)
        {
            worldObject currentObj = (worldObject) objectsInWorld.get(i);
            if(currentObj != this)
            {
                if(currentObj.partOfWorld && currentObj.coordX == this.coordX && currentObj.coordY == this.coordY)
                {
                    if(currentObj.getObjectType() == getObjectType())
                    {
                        currentObj.remove = true;
                    }
                }
            }
        }
        PersistentStorage.activeObject = null;
        if(anotherObject)
        {
            try{ PersistentStorage.activeObject = getClass().newInstance(); }
            catch(Exception e) { System.err.println("Exception: " + e.getMessage());}
            if(PersistentStorage.activeObject != null)
            {
                PersistentStorage.activeObject.lockToCursor = true;
                etherworld.addObject(PersistentStorage.activeObject, getX(), getY());
            }
        }
        if(saveString == null)
        {
            remove = true;
        }
    }
    
    public void nowPartOfWorld()
    {
    }
    
    public String saveObject()
    {
        if(partOfWorld && saveString != null)
        {
            return saveString + "," + (int)(coordX / PersistentStorage.tileSize) + "," + (int)(coordY / PersistentStorage.tileSize) + getExtraSaveData();
        }
        return null;
    }
    
    public String getExtraSaveData()
    {
        //overwrite this method to add more save data
        return "";
    }
    
    public abstract String getObjectType();
}
