import greenfoot.*;
public class PersistentStorage  
{
    //this class just holds data
    static KeyListener keylistener = new KeyListener();
    static boolean gamePaused = false;
    static EtherWorld etherworld;
    
    static int tileSize = 50;
    
    static Object activeWindow = null;
    
    static boolean spawnPointEnabled = true;
    
    static String selectMode = "tile";
    
    static String currentFile = null;
    
    static worldObject activeObject;
}
