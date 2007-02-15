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

public class FadeData implements Data {
	//private int _totalChannels;
	
	private int _cueNumber;
	private int _nextCue;
	
	private int _upTime;
	private int _downTime;
	private int _delayUpTime;
	private int _delayDownTime;
	private int _followTime;
	//private String _description;
	
	private CueData _cue;
	
	public FadeData(int cueNumber, int upTime, int downTime, int delayUpTime, int delayDownTime, CueData cue){
		//_totalChannels = channels;
		_cueNumber = cueNumber;
		_upTime = upTime;
		_downTime = downTime;
		_delayUpTime = delayUpTime;
		_delayDownTime = delayDownTime;
		
		_nextCue = -1;
		_followTime = -1;
		//_description = "";
		
		_cue = cue;
	}
	
	public void setNextCue(int nc) {_nextCue = nc;}
	public void setFollowTime(int ft) {_followTime = ft;}
	//public void setDescription(String d) {_description = d;}
	
	
	public CueData getCue() {return _cue;}
	//public int getTotalChannels() {return _totalChannels;}
	
	public int getCueNumber() {return _cueNumber;}
	public int getNextCue() {return _nextCue;}
	public int getUpTime() {return _upTime;}
	public int getDownTime() {return _downTime;}
	public int getDelayUpTime() {return _delayUpTime;}
	public int getDelayDownTime() {return _delayDownTime;}
	public int getFollowTime() {return _followTime;}
	
	//public String getDescription() {return _description;}
}
