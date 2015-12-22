import greenfoot.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

import javax.swing.*;
import greenfoot.core.WorldHandler;
import java.awt.event.*;
import java.awt.*;
/**
 * 
 * 
 */
public class KeyMonitor
{
    static List keysPressed = new ArrayList();
    greenfoot.MouseInfo mouse = Greenfoot.getMouseInfo();
    //static boolean mouseButton1 = false; static boolean mouseButton2 = false; static boolean mouseButton3 = false;
    boolean[] keyPressed = new boolean[525]; //KeyEvent.VK_ENTER
    //Object[][] key = new Object[525][2];
    List<String> keyString = new ArrayList();
    List<Integer> keyValue = new ArrayList();
    
    public KeyMonitor()
    {
        //key[KeyEvent.VK_][0] = ""; key[KeyEvent.VK_][1] = KeyEvent.VK_;
        
        /*key[KeyEvent.VK_A][0] = "a"; key[KeyEvent.VK_A][1] = KeyEvent.VK_A;
        key[KeyEvent.VK_B][0] = "b"; key[KeyEvent.VK_B][1] = KeyEvent.VK_B;
        key[KeyEvent.VK_C][0] = "c"; key[KeyEvent.VK_C][1] = KeyEvent.VK_C;
        key[KeyEvent.VK_D][0] = "d"; key[KeyEvent.VK_D][1] = KeyEvent.VK_D;
        key[KeyEvent.VK_E][0] = "e"; key[KeyEvent.VK_E][1] = KeyEvent.VK_E;
        key[KeyEvent.VK_F][0] = "f"; key[KeyEvent.VK_F][1] = KeyEvent.VK_F;
        key[KeyEvent.VK_G][0] = "g"; key[KeyEvent.VK_G][1] = KeyEvent.VK_G;
        key[KeyEvent.VK_H][0] = "h"; key[KeyEvent.VK_H][1] = KeyEvent.VK_H;
        key[KeyEvent.VK_I][0] = "i"; key[KeyEvent.VK_I][1] = KeyEvent.VK_I;
        key[KeyEvent.VK_J][0] = "j"; key[KeyEvent.VK_J][1] = KeyEvent.VK_J;
        key[KeyEvent.VK_K][0] = "k"; key[KeyEvent.VK_K][1] = KeyEvent.VK_K;
        key[KeyEvent.VK_L][0] = "l"; key[KeyEvent.VK_L][1] = KeyEvent.VK_L;
        key[KeyEvent.VK_M][0] = "m"; key[KeyEvent.VK_M][1] = KeyEvent.VK_M;
        key[KeyEvent.VK_N][0] = "n"; key[KeyEvent.VK_N][1] = KeyEvent.VK_N;
        key[KeyEvent.VK_O][0] = "o"; key[KeyEvent.VK_O][1] = KeyEvent.VK_O;
        key[KeyEvent.VK_P][0] = "p"; key[KeyEvent.VK_P][1] = KeyEvent.VK_P;
        key[KeyEvent.VK_Q][0] = "q"; key[KeyEvent.VK_Q][1] = KeyEvent.VK_Q;
        key[KeyEvent.VK_R][0] = "r"; key[KeyEvent.VK_R][1] = KeyEvent.VK_R;
        key[KeyEvent.VK_S][0] = "s"; key[KeyEvent.VK_S][1] = KeyEvent.VK_S;
        key[KeyEvent.VK_T][0] = "t"; key[KeyEvent.VK_T][1] = KeyEvent.VK_T;
        key[KeyEvent.VK_U][0] = "u"; key[KeyEvent.VK_U][1] = KeyEvent.VK_U;
        key[KeyEvent.VK_V][0] = "v"; key[KeyEvent.VK_V][1] = KeyEvent.VK_V;
        key[KeyEvent.VK_W][0] = "w"; key[KeyEvent.VK_W][1] = KeyEvent.VK_W;
        key[KeyEvent.VK_X][0] = "x"; key[KeyEvent.VK_X][1] = KeyEvent.VK_X;
        key[KeyEvent.VK_Y][0] = "y"; key[KeyEvent.VK_Y][1] = KeyEvent.VK_Y;
        key[KeyEvent.VK_Z][0] = "z"; key[KeyEvent.VK_Z][1] = KeyEvent.VK_Z;
        
        key[KeyEvent.VK_0][0] = "0"; key[KeyEvent.VK_0][1] = KeyEvent.VK_0;
        key[KeyEvent.VK_1][0] = "1"; key[KeyEvent.VK_1][1] = KeyEvent.VK_1;
        key[KeyEvent.VK_2][0] = "2"; key[KeyEvent.VK_2][1] = KeyEvent.VK_2;
        key[KeyEvent.VK_3][0] = "3"; key[KeyEvent.VK_3][1] = KeyEvent.VK_3;
        key[KeyEvent.VK_4][0] = "4"; key[KeyEvent.VK_4][1] = KeyEvent.VK_4;
        key[KeyEvent.VK_5][0] = "5"; key[KeyEvent.VK_5][1] = KeyEvent.VK_5;
        key[KeyEvent.VK_6][0] = "6"; key[KeyEvent.VK_6][1] = KeyEvent.VK_6;
        key[KeyEvent.VK_7][0] = "7"; key[KeyEvent.VK_7][1] = KeyEvent.VK_7;
        key[KeyEvent.VK_8][0] = "8"; key[KeyEvent.VK_8][1] = KeyEvent.VK_8;
        key[KeyEvent.VK_9][0] = "9"; key[KeyEvent.VK_9][1] = KeyEvent.VK_9;
        
        key[KeyEvent.VK_NUMPAD0][0] = "numpad0"; key[KeyEvent.VK_NUMPAD0][1] = KeyEvent.VK_NUMPAD0;
        key[KeyEvent.VK_NUMPAD1][0] = "numpad1"; key[KeyEvent.VK_NUMPAD1][1] = KeyEvent.VK_NUMPAD1;
        key[KeyEvent.VK_NUMPAD2][0] = "numpad2"; key[KeyEvent.VK_NUMPAD2][1] = KeyEvent.VK_NUMPAD2;
        key[KeyEvent.VK_NUMPAD3][0] = "numpad3"; key[KeyEvent.VK_NUMPAD3][1] = KeyEvent.VK_NUMPAD3;
        key[KeyEvent.VK_NUMPAD4][0] = "numpad4"; key[KeyEvent.VK_NUMPAD4][1] = KeyEvent.VK_NUMPAD4;
        key[KeyEvent.VK_NUMPAD5][0] = "numpad5"; key[KeyEvent.VK_NUMPAD5][1] = KeyEvent.VK_NUMPAD5;
        key[KeyEvent.VK_NUMPAD6][0] = "numpad6"; key[KeyEvent.VK_NUMPAD6][1] = KeyEvent.VK_NUMPAD6;
        key[KeyEvent.VK_NUMPAD7][0] = "numpad7"; key[KeyEvent.VK_NUMPAD7][1] = KeyEvent.VK_NUMPAD7;
        key[KeyEvent.VK_NUMPAD8][0] = "numpad8"; key[KeyEvent.VK_NUMPAD8][1] = KeyEvent.VK_NUMPAD8;
        key[KeyEvent.VK_NUMPAD9][0] = "numpad9"; key[KeyEvent.VK_NUMPAD9][1] = KeyEvent.VK_NUMPAD9;
        
        key[KeyEvent.VK_ENTER][0] = "enter"; key[KeyEvent.VK_ENTER][1] = KeyEvent.VK_ENTER;
        key[KeyEvent.VK_BACK_SPACE][0] = "backspace"; key[KeyEvent.VK_BACK_SPACE][1] = KeyEvent.VK_BACK_SPACE;
        key[KeyEvent.VK_TAB][0] = "tab"; key[KeyEvent.VK_TAB][1] = KeyEvent.VK_TAB;
        key[KeyEvent.VK_ESCAPE][0] = "escape"; key[KeyEvent.VK_ESCAPE][1] = KeyEvent.VK_ESCAPE;
        key[KeyEvent.VK_SPACE][0] = "space"; key[KeyEvent.VK_SPACE][1] = KeyEvent.VK_SPACE;*/
        
        //keyString.add(""); keyValue.add(KeyEvent.VK_);
        
        keyString.add("enter"); keyValue.add(KeyEvent.VK_ENTER);
        keyString.add("backspace"); keyValue.add(KeyEvent.VK_BACK_SPACE);
        keyString.add("tab"); keyValue.add(KeyEvent.VK_TAB);
        keyString.add("escape"); keyValue.add(KeyEvent.VK_ESCAPE);
        keyString.add("space"); keyValue.add(KeyEvent.VK_SPACE);
        
        keyString.add("a"); keyValue.add(KeyEvent.VK_A);
        keyString.add("b"); keyValue.add(KeyEvent.VK_B);
        keyString.add("c"); keyValue.add(KeyEvent.VK_C);
        keyString.add("d"); keyValue.add(KeyEvent.VK_D);
        keyString.add("e"); keyValue.add(KeyEvent.VK_E);
        keyString.add("f"); keyValue.add(KeyEvent.VK_F);
        keyString.add("g"); keyValue.add(KeyEvent.VK_G);
        keyString.add("h"); keyValue.add(KeyEvent.VK_H);
        keyString.add("i"); keyValue.add(KeyEvent.VK_I);
        keyString.add("j"); keyValue.add(KeyEvent.VK_J);
        keyString.add("k"); keyValue.add(KeyEvent.VK_K);
        keyString.add("l"); keyValue.add(KeyEvent.VK_L);
        keyString.add("m"); keyValue.add(KeyEvent.VK_M);
        keyString.add("n"); keyValue.add(KeyEvent.VK_N);
        keyString.add("o"); keyValue.add(KeyEvent.VK_O);
        keyString.add("p"); keyValue.add(KeyEvent.VK_P);
        keyString.add("q"); keyValue.add(KeyEvent.VK_Q);
        keyString.add("r"); keyValue.add(KeyEvent.VK_R);
        keyString.add("s"); keyValue.add(KeyEvent.VK_S);
        keyString.add("t"); keyValue.add(KeyEvent.VK_T);
        keyString.add("u"); keyValue.add(KeyEvent.VK_U);
        keyString.add("v"); keyValue.add(KeyEvent.VK_V);
        keyString.add("w"); keyValue.add(KeyEvent.VK_W);
        keyString.add("x"); keyValue.add(KeyEvent.VK_X);
        keyString.add("y"); keyValue.add(KeyEvent.VK_Y);
        keyString.add("z"); keyValue.add(KeyEvent.VK_Z);
        
        keyString.add("0"); keyValue.add(KeyEvent.VK_0);
        keyString.add("1"); keyValue.add(KeyEvent.VK_1);
        keyString.add("2"); keyValue.add(KeyEvent.VK_2);
        keyString.add("3"); keyValue.add(KeyEvent.VK_3);
        keyString.add("4"); keyValue.add(KeyEvent.VK_4);
        keyString.add("5"); keyValue.add(KeyEvent.VK_5);
        keyString.add("6"); keyValue.add(KeyEvent.VK_6);
        keyString.add("7"); keyValue.add(KeyEvent.VK_7);
        keyString.add("8"); keyValue.add(KeyEvent.VK_8);
        keyString.add("9"); keyValue.add(KeyEvent.VK_9);
        
        keyString.add("numpad0"); keyValue.add(KeyEvent.VK_NUMPAD0);
        keyString.add("numpad1"); keyValue.add(KeyEvent.VK_NUMPAD1);
        keyString.add("numpad2"); keyValue.add(KeyEvent.VK_NUMPAD2);
        keyString.add("numpad3"); keyValue.add(KeyEvent.VK_NUMPAD3);
        keyString.add("numpad4"); keyValue.add(KeyEvent.VK_NUMPAD4);
        keyString.add("numpad5"); keyValue.add(KeyEvent.VK_NUMPAD5);
        keyString.add("numpad6"); keyValue.add(KeyEvent.VK_NUMPAD6);
        keyString.add("numpad7"); keyValue.add(KeyEvent.VK_NUMPAD7);
        keyString.add("numpad8"); keyValue.add(KeyEvent.VK_NUMPAD8);
        keyString.add("numpad9"); keyValue.add(KeyEvent.VK_NUMPAD9);
        
        JPanel panel = WorldHandler.getInstance().getWorldCanvas();
        
        panel.addKeyListener(new KeyListener() {
            
            public void keyPressed(KeyEvent e) { keyPressed[e.getKeyCode()] = true;}

            public void keyReleased(KeyEvent e) { keyPressed[e.getKeyCode()] = false;}
            
            public void keyTyped(KeyEvent e) {}
        });
        panel.setFocusTraversalKeysEnabled(false);
        panel.setFocusable(true);
    }
    
