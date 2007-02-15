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

import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;

import com.cameocontrol.cameo.control.CameoCue;
import com.cameocontrol.cameo.control.ConsoleCue;
import com.cameocontrol.cameo.output.LiveCue;


public class GraphCell extends DefaultGraphCell {

	protected int _channelNumber;
	
	protected GraphCell(int cn, LiveCue c)
	{
		_channelNumber = cn;

		this.setUserObject(new CueChannelWraper(cn, c));

		GraphConstants.setOpaque(this.getAttributes(), true);
		GraphConstants.setEditable(this.getAttributes(),false);
		GraphConstants.setSizeable(this.getAttributes(), false);
		GraphConstants.setMoveable(this.getAttributes(), false);
	}
	
	public int getChannelNumber() {return _channelNumber;}
	
	//public Cue getCue() {return _cue;}
	//public int getChannelNumber() {return _channelNumber;}
	
	protected class CueChannelWraper
	{
		private LiveCue _cue;
		private int _channelNumber;
		
		CueChannelWraper(int cn, LiveCue c)
		{
			super();
			_cue = c;
			_channelNumber = cn;
			//this.add(new JLabel(Integer.toString(_channelNumber+1) +"\n"+ _cue.getLevel(_channelNumber)));
		}
		
		public String toString()
		{
			//this.add
			if( _cue.getLevel(_channelNumber) >= 0)
				return Integer.toString(_channelNumber+1) + "\n" + _cue.getLevel(_channelNumber);
			
			return Integer.toString(_channelNumber+1) + "\n";
		}
		
	}
}
