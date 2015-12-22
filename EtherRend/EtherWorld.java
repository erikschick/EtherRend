import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import greenfoot.core.WorldHandler;
public class EtherWorld extends World
{
    List mapTiles = new ArrayList();
    //KeyListener keylistener = PersistentStorage.keylistener;
    KeyMonitor keymonitor = PersistentStorage.keymonitor;
    
    //String[] tileTypes = {"TILE_grass", "TILE_dirt", "TILE_rock", "TILE_water", "ENTITY_soulColumn"};
    //Class[] tileClasses = {TILE_grass.class, TILE_dirt.class, TILE_rock.class, TILE_water.class, ENTITY_soulColumn.class};
    
    List<String> tileTypes = Arrays.asList(new String[] {
        "TILE_grass", "TILE_dirt", "TILE_rock", "TILE_water", "ENTITY_soulColumn",
        "ENTITY_spawnPoint", "ENTITY_portal"
    });
    List<Class> tileClasses = Arrays.asList(new Class[] {
        TILE_grass.class, TILE_dirt.class, TILE_rock.class, TILE_water.class, ENTITY_soulColumn.class,
        ENTITY_spawnPoint.class, ENTITY_portal.class
    });
    
    
/*    List<String> specialTypes = Arrays.asList(new String[] {
        "ENTITY_portal"
    });
    List<Class> specialClasses = Arrays.asList(new Class[] {
        ENTITY_portal.class
    });*/
    double cameraX = 400; double cameraY = 300;
    
    public EtherWorld()
    {
        this("worlds/default", true);
    }
    
    public EtherWorld(String worldname, boolean spawnPoint, int x, int y)
    {
        this(worldname, spawnPoint);
        PersistentStorage.setPlayerCoords(x * PersistentStorage.tileSize, y * PersistentStorage.tileSize);
    }
    
    public EtherWorld(String worldname, boolean spawnPoint)
    {
        super(800, 600, 1, false);        
        Greenfoot.start();
        PersistentStorage.etherworld = this;
        PersistentStorage.setWorld(this);
        Greenfoot.setSpeed(55);
        loadWorld(worldname);
        addObject(PersistentStorage.player, 0, 0);
        addObject(new testEntity(), 0, 0);
        
        System.out.println("when an entity gets bounced off an object, make it get pushed more to whatever side it is facing");
        setPaintOrder(markertmp.class, staticCanvas.class, TargetSelector.class, Particle.class, ENTITY_player.class, basicShot.class, Entity.class, ENTITY_spawnPoint.class, Tile.class);
    }
    
    public void act()
    {
        cameraFollow();
        keymonitor.act();
    }
    
    public void loadWorld(String worldname)
    {
        loadWorld(worldname, true);
    }
    
    public void loadWorld(String worldname, boolean spawnPoint)
    {
        if(worldname != null)
        {
            if(spawnPoint) PersistentStorage.spawnPointEnabled = true;
            else PersistentStorage.spawnPointEnabled = false;
            clearWorld();
            if(!worldname.endsWith(".ethrmap"))
            {
                worldname = worldname + ".ethrmap";
            }
            mapTiles = FileIOHandler.readText(worldname);
            for(int i = 0; i < mapTiles.size(); i++)
            {
                if(mapTiles.get(i) != null)
                {
                    try
                    {
                        String lineText = (String)mapTiles.get(i);
                        String bufLineText = lineText;
                        
                        String objType = lineText.substring(0, lineText.indexOf(','));
                        bufLineText = lineText.substring(objType.length() + 1);
                        if(tileTypes.contains(objType)) addTile(objType, bufLineText);
                        //if(specialTypes.contains(objType)) addSpecial(objType, bufLineText);
                    }
                    catch(Exception e) {}
                }
            }
        }
    }
    
    public void clearWorld()
    {
        List<worldObject> objectsToClear = getObjects(worldObject.class);
        for(int i = 0; i < objectsToClear.size(); i++)
        {
            if(objectsToClear.get(i).partOfWorld)
            {
                objectsToClear.get(i).remove = true;
            }
        }
    }
    
    public void addTile(String tileType, String tileData)
    {   
        String tileXst = tileData;
        tileXst = tileXst.substring(0, tileXst.indexOf(','));
        String bufLineText = tileData.substring(tileXst.length() + 1);
        tileData = bufLineText;
        String tileYst = bufLineText;
        
        String working = new String();
        if(tileYst.contains(","))
        {
            tileYst = tileYst.substring(0, tileYst.indexOf(','));
            bufLineText = tileData.substring(tileYst.length() + 1);
            working = bufLineText;
            tileData = working;
        }
        
        int tileX = Integer.parseInt(tileXst);
        int tileY = Integer.parseInt(tileYst);
        
        List<String> tileInfo = new ArrayList();
        bufLineText = bufLineText + ",";
        while(!"".equals(bufLineText))
        {  
            tileInfo.add(bufLineText.substring(0, bufLineText.indexOf(',')));  
            bufLineText = bufLineText.substring(bufLineText.indexOf(',') + 1);  
        }  
        
        if(tileTypes.indexOf(tileType) >= 0)
        {
            try {
                worldObject tileToAdd = (worldObject)(tileClasses.get(tileTypes.indexOf(tileType)).newInstance());
                addObject(tileToAdd, tileX*PersistentStorage.tileSize, tileY*PersistentStorage.tileSize);
                tileToAdd.setData(tileInfo);
                tileToAdd.partOfWorld = true;
            }
            catch(Exception e)
            {
                System.err.println("Caught Exception: " + e.getMessage());
            }
        }
    }
    
    public void setCamera(double xtmp, double ytmp)
    {
        //cameraX = xtmp + 400;
        //cameraY = ytmp + 300;
        //cameraX = xtmp - 400;
        //cameraY = ytmp - 300;
    }
    
    public void cameraFollow()
    {
        cameraX = 400 - PersistentStorage.cameraTarget.coordX;
        cameraY = 300 - PersistentStorage.cameraTarget.coordY;
    }
}
