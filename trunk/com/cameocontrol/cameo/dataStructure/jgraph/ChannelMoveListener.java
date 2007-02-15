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


import org.jgraph.event.GraphModelEvent;
import org.jgraph.event.GraphModelListener;
import org.jgraph.graph.GraphConstants;

import com.cameocontrol.cameo.action.ACTChanLocation;
import com.cameocontrol.cameo.action.ActionInterpreter;

public class ChannelMoveListener implements GraphModelListener {
	private ActionInterpreter _actInt;
	
	public ChannelMoveListener(ActionInterpreter ai) {
		_actInt = ai;
	}

	public void graphChanged(GraphModelEvent e) {
		//e.getChange().getAttributes().containsKey(GraphConstants.);
		//double x = GraphConstants.getBounds(e.getChange().getAttributes()).getCenterX();
		//double y = GraphConstants.getBounds(e.getChange().getAttributes()).getCenterY();
		//Object o[] = e.getChange().getChanged();
		//System.err.println(e.getChange().getChanged());
		for(Object o : e.getChange().getChanged())
			if(o instanceof MagicGraphCell){
				MagicGraphCell cell = (MagicGraphCell)o;
				double x = GraphConstants.getBounds(cell.getAttributes()).getCenterX();
				double y = GraphConstants.getBounds(cell.getAttributes()).getCenterY();
				
				_actInt.interprete(new ACTChanLocation(cell.getChannelNumber(), x, y));
				
				//System.err.println(cell);
				//e.getChange().getAttributes();
				//e.getChange().getAttributes().get(oo);
			
				//System.err.println(Double.toString(GraphConstants.getBounds(cell.getAttributes()).getCenterX())+"\n");
				
				
				//System.err.println(Double.toString(GraphConstants.getBounds(oo.getAttributes()).getCenterX())+"\n");
				//System.err.println(Double.toString(GraphConstants.getBounds(oo.getAttributes()).getCenterY())+"\n");
			
			}
				
		
		//System.err.print(x+" "+y);
		//System.err.print(e.getChange().getChanged());
		/*
		fileOutput.write(((GData)vertex.element()).getId() + "\n");
        	fileOutput.write(Double.toString(GraphConstants.getBounds(vertex.getCellRefrence().getAttributes()).getCenterX())+"\n");
        	fileOutput.write(Double.toString(GraphConstants.getBounds(vertex.getCellRefrence().getAttributes()).getCenterY())+"\n");
		*/
	}
}
