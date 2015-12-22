import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.awt.*;
/**
 * 
 * 
 */
public class KeyListener
{
    static List keysPressed = new ArrayList();
    greenfoot.MouseInfo mouse = Greenfoot.getMouseInfo();
    //static boolean mouseButton1 = false; static boolean mouseButton2 = false; static boolean mouseButton3 = false;
    
    public void act()
    {
        refreshMouseClick();
    }
    
    public boolean isKeyDown(String k)
    {
        if(Greenfoot.isKeyDown(k))
        {
            return true;
        }
        return false;
    }
    
    public boolean isKeyJustPressed(String k)
    {
        if(!keysPressed.contains(k) && Greenfoot.isKeyDown(k))
        {
            keysPressed.add(k);
            return true;
        }
        if(keysPressed.contains(k) && !Greenfoot.isKeyDown(k))
        {
            keysPressed.remove(k);
            return false;
        }
        return false;
    }
    
    public List readText(String reqfile)// throws IOException
    {
        FileInputStream fis = null;
        BufferedReader r = null;
        List lines = new ArrayList();
        try
        {
            if(reqfile != null)
            {
                fis = new FileInputStream(reqfile);
                r = new BufferedReader(new InputStreamReader(fis));
            
                String linetmp;
                while((linetmp = r.readLine()) != null)
                {
                    lines.add(linetmp);
                }
            }
        }
        catch(IOException e)
        {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return lines;
    }  
      
    public String readText(String reqfile, int row)// throws IOException
    {  
        if(row < readText(reqfile).size())
        {
            return (String)readText(reqfile).get(row-1);
        }
        else
        return null;
    }
    
    public static int fontSizeToFit(String textToDisplay, int width, int height, String fontName, int fontStyle)
    {
        for(int i = 0; i < 100; i++)
        {
            if(40 - i > 0)
            {
                Graphics img = new GreenfootImage(1,1).getAwtImage().getGraphics();
                img.setFont(new Font(fontName, fontStyle, 40 - i));
                FontMetrics fm = img.getFontMetrics();
                if(fm.stringWidth(textToDisplay) <= width && fm.getHeight() <= height)
                {
                    return 40 - i;
                }
            }
        }
        return 4;
    }
    
    public static int getFontHeight(Font f)
    {
        Graphics img = new GreenfootImage(1,1).getAwtImage().getGraphics();
        img.setFont(f);
        FontMetrics fm = img.getFontMetrics();
        return fm.getHeight();
    }
    
    public static int getMouseX()
    {
        greenfoot.MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            return mouse.getX();
        }
        return 0;
    }
    
    public static int getMouseY()
    {
        greenfoot.MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            return mouse.getY();
        }
        return 0;
    }
    
    public void refreshMouseClick()
    {
        mouse = Greenfoot.getMouseInfo();
        /*if(mouse != null)
        {
            if(Greenfoot.mousePressed(null) && !keysPressed.contains("mouseButton"))
            {
                keysPressed.add("mouseButton");
            }
            else if(keysPressed.contains("mouseButton"))
            {
                keysPressed.remove("mouseButton");
            }
        }*/
        if(mouse != null)
        {
            if(Greenfoot.mousePressed(null) && !keysPressed.contains("mouseButtonLeft") && mouse.getButton() == 1)
            {
                keysPressed.add("mouseButtonLeft");
            }
            else if(keysPressed.contains("mouseButtonLeft"))
            {
                keysPressed.remove("mouseButtonLeft");
            }
            if(Greenfoot.mousePressed(null) && !keysPressed.contains("mouseButtonMiddle") && mouse.getButton() == 2)
            {
                keysPressed.add("mouseButtonMiddle");
            }
            else if(keysPressed.contains("mouseButtonMiddle"))
            {
                keysPressed.remove("mouseButtonMiddle");
            }
            if(Greenfoot.mousePressed(null) && !keysPressed.contains("mouseButtonRight") && mouse.getButton() == 3)
            {
                keysPressed.add("mouseButtonRight");
            }
            else if(keysPressed.contains("mouseButtonRight"))
            {
                keysPressed.remove("mouseButtonRight");
            }
        }
    }
    
    public boolean mouseOver(Actor actorTemp)
    {
        if(mouse != null)
        {
            int actorXradius = actorTemp.getImage().getWidth() / 2;
            int actorYradius = actorTemp.getImage().getHeight() / 2;
            if(mouse.getX() > actorTemp.getX() - actorXradius && mouse.getX() < actorTemp.getX() + actorXradius && mouse.getY() > actorTemp.getY() - actorYradius && mouse.getY() < actorTemp.getY() + actorYradius)
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseClicked(Actor actorTemp, String button)
    {
        if(mouseOver(actorTemp))
        {
            if(button == null)
            {
                if(keysPressed.contains("mouseButtonLeft") || keysPressed.contains("mouseButtonMiddle") || keysPressed.contains("mouseButtonRight"))
                {
                    return true;
                }
            }
            else if(button != null)
            {
                if(button.equals("left"))
                {
                    if(keysPressed.contains("mouseButtonLeft"))
                    {
                        return true;
                    }
                }
                if(button.equals("middle"))
                {
                    if(keysPressed.contains("mouseButtonMiddle"))
                    {
                        return true;
                    }
                }
                if(button.equals("right"))
                {
                    if(keysPressed.contains("mouseButtonRight"))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}