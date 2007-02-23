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

import com.cameocontrol.cameo.file.adt.GroupData;

public abstract class BasicGroup implements ConsoleGroup {
	protected int _number; 
	protected ConsoleCue _cue;

	protected BasicGroup(int n,  ConsoleCue cue){
		_number = n;
		_cue = cue;
	}

	public int getNumber() {return _number;}
	
	public ConsoleCue getCue() {return _cue;}

	public String toString(){
		return _number+" "+_cue.getDiscription();
	}
	
	public GroupData distill() {
		GroupData fd = new GroupData(
				_number, 
				_cue.distill()
			);
		
		return fd;
	}

	public void extractFrom(GroupData data) {
		_number = data.getGroupNumber();
		CameoCue cue = new CameoCue();
		cue.extractFrom(data.getCue());
		_cue = cue;
	}
}
