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

package com.cameocontrol.cameo.output;

import com.cameocontrol.cameo.control.CameoChannel;
import com.cameocontrol.cameo.control.CameoCue;
import com.cameocontrol.cameo.control.ChannelSet;
import com.cameocontrol.cameo.control.ConsoleChannel;
import com.cameocontrol.cameo.control.ConsoleCue;
import com.cameocontrol.cameo.output.LiveChannel.ChannelState;
import com.cameocontrol.cameo.util.ShortUntils;

public class LiveCue{
	
	protected ChannelSet _selected;
	protected int _totalChannels;
	protected LiveChannel[] _channels;
	
	
	public LiveCue(int c) {
		_totalChannels = c;
		_channels = new LiveChannel[c];
		
		for(int x = 0; x < _totalChannels; x++)
			_channels[x] = new LiveChannel();
		
		_selected = new ChannelSet();
		_selected.add(0);
	}
	
	public ConsoleCue getCue() {
		CameoCue cue = new CameoCue();
		
		for(int x=0; x < _totalChannels; x++)
			cue.setLevel(x, _channels[x].getLevel());
		
		return cue;
	}
	
	public ChannelSet getCurrentSelection() {return _selected;}
	public void select(ChannelSet c){_selected = c;}
	
	public boolean isMarked(int x) {return _channels[x].getState() != ChannelState.UNMARKED;}
	
		
	/**
	 * updates levels makeing sure to track thier level state
	 * @param c
	 */
	public void updateCue(ConsoleCue c) {
		for(int x = 0; x<_totalChannels; x++)
			_channels[x].setLevel(c.getLevel(x));
	}
	
	public void clearMarks(){
		for(int x = 0; x<_totalChannels; x++)
			_channels[x].setState(ChannelState.UNMARKED);
	}
	
	public void setOut(int i){
		if (i >= 0 && i < _totalChannels)
			_channels[i].updateLevel(ConsoleChannel.OUT);
	}
	
	public short plus(int i, short l, boolean range){
		if (i >= 0 && i < _totalChannels){
			if(range) {
				if(_channels[i].getLevel() > 0){
					_channels[i].updateLevel(ShortUntils.sumShort(_channels[i].getLevel(),l));
				}
			}
			else {
				_channels[i].updateLevel(ShortUntils.sumShort(_channels[i].getLevel(),l));
			}
		}
		
		short lev = _channels[i].getLevel();
		if(lev < 0)
			return 0;
		return lev;
	}
	
	public short minus(int i, short l){
		if (i >= 0 && i < _totalChannels){
				_channels[i].updateLevel(ShortUntils.subShort(_channels[i].getLevel(),l));
		}
	
		short lev = _channels[i].getLevel();
		if(lev < 0)
			return 0;
		return lev;
	}
	
	public short getLevel(int x) {
		assert(0 <= x && x < _channels.length);
		return _channels[x].getLevel();
	}
	
	public void setLevel(int i, short l) {
		if (i >= 0 && i < _totalChannels) {
			_channels[i].updateLevel(l);
		}
	}
	
	public void setLevel(int i, short l[]) {
		if (i >= 0 && i < _totalChannels) {
				_channels[i].updateLevel(l[i]);
			}
	}
	
	public void setTotalChannels(int x) {
		LiveChannel[] chans = new LiveChannel[x];
		for(int i=0; i<x; i++)
			if(i<_totalChannels)
				chans[i] = _channels[i];
			else
				chans[i] = new LiveChannel();
		
		_channels = chans;
		_totalChannels = x;
	}
}
