import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * Write a description of class expandMenu here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class expandMenu extends ActorExtended// implements WindowListener
{
    int width = 100;
    int height = 50;
    int heightPerItem = 50;
    int x = 0;
    int y = 0;
    JFrame frame;
    
    expandMenu thisobj = this;
    
    worldObject parent;
    List<String> dataTitles;
    List<String> dataData;
    
    JTextField textField[];
    JLabel label[];
    
    List<String> returnData = new ArrayList();
    
    KeyListener keylistener = PersistentStorage.keylistener;
    
    public expandMenu(worldObject parent, int x, int y)
    {
        PersistentStorage.activeWindow = this;
        this.x = x;
        this.y = y;
        this.dataTitles = parent.getDataTitles();
        this.dataData = parent.getDataData();
        this.parent = parent;
        getImage().clear();
        setImage(getImage());
        if(dataTitles != null && dataData != null)
        {
            if(dataTitles.isEmpty() || dataData.isEmpty())
            {
                remove = true;
            }
            else
            {
                createGUI();
            }
        }
    }
    
    private void createGUI() {
        JFrame frame = new JFrame("TileData");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        textField = new JTextField[dataTitles.size()];
        label = new JLabel[dataTitles.size()];
        for(int i = 0; i < dataTitles.size(); i++)
        {
            label[i] = new JLabel(" " + dataTitles.get(i));
            c.fill = GridBagConstraints.HORIZONTAL;
            label[i].setHorizontalTextPosition(JLabel.CENTER);
            c.ipadx = 6;
            c.ipady = 2;
            c.gridx = 1;
            c.gridy = i;
            frame.add(label[i], c);
            
            textField[i] = new JTextField(dataData.get(i), 20);
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipadx = 6;
            c.ipady = 2;
            c.gridx = 2;
            c.gridy = i;
            frame.add(textField[i], c);
        }
        
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowListener(){
            public void windowClosed(WindowEvent e)
            {
                for(int i = 0; i < textField.length; i++)
                {
                    returnData.add(textField[i].getText());
                }
                if(parent != null)
                {
                    parent.setData(returnData);
                }
                PersistentStorage.activeWindow = null;
                remove = true;
            }
            public void windowClosing(WindowEvent e){}
            public void windowDeactivated(WindowEvent e){}
            public void windowActivated(WindowEvent e){}
            public void windowDeiconified(WindowEvent e){}
            public void windowIconified(WindowEvent e){}
            public void windowOpened(WindowEvent e){}
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public void addedToWorldExtended()
    {
        setRealLocation(x + (width/2), y + (height/2));
    }
    
    public void actExtended()
    {
        //checkMouse();
    }    
    
    public void checkMouse()
    {
        if(!keylistener.mouseOver(this) && keylistener.keysPressed.contains("mouseButtonLeft"))
        {
            remove = true;
        }
    }
}
