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

import com.cameocontrol.cameo.dataStructure.Copyable;
import com.cameocontrol.cameo.file.adt.CueData;
import com.cameocontrol.cameo.resource.Resource;

//TODO - Suclass for groups and sections

public interface ConsoleCue extends Resource<CueData>, Copyable<ConsoleCue>, Iterable<Integer> {
	public String DESCIPTION_TAG = "discription";
	public String CHANNEL_TAG = "Channel";
	public String NUMBER_ATTRIB = "number";
	
	public String getDiscription();
	public void setDiscription(String d) ;
	
	public int getHighestChannel();
	public Hashtable<Integer,ConsoleChannel> getChannels();
	public ConsoleChannel getChannel(int x);
	
	public short getLevel(int x);
	public short[] getLevels(int start, int end);
	
	//public void setLevels(short[] l);
	public void setLevel(int i, short l);
	public void setLevel(ChannelSet channels, short l);
	//ublic void setLevel(int i, short l[]);
	public void setOut(int i);
	public void setOut(ChannelSet channels);
	
	public void plus(int i, short l);
	public void plus(ChannelSet channels, short l);
	
	public void minus(int i, short l);
	public void minus(ChannelSet channels, short l);

	public ConsoleCue duplicate();
}
