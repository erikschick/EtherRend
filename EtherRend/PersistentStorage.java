import greenfoot.*;
public class PersistentStorage  
{
    //this class just holds data
    //static KeyListener keylistener = new KeyListener();
    static KeyMonitor keymonitor = new KeyMonitor();
    static boolean gamePaused = false;
    
    static EtherWorld etherworld = null;
    
    static int tileSize = 50;
    
    static boolean spawnPointEnabled = true;
    
    static GreenfootImage canvasImg = new GreenfootImage(800, 600);
    static staticCanvas staticcanvas = new staticCanvas(canvasImg);
    
    static ENTITY_player player = new ENTITY_player();
    static double playerDamageCooldown = 0;
    static double playerPresence = 100;
    
    static TargetSelector playerTargetSelector = new TargetSelector();
    static EtherWorldActor playerTarget = null;
    
    static EtherWorldActor cameraTarget = player;
    
    public static void setWorld(EtherWorld e)
    {
        etherworld = e;
    }
    
    public static void setPlayerCoords(double x, double y)
    {
        //player.mapCoordX = x;
        //player.mapCoordY = y;
        //player.coordX = x;
        //player.coordY = y;
        player.setCoords(x, y);
    }
    
    /*public static void setPlayerOffset(double x, double y)
    {
        player.offsetX = x;
        player.offsetY = y;
    }*/
    
    public static void syncPlayer(ENTITY_player p)
    {
        player = p;
        player.setPresence(playerPresence);
    }
    
    public static ENTITY_player getSyncedPlayer(){return player;}
    
    /*public static void damagePlayer(double amount)
    {
        if(playerDamageCooldown > 0)
        {
            playerDamageCooldown = playerDamageCooldown - 1;
        }
        if(PersistentStorage.playerDamageCooldown <= 0)
        {
            playerPresence = playerPresence - amount;
            playerDamageCooldown = 30;
        }
    }*/
}
