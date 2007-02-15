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

public class MenuSettings implements ActionListener{
	private WindowBuilder _builder;
	private String _patch = "Patch";
	private String _shows = "Show Settings";
	private String _prefs = "Cameo Preferences";
	private String _clear = "Clear Functions";
	
	public MenuSettings(WindowBuilder wb){_builder = wb;}
	
	public void getMenu(int x,MenuData menus)
	{
		int menuItems = 5;
		int item = 0;
		
		menus.names[x] = "Settings";
		menus.discriptions[x] = "Menu of Show settings";
		menus.keyEvents[x] = KeyEvent.VK_S;
		menus.itemsNames[x] = new String[menuItems];
		menus.itemsDiscriptions[x] = new String[menuItems];
		menus.itemsKeyEvents[x]= new int[menuItems];
		menus.itemsKeyMasks[x] = new int[menuItems];
		menus.itemsActionListeners[x] = new ActionListener[menuItems];
		
		item = 0;
		menus.itemsNames[x][item] = _shows;
		menus.itemsDiscriptions[x][item] = "Settings for the current show file";
		menus.itemsKeyEvents[x][item] = -1;
		menus.itemsKeyMasks[x][item] = -1;
		menus.itemsActionListeners[x][item] = this;
		
		item = 1;
		menus.itemsNames[x][item] = _prefs;
		menus.itemsDiscriptions[x][item] = "Settings for this application";
		menus.itemsKeyEvents[x][item] = -1;
		menus.itemsKeyMasks[x][item] = -1;
		menus.itemsActionListeners[x][item] = this;
		
		item = 2;
		menus.itemsNames[x][item] = _clear;
		menus.itemsDiscriptions[x][item] = "Clear show information";
		menus.itemsKeyEvents[x][item] = -1;
		menus.itemsKeyMasks[x][item] = -1;
		menus.itemsActionListeners[x][item] = this;
		
		item = 3;
		menus.itemsNames[x][item] = "Color Pallet";
		menus.itemsDiscriptions[x][item] = "Setup the color palette of the program";
		menus.itemsKeyEvents[x][item] = -1;
		menus.itemsKeyMasks[x][item] = -1;
		menus.itemsActionListeners[x][item] = this;
		
		item = 4;
		menus.itemsNames[x][item] = _patch;
		menus.itemsDiscriptions[x][item] = "Setup the Show Patch";
		menus.itemsKeyEvents[x][item] = -1;
		menus.itemsKeyMasks[x][item] = -1;
		menus.itemsActionListeners[x][item] = this;
	}
	
	public void actionPerformed(ActionEvent event) 
    {
		JMenuItem source = (JMenuItem)(event.getSource());
		
		if(source.getText().compareTo(_patch) == 0) {_builder.buildPatch();} 
		else if(source.getText().compareTo(_shows) == 0) {_builder.buildShowSettings();}
		else if(source.getText().compareTo(_prefs) == 0) {_builder.buildSettingsPrefs();}
		else if(source.getText().compareTo(_clear) == 0) {_builder.buildSettingsClear();}
    }

}
