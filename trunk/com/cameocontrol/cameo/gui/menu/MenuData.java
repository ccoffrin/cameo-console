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

import java.awt.event.ActionListener;

public class MenuData {
	public String[] names;
	public String[] discriptions;
	public int[] keyEvents;

	public String[][] itemsNames;
	public String[][] itemsDiscriptions;
	public int[][] itemsKeyEvents;
	public int[][] itemsKeyMasks;
	public ActionListener[][] itemsActionListeners;

	MenuData(int menus)
	{
		 names = new String[menus];
		 discriptions = new String[menus];
		 keyEvents = new int[menus];

		 itemsNames = new String[menus][];
		 itemsDiscriptions = new String[menus][];
		 itemsKeyEvents = new int[menus][];
		 itemsKeyMasks = new int[menus][];
		 itemsActionListeners = new ActionListener[menus][];
	}
}
