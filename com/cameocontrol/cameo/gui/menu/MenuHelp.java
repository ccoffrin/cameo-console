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

public class MenuHelp implements ActionListener {

	private WindowBuilder _builder;
	private String about = "About";
	
	public MenuHelp(WindowBuilder wb){_builder = wb;}
	
	public void getMenu(int x,MenuData menus)
	{
		int menuItems = 1;
		int item = 0;
		

		menus.names[x] = "Help";
		menus.discriptions[x] = "Menu of Cameo Information";
		menus.keyEvents[x] = KeyEvent.VK_H;
		menus.itemsNames[x] = new String[menuItems];
		menus.itemsDiscriptions[x] = new String[menuItems];
		menus.itemsKeyEvents[x]= new int[menuItems];
		menus.itemsKeyMasks[x] = new int[menuItems];
		menus.itemsActionListeners[x] = new ActionListener[menuItems];
		
		item = 0;
		menus.itemsNames[x][item] = about;
		menus.itemsDiscriptions[x][item] = "Details about the origin of Cameo";
		menus.itemsKeyEvents[x][item] = -1;
		menus.itemsKeyMasks[x][item] = -1;
		menus.itemsActionListeners[x][item] = this;
	}
	
	public void actionPerformed(ActionEvent event) 
    {
		JMenuItem source = (JMenuItem)(event.getSource());
		
		if(source.getText().compareTo(about) == 0){
			_builder.buildAbout();
	    }
    }
}
