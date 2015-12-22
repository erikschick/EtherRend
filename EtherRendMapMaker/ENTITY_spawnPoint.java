import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.lang.StringBuffer;
import java.util.ArrayList;
public class ENTITY_spawnPoint extends Entity
{
    boolean visible = true;
    public void addedToEtherworld()
    {
        saveString = "ENTITY_spawnPoint";
        if(partOfWorld && PersistentStorage.spawnPointEnabled)
        {
            PersistentStorage.etherworld.setCamera(-coordX, -coordY);
            PersistentStorage.spawnPointEnabled = false;
        }
    }
    
    public void setData(List<String> data)
    {
        if(data.contains("visible"))
        {
            visible = true;
            setImage("ENTITY_spawnPoint.png");
        }
        else if(data.contains("invisible"))
        {
            visible = false;
            getImage().clear();
        }
    }
    
    public String getExtraSaveData()
    {
        StringBuffer dataString = new StringBuffer();
        if(visible) dataString.append(",visible");
        else if(!visible) dataString.append(",invisible");
        
        return dataString.toString();
    }
    
    public void nowPartOfWorld()
    {
        /*List<worldObject> objectsInWorld = etherworld.getObjects(ENTITY_spawnPoint.class);
        for(int i = 0; i < objectsInWorld.size(); i++)
        {
            worldObject currentObj = (worldObject) objectsInWorld.get(i);
            if(currentObj != this)
            {
                if(currentObj.partOfWorld)
                {
                    currentObj.remove = true;
                }
            }
        }*/
    }
    
    public List getDataTitles()
    {
        List<String> dataList = new ArrayList();
        dataList.add("Visibility");
        return dataList;
    }
    
    public List getDataData()
    {
        List<String> dataList = new ArrayList();
        if(visible) dataList.add("visible"); else dataList.add("invisible");
        return dataList;
    }
}
