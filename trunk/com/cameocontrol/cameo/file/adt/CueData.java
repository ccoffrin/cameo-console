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

package com.cameocontrol.cameo.file.adt;

import java.util.Hashtable;

public class CueData extends Hashtable<Integer, Short> implements Data {
	private int _totalChannels;
	private String _description;

	public CueData(){
		//_totalChannels = channels;
		_description = "";
	}
	
	public void addLevel(int channel, short level){
		put(new Integer(channel), new Short(level));
	}
	
	public void setTotalChannels(int channels) {_totalChannels = channels;}
	public int getTotalChannels() {return _totalChannels;}
	
	public String getDescription() {return _description;}
	public void setDescription(String s) {_description = s;}
}
