import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;

public class EtherWorld extends World
{
    List mapTiles = new ArrayList();
    KeyListener keylistener = PersistentStorage.keylistener;
    
    //String[] tileTypes = {"TILE_grass", "TILE_dirt", "TILE_rock", "TILE_water", "ENTITY_soulColumn", "ENTITY_spawnPoint"};
    //Class[] tileClasses = {TILE_grass.class, TILE_dirt.class, TILE_rock.class, TILE_water.class, ENTITY_soulColumn.class, ENTITY_spawnPoint.class};
    
    List<String> tileTypes = Arrays.asList(new String[] {
        "TILE_grass", "TILE_dirt", "TILE_rock", "TILE_water", "ENTITY_soulColumn",
        "ENTITY_spawnPoint", "ENTITY_portal"
    });
    List<Class> tileClasses = Arrays.asList(new Class[] {
        TILE_grass.class, TILE_dirt.class, TILE_rock.class, TILE_water.class, ENTITY_soulColumn.class,
        ENTITY_spawnPoint.class, ENTITY_portal.class
    });
    
    double cameraX = 400; double cameraY = 300;
    double cameraSpeed = 2;
    
    String KEY_LEFT = "left";
    String KEY_RIGHT = "right";
    String KEY_UP = "up";
    String KEY_DOWN = "down";
    
    
    public EtherWorld()
    {
        super(800, 600, 1, false);
        
        PersistentStorage.etherworld = this;
        Greenfoot.setSpeed(55);
        Greenfoot.start();
        loadWorld(PersistentStorage.currentFile);
        
        addObject(new objectSidebar(50, 50), 400, 300);
        
        saveButton savebutton = new saveButton();
        savebutton.getImage().scale(60, 30);
        addObject(savebutton, 40, 570);
        loadButton loadbutton = new loadButton();
        loadbutton.getImage().scale(60, 30);
        addObject(loadbutton, 110, 570);
        addObject(new DataSelectModeToggle(), 30, 400);
        addObject(new CoordDisplay(), 0, 0);
        
        if(PersistentStorage.currentFile == null)
        {
            addObject(new ENTITY_spawnPoint(), 0, 0);
        }
        
        setPaintOrder(objectSidebar.class, DataSelectModeToggle.class, notification.class, CoordDisplay.class, menuItem.class, expandMenu.class, staticCanvas.class, clickableButton.class, tileHighlight.class, Entity.class, Tile.class);
    }
    
    public void act()
    {
        keylistener.act();
        checkKeys();
    }
    
    public void checkKeys()
    {
        if(keylistener.isKeyDown(KEY_LEFT))
        {
            cameraX = cameraX + cameraSpeed;
        }
        if(keylistener.isKeyDown(KEY_RIGHT))
        {
            cameraX = cameraX - cameraSpeed;
        }
        if(keylistener.isKeyDown(KEY_DOWN))
        {
            cameraY = cameraY - cameraSpeed;
        }
        if(keylistener.isKeyDown(KEY_UP))
        {
            cameraY = cameraY + cameraSpeed;
        }
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
            mapTiles = keylistener.readText(worldname);
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
    
    public void saveWorld(Boolean newFile)
    {
        List<worldObject> worldObjects = getObjects(worldObject.class);
        //List<String> stringsToSave = new ArrayList();
        String[] stringsToSave = new String[worldObjects.size()];
        for(int i = 0; i < worldObjects.size(); i++)
        {
            //stringsToSave.add(worldObjects.get(i).saveObject());
            stringsToSave[i] = worldObjects.get(i).saveObject();
        }
        if(newFile)
        {
            FileSaver.newFile(stringsToSave);
        }
        else
        {
            FileSaver.saveFile(PersistentStorage.currentFile, stringsToSave);
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
        cameraX = xtmp + 400;
        cameraY = ytmp + 300;
    }
}
