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

import com.cameocontrol.cameo.file.adt.FadeData;
import com.cameocontrol.cameo.resource.Resource;

public interface ConsoleFade extends Resource<FadeData>, Comparable<ConsoleFade> {
	public String NUMBER_ATTRIB = "number";
	public String UP_TIME_ATTRIB = "upTime";
	public String DOWN_TIME_ATTRIB = "downTime";
	public String UP_TIME_DELAY_ATTRIB = "upTimeDelay";
	public String DOWn_TIME_DELAY_ATTRIB = "downTimeDelay";
	public String FOLLOW_TIME_ATTRIB = "followTime";
	public String NEXT_CUE_ATTRIB = "nextCue";
	
	public int DEFAULT_NEXT = -1;
	
	public int getNumber();
	
	//Possibly these actual links can be removed
	public void setNextCue(ConsoleFade c);
	public ConsoleFade getNextCue ();
	
	public void setNextCueNumber(int num);
	public int getNextCueNumber();
	
	public ConsoleCue getCue();
	
	public ConsoleTimeing getTimeing();
	
	public boolean equals(ConsoleFade fade);
}
