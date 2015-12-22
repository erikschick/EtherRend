import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
public class ENTITY_portal extends Entity
{
    String destWorld;
    int destX;
    int destY;
    int r; int g; int b;
    int teleThreshold;
    public void setData(List<String> data)
    {
        destWorld = data.get(0);
        teleThreshold = Integer.parseInt(data.get(1));
        destX = Integer.parseInt(data.get(2));
        destY = Integer.parseInt(data.get(3));
        r = Integer.parseInt(data.get(4));
        g = Integer.parseInt(data.get(5));
        b = Integer.parseInt(data.get(6));
        GreenfootImage img = new GreenfootImage(50, 50);
        img.setColor(new Color(r, g, b));
        img.fill();
        setImage(ImageWorker.applyAlphaMap(img, new GreenfootImage("ENTITY_portal.png")));
    }
    
    public String getExtraSaveData()
    {
        StringBuffer dataString = new StringBuffer();
        dataString.append("," + destWorld);
        dataString.append("," + teleThreshold);
        dataString.append("," + destX);
        dataString.append("," + destY);
        dataString.append("," + r);
        dataString.append("," + g);
        dataString.append("," + b);
        
        return dataString.toString();
    }
    
    public List getDataTitles()
    {
        List<String> dataList = new ArrayList();
        dataList.add("Dest World");
        dataList.add("Teleport Threshold");
        dataList.add("Dest X");
        dataList.add("Dest Y");
        dataList.add("R");
        dataList.add("G");
        dataList.add("B");
        return dataList;
    }
    
    public List getDataData()
    {
        List<String> dataList = new ArrayList();
        dataList.add(destWorld);
        dataList.add("" + teleThreshold);
        dataList.add("" + destX);
        dataList.add("" + destY);
        dataList.add("" + r);
        dataList.add("" + g);
        dataList.add("" + b);
        return dataList;
    }
    
    public void addedToEtherworld()
    {
        saveString = "ENTITY_portal";
        if(partOfWorld)
        {
        }
    }
}