    public int getKeyValue(String key)
    {
        return keyValue.get(keyString.indexOf(key));
    }
    
    public void act()
    {
        refreshMouseClick();
    }
    
    public boolean isKeyDown(String k)
    {
        //if(Greenfoot.isKeyDown(k))
        if(keyPressed[getKeyValue(k)])
        {
            return true;
        }
        return false;
    }
    
    public boolean isKeyJustPressed(String k)
    {
        //if(!keysPressed.contains(k) && Greenfoot.isKeyDown(k))
        if(!keysPressed.contains(k) && keyPressed[getKeyValue(k)])
        {
            keysPressed.add(k);
            return true;
        }
        if(keysPressed.contains(k) && !keyPressed[getKeyValue(k)])
        {
            keysPressed.remove(k);
            return false;
        }
        return false;
    }
    
    /*public List readText(String reqfile)// throws IOException
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
    }*/
    
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
        if(mouse != null)
        {
            if(Greenfoot.mousePressed(null) && !keysPressed.contains("mouseButton"))
            {
                keysPressed.add("mouseButton");
            }
            else if(keysPressed.contains("mouseButton"))
            {
                keysPressed.remove("mouseButton");
            }
        }
    }
    
    public boolean mouseClicked(Actor actorTemp, String button)
    {
        if(keysPressed.contains("mouseButton"))
        {
            int actorXradius = actorTemp.getImage().getWidth() / 2;
            int actorYradius = actorTemp.getImage().getHeight() / 2;
            if(mouse.getX() > actorTemp.getX() - actorXradius && mouse.getX() < actorTemp.getX() + actorXradius && mouse.getY() > actorTemp.getY() - actorYradius && mouse.getY() < actorTemp.getY() + actorYradius)
            {
                if(button.equals("left") && mouse.getButton() == 1)
                {
                    return true;
                }
                if(button.equals("right") && mouse.getButton() == 3)
                {
                    return true;
                }
                if(button.equals("middle") && mouse.getButton() == 2)
                {
                    return true;
                }
            }
        }
        return false;
    }
}