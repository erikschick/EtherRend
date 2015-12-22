import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
public class CoordDisplay extends ActorExtended
{
    KeyListener keylistener = PersistentStorage.keylistener;
    public void addedToWorldExtended()
    {
        setImage(new GreenfootImage(140, 40));
        getImage().setColor(Color.BLACK);
        getImage().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        setRealLocation(730, 20);
    }
    
    public void actExtended() 
    {
        drawCoords();
    }    
    
    public void drawCoords()
    {
        GreenfootImage img = getImage();
        img.clear();
        double coordX = keylistener.getMouseX() - PersistentStorage.etherworld.cameraX;
        double coordY = keylistener.getMouseY() - PersistentStorage.etherworld.cameraY;
        coordX = Math.round(coordX / PersistentStorage.tileSize);
        coordY = Math.round(coordY / PersistentStorage.tileSize);
        img.drawString("X: " + (int)coordX + " Y: " + (int)coordY, 4, 38);
    }
}
