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

import com.cameocontrol.cameo.control.ConsoleCue;
import com.cameocontrol.cameo.file.adt.PatchData;
import com.cameocontrol.cameo.resource.Resource;

public interface Patch extends Resource<PatchData> {
	public String CHANNEL_TAG = "Channel";
	public String NUMBER_ATTRIB = "number";
	
	

	public void patch(int c, int d);
	public TreeSet<Integer> getDimsFor(int c);
	public int getChannelFor(int d);
	public boolean isDimPatched(int d);
	public boolean isChanPatched(int c);
	
	public void unpatchChan(int c);
	public void unpatchDim(int d);
	public void setPatch1to1();
	public void unpatchAll();
	
	public int getTotalChannels();
	public int getTotalDimmers();
	

	public TreeSet<Integer> getUnpachedDims();
	
	public short[] translateLevelsToDim(ConsoleCue c);
	
	public ConsoleCue translateLevelsToChan(short[] d);
	
	public void setTotalChannels(int c);
	
	public void setTotalDimmers(int d);
	

}
