import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.awt.Frame;
import java.io.*;
/**
 * Write a description of class loadButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class loadButton extends clickableButton
{
    public void mouseClickAction()
    {
        JFileChooser fc = new JFileChooser();
        File directory = null;
        File currentPath = null;
        if(PersistentStorage.currentFile == null)
        {
            directory = new File(".");
            try { currentPath = new File(directory.getCanonicalPath()); }
            catch(Exception e) {}
        }
        else
        {
            directory = new File(PersistentStorage.currentFile);
            try { currentPath = new File(directory.getCanonicalPath()); }
            catch(Exception e) {}
        }
        
        if(currentPath != null) fc.setCurrentDirectory(currentPath);
        Frame fcframe = new Frame();
        fc.setFileFilter(new javax.swing.filechooser.FileFilter()
            {   public boolean accept(File f)
                {
                    if(f.isDirectory()) return true;
                    String[] extension = {".ethrmap"};
                    for(int i = 0; i < extension.length; i++)
                    {
                        if(f.getName().toLowerCase().endsWith(extension[i])) return true;
                    }
                    return false;
                }
                public String getDescription()
                {
                    return "ethrmap files";
                }
            });
        int returnVal = fc.showOpenDialog(fcframe);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
            File selFile = fc.getSelectedFile();
            PersistentStorage.currentFile = selFile.toString();
            PersistentStorage.etherworld.loadWorld(PersistentStorage.currentFile);
        }
    }
}
