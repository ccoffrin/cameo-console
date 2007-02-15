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

package com.cameocontrol.cameo.control;

import java.util.Hashtable;
import java.util.Enumeration;

import com.cameocontrol.cameo.dataStructure.Position;
import com.cameocontrol.cameo.file.adt.MagicChannelData;
import com.cameocontrol.cameo.file.adt.MagicSheetData;

public abstract class BasicMagicSheet implements ConsoleMagicSheet {
	protected Hashtable<Integer,Position> _chans;
	
	public BasicMagicSheet(){
		_chans = new Hashtable<Integer,Position>();
	}

	public void AddChannel(int num, double x, double y){
		_chans.put(new Integer(num), new Position(x,y));
	}
	
	public Position getChannel(int num){
		if(_chans.containsKey(new Integer(num)))
			return _chans.get(new Integer(num));
		else
			return new Position();
	}
	
	public Enumeration<Position> getChannels(){
		return _chans.elements();
	}

	public MagicSheetData distill() {
		MagicSheetData msd = new MagicSheetData();
		
		for(Integer chan : _chans.keySet()){
			Position p = _chans.get(chan);
			msd.put(new Integer(chan), new MagicChannelData(p.getX(), p.getY()));
		}
		
		return msd;
	}

	public void extractFrom(MagicSheetData data) {
		// TODO Auto-generated method stub
		
	}
}
