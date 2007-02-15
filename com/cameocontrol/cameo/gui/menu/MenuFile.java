/************************************************************************************
 *  Copyright 2006 Carleton Coffrin
 * 
 *  This file is part of Cameo.
 *  
 *  Cameo is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *  
 *  Cameo is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with Cameo; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  
 *************************************************************************************/

package com.cameocontrol.cameo.gui.menu;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenuItem;

import com.cameocontrol.cameo.gui.WindowBuilder;

public class MenuFile implements ActionListener{
	private WindowBuilder _builder;
	
	private String save = "Save Show";
	private String saveAs = "Save Show As";
	private String load = "Load Show";
	private String quit = "Quit";
	
	public MenuFile(WindowBuilder wb){_builder = wb;}
	
	public void getMenu(int x,MenuData menus)
	{
		int menuItems = 4;
		int item = 0;
		
		menus.names[x] = "File";
		menus.discriptions[x] = "Menu of file options";
		menus.keyEvents[x] = KeyEvent.VK_F;
		menus.itemsNames[x] = new String[menuItems];
		menus.itemsDiscriptions[x] = new String[menuItems];
		menus.itemsKeyEvents[x] = new int[menuItems];
		menus.itemsKeyMasks[x] = new int[menuItems];
		menus.itemsActionListeners[x] = new ActionListener[menuItems];
		
		item = 0;
		menus.itemsNames[x][item] = save;
		menus.itemsDiscriptions[x][item] = "Saves the current show to a file";
		menus.itemsKeyEvents[x][item] = KeyEvent.VK_S;
		menus.itemsKeyMasks[x][item] = ActionEvent.ALT_MASK;
		menus.itemsActionListeners[x][item] = this;
		
		item = 1;
		menus.itemsNames[x][item] = saveAs;
		menus.itemsDiscriptions[x][item] = "Saves the current show to a file";
		menus.itemsKeyEvents[x][item] = KeyEvent.VK_A;
		menus.itemsKeyMasks[x][item] = ActionEvent.ALT_MASK;
		menus.itemsActionListeners[x][item] = this;
		
		item = 2;
		menus.itemsNames[x][item] = load;
		menus.itemsDiscriptions[x][item] = "Loads a show from a file";
		menus.itemsKeyEvents[x][item] = KeyEvent.VK_O;
		menus.itemsKeyMasks[x][item] = ActionEvent.ALT_MASK;
		menus.itemsActionListeners[x][item] = this;
		
		item = 3;
		menus.itemsNames[x][item] = quit;
		menus.itemsDiscriptions[x][item] = "Closes the application";
		menus.itemsKeyEvents[x][item] =KeyEvent.VK_Q;
		menus.itemsKeyMasks[x][item] = ActionEvent.CTRL_MASK;
		menus.itemsActionListeners[x][item] = this;
	}
	
	public void actionPerformed(ActionEvent event) 
    {
		JMenuItem source = (JMenuItem)(event.getSource());
		
		if(source.getText().compareTo(save) == 0) {_builder.buildSave();} 
		else if(source.getText().compareTo(saveAs) == 0) {_builder.buildSaveDiag();}
		else if(source.getText().compareTo(load) == 0) {_builder.buildLoadDiag();}
		else if(source.getText().compareTo(quit) == 0) {_builder.quitApp();}
		/*
        JMenuItem source = (JMenuItem)(e.getSource());
        
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        
       if(source.getText().compareTo(menus.menuItemsNames[0][0]) == 0)
       {
	       	fileChooser.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
	       		
      		int returnVal = fileChooser.showSaveDialog(panel);

            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                //This is where a real application would open the file.

               // gUtil.SaveGraph(file.getPath());
                
                JOptionPane.showMessageDialog(panel, file.getName() + " Saved.");
                
            } 
            else 
            {
            		//JOptionPane.showMessageDialog(frame,"Save command cancelled by user.");
            }

       }
       else if (source.getText().compareTo(menuItemsNames[0][1]) == 0)
       {
	       	fileChooser.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
	       	
       		int returnVal = fileChooser.showOpenDialog(panel);

            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File file = fileChooser.getSelectedFile();
                //This is where a real application would open the file.
                //JOptionPane.showMessageDialog(frame,"Opening: " + file.getName() + ".");
        			
                	//gUtil.LoadGraph(file.getPath());
    				
            } 
            else 
            {
            		//JOptionPane.showMessageDialog(frame,"Open command cancelled by user.");
            }
       }	
       */
    }
}
