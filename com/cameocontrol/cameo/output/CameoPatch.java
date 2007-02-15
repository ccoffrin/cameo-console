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

import java.util.TreeSet;

public class CameoPatch extends BasicPatch{
	public String getIDValue() {return "cameo";}
	
	/**
	 * Default all channels all dimmers patched 1 to 1
	 *
	 */
	public CameoPatch(){
		for(int x=0; x<_totalChannels; x++){
			TreeSet<Integer> dims = new TreeSet<Integer>();
			dims.add(new Integer(x));
			_patch.add(dims);
		}
	}
}
