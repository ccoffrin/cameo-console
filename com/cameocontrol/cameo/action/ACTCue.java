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

package com.cameocontrol.cameo.action;

public class ACTCue extends ACTAction{
	int _number;
	int _upTime;
	int _downTime;
	int _upTimeDelay;
	int _downTimeDelay;
	
	int _nextCue;
	int _followTime;
	
	
	public ACTCue() {
		_number = -1;
		_upTime = -1;
		_downTime = -1;
		_upTimeDelay = -1;
		_downTimeDelay = -1;
		
		_nextCue = -1;
		_followTime = -1;
	}
	
	public ACTCue(int cueNum) {
		_number = cueNum;
		_upTime = -1;
		_downTime = -1;
		_upTimeDelay = -1;
		_downTimeDelay = -1;
		
		_nextCue = -1;
		_followTime = -1;
	}
	
	/*
	public ACTCue(int cueNum, int ut, int dt, int udt, int ddt) {
		_number = cueNum;
		_upTime = ut;
		_downTime = dt;
		_upTimeDelay = udt;
		_downTimeDelay = ddt;
	}*/
	
	public void setTime(int x) {_upTime = x; _downTime = x;}
	public void setUpTime(int x) {_upTime = x;}
	public void setDownTime(int x) {_downTime = x;}
	
	public void setDelayTime(int x) {_upTimeDelay = x; _downTimeDelay = x;}
	public void setDelayUpTime(int x) {_upTimeDelay = x;}
	public void setDelayDownTime(int x) {_downTimeDelay = x;}
	
	public void setNextCue(int x) {_nextCue = x;}
	
	public void setFollowTime(int x) {_followTime = x;}
	
	@Override
	public void visit(IVisitor v) {
		v.applyACTCue(this);
	}
	
	public String toString(){
		String temp = "";
		temp += (_number > -1?"cue "+_number:"current cue")+" ";
		temp += "T:"+_upTime+"/"+_downTime+" ";
		temp += "D:"+_upTimeDelay+"/"+_downTimeDelay+" ";
		temp += (_nextCue > -1?"F:"+_nextCue+" FT:"+_followTime+" ":"");
		return temp;
	}
}
