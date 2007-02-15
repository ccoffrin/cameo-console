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

import com.cameocontrol.cameo.file.adt.CueListData;
import com.cameocontrol.cameo.resource.Resource;

/// List of cue tranistions with a pointer to the current cue
public interface ConsoleCueList extends Resource<CueListData>, Iterable<ConsoleFade> {
	
	//public void setTotalChannels(int c);
	
	public void clearCues();
	
	public void recordTracking(ConsoleFade newCue);
	public void updateTracking(ConsoleFade newCue);
	
	public void addTracking(ConsoleFade newCue, boolean merge);
	
	public void addCueOnly(ConsoleFade c);
	
	//TODO: impliment this
	public void removeTracking(ConsoleFade c) ;
	
	public void removeCueOnly(ConsoleFade c);
	

	
	public boolean isCurrentCue(ConsoleFade c);
	
	public ConsoleFade getCurrentCue();
	public void setCurrentCue(ConsoleFade c);
	
	
	public ConsoleFade getCueNumbered(int x);
	
	public int size();
	
	//public ConsoleFade getCueIndexed(int x);
	
	public int getNextCueNumber();
	
	public ConsoleFade getNextCue();
	
	public ConsoleFade getPrevCue();
	
	//public int getNumberChannels();
	//public int dataFields()
}
