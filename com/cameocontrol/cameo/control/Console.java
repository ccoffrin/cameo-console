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

import com.cameocontrol.cameo.control.ConsoleSettings.RecordMode;

public interface Console {
	
	//Cue Building
	public void select(ChannelSet c); 
	public void at(short l);
	public void at(ChannelSet c, short l);
	public void plus(short l);
	public void plus(ChannelSet c, short l);
	public void minus(short l);
	public void minus(ChannelSet c, short l);
	public void at(int cueNum);
	public void at(ChannelSet c, int cueNum);
	public void out(); 
	public void out(ChannelSet c); 
	
	//Cue List Building
	public void recordCue(int cueNum, ConsoleTimeing t, int NextNum);
	public void recordCue(int cueNum, int NextNum);
	public void recordCue(int cueNum);
	public void recordCue();
	public void deleteCue(int cueNum);
	public void deleteAllCues();
	public void copyCue(int from, int to);
	public void time(int ut, int dt);
	public void time(int cueNum, int ut, int dt);
	public void delay(int ut, int dt);
	public void delay(int cueNum, int ut, int dt);
	public void follow(int t);
	public void follow(int cueNum, int t);
	public void link(int nextCue);
	public void link(int cueNum, int nextCue);
	public void description(String d);
	public void description(int cueNum, String d);
	
	//State Control
	public void go() ;
	public void back(); 
	public void loadCue(int cueNum);
	public void gotoCue(int cueNum);
	public void gotoCue(int cueNum, ConsoleTimeing t);
	
	
	//Setting Manipulation
	public void recordMode(RecordMode r);
	public void totalChannels(int c);
	public void totalDimmers(int d);
	
	
	public void defaultUpTime(int t);
	public void defaultDownTime(int t);
	public void defaultGotoCueTime(int t);
	
	public void channelsPerLine(int t);
	public void channelsHGroup(int t);
	public void channelsVGroup(int t);
	
	public void showTitle(String s);
	public void showComment(String s);
	public void middleMouseAction(String s);
	
	//	Magic Sheet Setup
	public void channelLocation(int c, double x, double y);
	
	
}
