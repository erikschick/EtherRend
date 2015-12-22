import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
public class objectSidebar extends ActorExtended
{
    List<worldObject> tileOptions = Arrays.asList(new worldObject[] {
        new TILE_water(), new TILE_dirt(), new TILE_rock(), new TILE_grass(), new TILE_null()
    });
    List<worldObject> entityOptions = Arrays.asList(new worldObject[] {
        new ENTITY_soulColumn(), new ENTITY_spawnPoint(), new ENTITY_portal(), new ENTITY_null()
    });
    List<worldObject> objOptions = new ArrayList();
    
    GreenfootImage img = new GreenfootImage(800, 600);
    int xOffset = 0;
    int yOffset = 0;
    public objectSidebar(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
    
    public void addedToWorldExtended()
    {
        objOptions.addAll(tileOptions);
        objOptions.addAll(entityOptions);
        setImage("Blank.png");
        int tileCount = 0;
        int entityCount = 0;
        for(int i = 0; i < objOptions.size(); i++)
        {
            objOptions.get(i).cameraAffected = false;
            objOptions.get(i).partOfWorld = false;
            objOptions.get(i).getImage().scale((PersistentStorage.tileSize / 2), (PersistentStorage.tileSize / 2));
            if(objOptions.get(i).getObjectType() == "tile")
            {
                getWorld().addObject(objOptions.get(i), xOffset, tileCount * (PersistentStorage.tileSize / 2) + tileCount * 2 + yOffset);
                tileCount++;
            }
            else if(objOptions.get(i).getObjectType() == "entity")
            {
                getWorld().addObject(objOptions.get(i), xOffset + (PersistentStorage.tileSize / 2) + 5, entityCount * (PersistentStorage.tileSize / 2) + entityCount * 2 + yOffset);
                entityCount++;
            }
        }
        drawStaticTiles();
    }
    
    public void drawStaticTiles()
    {
        for(worldObject o : objOptions)
        {
            GreenfootImage i = o.getImage();
            img.drawImage(i, o.getX() - i.getWidth()/2, o.getY() - i.getHeight()/2);
            o.getImage().clear();
        }
        setImage(img);
    }
    
    public void actExtended() 
    {
        checkTileSelect();
    }
    
    public void checkTileSelect()
    {
        if(objOptions != null)
        {
            for(int i = 0; i < objOptions.size(); i++)
            {
                if(keylistener.mouseClicked(objOptions.get(i), "left"))
                {
                    try{ PersistentStorage.activeObject = objOptions.get(i).getClass().newInstance(); }
                    catch(Exception e) {}
                    PersistentStorage.activeObject.lockToCursor = true;
                    getWorld().addObject(PersistentStorage.activeObject, 0, 0);
                }
            }
        }
    }
}
