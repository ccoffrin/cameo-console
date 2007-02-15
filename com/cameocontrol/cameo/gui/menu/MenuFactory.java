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

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import com.cameocontrol.cameo.gui.WindowBuilder;

import java.io.File;

/*
 * Generates a JMenuBar given a Jpanel and a JGraphUtil
 * 
 */
public class MenuFactory //implements ActionListener
{
	private final int numberMunes = 3; 
//	Where the GUI is created:
	private JComponent		panel;

	private JMenuBar		menuBar;
	private JFileChooser	fileChooser;
	private WindowBuilder _builder;
	private MenuData menus;

	public MenuFactory(JComponent p, WindowBuilder wb)
	{
		fileChooser = new JFileChooser();
		panel = p;
		_builder = wb;
		menus = new MenuData(numberMunes);
		
		int menu = 0;
		int item = 0;
		
		
		//menu = 0; 
		new MenuFile(_builder).getMenu(0, menus);
		new MenuSettings(_builder).getMenu(1, menus);
		new MenuHelp(_builder).getMenu(2, menus);

	}
	
	public JMenuBar makeMenuBar()
	{
		menuBar = new JMenuBar();
		JMenu menu; 
		
		for(int x = 0; x < numberMunes; x++)
		{
			if(menus.keyEvents[x] == -1)
				menu = addMenu(menus.names[x], menus.discriptions[x]);
			else
				menu = addMenu(menus.names[x], menus.discriptions[x], menus.keyEvents[x]);
			
			menuBar.add(menu);
			
			for(int y = 0; y < menus.itemsNames[x].length; y++)
			{
				if(menus.itemsKeyEvents[x][y] == -1)
					addMenuItem(menu, menus.itemsNames[x][y], menus.itemsDiscriptions[x][y], menus.itemsActionListeners[x][y]);
				else
					addMenuItem(menu, menus.itemsNames[x][y], menus.itemsDiscriptions[x][y], menus.itemsKeyEvents[x][y],  menus.itemsKeyMasks[x][y],  menus.itemsActionListeners[x][y]);
			}
		}
		
		return menuBar;
	}
	
	private JMenu addMenu(String name, String description, int keyEvent)
	{
		JMenu menu;
		menu = new JMenu(name);
		menu.setMnemonic(keyEvent);
		menu.getAccessibleContext().setAccessibleDescription(description);
		
		return menu;
	}
	
	private JMenu addMenu(String name, String description)
	{
		JMenu menu;
		menu = new JMenu(name);
		menu.getAccessibleContext().setAccessibleDescription(description);
		
		return menu;
	}
	
	private JMenuItem addMenuItem(JMenu menu, String name, String description, int keyEvent, int keyMask, ActionListener a)
	{
		JMenuItem item;
		item = new JMenuItem(name,keyEvent);
		item.setAccelerator(KeyStroke.getKeyStroke(keyEvent, keyMask));
		item.getAccessibleContext().setAccessibleDescription(description);
		item.addActionListener(a);
	
		menu.add(item);
	
		return item;
	}
	
	private JMenuItem addMenuItem(JMenu menu, String name, String description, ActionListener a)
	{
		JMenuItem item;
		item = new JMenuItem(name);
		item.getAccessibleContext().setAccessibleDescription(description);
		item.addActionListener(a);
	
		menu.add(item);
	
		return item;
	}
	
	/* 
	 public void actionPerformed(ActionEvent e) 
	 {

	 }
	 */
}
