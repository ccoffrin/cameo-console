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

package com.cameocontrol.cameo.dataStructure.jgraph;


import org.jgraph.event.GraphSelectionEvent;
import org.jgraph.event.GraphSelectionListener;

import com.cameocontrol.cameo.action.ActionInterpreter;

public class ChannelSelectionListener implements GraphSelectionListener{
	private ActionInterpreter _actInt;
	
	public ChannelSelectionListener(ActionInterpreter ai) {
		_actInt = ai;
	}

	public void valueChanged(GraphSelectionEvent e) {
		String s = "";
		Object[] cells;
		cells = (Object[])e.getCells();
		for(int x = 0; x < cells.length; x++)
			if(x == 0)
				s = Integer.toString(((GraphCell)cells[x]).getChannelNumber()+1);
			else
				s += "+"+(((GraphCell)cells[x]).getChannelNumber()+1);
		_actInt.interprete(s);
	}

}
