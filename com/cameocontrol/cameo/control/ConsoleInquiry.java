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

import java.util.Collection;
import java.util.Iterator;

import com.cameocontrol.cameo.output.LiveCue;

public interface ConsoleInquiry {
	public boolean hasCue(int cueNum);
	public boolean hasChannel(int chanNum);
	public int getTotalChannels();
	public int getCurrentCueNumber();
	public LiveCue getLiveCue();
	public ConsoleSettings getSettings();
	public ConsolePrefrences getPrefrences();
	public boolean isSimulation();
	
	//Patch GUI
	public boolean isPatchedChan(int c);
	public Collection<Integer> getDimmers(int c);
	public Collection<Integer> getUnpachedDims();
	
	//Que List GUI
	public int getTotalCues();
	public boolean isCurrentCue(int x);
	public ConsoleFade getCue(int x);
	public Iterator<ConsoleFade> getCues();
	
	//MagicSheet GUI
	public ConsoleMagicSheet getMagicSheet();
}
